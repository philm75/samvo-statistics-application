package com.samvo.statistics.service;

import java.util.List;
import java.util.Map;

import com.samvo.statistics.model.match.Match;
import com.samvo.statistics.model.match.MatchSummary;

/**
 * @author Phil Merrilees
 * 14 Jun 2015
 * Description - Statistics business service component.
 */
public interface StatisticsService {

	/**
	 * Create a match.
	 * 
	 * @param match
	 */
	void createMatch(Match match);
	
	/**
	 * Create matches.
	 * 
	 * @param matches
	 */
	void createMatches(List<Match> matches);
	
	/**
	 * Get all matches by feed type.
	 * 
	 * @param feedTypeId
	 * @return List<Match>
	 */
	List<Match> getAllByFeedType(Integer feedTypeId);

	/**
	 * Get all match summaries vy feed type.
	 * 
	 * @param feedTypeId
	 * @return List<MatchSummary>
	 */
	List<MatchSummary> getAllSummary(Integer feedTypeId);
	
	/**
	 * Update minute first goal scored.
	 * 
	 * @param matchId
	 * @param timeGoalScored
	 * @param feedTypeId
	 * @param bookieId
	 */
	void updateFirstGoalScore(Integer matchId, Integer timeGoalScored, Integer feedTypeId, Integer bookieId);
	
	/**
	 * Set the Kick Off prices for a match/bookie.
	 * 
	 * @param matchId
	 * @param homePrice
	 * @param drawPrice
	 * @param awayPrice
	 * @param htUndefHgPrice
	 * @param feedTypeId
	 * @param bookieId
	 */
 	void updateKo(Integer matchId, Double homePrice, Double drawPrice, Double awayPrice, Double htUndefHgPrice, Integer feedTypeId, Integer bookieId);
	  
	/**
	 * Get map of bookies (key - name, value - bookie ID)
	 * 
	 * @return Map<String, Integer>
	 */
	Map<String, Integer> getBookies();
		
	/**
	 * Get matches by feed type.
	 * 
	 * @param feedTypeId
	 * @return Map<String, Match>
	 */
	Map<String, Match> getMatchesByIndicator(Integer feedTypeId);	
	
	/**
	 * Update match minute price for match ID and bookie ID.
	 * 
	 * @param matchId
	 * @param bookieId
	 * @param feedTypeId
	 * @param minute
	 * @param price
	 */
	void updateMatchMinute(Integer matchId, Integer bookieId, Integer feedTypeId, Integer minute, Double price);
	
	/**
	 * Stop in-running market process.
	 */
	void interruptInRunningMarket();
	
	/**
	 * Update in-running Indicator.
	 * 
	 * @param matchId
	 * @param feedTypeId
	 * @param indicator
	 * @param bookieId
	 */
	void updateInRunningIndicator(Integer matchId, Integer feedTypeId, Integer indicator, Integer bookieId);	
}
