package com.samvo.statistics.model.match;

/**
 * @author Phil
 * 11 Jun 2015
 * Description - Model for a betting odd.
 */
public class Odd {

	/**
	 * Match identifier.
	 */
	private Integer matchId;
	
	/**
	 * Bookie identifier.
	 */
	private Integer bookieId;
	
	/**
	 * Market.
	 */
	private String market;
	
	/**
	 * Market Type.
	 */
	private String marketType;
	
	/**
	 * Value for handicap.
	 */
	private String handicapValue;
	
	/**
	 * Home price for win/draw/win market type.
	 */	
	private String homePrice;
	
	/**
	 * Draw price for win/draw/win market type.
	 */
	private String drawPrice;
	
	/**
	 * Away price for win/draw/win market type.
	 */
	private String awayPrice;
	
	/**
	 * HOME in ah market type 
	 */
	private String homeAhMarketType;
	
	/**
	 * Home OVER in ou market type
	 */
	private String homeOverInOuMarketType;
	
	/**
	 * AWAY in ah market type 
	 */
	private String awayAhMarketType;

	/**
	 * UNDER in ou market type
	 */
	private String underInOuMarketType;
	
	/**
	 * Time stamp when the price was last updated.
	 */		
	private long lastPriceUpdated;
	
	/**
	 * Time stamp when the price was last received.
	 */
	private long lastUpdate;

	/**
	 * Kick off home price.
	 */
	private String kickOffHomePrice;
	
	/**
	 * Kick off away price.
	 */
	private String kickOffAwayPrice;
	
	/**
	 * Kick off draw price.
	 */
	private String kickOffDrawPrice;
	
	/**
	 * Under 0.5 goal half time price.
	 */
	private String underHalfGoalHalfTimePrice;
	
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
	 * @return String
	 */
	public String getMarket() {
		return market;
	}

	/**
	 * 
	 * @param market
	 */
	public void setMarket(String market) {
		this.market = market;
	}

	/**
	 * 
	 * @return String
	 */
	public String getMarketType() {
		return marketType;
	}

	/**
	 * 
	 * @param marketType
	 */
	public void setMarketType(String marketType) {
		this.marketType = marketType;
	}

	/**
	 * 
	 * @return String
	 */
	public String getHandicapValue() {
		return handicapValue;
	}

	/**
	 * 
	 * @param handicapValue
	 */
	public void setHandicapValue(String handicapValue) {
		this.handicapValue = handicapValue;
	}

	/**
	 * 
	 * @return String
	 */
	public String getHomePrice() {
		return homePrice;
	}

	/**
	 * 
	 * @param homePrice
	 */
	public void setHomePrice(String homePrice) {
		this.homePrice = homePrice;
	}

	/**
	 * 
	 * @return String
	 */
	public String getDrawPrice() {
		return drawPrice;
	}

	/**
	 * 
	 * @param drawPrice
	 */
	public void setDrawPrice(String drawPrice) {
		this.drawPrice = drawPrice;
	}

	/**
	 * 
	 * @return String
	 */
	public String getAwayPrice() {
		return awayPrice;
	}

	/**
	 * 
	 * @param awayPrice
	 */
	public void setAwayPrice(String awayPrice) {
		this.awayPrice = awayPrice;
	}

	/**
	 * 
	 * @return String
	 */
	public String getHomeAhMarketType() {
		return homeAhMarketType;
	}

	/**
	 * 
	 * @param homeAhMarketType
	 */
	public void setHomeAhMarketType(String homeAhMarketType) {
		this.homeAhMarketType = homeAhMarketType;
	}

	/**
	 * 
	 * @return String
	 */
	public String getHomeOverInOuMarketType() {
		return homeOverInOuMarketType;
	}

	/**
	 * 
	 * @param homeOverInOuMarketType
	 */
	public void setHomeOverInOuMarketType(String homeOverInOuMarketType) {
		this.homeOverInOuMarketType = homeOverInOuMarketType;
	}

	/**
	 * 
	 * @return String
	 */
	public String getAwayAhMarketType() {
		return awayAhMarketType;
	}

	/**
	 * 
	 * @param awayAhMarketType
	 */
	public void setAwayAhMarketType(String awayAhMarketType) {
		this.awayAhMarketType = awayAhMarketType;
	}

	/**
	 * 
	 * @return String
	 */
	public String getUnderInOuMarketType() {
		return underInOuMarketType;
	}

	/**
	 * 
	 * @param underInOuMarketType
	 */
	public void setUnderInOuMarketType(String underInOuMarketType) {
		this.underInOuMarketType = underInOuMarketType;
	}

	/**
	 * 
	 * @return long
	 */
	public long getLastPriceUpdated() {
		return lastPriceUpdated;
	}

	/**
	 * 
	 * @param lastPriceUpdated
	 */
	public void setLastPriceUpdated(long lastPriceUpdated) {
		this.lastPriceUpdated = lastPriceUpdated;
	}

	/**
	 * 
	 * @return long
	 */ 
	public long getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * 
	 * @param lastUpdate
	 */
	public void setLastUpdate(long lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/**
	 * 
	 * @return String
	 */
	public String getKickOffHomePrice() {
		return kickOffHomePrice;
	}

	/**
	 * 
	 * @param kickOffHomePrice
	 */
	public void setKickOffHomePrice(String kickOffHomePrice) {
		this.kickOffHomePrice = kickOffHomePrice;
	}

	/**
	 * 
	 * @return String
	 */
	public String getKickOffAwayPrice() {
		return kickOffAwayPrice;
	}

	/**
	 * 
	 * @param kickOffAwayPrice
	 */
	public void setKickOffAwayPrice(String kickOffAwayPrice) {
		this.kickOffAwayPrice = kickOffAwayPrice;
	}

	/**
	 * 
	 * @return String
	 */
	public String getKickOffDrawPrice() {
		return kickOffDrawPrice;
	}

	/**
	 * 
	 * @param kickOffDrawPrice
	 */
	public void setKickOffDrawPrice(String kickOffDrawPrice) {
		this.kickOffDrawPrice = kickOffDrawPrice;
	}

	/**
	 * 
	 * @return String
	 */
	public String getUnderHalfGoalHalfTimePrice() {
		return underHalfGoalHalfTimePrice;
	}

	/**
	 * 
	 * @param underHalfGoalHalfTimePrice
	 */
	public void setUnderHalfGoalHalfTimePrice(String underHalfGoalHalfTimePrice) {
		this.underHalfGoalHalfTimePrice = underHalfGoalHalfTimePrice;
	}		
}
