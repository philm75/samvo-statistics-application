package com.samvo.statistics.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.samvo.statistics.model.match.Match;
import com.samvo.statistics.model.match.MatchSummary;
import com.samvo.statistics.model.match.MatchTime;

/**
 * @author Phil
 * 18 Jun 2015
 * Description - Statistics Service mock class.
 */
public class StatisticsServiceMock implements StatisticsService {

	@Override
	public List<MatchSummary> getAllSummary(Integer feedTypeId) {
		List<MatchSummary> matchSummary = new ArrayList<MatchSummary>();
		
		int match=1;
		
		matchSummary.add(createMatchSummary("AwayTeam" + String.valueOf(match), 
											new DateTime(), 
											"homeTeam" + String.valueOf(match), 
											new Double(match), new Double(match), 
											new Double(match), new Double(match),
											"3:00PM", null));
		match++;
		
		matchSummary.add(createMatchSummary("AwayTeam" + String.valueOf(match), 
				new DateTime(), 
				"homeTeam" + String.valueOf(match), 
				new Double(match),new Double(match), 
				new Double(match), new Double(match),
				"3:00PM",
				30));
		
		match++;
		
		matchSummary.add(createMatchSummary("AwayTeam" + String.valueOf(match), 
				new DateTime(), 
				"homeTeam" + String.valueOf(match), 
				new Double(match), new Double(match), 
				new Double(match), new Double(match),
				"3:00PM",
				60));
		
		match++;
		
		matchSummary.add(createMatchSummary("AwayTeam" + String.valueOf(match), 
				new DateTime(), 
				"homeTeam" + String.valueOf(match), 
				new Double(match), new Double(match), 
				new Double(match), new Double(match),
				"3:00PM", null));		
		return matchSummary;
	}
	
	private MatchSummary createMatchSummary(String awayTeam, 
											DateTime gameDate, 
											String homeTeam, 
											Double awayPrice, 
											Double drawPrice, 
											Double homePrice, 
											Double ouHalfGoalPrice,
											String kickOffTime,
											Integer timeFirstGoal) {
		MatchSummary matchSummary = new MatchSummary();
		matchSummary.setAwayTeam(awayTeam);
		matchSummary.setGameDate(gameDate);
		matchSummary.setHomeTeam(homeTeam);
		matchSummary.setKickOffAwayPrice(awayPrice);
		matchSummary.setKickOffDrawPrice(drawPrice);
		matchSummary.setKickOffHomePrice(homePrice);
		matchSummary.setKickOffOuHalfAGoal(ouHalfGoalPrice);
		matchSummary.setKickOffTime(kickOffTime);
		matchSummary.setMatchMinutes(getMatchMinutes());
		matchSummary.setTimeFirstGoal(timeFirstGoal);
		return matchSummary;
	}
	
	private Map<Integer, MatchTime> getMatchMinutes() {
		Map<Integer, MatchTime> matchMinutes = new HashMap<Integer, MatchTime>();
		int minute = 1;
		
		MatchTime matchTime = null;
		while (minute < 46) {
			matchTime = new MatchTime();
			matchTime.setAwayPrice(1.0);
			matchTime.setHomePrice(3.0);
			matchTime.setBookieId(1);
			matchTime.setDrawPrice(2.0);
			matchTime.setMatchId(1);
			matchTime.setMinute(minute);
			matchTime.setHtUnderHgPrice(4.0);
			matchMinutes.put(matchTime.getMinute(), matchTime);
			minute++;
		}
		return matchMinutes;
	}

	@Override
	public void createMatch(Match match) {		
	}

	@Override
	public void createMatches(List<Match> matches) {
	}

	@Override
	public List<Match> getAllByFeedType(Integer feedTypeId) {
		return Collections.emptyList();
	}

	@Override
	public void updateFirstGoalScore(Integer matchId, Integer timeGoalScored, Integer feedTypeId, Integer bookieId) {
	}

	@Override
	public Map<String, Integer> getBookies() {
		return Collections.emptyMap();
	}

	@Override
	public void updateMatchMinute(Integer matchId, Integer bookieId, Integer feedTypeId, Integer minute, Double price) {		
	}

	@Override
	public Map<String, Match> getMatchesByIndicator(Integer feedTypeId) {
		return Collections.emptyMap();
	}

	@Override
	public void interruptInRunningMarket() {		
	}

	@Override
	public void updateKo(Integer matchId, Double homePrice, Double drawPrice,Double awayPrice, Double htUndefHgPrice, Integer feedTypeId, Integer bookieId) {
	}

	@Override
	public void updateInRunningIndicator(Integer matchId, Integer feedTypeId, Integer indicator, Integer bookieId) {
	}
}
