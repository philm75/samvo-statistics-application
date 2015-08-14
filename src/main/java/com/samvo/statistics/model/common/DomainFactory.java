package com.samvo.statistics.model.common;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.samvo.statistics.model.match.Match;

/**
 * @author Phil Merrilees
 * 14 Jun 2015
 * Description -
 */
public class DomainFactory {
	
	/**
	 * 
	 */
	private DomainFactory() {
		
	}
	
	/** 
	 * Convert web service match data into local domain model.
	 * 
	 * @param feedTypeId
	 * @param input
	 * @param bookieId
	 * @param awayPrice
	 * @param homePrice
	 * @param drawPrice
	 * @param koOuHfPrice
	 * @param ouHandicapValue
	 * @return Match
	 */
	public static Match toMatch(Integer feedTypeId, com.samvo.ops.client.model.feed.Match input, Integer bookieId, Double awayPrice, Double homePrice, Double drawPrice, Double koOuHfPrice, Double ouHandicapValue) {
		Match match = new Match();
		match.setAwayScore(input.getAwayScore());
		match.setAwayTeamId(input.getAwayTeamId());
		match.setAwayTeamName(input.getAwayTeamName());
		match.setFeedTypeId(feedTypeId);
		match.setHomeScore(input.getHomeScore());
		match.setHomeTeamId(input.getHomeTeamId());
		match.setHomeTeamName(input.getHomeTeamName());
		match.setLeagueId(input.getLeagueId());
		match.setLeagueName(input.getLeagueName());
		match.setMatchId(input.getMatchId());
		match.setMatchTime(input.getMatchTime());
		match.setRunningIndicator(input.getRunningIndicator());
		match.setTimeGameLive(input.getTimeGameLive());
		match.setMatchDate(toDate(match.getMatchTime()));
		match.setBookieId(bookieId);
		match.setKoAwayPrice(awayPrice);
		match.setKoDrawPrice(drawPrice);
		match.setKoHomePrice(homePrice);
		match.setKoOuHfPrice(koOuHfPrice);
		match.setOuHandicapValue(ouHandicapValue);
		return match;
	}
	
	private static DateTime toDate(String matchTime) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd H:mm:ss");
		return formatter.parseDateTime(matchTime);
	}	
}
