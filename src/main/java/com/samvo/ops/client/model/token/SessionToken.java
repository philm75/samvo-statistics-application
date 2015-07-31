package com.samvo.ops.client.model.token;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Phil
 * 7 Jun 2015
 * Description - Model for a session token
 */
@XmlRootElement(name="sessionToken")
@XmlAccessorType(XmlAccessType.FIELD)
public class SessionToken {

	/**
	 * Token.
	 */
	@XmlAttribute(name="sessionToken", required=true)
	private String token;
	
	/**
	 * 
	 */
	public SessionToken() {
		
	}

	/**
	 * 
	 * @return String
	 */
	public String getToken() {
		return token;
	}

	/**
	 * 
	 * @param token
	 */
	public void setToken(String token) {
		this.token = token;
	}	
}
