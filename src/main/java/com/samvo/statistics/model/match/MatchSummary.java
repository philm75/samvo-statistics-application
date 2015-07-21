package com.samvo.statistics.model.match;

import java.util.LinkedHashMap;
import java.util.Map;

import org.joda.time.DateTime;

import com.samvo.statistics.model.common.BaseModel;

/**
 * @author Phil
 * 14 Jun 2015
 * Description - Model for a match summary.
 */
public class MatchSummary extends BaseModel {

	/**
	 * Match date.
	 */
	private DateTime gameDate;
	
	/**
	 * Kick off time.
	 */
	private String kickOffTime;
	
	/**
	 * Home team name.
	 */
	private String homeTeam;
	
	/**
	 * Away team name.
	 */
	private String awayTeam;
	
	/**
	 * Time first goal.
	 */
	private Integer timeFirstGoal;
	
	/**
	 * Kick off home price.
	 */
	private Double kickOffHomePrice;
	
	/**
	 * Kick off away price.
	 */
	private Double kickOffAwayPrice;
	
	/**
	 * Kick off draw price.
	 */
	private Double kickOffDrawPrice;

	/**
	 * Kick off over under 0.5 goal. 
	 */
	private Double kickOffOuHalfAGoal;
	
	/**
	 * Bookies name.
	 */
	private String bookieName;
	
	/**
	 * Match minutes.
	 */
	private Map<Integer, MatchTime> matchMinutes = new LinkedHashMap<Integer, MatchTime>();
	
	/**
	 * 
	 */
	public MatchSummary() {		
	}

	/**
	 * 
	 * @return DateTime
	 */
	public DateTime getGameDate() {
		return gameDate;
	}

	/**
	 * 
	 * @param gameDate
	 */
	public void setGameDate(DateTime gameDate) {
		this.gameDate = gameDate;
	}

	/**
	 * 
	 * @return String
	 */
	public String getKickOffTime() {
		return kickOffTime;
	}

	/**
	 * 
	 * @param kickOffTime
	 */
	public void setKickOffTime(String kickOffTime) {
		this.kickOffTime = kickOffTime;
	}

	/**
	 * 
	 * @return String
	 */
	public String getHomeTeam() {
		return homeTeam;
	}

	/**
	 * 
	 * @param homeTeam
	 */
	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	/**
	 * 
	 * @return String
	 */
	public String getAwayTeam() {
		return awayTeam;
	}

	/**
	 * 
	 * @param awayTeam
	 */
	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
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
	public Double getKickOffHomePrice() {
		return kickOffHomePrice;
	}

	/**
	 * 
	 * @param kickOffHomePrice
	 */
	public void setKickOffHomePrice(Double kickOffHomePrice) {
		this.kickOffHomePrice = kickOffHomePrice;
	}

	/**
	 * 
	 * @return Double
	 */
	public Double getKickOffAwayPrice() {
		return kickOffAwayPrice;
	}

	/**
	 * 
	 * @param kickOffAwayPrice
	 */
	public void setKickOffAwayPrice(Double kickOffAwayPrice) {
		this.kickOffAwayPrice = kickOffAwayPrice;
	}

	/**
	 * 
	 * @return Double
	 */
	public Double getKickOffDrawPrice() {
		return kickOffDrawPrice;
	}

	/**
	 * 
	 * @param kickOffDrawPrice
	 */
	public void setKickOffDrawPrice(Double kickOffDrawPrice) {
		this.kickOffDrawPrice = kickOffDrawPrice;
	}

	/**
	 * 
	 * @return Double
	 */
	public Double getKickOffOuHalfAGoal() {
		return kickOffOuHalfAGoal;
	}

	/**
	 * 
	 * @param kickOffOuHalfAGoal
	 */
	public void setKickOffOuHalfAGoal(Double kickOffOuHalfAGoal) {
		this.kickOffOuHalfAGoal = kickOffOuHalfAGoal;
	}

	/**
	 * 
	 * @return Map<Integer, MatchTime>
	 */
	public Map<Integer, MatchTime> getMatchMinutes() {
		return matchMinutes;
	}

	/**
	 * 
	 * @param matchMinutes
	 */
	public void setMatchMinutes(Map<Integer, MatchTime> matchMinutes) {
		this.matchMinutes = matchMinutes;
	}

	/**
	 * 
	 * @return String
	 */
	public String getBookieName() {
		return bookieName;
	}

	/**
	 * 
	 * @param bookieName
	 */
	public void setBookieName(String bookieName) {
		this.bookieName = bookieName;
	}	
}
