package com.samvo.ops.client;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import com.samvo.ops.client.model.feed.Matches;

/**
 * @author Phil
 * 4 Jun 2015
 * Description - Early Market Feed Rest Client.
 */
@Component("earlyMarketRestClient")
public class EarlyMarketRestClient extends AbstractMarketRestClient implements MarketRestClient {

	/**
	 * Early market URL.
	 */
	private static final String EM_URL = "https://%s/OPS/EMData?sessiontoken=%s";
	
	/**
	 * Early Market URL with match Id parameter.
	 */
	private static final String EM_BY_MATCH_IDS_URL = "https://%s/OPS/EMData?sessiontoken=%s&matchId=%s";
	
	@Override
	public Matches getMarketFeedData(String sessionToken) throws InvalidSessionTokenException {
		try {
			return restTemplate.getForObject(String.format(EM_URL, host, sessionToken), Matches.class);			
		} catch (RestClientException e) {
			throw new InvalidSessionTokenException();
		}
	}

	@Override
	public Matches getMarketFeedData(String sessionToken, Set<String> matchIds) throws InvalidSessionTokenException {
		try {
			return restTemplate.getForObject(String.format(EM_BY_MATCH_IDS_URL, host, sessionToken, toDelimitedStrings(matchIds)), Matches.class);
		} catch (RestClientException e) {
			throw new InvalidSessionTokenException();
		}			
	}

	@Override
	public String getMarketFeedDataXml(String sessionToken) {
		return StringUtils.EMPTY;
	}

	@Override
	public String getMarketFeedDataXml(String sessionToken, Set<String> matchIds) {
		return StringUtils.EMPTY;
	}
}
