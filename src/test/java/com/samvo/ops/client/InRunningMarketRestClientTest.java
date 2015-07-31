package com.samvo.ops.client;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.samvo.ops.client.model.feed.Match;
import com.samvo.ops.client.model.feed.Matches;

/**
 * @author Phil
 * 8 Jun 2015
 * Description -
 */
public class InRunningMarketRestClientTest extends BaseRestClientTest {

	@Autowired
	private InRunningMarketRestClient inRunningMarketRestClient;

	@Autowired
	private AuthenticateClient authenticateClient;
	
	private String token;
	
	@Before
	public void setup() {
		token = authenticateClient.authenticate();
	}
	
	@Test
	public void testGetMarketFeedData() throws InvalidSessionTokenException {		
		Matches matches = inRunningMarketRestClient.getMarketFeedData(token);
		assertTrue(matches.getMatches().size() > 0);
	}
	
//	@Test
//	public void testGetMarketFeedDataByMatchIds() throws InvalidSessionTokenException {
//		Matches matches = inRunningMarketRestClient.getMarketFeedData(token);
//		Set<String> matchIds = new HashSet<String>();
//		matchIds.add("1019391");
//		matchIds.add("1020661");
//		matchIds.add("1022521");
//		
//		matches.getMatches().clear();
//		matches = inRunningMarketRestClient.getMarketFeedData(token, matchIds);
//		assertEquals(1, matches.getMatches().size());
//		System.out.println(matches.getMatches().get(0).toString());
//		System.out.println(matches.getMatches().get(1).toString());
//		System.out.println(matches.getMatches().get(2).toString());
//		
//	}	
}
