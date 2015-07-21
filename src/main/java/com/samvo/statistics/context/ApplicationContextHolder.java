package com.samvo.statistics.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Phil Merrilees
 * 14 Jun 2015
 * Description - Singleton Holder class for loading spring application context.
 */
public class ApplicationContextHolder {

	private static class Holder {
		static final ApplicationContext INSTANCE = new ClassPathXmlApplicationContext("classpath:application-context.xml");
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
