package com.samvo.ops.client;

import java.util.Set;

import com.samvo.ops.client.model.feed.Matches;

/**
 * @author Phil
 * 
 * 2 Jun 2015
 * 
 * Description - OPS Feed Rest Client Interface.
 */
public interface MarketRestClient {

	/**
	 * Gets market feed data. 
	 * 
	 * @param sessionToken
	 * @return Matches
	 * @throws InvalidSessionTokenException
	 */
	Matches getMarketFeedData(String sessionToken) throws InvalidSessionTokenException;
	
	/**
	 * Get market feed data for a set of match IDs.
	 * 
	 * @param sessionToken
	 * @param matchIds
	 * @return Matches
	 * @throws InvalidSessionTokenException
	 */
	Matches getMarketFeedData(String sessionToken, Set<String> matchIds) throws InvalidSessionTokenException;
	
	/**
	 * Get market feed data in XML.
	 * 
	 * @param sessionToken
	 * @return String
	 */
	String getMarketFeedDataXml(String sessionToken);
	
	/**
	 * Get market feed data in XML for a set of match IDs.
	 * 
	 * @param sessionToken
	 * @param matchIds
	 * @return String
	 */
	String getMarketFeedDataXml(String sessionToken, Set<String> matchIds);
}
