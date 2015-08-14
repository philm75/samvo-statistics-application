package com.samvo.statistics.generator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import com.samvo.ops.client.InvalidSessionTokenException;
import com.samvo.ops.client.model.feed.Matches;
import com.samvo.statistics.model.match.Match;

/**
 * @author Phil Merrilees
 * 25 Jun 2015
 * Description - Today market statistics generator.
 */
@Component("tmStatisticsGenerator")
public class TMStatisticsGenerator extends StatisticsGenerator {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = Logger.getLogger(TMStatisticsGenerator.class);
	
	/**
	 * Session Token.
	 */
	private String sessionToken;
	
	/**
	 * Matches to persist.
	 */
	private Map<String, Match> matches = new HashMap<String, Match>();
	
	/**
	 * 
	 */
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
			
		/**
		 * Fetch today market.
		 */
		Matches todayMatches = getTodayMarket();
		if (todayMatches != null) {
			processTMResponse(todayMatches, feedTypeId, matches);			
		}
				
		for (Map.Entry<String, Match> entry : matches.entrySet()) {
			LOGGER.info("Processing Match - " + entry.getValue().toString());
			
			/**
			 * Has match o/u half time market half a goal price.
			 */
			if (TMStatisticsGenerator.hasOuHtPrice(entry.getValue())) {
				if (LOGGER.isInfoEnabled()) {LOGGER.info("Match in market key - " + entry.getKey());}
				statisticsService.createMatch(entry.getValue());
			}
		}
	}
	
	private static boolean hasOuHtPrice(Match match) {
		boolean result = false;
		if (match.getKoOuHfPrice() == null) {
			result = false;
		} else {
			BigDecimal value = new BigDecimal(match.getKoOuHfPrice());
			if (value.compareTo(BigDecimal.ZERO) > 0) {
				result = true;
			} else {
				result = false;
			}
		}
		return result;
	}
	
	private Matches getTodayMarket() {
		Matches matches = null;
		try {		
			return opsClient.getTodayMarketFeedData(sessionToken);			
		} catch (ResourceAccessException e) {
			return matches;
		} catch (InvalidSessionTokenException e) {
			// Invalid Session Token exception, re-authenicate...
			sessionToken = opsClient.authenticate();
			try {
				matches = opsClient.getTodayMarketFeedData(sessionToken);
			} catch (ResourceAccessException error) {
				return matches;
			} catch (InvalidSessionTokenException e1) {
				// Exception SHOULD not be thrown...
			}			
		}
		return matches;
	}
}
