package com.samvo.ops.client;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Phil
 * 9 Jun 2015
 * Description -
 */
public class AbstractMarketRestClientTest extends BaseRestClientTest {

	@Autowired
	private EarlyMarketRestClient earlyMarketRestClient;
	
	@Test
	public void testToDelimitedStrings() {
		Set<String> matchIds = new HashSet<String>();
		matchIds.add("123456");
		matchIds.add("789012");
		matchIds.add("345678");
		
		assertEquals("789012,123456,345678", EarlyMarketRestClient.toDelimitedStrings(matchIds));
	}
}
