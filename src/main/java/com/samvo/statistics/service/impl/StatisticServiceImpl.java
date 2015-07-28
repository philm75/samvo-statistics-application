package com.samvo.statistics.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.samvo.statistics.dao.BookieDao;
import com.samvo.statistics.dao.MatchDao;
import com.samvo.statistics.dao.MatchTimeDao;
import com.samvo.statistics.generator.IRStatisticsGenerator;
import com.samvo.statistics.model.match.Bookie;
import com.samvo.statistics.model.match.Match;
import com.samvo.statistics.model.match.MatchSummary;
import com.samvo.statistics.service.StatisticsService;

/**
 * @author Phil Merrilees
 * 14 Jun 2015
 * Description - Statistics business service default implementation. 
 */
@Transactional
@Service
public class StatisticServiceImpl implements StatisticsService {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = Logger.getLogger(StatisticServiceImpl.class);
	
	/**
	 * Match data access component.
	 */
	@Autowired
	private transient MatchDao matchDao;
	
	/**
	 * Match time data access component.
	 */
	@Autowired
	private transient MatchTimeDao matchTimeDao;
	
	/**
	 * Bookie data access component.
	 */
	@Autowired 
	private transient BookieDao bookieDao;
	
	/**
	 * In-running statistics generator.
	 */
	@Autowired
	private transient IRStatisticsGenerator irStatisticsGenerator;
		
	@Override
	public void createMatch(Match match) {
		if (LOGGER.isDebugEnabled()) {LOGGER.debug(String.format("createMatch(match=%s", match.toString()));}
		matchDao.createMatch(match);
	}

	@Override
	public void createMatches(List<Match> match) {
		if (LOGGER.isDebugEnabled()) {LOGGER.debug(String.format("createMatches(match=%s", match.toString()));}
		matchDao.createMatches(match);
	}

	@Transactional(readOnly=true)
	@Override
	public List<MatchSummary> getAllSummary(Integer feedTypeId) {
		if (LOGGER.isDebugEnabled()) {LOGGER.debug(String.format("getAllSummary(feedTypeId=%s", feedTypeId.toString()));}
		return matchDao.readAllSummary(feedTypeId);
	}

	@Override
	public void updateFirstGoalScore(Integer matchId, Integer timeGoalScored, Integer feedTypeId, Integer bookieId) {
		if (LOGGER.isDebugEnabled()) {LOGGER.info(String.format("updateFirstGoalScore(matchId=%s,timeGoalScored=%s,feedTypeId=%s", matchId.toString(), timeGoalScored.toString(), feedTypeId.toString(), bookieId.toString()));}
		matchDao.updateFirstGoalScore(matchId, timeGoalScored, feedTypeId, bookieId);
	}

	@Override
	public void updateKo(Integer matchId, Double homePrice, Double drawPrice, Double awayPrice, Double htUndefHgPrice, Integer feedTypeId, Integer bookieId) {
		if (LOGGER.isDebugEnabled()) {LOGGER.debug(String.format("updateKo(matchId=%s,feedTypeId=%s,indicator=%s", matchId.toString(), feedTypeId.toString(), String.valueOf(bookieId)));}
		matchDao.updateKo(matchId, homePrice, drawPrice, awayPrice, htUndefHgPrice, feedTypeId, bookieId);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Match> getAllByFeedType(Integer feedTypeId) {
		if (LOGGER.isDebugEnabled()) {LOGGER.debug(String.format("getAllByFeedType(feedTypeId=%s", feedTypeId.toString()));}
		return matchDao.readMatchByFeedType(feedTypeId);
	}

	@Transactional(readOnly=true)
	@Override
	public Map<String, Integer> getBookies() {
		if (LOGGER.isDebugEnabled()) {LOGGER.debug(String.format("getBookies()"));}
		Map<String, Integer> map = new HashMap<String, Integer>();
		List<Bookie> bookies = bookieDao.readAll();
		Iterator<Bookie> iterator = bookies.iterator();
		Bookie bookie = null;
		while (iterator.hasNext()) {
			bookie = iterator.next();
			map.put(bookie.getName(), bookie.getId());
		}
		return map;
	}

	@Transactional(readOnly=true)
	@Override
	public Map<String, Match> getMatchesByIndicator(Integer feedTypeId) {
		if (LOGGER.isDebugEnabled()) {LOGGER.debug(String.format("getMatchesByIndicator(feedTypeId=%s)", String.valueOf(feedTypeId)));}
		return matchDao.readMatchesByIndicator(feedTypeId);
	}

	@Override
	public void updateMatchMinute(Integer matchId, Integer bookieId, Integer feedTypeId, Integer minute, Double price) {
		matchTimeDao.updateMatchTime(matchId, feedTypeId, bookieId, minute, price);
	}

	@Override
	public void interruptInRunningMarket() {
		irStatisticsGenerator.stopProcess();
	}

	@Override
	public void updateInRunningIndicator(Integer matchId, Integer feedTypeId, Integer indicator, Integer bookieId) {
		if (LOGGER.isDebugEnabled()) {LOGGER.debug(String.format("updateInRunningIndicator(matchId=%s,feedTypeId=%s,indicator=%s,bookieId=%s)", String.valueOf(matchId), String.valueOf(feedTypeId), String.valueOf(indicator), String.valueOf(bookieId)));}
		matchDao.updateInRunningIndicator(matchId, feedTypeId, indicator, bookieId);
	}
}
