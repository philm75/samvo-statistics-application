package com.samvo.statistics.generator;

import java.util.HashSet;
import java.util.Set;

import com.samvo.statistics.model.market.MarketType;
import com.samvo.statistics.model.market.Market;
/**
 * @author Phil
 * 14 Jun 2015
 * Description - Filters to apply to the match/odds data from OPS feed.
 */
final class StatisticsFilter {

	/**
	 * Handicap.
	 */
	private static final String HANDICAP = "0.5";
	
	/**
	 * Handicap values.
	 */
	private static final Set<String> HANDICAP_VALUES = new HashSet<String>();
	
	static {
		HANDICAP_VALUES.add("0.5");
		HANDICAP_VALUES.add("0.75");
		HANDICAP_VALUES.add("1");
		HANDICAP_VALUES.add("1.25");		
		HANDICAP_VALUES.add("1.5");
	}
	
	/**
	 * Is the Odd in the half time market, over/under market type 0.5 goal handicap.
	 * 
	 * @param market
	 * @param marketType
	 * @param handicapValue
	 * @return true if exists, otherwise returns false.
	 */
	protected static boolean isHalfTimeMarketOverUnderHalfAGoal(String market, String marketType,  String handicapValue) {
		return market.equals(Market.HT.indicator()) && 
			   marketType.equals(MarketType.OU.indicator()) &&
			   HANDICAP.equals(handicapValue);
	}
	
	/**
	 * Is the Odd in the half time market, over/under market type 0.5, 0.75, 1, 1.25 or 1.5 goal handicap.
	 * 
	 * @param market
	 * @param marketType
	 * @param handicapValue
	 * @return true if it matches, otherwise returns false.
	 */
	protected static boolean isHalfTimeMarketOverUnder(String market, String marketType,  String handicapValue) {
		return market.equals(Market.HT.indicator()) && 
			   marketType.equals(MarketType.OU.indicator()) &&
			   HANDICAP_VALUES.contains(handicapValue);
	}
	
	/**
	 * Is the odd in the full time market, Win Draw Win market type.
	 * 
	 * @param market
	 * @param marketType
	 * @return true if it matches, otherwise returns false.
	 */
	protected static boolean isFullTimeWinDrawDin(String market, String marketType) {
		return (market.equals(Market.FT.indicator()) && marketType.equals(MarketType.ST.indicator()));
	}
}