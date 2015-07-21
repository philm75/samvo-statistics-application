package com.samvo.statistics.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
				
		/**
		 * Populate the database. 
		 */
		if (!matches.isEmpty()) {
			statisticsService.createMatches(new ArrayList<Match>(matches.values()));			
		}
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
