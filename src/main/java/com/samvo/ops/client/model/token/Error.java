package com.samvo.ops.client.model.token;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.samvo.ops.client.model.BaseModel;

/**
 * @author Phil
 * 9 Jun 2015
 * Description -
 */
@XmlRootElement(name="error")
@XmlAccessorType(XmlAccessType.FIELD)
public class Error extends BaseModel {

	/**
	 * Error code.
	 */
	@XmlAttribute(required=true)
	private String errorCode;
	
	/**
	 * Error message.
	 */
	@XmlAttribute(required=true)
	private String errorMessage;

	/**
	 * 
	 */
	public Error() {		
	}

	/**
	 * 
	 * @return String
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * 
	 * @param errorCode
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * 
	 * @return String
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * 
	 * @param errorMessage
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}	
}
