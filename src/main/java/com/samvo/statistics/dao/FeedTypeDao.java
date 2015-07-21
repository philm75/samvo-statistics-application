package com.samvo.statistics.dao;

import java.util.List;

import com.samvo.statistics.model.match.FeedType;

/**
 * @author Phil
 * 11 Jun 2015
 * Description - Feed type data access component.
 */
public interface FeedTypeDao {

	/**
	 * Read all feed types.
	 * 
	 * @return List<FeedType>
	 */
	List<FeedType> readAll();
}
