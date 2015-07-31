package com.samvo.ops.client.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Phil
 * 8 Jun 2015
 * Description - Singleton Holder component for fetching spring application context.
 */
public class ApplicationContextHolder {

	
	private static class Holder {
		static final ApplicationContext INSTANCE = new ClassPathXmlApplicationContext("classpath:client-context.xml");
	}
	
	/**
	 * 
	 */
	private ApplicationContextHolder() {
		
	}
	
	/**
	 * 
	 * @return ApplicationContext
	 */ 
	public static ApplicationContext getContext() {
		return Holder.INSTANCE;
	}
}
