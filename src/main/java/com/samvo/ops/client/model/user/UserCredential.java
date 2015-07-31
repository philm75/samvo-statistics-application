package com.samvo.ops.client.model.user;

/**
 * @author Phil
 * 8 Jun 2015
 * Description -
 */
public class UserCredential {

	/**
	 * User name.
	 */
	private String username;
	
	/**
	 * Password.
	 */
	private String password;

	/**
	 * 
	 * @return String
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
