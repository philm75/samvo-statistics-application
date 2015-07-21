package com.samvo.statistics.dao;

import java.util.List;

import com.samvo.statistics.model.match.Bookie;

/**
 * @author Phil Merrilees
 * 11 Jun 2015
 * Description - Bookie data access component.
 */
public interface BookieDao {

	/**
	 * Creates a bookie.
	 * 
	 * @param name
	 * @return Bookie
	 */
	Bookie createBookie(String name);
	
	/**
	 * Reads all bookies.
	 * 
	 * @param bookies
	 * @return List<Bookie>
	 */
	List<Bookie> readAll();
	
	/**
	 * Read bookie name by unique identifier.
	 * 
	 * @param id
	 * @return String
	 */
	String readById(Integer id);
	
}
