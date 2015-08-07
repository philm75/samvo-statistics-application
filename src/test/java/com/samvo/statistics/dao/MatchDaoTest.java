package com.samvo.statistics.dao;

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
				
		List<Match> matchesByType = matchDao.readMatchByFeedType(FeedTypes.IN_RUNNING.getTypeId());
		assertFalse(matchesByType.isEmpty());		
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
		assertFalse(summaries.isEmpty());
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
		Integer bookieId = new Integer(1);
		Integer feedTypeId = FeedTypes.IN_RUNNING.getTypeId();
		matchDao.updateKo(matchId, homePrice, drawPrice, awayPrice, htUndefHgPrice, feedTypeId, bookieId);
	}
	
	@Test
	public void testReadByIndicator() {
		Integer feedTypeId = FeedTypes.IN_RUNNING.getTypeId();
		Map<String, Match> matches = matchDao.readMatchesByIndicator(feedTypeId);
		assert(matches.size() > 0);
	}
}
