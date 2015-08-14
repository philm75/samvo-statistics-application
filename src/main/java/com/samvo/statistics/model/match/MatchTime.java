package com.samvo.statistics.model.match;

import com.samvo.statistics.model.common.BaseModel;

/**
 * @author Phil
 * 17 Jun 2015
 * Description - Model for a matches odds by time in minutes.
 */
public class MatchTime extends BaseModel {
 
	/**
	 * Match ID.
	 */
	private Integer matchId;
		
	/**
	 * Feed type ID.
	 */
	private Integer feedTypeId;
	
	/**
	 * Minute in the match.
	 */
	private Integer minute;
	
	/**
	 * Bookie unuqie identifier.
	 */
	private Integer bookieId;
	
	/**
	 * Price  e.g. 2.38
	 */
	private Double homePrice;

	/**
	 * Away price.
	 */
	private Double awayPrice;
	
	/**
	 * Draw price.
	 */
	private Double drawPrice;
	
	/**
	 * Half time under 0.5 goal price.
	 */
	private Double htUnderHgPrice;
	
	/**
	 * Handicap Value.
	 */
	private Double handicapValue;
		
	/**
	 * 
	 */
	public MatchTime() {
	}
	
	/**
	 * 
	 * @param matchId
	 * @param minute
	 * @param bookieId
	 * @param homePrice
	 * @param awayPrice
	 * @param drawPrice
	 * @param htUnderHgPrice
	 * @param feedTypeId
	 */
	public MatchTime(Integer matchId, Integer minute, Integer bookieId, Double homePrice, Double awayPrice, Double drawPrice, Double htUnderHgPrice, Integer feedTypeId) {
		this.matchId = matchId;
		this.minute = minute;
		this.bookieId = bookieId;
		this.homePrice = homePrice;
		this.awayPrice = awayPrice;
		this.drawPrice = drawPrice;
		this.htUnderHgPrice = htUnderHgPrice;
		this.feedTypeId = feedTypeId;
	}

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
	 * @return Integer
	 */
	public Integer getMinute() {
		return minute;
	}

	/**
	 * 
	 * @param minute
	 */
	public void setMinute(Integer minute) {
		this.minute = minute;
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
	public Double getHomePrice() {
		return homePrice;
	}

	/**
	 * 
	 * @param homePrice
	 */
	public void setHomePrice(Double homePrice) {
		this.homePrice = homePrice;
	}

	/**
	 * 
	 * @return Double
	 */
	public Double getAwayPrice() {
		return awayPrice;
	}

	/**
	 * 
	 * @param awayPrice
	 */
	public void setAwayPrice(Double awayPrice) {
		this.awayPrice = awayPrice;
	}

	/**
	 * 
	 * @return Double
	 */
	public Double getDrawPrice() {
		return drawPrice;
	}

	/**
	 * 
	 * @param drawPrice
	 */
	public void setDrawPrice(Double drawPrice) {
		this.drawPrice = drawPrice;
	}

	/**
	 * 
	 * @return Double
	 */
	public Double getHtUnderHgPrice() {
		return htUnderHgPrice;
	}

	/**
	 * 
	 * @param htUnderHgPrice
	 */
	public void setHtUnderHgPrice(Double htUnderHgPrice) {
		this.htUnderHgPrice = htUnderHgPrice;
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
	 * @return the handicapValue
	 */
	public Double getHandicapValue() {
		return handicapValue;
	}

	/**
	 * @param handicapValue the handicapValue to set
	 */
	public void setHandicapValue(Double handicapValue) {
		this.handicapValue = handicapValue;
	}	
}
