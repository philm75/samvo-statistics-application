package com.samvo.statistics.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.samvo.statistics.generator.IRStatisticsGenerator;
import com.samvo.statistics.generator.TMStatisticsGenerator;
import com.samvo.statistics.model.match.FeedTypes;
import com.samvo.statistics.service.ExportService;
import com.samvo.statistics.service.StatisticsService;

/**
 * @author Phil
 * 2 Jul 2015
 * Description - General application controller for web services.
 */
@RestController
public class ApplicationController {
	
	/**
	 * Export business service component.
	 */
	@Autowired
	private transient ExportService exportService;
	
	/**
	 * Statistics business service component.
	 */
	@Autowired
	private transient StatisticsService statisticsService;
	
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
	 * Excel content type.
	 */
	private static final String DOWNLOAD_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	
	/**
	 * Content disposition.
	 */
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    
    /**
     * Attachment.
     */
    private static final String ATTACHMENT = "attachment; filename=%s";
    
    /**
     * Export file name.
     */
    private static final String EXPORT_FILE_NAME = "export.xlsx";    
    
	@RequestMapping("/")
	public String welcome() {
		return "Welcome to the Samvo statistics application!";
	}
	
	/**
	 * Export to generate the spreadsheet with match statistics.
	 * 
	 * @param response
	 */
	@RequestMapping(value="/matches/export", method=RequestMethod.GET)
	public void export(HttpServletResponse response) {
        response.setContentType(DOWNLOAD_CONTENT_TYPE);
        response.setHeader(CONTENT_DISPOSITION, String.format(ATTACHMENT, EXPORT_FILE_NAME));
		try {
			exportService.create(FeedTypes.IN_RUNNING.getTypeId(), response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Halt the in-running match process.
	 */
	@RequestMapping(value="/matches/inRunning/stop", method=RequestMethod.GET)
	public void stopInRunning() {
		statisticsService.interruptInRunningMarket();
	}
	
	/**
	 * Start in-running match statistics generation process.
	 */
	@RequestMapping(value="/matches/inRunning/start", method=RequestMethod.DELETE)	
	public void startInRunningStatistics() {
		irStatisticsGenerator.runProcess();
	}
	
	/**
	 * Start today match statistics generation process.
	 */
	@RequestMapping(value="/matches/today/start", method=RequestMethod.GET)	
	public void startTodayStatistics() {
		tmStatisticsGenerator.runProcess();
	}	
}
