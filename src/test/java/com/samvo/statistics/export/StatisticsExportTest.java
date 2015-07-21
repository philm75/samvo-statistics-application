package com.samvo.statistics.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.samvo.statistics.model.match.FeedTypes;
import com.samvo.statistics.service.ExportService;

/**
 * @author Phil
 * 17 Jun 2015
 * Description - Statistics Export unit test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-application-context.xml" })
public class StatisticsExportTest {

	@Autowired
	private ExportService exportService;
	
	@Before
	public void setup() {
		File file = new File("D://test.xlsx");
		file.delete();
	}
	
	@Test
	public void testExport() throws IOException {
		String fileName = "D://test.xlsx";
		OutputStream outputStream = new FileOutputStream(fileName);
		exportService.create(FeedTypes.IN_RUNNING.getTypeId(), outputStream);
	}
}
