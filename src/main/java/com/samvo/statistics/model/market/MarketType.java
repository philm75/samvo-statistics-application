package com.samvo.statistics.model.market;

/**
 * @author Phil
 * 14 Jun 2015
 * Description - Market type enumerator.
 */
public enum MarketType {

	AH ("Handicap market type", "ah"),
	
	OU ("Over/under market type", "ou"),
	
	ST ("Win/draw/win market type", "st");
	
	/**
	 * Indicator.
	 */
	private final String indicator;
	
	/**
	 * Type description.
	 */
	private final String description;
	
	/**
	 * 
	 * @param description
	 * @param indicator
	 */
	private MarketType(String description, String indicator) {
		this.description = description;
		this.indicator = indicator;
	}
	
	/**
	 * 
	 * @return String
	 */
	public final String description() {
		return this.description;
	}

	/**
	 * 
	 * @return String
	 */
	public String indicator() {
		return indicator;
	}
}
