package com.samvo.statistics.generator;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.samvo.ops.client.model.feed.Matches;
import com.samvo.statistics.client.OpsClient;
import com.samvo.statistics.model.match.FeedTypes;
import com.samvo.statistics.model.match.Match;
import com.samvo.statistics.model.common.DomainFactory;
import com.samvo.statistics.service.StatisticsService;

/**
 * @author Phil Merrilees
 * 11 Jun 2015
 * Description - Statistics generator Base class.
 */
abstract class StatisticsGenerator {
	
	/**
	 * OPS Client.
	 */
	@Autowired
	protected OpsClient opsClient;

	/**
	 * Statistics business service component.
	 */
	@Autowired
	protected transient StatisticsService statisticsService;
	
	/**
	 * Feed type ID.
	 */
	protected Integer feedTypeId = FeedTypes.IN_RUNNING.getTypeId();	
	
	/**
	 * Bookies.
	 */
	protected Map<String, Integer> bookies;
	
	/**
	 * Run the generator.
	 */
	protected abstract void runProcess();

	/**
	 * Process web service response data.
	 * 
	 * @param input
	 * @param feedTypeId
	 * @param matches
	 */
	protected void processTMResponse(Matches input, Integer feedTypeId, Map<String, Match> matches) {
		Iterator<com.samvo.ops.client.model.feed.Match> matchIterator = input.getMatches().iterator();
		Iterator<com.samvo.ops.client.model.feed.Bookie> bookieIterator = null;
		Iterator<com.samvo.ops.client.model.feed.Odd> oddIterator = null;
		
		com.samvo.ops.client.model.feed.Match match = null;
		com.samvo.ops.client.model.feed.Bookie bookie = null;
		com.samvo.ops.client.model.feed.Odd odd = null;
		
		Double awayPrice = null;
		Double homePrice = null;
		Double drawPrice = null;
		Double ouHfPrice = null;
		Double handicapValue = null;
		String key = "";
		
		while (matchIterator.hasNext()) {
			match = matchIterator.next();			
			bookieIterator = match.getBookies().iterator();
			while (bookieIterator.hasNext()) {
				bookie = bookieIterator.next();
				key = String.valueOf(match.getMatchId()) + "|" + bookie.getName().trim();
				awayPrice = null;
				homePrice = null;
				drawPrice = null;
				ouHfPrice = null;
				handicapValue = null;
				oddIterator = bookie.getOdds().iterator();
				while(oddIterator.hasNext()) {
					odd = oddIterator.next();
					if (StatisticsFilter.isFullTimeWinDrawDin(odd.getMarket(), odd.getMarketType())) {
						awayPrice = Double.valueOf(odd.getAwayPrice());
						homePrice = Double.valueOf(odd.getHomePrice());
						drawPrice = Double.valueOf(odd.getDrawPrice());						
					}
					
					if (StatisticsFilter.isHalfTimeMarketOverUnder(odd.getMarket(), odd.getMarketType(), odd.getHandicapValue())) {
						if (ouHfPrice == null || Double.valueOf(odd.getAwayAhMarketTypeOrUnderInOuMarketType()) > ouHfPrice) {
							ouHfPrice = Double.valueOf(odd.getAwayAhMarketTypeOrUnderInOuMarketType());
							handicapValue = Double.valueOf(odd.getHandicapValue());							
						}
					}
				}
				if (matches.get(key) == null) {
					matches.put(key, DomainFactory.toMatch(feedTypeId, 
														   match, 
														   bookies.get(bookie.getName()),
														   awayPrice, 
														   homePrice, 
														   drawPrice,
														   ouHfPrice,
														   handicapValue));					
				}
			}
		}
	}
	
	/**
	 * Process web service response data.
	 * 
	 * @param input
	 * @param feedTypeId
	 * @param matches
	 */
	protected void processIRResponse(Matches input, Integer feedTypeId, Map<String, Match> matches) {
		Iterator<com.samvo.ops.client.model.feed.Match> matchIterator = input.getMatches().iterator();
		Iterator<com.samvo.ops.client.model.feed.Bookie> bookieIterator = null;
		Iterator<com.samvo.ops.client.model.feed.Odd> oddIterator = null;
		
		com.samvo.ops.client.model.feed.Match match = null;
		com.samvo.ops.client.model.feed.Bookie bookie = null;
		com.samvo.ops.client.model.feed.Odd odd = null;
		
		Double awayPrice = null;
		Double homePrice = null;
		Double drawPrice = null;
		Double ouHfPrice = null;
		String key = "";
		
		while (matchIterator.hasNext()) {
			match = matchIterator.next();			
			bookieIterator = match.getBookies().iterator();
			while (bookieIterator.hasNext()) {
				bookie = bookieIterator.next();
				key = String.valueOf(match.getMatchId()) + "|" + bookie.getName().trim();
				awayPrice = null;
				homePrice = null;
				drawPrice = null;
				ouHfPrice = null;
				oddIterator = bookie.getOdds().iterator();
				while(oddIterator.hasNext()) {
					odd = oddIterator.next();
					if (StatisticsFilter.isFullTimeWinDrawDin(odd.getMarket(), odd.getMarketType())) {
						awayPrice = Double.valueOf(odd.getAwayPrice());
						homePrice = Double.valueOf(odd.getHomePrice());
						drawPrice = Double.valueOf(odd.getDrawPrice());						
					}
					
					if (StatisticsFilter.isHalfTimeMarketOverUnderHalfAGoal(odd.getMarket(), odd.getMarketType(), odd.getHandicapValue())) {
						ouHfPrice = Double.valueOf(odd.getAwayAhMarketTypeOrUnderInOuMarketType());
					}
				}

				if (matches.get(key) == null) {
					matches.put(key, DomainFactory.toMatch(feedTypeId, 
														   match, 
														   bookies.get(bookie.getName()), 
														   awayPrice, 
														   homePrice, 
														   drawPrice, 
														   ouHfPrice, 
														   Double.valueOf(odd.getHandicapValue())));
				}					
			}
		}
	}
	
	/**
	 * 
	 * @return sessionToken
	 */
	protected String authenticate() {
		return opsClient.authenticate();
	}
	
	/**
	 * format example 1h 40 (1st half, 40th minute), 2h 9 (2nd half, 9th minute).
	 * 
	 * @param value
	 * @return
	 */
	protected static Integer timeGameLiveMinute(String value) {	
		Integer result;
		if (value.equals("ht")) {
			result = new Integer(56);
		} else {
			if (StringUtils.isNotBlank(value)) {
				String[] values = value.split(" ");
				if (value.substring(0, 2).equals("1h")) {
					result = Integer.parseInt(values[1]);
				} else {
					// Must be 2nd half...
					result = new Integer(57);
				}				
			} else {
				result = new Integer(0);
			}
		}
		return result;
	}
}
