package com.samvo.statistics.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.samvo.statistics.model.match.FeedTypes;
import com.samvo.statistics.model.match.Match;
import com.samvo.statistics.model.match.MatchSummary;

/**
 * @author Phil
 * 23 Jun 2015
 * Description - Unit tests for match data access component.
 */
public class MatchDaoTest extends BaseDaoTest {

	@Autowired
	private MatchDao matchDao;
	
	@Test
	public void testCreateMatch() {
		Match match = new Match();
		
		DateTime date = new DateTime();
		
		match.setAwayScore(0);
		match.setAwayTeamId(1);
		match.setAwayTeamName("Away Team");
		match.setFeedTypeId(FeedTypes.IN_RUNNING.getTypeId());
		match.setHomeScore(0);
		match.setHomeTeamId(2);
		match.setHomeTeamName("Home Team");
		match.setKoAwayPrice(1.0);
		match.setKoDrawPrice(2.0);
		match.setKoHomePrice(3.0);
		match.setKoOuHfPrice(4.0);
		match.setLeagueId(10);
		match.setLeagueName("Premier League");
		match.setMatchId(100);
		match.setMatchTime("2015-06-05 03:52:45");
		match.setRunningIndicator(1);
		match.setTimeFirstGoal(5);
		match.setTimeGameLive("2h 3");
		match.setMatchDate(date);
		match.setBookieId(1);
		
		matchDao.createMatch(match);
		
		List<Match> matches = matchDao.readAll();
		assertFalse(matches.isEmpty());
		assert(matches.size() == 1);
		
		Match result = matches.get(0);
		assertEquals(result.getAwayScore().intValue(), match.getAwayScore().intValue());
		assertEquals(result.getAwayTeamId().intValue(), match.getAwayTeamId().intValue());
		assertEquals(result.getAwayTeamName(), match.getAwayTeamName());
		assertEquals(result.getFeedTypeId(), match.getFeedTypeId());
		assertEquals(result.getHomeScore(), match.getHomeScore());
		assertEquals(result.getHomeTeamId(), match.getHomeTeamId());
		assertEquals(result.getHomeTeamName(), match.getHomeTeamName());
		assertEquals(result.getLeagueId(), match.getLeagueId());
		assertEquals(result.getLeagueName(), match.getLeagueName());
		assertEquals(result.getMatchId(), match.getMatchId());
		assertEquals(result.getMatchTime(), match.getMatchTime());
		assertEquals(result.getRunningIndicator(), match.getRunningIndicator());
		assertEquals(result.getTimeFirstGoal(), match.getTimeFirstGoal());
		assertEquals(result.getTimeGameLive(), match.getTimeGameLive());
		assert(result.getKoAwayPrice() == match.getKoAwayPrice());
		assert(result.getKoDrawPrice() == match.getKoDrawPrice());
		assert(result.getKoHomePrice() == match.getKoHomePrice());
		assert(result.getKoOuHfPrice() == match.getKoOuHfPrice());
		assertEquals(result.getTimeGameLive(), match.getTimeGameLive());
		
		List<Match> matchesByType = matchDao.readMatchByFeedType(FeedTypes.IN_RUNNING.getTypeId());
		assertEquals(matchesByType.size(), 1);
		
	}
	
