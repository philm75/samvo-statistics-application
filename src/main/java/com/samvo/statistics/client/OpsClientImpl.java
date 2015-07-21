package com.samvo.statistics.client;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.samvo.ops.client.AuthenticateClient;
import com.samvo.ops.client.MarketRestClient;
import com.samvo.ops.client.InvalidSessionTokenException;
import com.samvo.ops.client.model.feed.Matches;

/**
 * @author Phil
 * 9 Jun 2015
 * Description - 
 */
@Component("opsClient")
public class OpsClientImpl implements OpsClient {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = Logger.getLogger(OpsClientImpl.class);
	
	/**
	 * Early market rest client.
	 */
	@Autowired
	@Qualifier("earlyMarketRestClient")
	private MarketRestClient earlyMarketRestClient;

	/**
	 * In running market rest client
	 */
	@Autowired
	@Qualifier("inRunningMarketRestClient")
	private MarketRestClient inRunningMarketRestClient;
	
	/**
	 * Authenticate client.
	 */
	@Autowired
	private AuthenticateClient authenticateClient;

	/**
	 * Today market rest client.
	 */
	@Autowired
	@Qualifier("todayMarketRestClient")
	private MarketRestClient todayMarketRestClient;
	
	@Override
	public String authenticate() {
		if (LOGGER.isDebugEnabled()) {LOGGER.debug("authenticate()");}
		return authenticateClient.authenticate();
	}

	@Override
	public Matches getEarlyMarketFeedData(String sessionToken) throws InvalidSessionTokenException {
		if (LOGGER.isDebugEnabled()) {LOGGER.debug(String.format("getEarlyMarketFeedData(sessionToken=%s)", sessionToken));}
		return earlyMarketRestClient.getMarketFeedData(sessionToken);
	}

	@Override
	public Matches getEarlyMarketFeedData(String sessionToken, Set<String> matchIds) throws InvalidSessionTokenException {
		if (LOGGER.isDebugEnabled()) {LOGGER.debug(String.format("getEarlyMarketFeedData(sessionToken=%s,matchIds=%s)", sessionToken, matchIds.toString()));}
		return earlyMarketRestClient.getMarketFeedData(sessionToken, matchIds);
	}

	@Override
	public Matches getInRunningMarketFeedData(String sessionToken) throws InvalidSessionTokenException {
		if (LOGGER.isDebugEnabled()) {LOGGER.debug(String.format("getInRunningMarketFeedData(sessionToken=%s)", sessionToken));}
		return inRunningMarketRestClient.getMarketFeedData(sessionToken);
	}

	@Override
	public Matches getInRunningMarketFeedData(String sessionToken, Set<String> matchIds) throws InvalidSessionTokenException {
		if (LOGGER.isDebugEnabled()) {LOGGER.debug(String.format("getInRunningMarketFeedData(sessionToken=%s,matchIds=%s)", sessionToken, matchIds.toString()));		}
		return inRunningMarketRestClient.getMarketFeedData(sessionToken, matchIds);
	}

	@Override
	public Matches getTodayMarketFeedData(String sessionToken) throws InvalidSessionTokenException {
		if (LOGGER.isDebugEnabled()) {LOGGER.debug(String.format("getTodayMarketFeedData(sessionToken=%s)", sessionToken));}		
		return todayMarketRestClient.getMarketFeedData(sessionToken);
	}

	@Override
	public Matches getTodayMarketFeedData(String sessionToken, Set<String> matchIds) throws InvalidSessionTokenException {
		if (LOGGER.isDebugEnabled()) {LOGGER.debug(String.format("getTodayMarketFeedData(sessionToken=%s,matchIds=%s)", sessionToken, matchIds.toString()));}
		return todayMarketRestClient.getMarketFeedData(sessionToken, matchIds);
	}
}
