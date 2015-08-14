package com.samvo.statistics.model.match;

import java.util.Map;
import java.util.TreeMap;

import org.joda.time.DateTime;

import com.samvo.statistics.model.common.BaseModel;

/**
 * @author Phil Merrilees
 * 11 Jun 2015
 * Description - Model for a match.
 */
public class Match extends BaseModel {

	/**
	 * Match identifier.
	 */
	private Integer matchId;
	
	/**
	 * League name.
	 */
	private String leagueName;

	/**
	 * Home team name.
	 */
	private String homeTeamName;

	/**
	 * Away team name.
	 */
	private String awayTeamName;

	/**
	 * Time of the match, The time zone used in the feed is GMT +8, so its converted to local date GMT.
	 */		
	private String matchTime;
	
	/**
	 * In-running indicator.
	 */
	private Integer runningIndicator;
	
	/**
	 * Home team score.
	 */
	private Integer homeScore;

	/**
	 * Away team score.
	 */
	private Integer awayScore;
	
	/**
	 * Time the game has been live, format example 1h 40 (1st half, 40th minute), 2h 9 (2nd half, 9th minute). 
	 */	
	private String timeGameLive;
	
	/**
	 * Identifier for the league.
	 */
	private Integer leagueId;
	
	/**
	 * Identifier of the home team.
	 */	
	private Integer homeTeamId;
	
	/**
	 * Identifier of the away team.
	 */	
	private Integer awayTeamId;
	
	/**
	 * Market feed type id.
	 */
	private Integer feedTypeId;
	
	/**
	 * Time 1st goal.
	 */
	private Integer timeFirstGoal = new Integer(-1);
	
	/**
	 * Kick off home price.
	 */
	private Double koHomePrice;
	
	/**
	 * Kick off away price.
	 */
	private Double koAwayPrice;
	
	/**
	 * Kick off draw price.
	 */
	private Double koDrawPrice;
	
	/**
	 * Kick off Under market price.
	 */
	private Double koOuHfPrice;
	
	/**
	 * OU Market handicap.
	 */
	private Double ouHandicapValue;
	
	/**
	 * Match date.
	 */
	private DateTime matchDate;
	
	/**
	 * Bookies with odds for the match.
	 */
	private Integer bookieId;
	
	/**
	 * Match times (all minutes).
	 */
	private Map<Integer, MatchTime> matchTimes = new TreeMap<Integer, MatchTime>();
	
	/**
	 * 
	 * @return Integer
	 */
	public Integer getMatchId() {
		return matchId;
	}

