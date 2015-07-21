package com.samvo.statistics.generator;

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
	 * Is the Odd in the half time market, over/under 0.5 goal handicap.
	 * 
	 * @param market
	 * @param marketType
	 * @param handicapValue
	 * @return true if it matches, otherwise returns false.
	 */
	protected static boolean isHalfTimeMarketOverUnderHalfAGoal(String market, String marketType,  String handicapValue) {
		return (market.equals(Market.HT.indicator()) && marketType.equals(MarketType.OU.indicator()) && handicapValue.equals(HANDICAP));
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