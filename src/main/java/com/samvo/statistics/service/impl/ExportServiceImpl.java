package com.samvo.statistics.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samvo.statistics.model.match.MatchSummary;
import com.samvo.statistics.model.match.MatchTime;
import com.samvo.statistics.service.ExportService;
import com.samvo.statistics.service.StatisticsService;

/**
 * @author Phil Merrilees
 * 11 Jun 2015
 * Description - 
 */
@Service
public class ExportServiceImpl implements ExportService {

	/**
	 * Statistics business service component.
	 */
	@Autowired
	private transient StatisticsService statisticsService;
	
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = Logger.getLogger(ExportServiceImpl.class);
	
	/**
	 * Minute heading.
	 */
	private static final String MINUTE_HEADING = "Minute %s";
	
	public void create(Integer feedTypeId, OutputStream outputStream) {
		System.out.println(String.format("create(feedTypeId=%s,outputStream=%s)", String.valueOf(feedTypeId), String.valueOf(outputStream)));
		if (LOGGER.isDebugEnabled()) {LOGGER.debug(String.format("create(feedTypeId=%s,outputStream=%s)", String.valueOf(feedTypeId), String.valueOf(outputStream)));}
		try {
			XSSFWorkbook workBook = new XSSFWorkbook();		
			List<MatchSummary> matches = statisticsService.getAllSummary(feedTypeId);
			XSSFSheet sheet = workBook.createSheet("Ops Feed");
			createHeading(sheet);
			for (MatchSummary summary : matches) {
				createDetail(sheet, sheet.getLastRowNum()+1, summary);
			}		
			workBook.write(outputStream);
			outputStream.flush();
			outputStream.close();	
			workBook.close();			
		} catch (IOException e) {
			/// Do nothing exception should not be thrown...
		}
	}
	
	private void createHeading(XSSFSheet sheet) {
		XSSFRow row = sheet.createRow(0);		
		int columnIndex = 0;
		createCell(columnIndex,   row, "Date");  				
		createCell(++columnIndex, row, "Kick off time");
		createCell(++columnIndex, row, "Home Team");
		createCell(++columnIndex, row, "Away Team");
		createCell(++columnIndex, row, "Time 1st Goal");		
		createCell(++columnIndex, row, "Kick off Home price");
		createCell(++columnIndex, row, "Kick off Away price");
		createCell(++columnIndex, row, "Kick off Draw price");
		createCell(++columnIndex, row, "O/U 0.5 price");
		createCell(++columnIndex, row, "O/U Handicap");
		
		int minutes = 1;
		while (minutes < 56) {
			createCell(++columnIndex, row, String.format(MINUTE_HEADING, String.valueOf(minutes)));
			minutes++;
		}
	}
	
	private void createDetail(XSSFSheet sheet, int rowCount, MatchSummary matchSummary) {
		XSSFRow row = sheet.createRow(rowCount);
		int columnIndex = 0;
		createCell(columnIndex  , row, toString(matchSummary.getGameDate()));
		createCell(++columnIndex, row, matchSummary.getKickOffTime());
		createCell(++columnIndex, row, matchSummary.getHomeTeam());
		createCell(++columnIndex, row, matchSummary.getAwayTeam());
		
		if (matchSummary.getTimeFirstGoal() == null || matchSummary.getTimeFirstGoal().intValue() == -1) {
			createCell(++columnIndex, row, StringUtils.EMPTY);
		} else {
			createCell(++columnIndex, row, matchSummary.getTimeFirstGoal());			
		}

		createCell(++columnIndex, row, matchSummary.getKickOffHomePrice());
		createCell(++columnIndex, row, matchSummary.getKickOffAwayPrice());
		createCell(++columnIndex, row, matchSummary.getKickOffDrawPrice());
		createCell(++columnIndex, row, matchSummary.getKickOffOuHalfAGoal());
		
		if (matchSummary.getHandicapValue() == null || matchSummary.getHandicapValue().intValue() == 0.0) {
			createCell(++columnIndex, row, StringUtils.EMPTY);
		} else {
			createCell(++columnIndex, row, matchSummary.getHandicapValue());			
		}
		
		Iterator<MatchTime> iterator = matchSummary.getMatchMinutes().values().iterator();
		MatchTime matchTime = null;
		while (iterator.hasNext()) {
			matchTime = iterator.next();
			if (matchTime.getHtUnderHgPrice().doubleValue() > 0.0) {
				createCell(++columnIndex, row, matchTime.getHtUnderHgPrice().doubleValue());
			} else {
				createCell(++columnIndex, row, StringUtils.EMPTY);				
			}
		}
	}
	
	private void createCell(int columnIndex, XSSFRow row, String cellValue) {
		XSSFCell cell = row.createCell(columnIndex);
		cell.setCellValue(cellValue);		
	}	
	
	private void createCell(int columnIndex, XSSFRow row, double cellValue) {
		XSSFCell cell = row.createCell(columnIndex);
		cell.setCellValue(cellValue);		
	}

	/**
	 * Convert date to date string.
	 * 
	 * @param value
	 * @return String
	 */
	private String toString(DateTime value) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");
	    return fmt.print(value);
	}
}
