package com.samvo.ops.client.model.feed;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.samvo.ops.client.model.BaseModel;

/**
 * @author Phil
 * 2 Jun 2015
 * Description - Model for a OPS feed match.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Match extends BaseModel {

	/**
	 * Match Identifier.
	 */
	@XmlAttribute(name="id")
	private Integer matchId;

	/**
	 * Name of the league.
	 */
	@XmlAttribute(name="ge")
	private String leagueName;

	/**
	 * Name of the home team.
	 */
	@XmlAttribute(name="he")
	private String homeTeamName;

	/**
	 * Name of the away team.
	 */
	@XmlAttribute(name="ae")	
	private String awayTeamName;

	/**
	 * Time of the match.
	 */	
	@XmlAttribute(name="mt")	
	private String matchTime;
	
	/**
	 * In-running indicator.
	 */
	@XmlAttribute(name="running")	
	private Integer runningIndicator;
	
	/**
	 * Home team score.
	 */
	@XmlAttribute(name="htpt")
	private Integer homeScore;

	/**
	 * Away team score.
	 */
	@XmlAttribute(name="atpt")	
	private Integer awayScore;
	
	/**
	 * Time the game has been live.
	 */
	@XmlAttribute(name="rtime", required=false)	
	private String timeGameLive;
	
	/**
	 * Identifier for the league,
	 */
	@XmlAttribute(name="gid")	
	private Integer leagueId;
	
	/**
	 * Identifier of the home team.
	 */
	@XmlAttribute(name="hid")	
	private Integer homeTeamId;
	
	/**
	 * Identifier of the away team.
	 */
	@XmlAttribute(name="aid")		
	private Integer awayTeamId;

	/**
	 * Bookies.
	 */
	@XmlElement(name="bookie")
	private List<Bookie> bookies = new ArrayList<Bookie>();
	
	/**
	 * 
	 */
	public Match() {
		
	}
	
	/**
	 * 
	 * @return
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
	 * @return
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
	 * @return
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
	 * @return
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
	 * @return
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
	 * @return true if live, otherwise returns false.
	 */
	public boolean isMatchLive() {
		return (runningIndicator.intValue() == 1);
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
	 * @return
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
	 * @return
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
	 * @return
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
	 * @return
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
	 * @return
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
	 * @return
	 */
	public List<Bookie> getBookies() {
		return bookies;
	}

	/**
	 * 
	 * @param bookies
	 */
	public void setBookies(List<Bookie> bookies) {
		this.bookies = bookies;
	}
}
