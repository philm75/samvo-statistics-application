package com.samvo.statistics.configuration;

import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Phil Merrilees
 * 29 Jul 2015
 * Description - Data source configuration.
 *
 */
@Configuration
public class DatasourceConfiguration {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = Logger.getLogger(DatasourceConfiguration.class);
	
	/**
	 * Local DB for local dev environment.
	 */
	private static final String LOCAL_URL = "jdbc:postgresql://localhost:5432/opsfeed";
	
	/**
	 * User name.
	 */
	private static final String USERNAME = "postgres";
	
	/**
	 * Password.
	 */
	private static final String PASSWORD = "password";
	
	@Bean
	public DataSource dataSource()  {    
		URI dbUri;
		try {
			String username = StringUtils.EMPTY;
			String password = StringUtils.EMPTY;
			String url      = StringUtils.EMPTY;
			String dbProperty = System.getProperty("database.url");
			System.out.println("dbProperty=" + dbProperty);
			if (StringUtils.isNotBlank(dbProperty)) {
				dbUri = new URI(dbProperty);
				
				username = dbUri.getUserInfo().split(":")[0];
				password = dbUri.getUserInfo().split(":")[1];
				
				StringBuilder urlBuilder = new StringBuilder("jdbc:postgresql://");
				urlBuilder.append(dbUri.getHost());
				urlBuilder.append(":");
				urlBuilder.append(dbUri.getPort());
				urlBuilder.append(dbUri.getPath());
				url = urlBuilder.toString();
			} else {
				url = LOCAL_URL;
				username = USERNAME;
				password = PASSWORD;
			}

			if (LOGGER.isInfoEnabled()) {LOGGER.info("url=" + url);}
			if (LOGGER.isInfoEnabled()) {LOGGER.info("username=" + username);}
			if (LOGGER.isInfoEnabled()) {LOGGER.info("password=" + password);}

			System.out.println("url=" + url);
			System.out.println("username=" + username);
			System.out.println("password=" + password);
			
	        BasicDataSource basicDataSource = new BasicDataSource();
	        basicDataSource.setUrl(url);
	        basicDataSource.setUsername(username);
	        basicDataSource.setPassword(password);
	        return basicDataSource;
		} catch (URISyntaxException e) {
			return null;
	    }
	}
}
