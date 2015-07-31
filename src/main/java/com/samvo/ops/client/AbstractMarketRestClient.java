package com.samvo.ops.client;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

/**
 * @author Phil
 * 4 Jun 2015
 * Description - Abstract market rest client class.
 */
public class AbstractMarketRestClient {

	/**
	 * REST Template.
	 */
	@Autowired
	protected RestTemplate restTemplate;
	
	/**
	 * Base host
	 */
	@Value("${host}")
	protected String host;
	
	/**
	 * 
	 * @param matchIds
	 * @return String
	 */
	protected static String toDelimitedStrings(final Set<String> matchIds) {
		StringBuilder sb = new StringBuilder();
		Iterator<String> iterator = matchIds.iterator();
		while(iterator.hasNext()) {
			sb.append(iterator.next());
			sb.append(",");
		}
		sb.delete(sb.length()-1, sb.length());
		return sb.toString();
	}
	

}
