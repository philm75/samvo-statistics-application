package com.samvo.statistics.dao;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * @author Phil
 * 22 Jun 2015
 * Description -
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-application-context.xml"})
@TransactionConfiguration(transactionManager="transactionManager")
abstract class BaseDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

}
