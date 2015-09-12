package com.samvo.statistics.generator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.samvo.statistics.model.market.Market;
import com.samvo.statistics.model.market.MarketType;

/**
 * @author Phil
 * 13 Aug 2015
 * Description -
 */
public class StatisticsFilterTest {

	@Test
	public void testIsHalfTimeMarketOverUnderHalfAGoal() {
		assertTrue(StatisticsFilter.isHalfTimeMarketOverUnderHalfAGoal(Market.HT.indicator(), MarketType.OU.indicator(),  "0.5"));
		assertFalse(StatisticsFilter.isHalfTimeMarketOverUnderHalfAGoal(Market.HT.indicator(), MarketType.OU.indicator(), "0.75"));
	}
	
	@Test
	public void testIsHalfTimeMarketOverUnder() {
		assertTrue(StatisticsFilter.isHalfTimeMarketOverUnder(Market.HT.indicator(), MarketType.OU.indicator(),  "0.5"));
		assertTrue(StatisticsFilter.isHalfTimeMarketOverUnder(Market.HT.indicator(), MarketType.OU.indicator(),  "0.75"));
		assertTrue(StatisticsFilter.isHalfTimeMarketOverUnder(Market.HT.indicator(), MarketType.OU.indicator(),  "1"));
		assertTrue(StatisticsFilter.isHalfTimeMarketOverUnder(Market.HT.indicator(), MarketType.OU.indicator(),  "1.25"));
		assertTrue(StatisticsFilter.isHalfTimeMarketOverUnder(Market.HT.indicator(), MarketType.OU.indicator(),  "1.5"));
		assertFalse(StatisticsFilter.isHalfTimeMarketOverUnder(Market.HT.indicator(), MarketType.OU.indicator(),  "2.0"));		
	}
}
