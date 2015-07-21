package com.samvo.statistics.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Phil Merrilees
 * 2 Jul 2015
 * Description - Spring boot start up class.
 */
@Configuration
@ComponentScan(basePackages="com.samvo.statistics")
@EnableAutoConfiguration
@EnableWebMvc
@EnableScheduling
@ImportResource("classpath:client-context.xml")
public class Application {

	/**
	 * Main application entry point.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);		
	}
}
