package com.samvo.ops.client.model.feed;

/**
 * @author Phil
 * 3 Jun 2015
 * Description - OPS Feed error code enumerator.
 */
public enum OPSErrorCodes {

	OPS001("Invalid session token"),
	
	OPS002("Invalid loginid and password"),
	
	OPS003("You are not authenticated to use this feed");
	
	/**
	 * Error message.
	 */
	private final String errorMessage;
	
	/**
	 * 
	 * @param errorMessage
	 */
	private OPSErrorCodes(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	/**
	 * 
	 * @return String
	 */
	public final String getErrorMessage() {
		return this.errorMessage;
	}
}
