package com.samvo.statistics.client;

import java.util.Set;

import com.samvo.ops.client.InvalidSessionTokenException;
import com.samvo.ops.client.model.feed.Matches;

/**
 * @author Phil
 * 9 Jun 2015
 * Description - Client interface to invok OPS Client API.
 */
public interface OpsClient {
	
	/**
	 * Authenticate user account.
	 * 
	 * @return sessionToken
	 */
	String authenticate();
	
	/**
	 * Get early market feed data.
	 * 
	 * @param sessionToken
	 * @return Matches
	 * @throws InvalidSessionTokenException 
	 */
	Matches getEarlyMarketFeedData(String sessionToken) throws InvalidSessionTokenException;

	/**
	 * Get early market feed data by match IDs.
	 * 
	 * @param sessionToken
	 * @return Matches
	 * @throws InvalidSessionTokenException 
	 */
	Matches getEarlyMarketFeedData(String sessionToken, Set<String> matchIds) throws InvalidSessionTokenException;

	/**
	 * Get in-running market feed data.
	 * 
	 * @param sessionToken
	 * @return Matches
	 * @throws InvalidSessionTokenException 
	 */
	Matches getInRunningMarketFeedData(String sessionToken) throws InvalidSessionTokenException;

	/**
	 * Get in-running market feed data by match IDs.
	 * 
	 * @param sessionToken
	 * @param matchIds
	 * @return Matches
	 * @throws InvalidSessionTokenException 
	 */
	Matches getInRunningMarketFeedData(String sessionToken, Set<String> matchIds) throws InvalidSessionTokenException;
	
	/**
	 * Get today market feed data.
	 * 
	 * @param sessionToken
	 * @param matchIds
	 * @return Matches
	 * @throws InvalidSessionTokenException 
	 */
	Matches getTodayMarketFeedData(String sessionToken) throws InvalidSessionTokenException;

	/**
	 * Get today market feed data by match IDs.
	 * 
	 * @param sessionToken
	 * @param matchIds
	 * @return Matches
	 * @throws InvalidSessionTokenException 
	 */	
	Matches getTodayMarketFeedData(String sessionToken, Set<String> matchIds) throws InvalidSessionTokenException;
	
}
