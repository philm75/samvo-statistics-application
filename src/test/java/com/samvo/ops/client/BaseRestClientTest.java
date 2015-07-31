package com.samvo.ops.client;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Phil
 * 8 Jun 2015
 * Description -
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-client-context.xml" })
abstract class BaseRestClientTest {

	/**
	 * 
	 */
	protected final String username = "sportspool";
	
	/**
	 * 
	 */
	protected final String password = "aa1234";
}
