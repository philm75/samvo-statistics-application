package com.samvo.statistics.dao;

import java.util.List;

import com.samvo.statistics.model.match.MatchTime;

/**
 * @author Phil
 * 20 Jun 2015
 * Description - Data access component for match time minutes.
 */
public interface MatchTimeDao {

	/**
	 * Create a match time.
	 * 
	 * @param matchId
	 * @param feedTypeId
	 * @param bookieId
	 */
	void createMatchTime(Integer matchId, Integer feedTypeId, Integer bookieId);
	
	/**
	 * Update match time price.
	 * 
	 * @param matchId
	 * @param feedTypeId
	 * @param bookieId
	 * @param minute
	 * @param price
	 */
	void updateMatchTime(Integer matchId, Integer feedTypeId, Integer bookieId, Integer minute, Double price);
	
	/**
	 * 
	 * @param matchId
	 * @param feedTypeId
	 * @return
	 */
	List<MatchTime> readMatchTimeByMatchId(Integer matchId, Integer feedTypeId);
	
}
