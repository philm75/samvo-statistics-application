package com.samvo.ops.client;

import com.samvo.ops.client.model.feed.OPSErrorCodes;

/**
 * @author Phil
 * 9 Jun 2015
 * Description - Invalid session token exception.
 */
public class OpsFeedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8754855745933404596L;

	/**
	 * Error code
	 */
	private final OPSErrorCodes errorCode;
	
	/**
	 * 
	 * @param errorCode
	 */
	public OpsFeedException(OPSErrorCodes errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * 
	 * @return OPSErrorCodes
	 */
	public OPSErrorCodes getErrorCode() {
		return errorCode;
	}

	@Override
	public String getMessage() {
		return errorCode.getErrorMessage();
	}	
}
