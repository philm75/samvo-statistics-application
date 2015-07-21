package com.samvo.statistics.model.match;

import com.samvo.statistics.model.common.BaseModel;

/**
 * @author Phil
 * 11 Jun 2015
 * Description - Model for a feed type.
 */
public class FeedType extends BaseModel {

	/**
	 * Unique identifier.
	 */
	private Integer typeId;
	
	/**
	 * Feed type code
	 */
	private String code;
	
	/**
	 * Feed type name.
	 */
	private String name;

	/**
	 * 
	 * @return Integer
	 */
	public Integer getTypeId() {
		return typeId;
	}

	/**
	 * 
	 * @param typeId
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	/**
	 * 
	 * @return String
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}	
}