	@Test
	public void testCreateMatches() {
		DateTime date = new DateTime();
		List<Match> matches = new ArrayList<Match>();		
		Match match1 = new Match();
		match1.setAwayScore(0);
		match1.setAwayTeamId(1);
		match1.setAwayTeamName("Away Team");
		match1.setFeedTypeId(FeedTypes.IN_RUNNING.getTypeId());
		match1.setHomeScore(0);
		match1.setHomeTeamId(2);
		match1.setHomeTeamName("Home Team");
		match1.setKoAwayPrice(1.0);
		match1.setKoDrawPrice(2.0);
		match1.setKoHomePrice(3.0);
		match1.setKoOuHfPrice(4.0);
		match1.setLeagueId(10);
		match1.setLeagueName("Premier League");
		match1.setMatchId(100);
		match1.setMatchTime("2015-06-05 03:52:45");
		match1.setRunningIndicator(1);
		match1.setTimeFirstGoal(5);
		match1.setTimeGameLive("2h 3");
		match1.setBookieId(1);
		match1.setMatchDate(date);
		
		Match match2 = new Match();
		match2.setAwayScore(1);
		match2.setAwayTeamId(1);
		match2.setAwayTeamName("A Team");
		match2.setFeedTypeId(FeedTypes.IN_RUNNING.getTypeId());
		match2.setHomeScore(0);
		match2.setHomeTeamId(2);
		match2.setHomeTeamName("H Team");
		match2.setKoAwayPrice(1.0);
		match2.setKoDrawPrice(2.0);
		match2.setKoHomePrice(3.0);
		match2.setKoOuHfPrice(4.0);
		match2.setLeagueId(10);
		match2.setLeagueName("Championship");
		match2.setMatchId(101);
		match2.setMatchTime("2015-06-03 03:52:45");
		match2.setRunningIndicator(1);
		match2.setTimeFirstGoal(15);
		match2.setTimeGameLive("2h 3");
		match2.setBookieId(1);
		match2.setMatchDate(date);
		
		matches.add(match1);
		matches.add(match2);
		
		matchDao.createMatches(matches);
		
		List<Match> results = matchDao.readAll();
		assertFalse(results.isEmpty());
		assert(results.size() == 2);
		
		Match result = matches.get(0);
		assertEquals(result.getAwayScore().intValue(), match1.getAwayScore().intValue());
		assertEquals(result.getAwayTeamId().intValue(), match1.getAwayTeamId().intValue());
		assertEquals(result.getAwayTeamName(), match1.getAwayTeamName());
		assertEquals(result.getFeedTypeId(), match1.getFeedTypeId());
		assertEquals(result.getHomeScore(), match1.getHomeScore());
		assertEquals(result.getHomeTeamId(), match1.getHomeTeamId());
		assertEquals(result.getHomeTeamName(), match1.getHomeTeamName());
		assertEquals(result.getLeagueId(), match1.getLeagueId());
		assertEquals(result.getLeagueName(), match1.getLeagueName());
		assertEquals(result.getMatchId(), match1.getMatchId());
		assertEquals(result.getMatchTime(), match1.getMatchTime());
		assertEquals(result.getRunningIndicator(), match1.getRunningIndicator());
		assertEquals(result.getTimeFirstGoal(), match1.getTimeFirstGoal());
		assertEquals(result.getTimeGameLive(), match1.getTimeGameLive());
		assert(result.getKoAwayPrice() == match1.getKoAwayPrice());
		assert(result.getKoDrawPrice() == match1.getKoDrawPrice());
		assert(result.getKoHomePrice() == match1.getKoHomePrice());
		assert(result.getKoOuHfPrice() == match1.getKoOuHfPrice());
		assertEquals(result.getTimeGameLive(), match1.getTimeGameLive());
		
		result = matches.get(1);
		assertEquals(result.getAwayScore().intValue(), match2.getAwayScore().intValue());
		assertEquals(result.getAwayTeamId().intValue(), match2.getAwayTeamId().intValue());
		assertEquals(result.getAwayTeamName(), match2.getAwayTeamName());
		assertEquals(result.getFeedTypeId(), match2.getFeedTypeId());
		assertEquals(result.getHomeScore(), match2.getHomeScore());
		assertEquals(result.getHomeTeamId(), match2.getHomeTeamId());
		assertEquals(result.getHomeTeamName(), match2.getHomeTeamName());
		assertEquals(result.getLeagueId(), match2.getLeagueId());
		assertEquals(result.getLeagueName(), match2.getLeagueName());
		assertEquals(result.getMatchId(), match2.getMatchId());
		assertEquals(result.getMatchTime(), match2.getMatchTime());
		assertEquals(result.getRunningIndicator(), match2.getRunningIndicator());
		assertEquals(result.getTimeFirstGoal(), match2.getTimeFirstGoal());
		assertEquals(result.getTimeGameLive(), match2.getTimeGameLive());
		assert(result.getKoAwayPrice() == match2.getKoAwayPrice());
		assert(result.getKoDrawPrice() == match2.getKoDrawPrice());
		assert(result.getKoHomePrice() == match2.getKoHomePrice());
		assert(result.getKoOuHfPrice() == match2.getKoOuHfPrice());
		assertEquals(result.getTimeGameLive(), match2.getTimeGameLive());
		
		List<Match> matchesByType = matchDao.readMatchByFeedType(FeedTypes.IN_RUNNING.getTypeId());
		assertEquals(matchesByType.size(), 2);
	}
	
