package com.samvo.ops.client;

/**
 * @author Phil
 * 11 Jun 2015
 * Description - Market Rest Client exception
 */
public class InvalidSessionTokenException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2358168177174734948L;
	
	/**
	 * 
	 */
	private static final String MESSAGE = "Invalid Session Token";
	
	/**
	 * 
	 */
	public InvalidSessionTokenException() {
		
	}

	@Override
	public String getMessage() {
		return MESSAGE;
	}

}
