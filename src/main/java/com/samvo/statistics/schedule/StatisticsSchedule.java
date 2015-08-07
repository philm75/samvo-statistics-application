package com.samvo.statistics.schedule;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.samvo.statistics.generator.IRStatisticsGenerator;
import com.samvo.statistics.generator.TMStatisticsGenerator;

/**
 * @author Phil
 * 5 Jul 2015
 * Description - Samvo statistics scheduler.
 * 
 * Uses crontab settings to define a start time for the processes:
 * 
 * field         allowed values
 * -----         --------------
 * seconds       0-59
 * minutes       0-59
 * hours         0-23
 * day of month  1-31
 * month         1-12 
 * day of week   0-7 (0 or 7 is Sun)
 */
@Component("statisticsSchedule")
public class StatisticsSchedule {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = Logger.getLogger(StatisticsSchedule.class);
	
	/**
	 * In-running statistics generator.
	 */
	@Autowired
	private IRStatisticsGenerator irStatisticsGenerator;

	/**
	 * Today market statistics generator.
	 */
	@Autowired
	private TMStatisticsGenerator tmStatisticsGenerator;
	
	/**
	 * Run the in-running statistics generator process.
	 */
	@Scheduled(cron="*/60 * * * * *")
	public void createIrStats() {
		LOGGER.info("IR Process started....");	
		System.out.println("IR Process started....");
		try {
			irStatisticsGenerator.runProcess();			
		} catch (Exception e) {
			LOGGER.info("IR Process exception - " + e.getMessage());
		}
		System.out.println("IR Process finished....");
		LOGGER.info("IR Process finished....");
	}

	/**
	 * Run the today market statistics generator process.
	 */
	@Scheduled(cron="0 0/5 * * * ?")
	public void createTodayStats() {
		System.out.println("TM Process started....");
		LOGGER.info("TM Process started....");
		try {
			tmStatisticsGenerator.runProcess();			
		} catch (Exception e) {
			LOGGER.info("TM Process exception - " + e.getMessage());
		}
		LOGGER.info("TM Process finished....");
		System.out.println("TM Process finished....");
	}
}
