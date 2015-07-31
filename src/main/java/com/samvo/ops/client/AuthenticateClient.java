package com.samvo.ops.client;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.samvo.ops.client.model.feed.OPSErrorCodes;
import com.samvo.ops.client.model.token.Error;
import com.samvo.ops.client.model.token.SessionToken;
import com.samvo.ops.client.model.user.UserCredential;

/**
 * @author Phil Merrilees
 * 9 Jun 2015
 * Description -Authentication Client.
 */
@Component("authenticateClient")
public class AuthenticateClient {

	/**
	 * Login URL
	 */
	private static final String LOGIN_URL = "https://%s/OPS/login?loginid=%s&password=%s";
	
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
	 * User credentials for authentication.
	 */
	@Autowired
	private UserCredential user;
	
	/**
	 * Authenticates user name and password with OPS and returns a session token.
	 * 
	 * @return String token
	 */
	public String authenticate() {
		String response = restTemplate.getForObject(String.format(LOGIN_URL, host, user.getUsername(), user.getPassword()), String.class);
		String token = StringUtils.EMPTY;
		JAXBContext jbc;
		try {
			jbc = JAXBContext.newInstance(SessionToken.class);
			Unmarshaller unmarshaller = jbc.createUnmarshaller();
			SessionToken sessionToken = (SessionToken) unmarshaller.unmarshal(new StringReader(response));
			token = sessionToken.getToken();
		} catch (UnmarshalException e) {
			handleError(response);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return token;
	}
	
	private void handleError(String response) {
		JAXBContext jbc;
		try {
			jbc = JAXBContext.newInstance(Error.class);
			Unmarshaller unmarshaller = jbc.createUnmarshaller();
			Error error = (Error)unmarshaller.unmarshal(new StringReader(response));
			throw new OpsFeedException(OPSErrorCodes.valueOf(error.getErrorCode()));
		} catch (JAXBException e) {
			e.printStackTrace();
		}		
	}		
}
