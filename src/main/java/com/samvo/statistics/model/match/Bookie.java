package com.samvo.statistics.model.match;

import java.util.ArrayList;
import java.util.List;

import com.samvo.statistics.model.common.BaseModel;

/**
 * @author Phil
 * 11 Jun 2015
 * Description - Model for a bookie.
 */
public class Bookie extends BaseModel {

	/**
	 * Bookie Identifier.
	 */
	private Integer id;
	
	/**
	 * Name.
	 */
	private String name;

	/**
	 * Odds list.
	 */
	private List<Odd> odds = new ArrayList<Odd>();
	
	/**
	 * 
	 * @return Integer
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
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

	/**
	 * 
	 * @return List<Odd>
	 */
	public List<Odd> getOdds() {
		return odds;
	}

	/**
	 * 
	 * @param odds
	 */
	public void setOdds(List<Odd> odds) {
		this.odds = odds;
	}	
	
	/**
	 * 
	 * @param odd
	 */
	public void addOdd(Odd odd) {
		
	}
}
