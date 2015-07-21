package com.samvo.statistics.dao;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;

import com.samvo.statistics.model.match.FeedType;

/**
 * @author Phil
 * 22 Jun 2015
 * Description -
 */
public class FeedTypeDaoTest extends BaseDaoTest {

	@Autowired
	private FeedTypeDao feedTypeDao;
	
	@Test
	public void testReadAll() {
		List<FeedType> types = feedTypeDao.readAll();
		assertEquals(types.size(), 3);
	}
}
