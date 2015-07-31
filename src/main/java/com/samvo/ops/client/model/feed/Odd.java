package com.samvo.ops.client.model.feed;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import com.samvo.ops.client.model.BaseModel;

/**
 * @author Phil
 * 2 Jun 2015
 * Description - Model for an OPS feed Odd.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Odd extends BaseModel {

	/**
	 * Market.
	 */
	@XmlAttribute(name="et")
	private String market;
	
	/**
	 * Market Type.
	 */
	@XmlAttribute(name="t")
	private String marketType;
	
	/**
	 * Value for handicap.
	 */
	@XmlAttribute(name="h")
	private String handicapValue;
	
	/**
	 * Home price for win/draw/win market type.
	 */
	@XmlAttribute(name="o1")	
	private String homePrice;
	
	/**
	 * Draw price for win/draw/win market type.
	 */
	@XmlAttribute(name="o2")
	private String drawPrice;
	
	/**
	 * Away price for win/draw/win market type.
	 */
	@XmlAttribute(name="o3")
	private String awayPrice;
	
	/**
	 * HOME in ah market type or OVER in ou market type
	 */
	@XmlAttribute(name="o")	
	private String homeAhMarketTypeOrOverInOuMarketType;
	
	/**
	 * AWAY in ah market type or UNDER in ou market type
	 */
	@XmlAttribute(name="u")	
	private String awayAhMarketTypeOrUnderInOuMarketType;
	
	/**
	 * Time stamp when the price was last updated.
	 */
	@XmlAttribute(name="time")			
	private long lastPriceUpdated;
	
	/**
	 * Time stamp when the price was last received.
	 */
	@XmlAttribute(name="lastupdate", required=false)
	private long lastUpdate;	
	
	/**
	 * 
	 */
	public Odd() {	
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
	 * @return true if half time, otherwise full time.
	 */
	public boolean isHalfTimeMarket() {
		return (market.equals("1"));
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
	public String getHomeAhMarketTypeOrOverInOuMarketType() {
		return homeAhMarketTypeOrOverInOuMarketType;
	}

	/**
	 * 
	 * @param homeAhMarketTypeOrOverInOuMarketType
	 */
	public void setHomeAhMarketTypeOrOverInOuMarketType(String homeAhMarketTypeOrOverInOuMarketType) {
		this.homeAhMarketTypeOrOverInOuMarketType = homeAhMarketTypeOrOverInOuMarketType;
	}

	/**
	 * 
	 * @return String
	 */
	public String getAwayAhMarketTypeOrUnderInOuMarketType() {
		return awayAhMarketTypeOrUnderInOuMarketType;
	}

	/**
	 * 
	 * @param awayAhMarketTypeOrUnderInOuMarketType
	 */
	public void setAwayAhMarketTypeOrUnderInOuMarketType(String awayAhMarketTypeOrUnderInOuMarketType) {
		this.awayAhMarketTypeOrUnderInOuMarketType = awayAhMarketTypeOrUnderInOuMarketType;
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
	 * @return String
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
}
