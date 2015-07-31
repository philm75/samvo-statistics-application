package com.samvo.ops.client.model.feed;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.samvo.ops.client.model.BaseModel;

/**
 * @author Phil
 * 2 Jun 2015
 * Description -  Model for a OPS feed bookie.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Bookie extends BaseModel {

	/**
	 * Name of the bookie.
	 */
	@XmlAttribute
	private String name;
	
	/**
	 * Bookie odds.
	 */
	@XmlElement(name="odd")
	private List<Odd> odds = new ArrayList<Odd>();

	/**
	 * 
	 */
	public Bookie() {				
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
	 * @return
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
}
