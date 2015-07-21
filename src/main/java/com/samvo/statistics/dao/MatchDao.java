package com.samvo.statistics.dao;

import java.util.List;
import java.util.Map;

import com.samvo.statistics.model.match.Match;
import com.samvo.statistics.model.match.MatchSummary;

/**
 * @author Phil
 * 11 Jun 2015
 * Description - Match data access component.
 */
public interface MatchDao {

	/**
	 * Create a match.
	 * 
	 * @param match
	 */
	void createMatch(Match match);
	
	/**
	 * Create batch of matches.
	 * 
	 * @param matches
	 */
	void createMatches(List<Match> matches);
	
	/**
	 * Read all matches.
	 * 
	 * @return List<Match>
	 */
	List<Match> readAll();
	
	/**
	 * Read matches by feed type.
	 * 
	 * @param feedTypeId
	 * @return List<Match>
	 */
	List<Match> readMatchByFeedType(Integer feedTypeId);
	
	/**
	 * Read match summaries.
	 * 
	 * @param feedTypeId
	 * @return List<MatchSummary>
	 */
	List<MatchSummary> readAllSummary(Integer feedTypeId);
	
	/**
	 * Update first goal scored time for a match.
	 * 
	 * @param matchId
	 * @param timeGoalScored
	 * @param feedTypeId 
	 * @param bookieId
	 */
	void updateFirstGoalScore(Integer matchId, Integer timeGoalScored, Integer feedTypeId, Integer bookieId);
	
	/**
	 * Update the match kick off prices.
	 * 
	 * @param matchId
	 * @param homePrice
	 * @param drawPrice
	 * @param awayPrice
	 * @param htUndefHgPrice
	 * @param feedTypeId
	 * @param indicator
	 * @param bookieId
	 */
	void updateKo(Integer matchId, Double homePrice, Double drawPrice, Double awayPrice, Double htUndefHgPrice, Integer feedTypeId, Integer indicator, Integer bookieId);
	
	/**
	 * Read matches by feed type.
	 * 
	 * @param feedTypeId
	 * @return Map<String, Match>
	 */
	Map<String, Match> readMatchesByIndicator(Integer feedTypeId);

}
