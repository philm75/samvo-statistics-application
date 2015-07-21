package com.samvo.statistics.model.market;

/**
 * @author Phil
 * 14 Jun 2015
 * Description - Market enumerator.
 */
public enum Market {

	FT("0"),
	
	HT("1");
	
	/**
	 * Indicator.
	 */
	private final String indicator;
	
	/**
	 * 
	 * @param indicator
	 */
	private Market(String indicator) {
		this.indicator = indicator;
	}
	
	/**
	 * 
	 * @return String
	 */
	public final String indicator() {
		return this.indicator;
	}
}
