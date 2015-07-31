package com.samvo.statistics.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.samvo.statistics.model.match.FeedTypes;
import com.samvo.statistics.model.match.MatchTime;

/**
 * @author Phil
 * 22 Jun 2015
 * Description -
 */
public class MatchTimeDaoTest extends BaseDaoTest {
	
	@Autowired
	private MatchTimeDao matchTimeDao;
	
	@Test
	public void testCreateMatchTime() {
		Integer matchId = new Integer(1);
		Integer feedTypeId = FeedTypes.TODAY.getTypeId();
		Integer bookieId = new Integer(3);
		
		matchTimeDao.createMatchTime(matchId, feedTypeId, bookieId);
		
		List<MatchTime> matchMinutes = matchTimeDao.readMatchTimeByMatchId(matchId, feedTypeId);
		assertFalse(matchMinutes.isEmpty());
		
		Iterator<MatchTime> iterator = matchMinutes.iterator();
		MatchTime matchTime = null;
		int minuteCounter = 1;
		while (iterator.hasNext()) {
			matchTime = iterator.next();
			assertEquals(matchTime.getBookieId(), bookieId);
			assertEquals(matchTime.getFeedTypeId(), feedTypeId);
			assertEquals(matchTime.getMatchId(), matchId);
			assertEquals(matchTime.getMinute().intValue(), minuteCounter);
			assert(matchTime.getAwayPrice() == 0.0);
			assert(matchTime.getDrawPrice() == 0.0);
			assert(matchTime.getHomePrice() == 0.0);
			assert(matchTime.getHtUnderHgPrice() == 0.0);
			minuteCounter++;			
		}
	}
}