	/**
	 * 
	 * @param matchId
	 */
	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}

	/**
	 * 
	 * @return String
	 */
	public String getLeagueName() {
		return leagueName;
	}

	/**
	 * 
	 * @param leagueName
	 */
	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	/**
	 * 
	 * @return String
	 */
	public String getHomeTeamName() {
		return homeTeamName;
	}

	/**
	 * 
	 * @param homeTeamName
	 */
	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}

	/**
	 * 
	 * @return String
	 */
	public String getAwayTeamName() {
		return awayTeamName;
	}

	/**
	 * 
	 * @param awayTeamName
	 */
	public void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}

	/**
	 * 
	 * @return String
	 */
	public String getMatchTime() {
		return matchTime;
	}

	/**
	 * 
	 * @param matchTime
	 */
	public void setMatchTime(String matchTime) {
		this.matchTime = matchTime;
	}

	/**
	 * 
	 * @return Integer
	 */
	public Integer getRunningIndicator() {
		return runningIndicator;
	}

	/**
	 * 
	 * @param runningIndicator
	 */
	public void setRunningIndicator(Integer runningIndicator) {
		this.runningIndicator = runningIndicator;
	}

	/**
	 * 
	 * @return Integer
	 */
	public Integer getHomeScore() {
		return homeScore;
	}

	/**
	 * 
	 * @param homeScore
	 */
	public void setHomeScore(Integer homeScore) {
		this.homeScore = homeScore;
	}

	/**
	 * 
	 * @return Integer
	 */
	public Integer getAwayScore() {
		return awayScore;
	}

	/**
	 * 
	 * @param awayScore
	 */
	public void setAwayScore(Integer awayScore) {
		this.awayScore = awayScore;
	}

	/**
	 * 
	 * @return String
	 */
	public String getTimeGameLive() {
		return timeGameLive;
	}

	/**
	 * 
	 * @param timeGameLive
	 */
	public void setTimeGameLive(String timeGameLive) {
		this.timeGameLive = timeGameLive;
	}

	/**
	 * 
	 * @return Integer
	 */
	public Integer getLeagueId() {
		return leagueId;
	}

	/**
	 * 
	 * @param leagueId
	 */
	public void setLeagueId(Integer leagueId) {
		this.leagueId = leagueId;
	}

	/**
	 * 
	 * @return Integer
	 */
	public Integer getHomeTeamId() {
		return homeTeamId;
	}

	/**
	 * 
	 * @param homeTeamId
	 */
	public void setHomeTeamId(Integer homeTeamId) {
		this.homeTeamId = homeTeamId;
	}

	/**
	 * 
	 * @return Integer
	 */
	public Integer getAwayTeamId() {
		return awayTeamId;
	}

	/**
	 * 
	 * @param awayTeamId
	 */
	public void setAwayTeamId(Integer awayTeamId) {
		this.awayTeamId = awayTeamId;
	}

	/**
	 * 
	 * @return Integer
	 */
	public Integer getFeedTypeId() {
		return feedTypeId;
	}

	/**
	 * 
	 * @param feedTypeId
	 */
	public void setFeedTypeId(Integer feedTypeId) {
		this.feedTypeId = feedTypeId;
	}

	/**
	 * 
	 * @return Integer
	 */
	public Integer getTimeFirstGoal() {
		return timeFirstGoal;
	}

	/**
	 * 
	 * @param timeFirstGoal
	 */
	public void setTimeFirstGoal(Integer timeFirstGoal) {
		this.timeFirstGoal = timeFirstGoal;
	}

	/**
	 * 
	 * @return Double
	 */
	public Double getKoHomePrice() {
		return koHomePrice;
	}

	/**
	 * 
	 * @param koHomePrice
	 */
	public void setKoHomePrice(Double koHomePrice) {
		this.koHomePrice = koHomePrice;
	}

	/**
	 * 
	 * @return Double
	 */
	public Double getKoAwayPrice() {
		return koAwayPrice;
	}

	/**
	 * 
	 * @param koAwayPrice
	 */
	public void setKoAwayPrice(Double koAwayPrice) {
		this.koAwayPrice = koAwayPrice;
	}

	/**
	 * 
	 * @return Double
	 */
	public Double getKoDrawPrice() {
		return koDrawPrice;
	}

	/**
	 * 
	 * @param koDrawPrice
	 */
	public void setKoDrawPrice(Double koDrawPrice) {
		this.koDrawPrice = koDrawPrice;
	}

	/**
	 * 
	 * @return Double
	 */
	public Double getKoOuHfPrice() {
		return koOuHfPrice;
	}

	/**
	 * 
	 * @param koOuHfPrice
	 */
	public void setKoOuHfPrice(Double koOuHfPrice) {
		this.koOuHfPrice = koOuHfPrice;
	}
	
	/**
	 * 
	 * @return DateTime
	 */
	public DateTime getMatchDate() {
		return matchDate;
	}

	/**
	 * 
	 * @param matchDate
	 */
	public void setMatchDate(DateTime matchDate) {
		this.matchDate = matchDate;
	}

	/**
	 * 
	 * @return Map<Integer, MatchTime>
	 */
	public Map<Integer, MatchTime> getMatchTimes() {
		return matchTimes;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void addMatchTime(Integer key, MatchTime value) {
		matchTimes.put(key, value);
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public boolean matchTimeExists(Integer key) {
		return (matchTimes.get(key) != null);
	}
	
	/**
	 * 
	 * @return Integer
	 */
	public Integer getBookieId() {
		return bookieId;
	}

	/**
	 * 
	 * @param bookieId
	 */
	public void setBookieId(Integer bookieId) {
		this.bookieId = bookieId;
	}

	/**
	 * 
	 * @return Double
	 */
	public Double getOuHandicapValue() {
		return ouHandicapValue;
	}

	/**
	 * 
	 * @param ouHandicapValue
	 */
	public void setOuHandicapValue(Double ouHandicapValue) {
		this.ouHandicapValue = ouHandicapValue;
	}	
}
