package com.samvo.statistics.dao;

import java.util.List;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.samvo.statistics.model.match.Bookie;

/**
 * @author Phil
 * 22 Jun 2015
 * Description - Unit tests for Bookie data access component.
 */
public class BookieDaoTest extends BaseDaoTest {

	@Autowired
	private BookieDao bookieDao;
	
	@Test
	public void testReadAll() {
		List<Bookie> bookies = bookieDao.readAll();
		assertEquals(bookies.size(), 4);
	}
	
	@Test
	public void testCreateBookie() {
		String name = "testBookie";
		Bookie bookie = bookieDao.createBookie(name);
		assertEquals(bookie.getName().trim(), name);
		assert(bookie.getId() > 0);
	}
	
	@Test
	public void testReadBookieById() {
		assertEquals("ibc998", bookieDao.readById(1));
	}
}
