package com.samvo.statistics.generator;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import com.samvo.ops.client.InvalidSessionTokenException;
import com.samvo.ops.client.model.feed.Matches;
import com.samvo.statistics.model.match.FeedTypes;
import com.samvo.statistics.model.match.Match;

/**
 * @author Phil Merrilees
 * 25 Jun 2015
 * Description - In-running statistics generator.
 */
@Component("irStatisticsGenerator")
public class IRStatisticsGenerator extends StatisticsGenerator {
		
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = Logger.getLogger(IRStatisticsGenerator.class);
	
	/**
	 * Interrupt flag.
	 */
	private volatile boolean interrupt;

	/**
	 * Session Token.
	 */
	private String sessionToken;
	
	/**
	 * Matches to persist.
	 */
	private Map<String, Match> matches = new HashMap<String, Match>();
	
	/**
	 * In-running indicators.
	 */
	private static final int IN_RUNNING_INDICATOR = 1;
	private static final int NOT_RUNNING_INDICATOR = 0;
	
	@Override
	public void runProcess() {
		/**
		 * Get bookies names/ids.
		 */
		bookies = statisticsService.getBookies();
		 
		/**
		 * Authenticate user credentials to obtain session Token.
		 */
		sessionToken = authenticate();

		interrupt = false;

		if (!interrupt) {
			/**
			 * Clear out the map and reload from the web service response.
			 */
			if (!matches.isEmpty()) {
				matches.clear();			
			}

			/**
			 * Cache all of todays matches.
			 */
			Map<String, Match> matchCache = statisticsService.getMatchesByIndicator(FeedTypes.IN_RUNNING.getTypeId());			
			
			/**
			 * Fetch In running market data.
			 */
			Matches inRunningMarketMatches = getInRunningMarket();	
			if (inRunningMarketMatches != null) {
				processIRResponse(inRunningMarketMatches, feedTypeId, matches);				
			}
			
			Match match = null;
			
			for (Map.Entry<String, Match> entry : matches.entrySet()) {				
				  if (matchCache.containsKey(entry.getKey())) {
					  match = matchCache.get(entry.getKey());
				  
					  /**
					   * If the match is running, update the indicator. 
					   */
					  if (entry.getValue().getRunningIndicator().intValue() == IN_RUNNING_INDICATOR && match.getRunningIndicator().intValue() == NOT_RUNNING_INDICATOR) {
						  if (LOGGER.isInfoEnabled()) {LOGGER.info("Update Match running indicator - " + entry.getKey());}
					  
						  /**
						   * Set in-running indicator.
						   */
						  statisticsService.updateInRunningIndicator(entry.getValue().getMatchId(), 
								  									 feedTypeId, 
								  									 IN_RUNNING_INDICATOR, 
								  									 entry.getValue().getBookieId());
					  
						  /**
						   * If the match in the DB has no KO prices then update.
						   */
						  if (match.getKoOuHfPrice() == null && entry.getValue().getKoOuHfPrice() != null) {
							  if (LOGGER.isInfoEnabled()) {LOGGER.info("Update Match KO prices for match key - " + entry.getKey());}
							  
							  statisticsService.updateKo(entry.getValue().getMatchId(), 
									  					 entry.getValue().getKoHomePrice(), 
									  					 entry.getValue().getKoDrawPrice(), 
									  					 entry.getValue().getKoAwayPrice(), 
									  					 entry.getValue().getKoOuHfPrice(), 
									  					 feedTypeId, 
									  					 entry.getValue().getBookieId());						  
						  }
					  }
				  
					  Integer timeInGame = timeGameLiveMinute(entry.getValue().getTimeGameLive());
				  
					  /**
					   * If a goal has been scored, record the time.
					   */
					  if (entry.getValue().getAwayScore().intValue() > 0 || entry.getValue().getHomeScore().intValue() > 0) {					  
						  if (match.getTimeFirstGoal().intValue() == -1) {
							  if (LOGGER.isInfoEnabled()) {LOGGER.info("Goal scored in match " + entry.getKey());}
							  statisticsService.updateFirstGoalScore(entry.getValue().getMatchId(), 
									  								 timeInGame, 
									  								 feedTypeId, 
									  								 entry.getValue().getBookieId());
						  }
					  } else {
						  /**
						   * No goal score set, Under 0.5 HT price
						   */					  
						  if (timeInGame < 56 && entry.getValue().getKoOuHfPrice() != null) {
							  if (LOGGER.isInfoEnabled()) {						    
							  	LOGGER.info(String.format("Update minute for match ID=%s,bookieId=%s,feedTypeId=%s,timeInGame=%s,price=%s", 
							  																String.valueOf(entry.getValue().getMatchId()), 
																						    String.valueOf(entry.getValue().getBookieId()),
																						    String.valueOf(entry.getValue().getFeedTypeId()),
																						    String.valueOf(timeInGame),
																						    String.valueOf(entry.getValue().getKoOuHfPrice())));
							  }
						  
							  statisticsService.updateMatchMinute(entry.getValue().getMatchId(), 
									  							  entry.getValue().getBookieId(), 		
									  							  entry.getValue().getFeedTypeId(),
									  							  timeInGame, 	
									  							  entry.getValue().getKoOuHfPrice());
						  
						  }
					  }		  			
				  }
			}
		}
	}
	
	private Matches getInRunningMarket() {
		Matches matches = null;
		try {		
			return opsClient.getInRunningMarketFeedData(sessionToken);
		} catch (ResourceAccessException e) { 
			return matches;
		} catch (InvalidSessionTokenException e) {
			// Invalid Session Token exception, re-authenticate...
			sessionToken = opsClient.authenticate();
			try {
				matches = opsClient.getInRunningMarketFeedData(sessionToken);
			} catch (ResourceAccessException error) { 
				return matches;
			} catch (InvalidSessionTokenException e1) {
				// Exception SHOULD not be thrown...
			}			
		}
		return matches;
	}	

	/**
	 * Stop the process.
	 */
	public synchronized void stopProcess() {
		if (LOGGER.isInfoEnabled()) {LOGGER.info("stopProcess()");}
		if (!interrupt) {
			interrupt = true;				
		}
	}
}
