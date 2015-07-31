package com.samvo.ops.client.model.feed;

/**
 * @author Phil
 * 4 Jun 2015
 * Description - Enumerator for market types.
 */
public enum MarketType {

	AH("Handicap market type"),
	
	OU("Over/under market type"),
	
	ST("Win/draw/win market type");
	
	private final String description;
	
	private MarketType(String description) {
		this.description = description;
	}
	
	public final String getDescription()  {
		return description;
	}
}
