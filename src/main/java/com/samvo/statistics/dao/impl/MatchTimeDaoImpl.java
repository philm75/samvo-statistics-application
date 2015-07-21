package com.samvo.statistics.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.samvo.statistics.dao.MatchTimeDao;
import com.samvo.statistics.model.match.MatchTime;

/**
 * @author Phil Merrilees
 * 20 Jun 2015
 * Description - Match time  Data access component.
 */
@Repository("matchTimeDao")
public class MatchTimeDaoImpl implements MatchTimeDao {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = Logger.getLogger(MatchTimeDaoImpl.class);
	
	/**
	 * SQL statements.
	 */
	private static final String INSERT_MATCH_MINUTE_SQL = "INSERT INTO OPSFEED.MATCH_TIME_MINUTES (" +
														  "	MATCH_ID, " +
														  " FEED_TYPE_ID, " +
														  " BOOKIE_ID, " +
														  " MINUTE, " +
														  " HOME_PRICE, " +
														  " AWAY_PRICE, " +
														  " DRAW_PRICE, " +
														  " HT_UNDER_HG_PRICE " +		
														  " ) VALUES (" +
														  " :matchId, " +
														  " :feedTypeId, " +
														  " :bookieId, " +
														  " :minute, " +
														  " 0, " +
														  " 0, " +
														  " 0, " +
														  " 0 " +
														  ");";
				
	private static final String READ_MATCH_TIME_MINUTES_SQL = "SELECT MATCH_ID, " +
															  "       FEED_TYPE_ID, " + 
															  "       BOOKIE_ID, " +
	                                                          "       MINUTE, " +
	                                                          "       HOME_PRICE, " +
	                                                          "       AWAY_PRICE, " +
	                                                          "       DRAW_PRICE, " +
	                                                          "       HT_UNDER_HG_PRICE " + 
															  "FROM   OPSFEED.MATCH_TIME_MINUTES "  +
															  "WHERE  MATCH_ID = :matchId " +
															  "AND    FEED_TYPE_ID = :feedTypeId ";

	private static final String UPDATE_MATCH_TIME_MINUTES_SQL = "UPDATE OPSFEED.MATCH_TIME_MINUTES " +
																"SET    HT_UNDER_HG_PRICE = :price " +
																"WHERE  MATCH_ID = :matchId " +
																"AND    FEED_TYPE_ID = :feedTypeId " +
																"AND    BOOKIE_ID = :bookieId " +
																"AND    MINUTE = :minute ";
	
	/**
	 * JDBC Template.
	 */
    private NamedParameterJdbcTemplate jdbcTemplate;
    
    /**
     * Row mapper.
     */
    private MatchTimeRowMapper matchRowMapper = new MatchTimeRowMapper();
    
    /**
     * Number of match minutes.
     */
    private static final int NUMBER_OF_MATCH_MINUTES = 56;
    
    /**
     * 
     * @param dataSource
     */
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }	
       
	@Override
	public void createMatchTime(Integer matchId, Integer feedTypeId, Integer bookieId) {
		if (LOGGER.isDebugEnabled()) {LOGGER.debug(String.format("createMatchTime(matchId=%s,feedTypeId=%s,bookieId=%s)", String.valueOf(matchId), String.valueOf(feedTypeId), String.valueOf(bookieId)));}		
		int minuteCounter = 1;
		while (minuteCounter < NUMBER_OF_MATCH_MINUTES) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("matchId", matchId);
			paramMap.put("feedTypeId", feedTypeId);
			paramMap.put("bookieId", bookieId);
			paramMap.put("minute", minuteCounter);			
			jdbcTemplate.update(INSERT_MATCH_MINUTE_SQL, paramMap);
			minuteCounter++;
		}		
	}

	@Override
	public List<MatchTime> readMatchTimeByMatchId(Integer matchId, Integer feedTypeId) {
		if (LOGGER.isDebugEnabled()) {LOGGER.debug(String.format("readMatchTimeByMatchId(matchId=%s,feedTypeId=%s)", String.valueOf(matchId), String.valueOf(feedTypeId)));}		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("matchId", matchId);
		paramMap.put("feedTypeId", feedTypeId);
		return jdbcTemplate.query(READ_MATCH_TIME_MINUTES_SQL, paramMap, matchRowMapper);
	}
	

	@Override
	public void updateMatchTime(Integer matchId, Integer feedTypeId, Integer bookieId, Integer minute, Double price) {
		if (LOGGER.isDebugEnabled()) {LOGGER.debug(String.format("updateMatchTime(matchId=%s,feedTypeId=%s,bookieId=%s,minute=%s,price=%s)", String.valueOf(matchId), String.valueOf(feedTypeId), String.valueOf(bookieId), String.valueOf(minute), String.valueOf(price)));}		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("matchId", matchId);
		paramMap.put("bookieId", bookieId);
		paramMap.put("feedTypeId", feedTypeId);
		paramMap.put("minute", minute);
		paramMap.put("price", price);
		jdbcTemplate.update(UPDATE_MATCH_TIME_MINUTES_SQL, paramMap);
	}	
	
	private class MatchTimeRowMapper implements RowMapper<MatchTime> {

		@Override
		public MatchTime mapRow(ResultSet rs, int rowNum) throws SQLException {
			MatchTime matchTime = new MatchTime();
			matchTime.setAwayPrice(rs.getDouble("AWAY_PRICE"));
			matchTime.setBookieId(rs.getInt("BOOKIE_ID"));
			matchTime.setDrawPrice(rs.getDouble("DRAW_PRICE"));
			matchTime.setHomePrice(rs.getDouble("HOME_PRICE"));
			matchTime.setHtUnderHgPrice(rs.getDouble("HT_UNDER_HG_PRICE"));
			matchTime.setMatchId(rs.getInt("MATCH_ID"));
			matchTime.setMinute(rs.getInt("MINUTE"));
			matchTime.setFeedTypeId(rs.getInt("FEED_TYPE_ID"));
			return matchTime;
		}
	}
}
