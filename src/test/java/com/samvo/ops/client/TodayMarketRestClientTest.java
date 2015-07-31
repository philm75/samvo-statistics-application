package com.samvo.ops.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.samvo.ops.client.model.feed.Matches;

/**
 * @author Phil
 * 9 Jun 2015
 * Description -
 */
public class TodayMarketRestClientTest extends BaseRestClientTest {

	@Autowired
	private TodayMarketRestClient todayMarketRestClient;
	
	@Autowired
	private AuthenticateClient authenticateClient;
	
	private String token;
	
	@Before
	public void setup() {
		token = authenticateClient.authenticate();
	}
	
	@Test
	public void testGetMarketFeedData() throws InvalidSessionTokenException {
		Matches matches = todayMarketRestClient.getMarketFeedData(token);
		assertTrue(!matches.getMatches().isEmpty());
	}

	@Test
	public void testGetMarketFeedDataByMatchIds() throws InvalidSessionTokenException {
		Matches matches = todayMarketRestClient.getMarketFeedData(token);
		
		Set<String> matchIds = new HashSet<String>();
		matchIds.add(matches.getMatches().get(0).getMatchId().toString());
		matchIds.add(matches.getMatches().get(1).getMatchId().toString());
		matchIds.add(matches.getMatches().get(2).getMatchId().toString());
		
		matches.getMatches().clear();
		matches = todayMarketRestClient.getMarketFeedData(token, matchIds);
		
		assertEquals(matches.getMatches().size(), 3);
	}	
}
