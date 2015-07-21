package com.samvo.statistics.model.match;

/**
 * @author Phil
 * 14 Jun 2015
 * Description - Enumerator for feed types.
 */
public enum FeedTypes {
	
	TODAY (1, "TD", "Today Market"),
	
	IN_RUNNING (2, "IR", "In Running Market"),
	
	EARLY (3, "EM", "Early Market");	
	
	/**
	 * 
	 */
	private final Integer typeId;
	
	/**
	 * 
	 */
	private final String code;
	
	/**
	 * 
	 */
	private final String description;
	
	/**
	 * 
	 * @param typeId
	 * @param code
	 * @param description
	 */
	private FeedTypes(Integer typeId, String code, String description) {
		this.typeId = typeId;
		this.code = code;
		this.description = description;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getTypeId() {
		return typeId;
	}

	/**
	 * 
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}	
}
