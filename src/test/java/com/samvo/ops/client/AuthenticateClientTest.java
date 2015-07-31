package com.samvo.ops.client;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Phil
 * 9 Jun 2015
 * Description -
 */
public class AuthenticateClientTest extends BaseRestClientTest {

	@Autowired
	private AuthenticateClient authenticateClient;
	
	@Test
	public void testAuthenticate() {
		String token = authenticateClient.authenticate();
		assertNotNull(token);
	}
	
//	@Test
//	public void testAuthenticate_BadCredentials() {
//		try {
//			String token = earlyMarketRestClient.authenticate("XXXXX", "XXXXXX");
//			assertNotNull(token);			
//		} catch (OpsFeedException e) {
//			assertEquals(OPSErrorCodes.OPS002.getErrorMessage(), e.getMessage());
//		}
//	}	
	
}