	@Test
	public void testReadAllSummary() {
		DateTime date = new DateTime();
		
		List<Match> matches = new ArrayList<Match>();		
		Match match1 = new Match();
		match1.setAwayScore(0);
		match1.setAwayTeamId(1);
		match1.setAwayTeamName("Away Team");
		match1.setFeedTypeId(FeedTypes.IN_RUNNING.getTypeId());
		match1.setHomeScore(0);
		match1.setHomeTeamId(2);
		match1.setHomeTeamName("Home Team");
		match1.setKoAwayPrice(1.0);
		match1.setKoDrawPrice(2.0);
		match1.setKoHomePrice(3.0);
		match1.setKoOuHfPrice(4.0);
		match1.setLeagueId(10);
		match1.setLeagueName("Premier League");
		match1.setMatchId(100);
		match1.setMatchTime("2015-06-05 03:52:45");
		match1.setRunningIndicator(1);
		match1.setTimeFirstGoal(5);
		match1.setTimeGameLive("2h 3");
		match1.setBookieId(new Integer(4));
		match1.setMatchDate(date);
		
		Match match2 = new Match();
		match2.setAwayScore(1);
		match2.setAwayTeamId(1);
		match2.setAwayTeamName("A Team");
		match2.setFeedTypeId(FeedTypes.IN_RUNNING.getTypeId());
		match2.setHomeScore(0);
		match2.setHomeTeamId(2);
		match2.setHomeTeamName("H Team");
		match2.setKoAwayPrice(1.0);
		match2.setKoDrawPrice(2.0);
		match2.setKoHomePrice(3.0);
		match2.setKoOuHfPrice(4.0);
		match2.setLeagueId(10);
		match2.setLeagueName("Championship");
		match2.setMatchId(101);
		match2.setMatchTime("2015-06-03 03:52:45");
		match2.setRunningIndicator(1);
		match2.setTimeFirstGoal(15);
		match2.setTimeGameLive("2h 3");
		match2.setBookieId(new Integer(3));
		match2.setMatchDate(date);
		
		matches.add(match1);
		matches.add(match2);
		
		matchDao.createMatches(matches);		
		
		List<MatchSummary> summaries = matchDao.readAllSummary(FeedTypes.IN_RUNNING.getTypeId());
		assertEquals(summaries.size(), 2);
	}
	
	@Test
	public void testUpdateFirstGoalScore() {
		Integer matchId = 1;
		Integer timeGoalScored = 5;
		Integer feedTypeId = FeedTypes.IN_RUNNING.getTypeId();		
		Integer bookieId = new Integer(1);
		matchDao.updateFirstGoalScore(matchId, timeGoalScored, feedTypeId, bookieId);
	}
	
	@Test
	public void testUpdateKoPrices() {
		Integer matchId = 1; 
		Double homePrice = 1.0;
		Double drawPrice = 2.0;
		Double awayPrice = 3.0;
		Double htUndefHgPrice = 4.0;
		Integer indicator = 1;
		Integer bookieId = new Integer(1);
		Integer feedTypeId = FeedTypes.IN_RUNNING.getTypeId();
		matchDao.updateKo(matchId, homePrice, drawPrice, awayPrice, htUndefHgPrice, feedTypeId, indicator, bookieId);
	}
	
	@Test
	public void testReadByIndicator() {
		Integer feedTypeId = FeedTypes.IN_RUNNING.getTypeId();
		Map<String, Match> matches = matchDao.readMatchesByIndicator(feedTypeId);
		assert(matches.size() > 0);
	}
}
