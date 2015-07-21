package com.samvo.statistics.service;

import java.io.OutputStream;

/**
 * @author Phil
 * 11 Jun 2015
 * Description - Export business service component. 
 */
public interface ExportService {
	
	/**
	 * Create export.
	 * 
	 * @param feedTypeId
	 * @param outputStream
	 */
	void create(Integer feedTypeId, OutputStream outputStream);
}
