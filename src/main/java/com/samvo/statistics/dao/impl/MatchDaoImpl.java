package com.samvo.statistics.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.samvo.statistics.dao.MatchDao;
import com.samvo.statistics.dao.MatchTimeDao;
import com.samvo.statistics.model.match.Match;
import com.samvo.statistics.model.match.MatchSummary;
import com.samvo.statistics.model.match.MatchTime;

/**
 * @author Phil Merrilees
 * 14 Jun 2015
 * Description -
 */
@Repository("matchDao")
public class MatchDaoImpl implements MatchDao {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = Logger.getLogger(MatchDaoImpl.class);
	
	/**
	 * Match time data access component.
	 */
	@Autowired
	private transient MatchTimeDao matchTimeDao;
	
	/**
	 * Check if match exists.
	 */
	private static final String READ_EXISTS_SQL = "SELECT MATCH_ID " +
									   		      "FROM   OPSFEED.MATCH " +
			                                      "WHERE  MATCH_ID = :matchId " +
											      "AND    FEED_TYPE_ID = :feedTypeId " +
			                                      "AND    BOOKIE_ID = :bookieId";
	
	/**
	 * SQL statements.
	 */
    private static final String INSERT_SQL = "INSERT INTO OPSFEED.MATCH (" +
    										 "	MATCH_ID, " +
    										 "  LEAGUE_NAME, " +
    										 "  HOME_TEAM_NAME, " +
    										 "  AWAY_TEAM_NAME, " +
    										 "  MATCH_TIME, " +
    										 "  RUNNING_INDICATOR, " +
    										 "  HOME_SCORE, " +
    										 "  AWAY_SCORE, " +
    										 "  TIME_GAME_LIVE, " +
    										 "  LEAGUE_ID, " +
    										 "  HOME_TEAM_ID, " +
    										 "  AWAY_TEAM_ID, " +
    										 "  FEED_TYPE_ID, " +
    										 "  TIME_FIRST_GOAL, " +	
    										 "  KICK_OFF_HOME_PRICE, " +
    										 "  KICK_OFF_AWAY_PRICE, " +
    										 "  KICK_OFF_DRAW_PRICE, " +
    										 "  KICK_OFF_OU_HF_PRICE, " +
    										 "  MATCH_DATE, " +
    										 "  BOOKIE_ID " +
    										 ") VALUES (" +
    										 "  :matchId, " +
    										 "  :leagueName, " + 
    										 "  :homeTeamName, " + 
    										 "  :awayTeamName, " +
    										 "  :matchTime, " +
    										 "  :runningInd, " +
    										 "  :homeScore, " +
    										 "  :awayScore, " +
    										 "  :timeGameLive, " +
    										 "  :leagueId, " +
    										 "  :homeTeamId, " +
    										 "  :awayTeamId, " +
    										 "  :feedTypeId, " +
    										 "  :timeFirstGoal, " +	
    										 "  :kickOffHomePrice, " +
    										 "  :kickOffAwayPrice, " +
    										 "  :kickOffDrawPrice, " +
    										 "  :kickOffOuHfPrice, " +
    										 "  :matchDate," +
    										 "  :bookieId" +
    		                                 ");";
    
    private static final String READ_ALL_SQL = "SELECT " + 
			 								   " MATCH_ID, " +
			 								   " LEAGUE_NAME, " +
			 								   " HOME_TEAM_NAME, " +
			 								   " AWAY_TEAM_NAME, " +
			 								   " MATCH_TIME, " +
			 								   " RUNNING_INDICATOR, " +
			 								   " HOME_SCORE, " +
			 								   " AWAY_SCORE, " +
			 								   " TIME_GAME_LIVE, " +
			 								   " LEAGUE_ID, " +
			 								   " HOME_TEAM_ID, " +
			 								   " AWAY_TEAM_ID, " +
			 								   " FEED_TYPE_ID, " +
			 								   " TIME_FIRST_GOAL, " +	
			 								   " KICK_OFF_HOME_PRICE, " +
			 								   " KICK_OFF_AWAY_PRICE, " +
			 								   " KICK_OFF_DRAW_PRICE, " +
			 								   " KICK_OFF_OU_HF_PRICE, " +
			 								   " MATCH_DATE, " +
			 								   " BOOKIE_ID " +
    										   "FROM OPSFEED.MATCH";
    
    private static final String READ_BY_FEED_TYPE_SQL = "SELECT " + 
													   " MATCH_ID, " +
													   " LEAGUE_NAME, " +
													   " HOME_TEAM_NAME, " +
													   " AWAY_TEAM_NAME, " +
													   " MATCH_TIME, " +
													   " RUNNING_INDICATOR, " +
													   " HOME_SCORE, " +
													   " AWAY_SCORE, " +
													   " TIME_GAME_LIVE, " +
													   " LEAGUE_ID, " +
													   " HOME_TEAM_ID, " +
													   " AWAY_TEAM_ID, " +
													   " FEED_TYPE_ID, " +
													   " TIME_FIRST_GOAL, " +	
													   " KICK_OFF_HOME_PRICE, " +
													   " KICK_OFF_AWAY_PRICE, " +
													   " KICK_OFF_DRAW_PRICE, " +
													   " KICK_OFF_OU_HF_PRICE, " +
													   " MATCH_DATE, " +
					 								   " BOOKIE_ID " +													   
													   "FROM OPSFEED.MATCH " +
													   "WHERE FEED_TYPE_ID = :feedTypeId";
    
    private static final String READ_BY_INDICATOR_SQL = "SELECT " + 
    											   		" M.MATCH_ID, " +
    											   		" M.LEAGUE_NAME, " +
    											   		" M.HOME_TEAM_NAME, " +
    											   		" M.AWAY_TEAM_NAME, " +
    											   		" M.MATCH_TIME, " +
    											   		" M.RUNNING_INDICATOR, " +
    											   		" M.HOME_SCORE, " +
    											   		" M.AWAY_SCORE, " +
    											   		" M.TIME_GAME_LIVE, " +
    											   		" M.LEAGUE_ID, " +
    											   		" M.HOME_TEAM_ID, " +
    											   		" M.AWAY_TEAM_ID, " +
    											   		" M.FEED_TYPE_ID, " +
    											   		" M.TIME_FIRST_GOAL, " +	
    											   		" M.KICK_OFF_HOME_PRICE, " +
    											   		" M.KICK_OFF_AWAY_PRICE, " +
    											   		" M.KICK_OFF_DRAW_PRICE, " +
    											   		" M.KICK_OFF_OU_HF_PRICE, " +
    											   		" M.BOOKIE_ID, " +
    											   		" M.MATCH_DATE, " +
    											   		" B.NAME " +
    											   		"FROM OPSFEED.MATCH M, OPSFEED.BOOKIE B " +
    											   		"WHERE M.FEED_TYPE_ID = :feedTypeId " +
    											   		"AND   M.BOOKIE_ID = B.BOOKIE_ID ";
    
    private static final String READ_MATCH_SUMMARY_BY_FEED_TYPE_SQL = "SELECT M.MATCH_ID," +
                                                                      "       M.FEED_TYPE_ID," +    		
                                                                      "       M.MATCH_TIME," +
    																  "       M.HOME_TEAM_NAME," +
    																  "       M.AWAY_TEAM_NAME," + 
    																  "       M.TIME_FIRST_GOAL," + 
    																  "       M.KICK_OFF_HOME_PRICE," + 
    																  "       M.KICK_OFF_AWAY_PRICE," +
    																  "       M.KICK_OFF_DRAW_PRICE," +
    																  "       M.KICK_OFF_OU_HF_PRICE, " +
    																  "       B.BOOKIE_ID, " +
    																  "       B.NAME, " +
    																  "       MT.MINUTE, " +
    																  "       MT.HT_UNDER_HG_PRICE " +
    																  "FROM   OPSFEED.MATCH M, " +
    																  "       OPSFEED.MATCH_TIME_MINUTES MT, " +
    																  "       OPSFEED.BOOKIE B " +
    																  "WHERE  M.MATCH_ID = MT.MATCH_ID " +
    																  "AND    M.BOOKIE_ID = MT.BOOKIE_ID " +
    																  "AND    M.FEED_TYPE_ID = MT.FEED_TYPE_ID " +
    																  "AND    M.BOOKIE_ID = B.BOOKIE_ID " +
    																  "AND    M.FEED_TYPE_ID = :feedTypeId " +
    																  "AND    M.KICK_OFF_OU_HF_PRICE > 0 " +
    																  "ORDER BY M.MATCH_TIME, M.MATCH_ID, MT.MINUTE, M.BOOKIE_ID";
    																  
    private static final String UPDATE_FIRST_GOAL_SCORED_SQL = "UPDATE OPSFEED.MATCH " +
    														   "SET    TIME_FIRST_GOAL = :timeFirstGoal " +
    														   "WHERE  MATCH_ID        = :matchId       " +
    														   "AND    FEED_TYPE_ID    = :feedTypeId    " +
    														   "AND    BOOKIE_ID       = :bookieId      ";              

    private static final String UPDATE_KO_PRICES_SQL = "UPDATE OPSFEED.MATCH " +
    												   "SET    KICK_OFF_HOME_PRICE  = :homePrice, " + 
    												   "       KICK_OFF_AWAY_PRICE  = :awayPrice, " +
    												   "       KICK_OFF_DRAW_PRICE  = :drawPrice, " +
    												   "       KICK_OFF_OU_HF_PRICE = :ouHfPrice  " +
    												   "WHERE  MATCH_ID             = :matchId    " +
													   "AND    FEED_TYPE_ID         = :feedTypeId " +
    												   "AND    BOOKIE_ID            = :bookieId   ";

    private static final String UPDATE_INDICATOR_SQL = "UPDATE OPSFEED.MATCH " +
			   										   "SET    RUNNING_INDICATOR = :indicator  " +
			   										   "WHERE  MATCH_ID          = :matchId    " +
			   										   "AND    FEED_TYPE_ID      = :feedTypeId " +
			   										   "AND    BOOKIE_ID         = :bookieId   ";

	/**
	 * JDBC Template.
	 */
    private NamedParameterJdbcTemplate jdbcTemplate;
    
    /**
     * Match row mapper.
     */
    private MatchRowMapper matchRowMapper = new MatchRowMapper();
    
    /**
     * Match result set extractor.
     */
    private MatchResultSetExtractor matchResultSetExtractor = new MatchResultSetExtractor();
    
    /**
     * Match summary result set extractor.
     */
    private MatchSummaryResultSetExtractor matchSummaryResultSetExtractor = new MatchSummaryResultSetExtractor();
        
    /**
     * 
     * @param dataSource
     */
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    
	@Override
	public void createMatch(Match match) {
		if (LOGGER.isDebugEnabled()) {LOGGER.debug(String.format("createMatch(match=%s)", match.toString()));}
		
		if (!exists(match)) {
			try {
				/**
				 * Create match.
				 */
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("matchId"      	, match.getMatchId());
				paramMap.put("leagueName"   	, match.getLeagueName());
				paramMap.put("homeTeamName" 	, match.getHomeTeamName());
				paramMap.put("awayTeamName" 	, match.getAwayTeamName());
				paramMap.put("matchTime"    	, match.getMatchTime());
				paramMap.put("runningInd"   	, match.getRunningIndicator());
				paramMap.put("homeScore"    	, match.getHomeScore());
				paramMap.put("awayScore"    	, match.getAwayScore());
				paramMap.put("timeGameLive" 	, match.getTimeGameLive());
				paramMap.put("leagueId"     	, match.getLeagueId());
				paramMap.put("homeTeamId"   	, match.getHomeTeamId());
				paramMap.put("awayTeamId"       , match.getAwayTeamId());
				paramMap.put("feedTypeId"       , match.getFeedTypeId());
				paramMap.put("timeFirstGoal"    , match.getTimeFirstGoal());
				paramMap.put("kickOffHomePrice" , match.getKoHomePrice());
				paramMap.put("kickOffAwayPrice" , match.getKoAwayPrice());
				paramMap.put("kickOffOuHfPrice" , match.getKoOuHfPrice());	
				paramMap.put("kickOffDrawPrice" , match.getKoDrawPrice());
				paramMap.put("matchDate"        , new java.sql.Date(match.getMatchDate().getMillis())); 
				paramMap.put("bookieId"         , match.getBookieId());
				jdbcTemplate.update(INSERT_SQL, paramMap);
				
				/**
				 * Create match minutes for each bookie with odds on the match.
				 */
				matchTimeDao.createMatchTime(match.getMatchId(), match.getFeedTypeId(), match.getBookieId());				
			} catch (Exception e) {
				LOGGER.info("Exception=" + e.getMessage());
			}			
		} else {
			/**
			 * Update KO prices.
			 */
			updateKo(match.getMatchId(), 
					 match.getKoHomePrice(), 
					 match.getKoDrawPrice(), 
					 match.getKoAwayPrice(), 
					 match.getKoOuHfPrice(), 
					 match.getFeedTypeId(), 
					 match.getBookieId());
			
			LOGGER.info("Match exists already matchId=" + String.valueOf(match.getMatchId()) + ",bookieId=" + String.valueOf(match.getBookieId()) + ",feedType=" + String.valueOf(match.getFeedTypeId()));
		}
		
	}

	@Override
	public void createMatches(List<Match> matches) {	
		if (LOGGER.isDebugEnabled()) {LOGGER.debug(String.format("createMatches(matches=%s)", matches.toString()));}
		for (Match match : matches) {
			createMatch(match);
		}
	}

	@Override
	public List<Match> readAll() {
		if (LOGGER.isDebugEnabled()) {LOGGER.debug("readAll()");}
		return jdbcTemplate.query(READ_ALL_SQL, matchRowMapper);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MatchSummary> readAllSummary(Integer feedTypeId) {
		if (LOGGER.isDebugEnabled()) {LOGGER.debug(String.format("readAllSummary(feedTypeId=%s)", String.valueOf(feedTypeId)));}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("feedTypeId", feedTypeId);
		return (List<MatchSummary>)jdbcTemplate.query(READ_MATCH_SUMMARY_BY_FEED_TYPE_SQL, paramMap, matchSummaryResultSetExtractor);
	}	
	
	@Override
	public List<Match> readMatchByFeedType(Integer feedTypeId) {
		if (LOGGER.isDebugEnabled()) {LOGGER.debug(String.format("readMatchByFeedType(feedTypeId=%s)", String.valueOf(feedTypeId)));}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("feedTypeId", feedTypeId);
		return jdbcTemplate.query(READ_BY_FEED_TYPE_SQL, paramMap, matchRowMapper);
	}

	@Override
	public void updateFirstGoalScore(Integer matchId, Integer timeGoalScored, Integer feedTypeId, Integer bookieId) {
		if (LOGGER.isDebugEnabled()) {LOGGER.debug(String.format("updateFirstGoalScore(matchId=%s,timeGoalScored=%s,feedTypeId=%s,bookieId=%s)", String.valueOf(matchId), String.valueOf(timeGoalScored), String.valueOf(feedTypeId), String.valueOf(bookieId)));}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("matchId", matchId);
		paramMap.put("timeFirstGoal", timeGoalScored);
		paramMap.put("feedTypeId", feedTypeId);
		paramMap.put("bookieId" , bookieId);
		jdbcTemplate.update(UPDATE_FIRST_GOAL_SCORED_SQL, paramMap);
	}

	@Override
	public void updateKo(Integer matchId, Double homePrice, Double drawPrice, Double awayPrice, Double htUndefHgPrice, Integer feedTypeId, Integer bookieId) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(String.format("updateKoPrices(matchId=%s,feedTypeId=%s,homePrice=%s,drawPrice=%s,awayPrice=%s,htUndefHgPrice=%s,indicator=%s,bookieId=%s)", 
				                                   String.valueOf(matchId), String.valueOf(feedTypeId), String.valueOf(homePrice), String.valueOf(drawPrice), String.valueOf(awayPrice), 
				                                   String.valueOf(htUndefHgPrice), String.valueOf(bookieId)));
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("matchId", matchId);
		paramMap.put("feedTypeId", feedTypeId);
		paramMap.put("homePrice", homePrice);
		paramMap.put("awayPrice", awayPrice);		
		paramMap.put("drawPrice", drawPrice);
		paramMap.put("ouHfPrice", htUndefHgPrice);
		paramMap.put("bookieId" , bookieId);
		jdbcTemplate.update(UPDATE_KO_PRICES_SQL, paramMap);	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Match> readMatchesByIndicator(Integer feedTypeId) {
		if (LOGGER.isDebugEnabled()) {LOGGER.debug(String.format("readMatchesByIndicator(feedTypeId=%s)", String.valueOf(feedTypeId)));}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("feedTypeId", feedTypeId);
		return (Map<String, Match>)jdbcTemplate.query(READ_BY_INDICATOR_SQL, paramMap, matchResultSetExtractor);
	}
	
	@Override
	public void updateInRunningIndicator(Integer matchId, Integer feedTypeId, Integer indicator, Integer bookieId) {
		if (LOGGER.isDebugEnabled()) {LOGGER.debug(String.format("updateInRunningIndicator(matchId=%s,feedTypeId=%s,indicator=%s,bookieId=%s)", String.valueOf(matchId), String.valueOf(feedTypeId), String.valueOf(indicator), String.valueOf(bookieId)));}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("indicator" , indicator);
		paramMap.put("matchId"   , matchId);
		paramMap.put("feedTypeId", feedTypeId);
		paramMap.put("bookieId"  , bookieId);
		jdbcTemplate.update(UPDATE_INDICATOR_SQL, paramMap);
	}	

    private boolean exists(Match match) {
    	try {
        	Map<String, Object> paramMap = new HashMap<String, Object>();
        	paramMap.put("matchId"   , match.getMatchId());
        	paramMap.put("bookieId"  , match.getBookieId());
        	paramMap.put("feedTypeId", match.getFeedTypeId());
        	Integer matchId = jdbcTemplate.queryForObject(READ_EXISTS_SQL, paramMap, Integer.class);
        	return (matchId != null);    		
    	} catch (EmptyResultDataAccessException e) {
    		return false;
    	}
    }
    
	private class MatchRowMapper implements RowMapper<Match> {

		@Override
		public Match mapRow(ResultSet rs, int rowNum) throws SQLException {
			Match match = new Match();
			match.setMatchId(rs.getInt("MATCH_ID"));
			match.setLeagueName(rs.getString("LEAGUE_NAME").trim());
			match.setLeagueId(rs.getInt("LEAGUE_ID"));
			match.setHomeTeamName(rs.getString("HOME_TEAM_NAME").trim());
			match.setAwayTeamName(rs.getString("AWAY_TEAM_NAME").trim());
			match.setHomeScore(rs.getInt("HOME_SCORE"));
			match.setAwayScore(rs.getInt("AWAY_SCORE"));
			match.setHomeTeamId(rs.getInt("HOME_TEAM_ID"));
			match.setAwayTeamId(rs.getInt("AWAY_TEAM_ID"));
			match.setFeedTypeId(rs.getInt("FEED_TYPE_ID"));
			match.setMatchTime(rs.getString("MATCH_TIME").trim());
			match.setRunningIndicator(rs.getInt("RUNNING_INDICATOR"));
			match.setTimeGameLive(rs.getString("TIME_GAME_LIVE").trim());
			match.setKoAwayPrice(rs.getDouble("KICK_OFF_AWAY_PRICE"));
			match.setKoDrawPrice(rs.getDouble("KICK_OFF_DRAW_PRICE"));
			match.setKoHomePrice(rs.getDouble("KICK_OFF_HOME_PRICE"));
			match.setKoOuHfPrice(rs.getDouble("KICK_OFF_OU_HF_PRICE"));
			match.setTimeFirstGoal(rs.getInt("TIME_FIRST_GOAL"));
			match.setMatchDate(new DateTime(rs.getDate("MATCH_DATE")));
			match.setBookieId(rs.getInt("BOOKIE_ID"));
			return match;
		}
	}
	
	@SuppressWarnings("rawtypes")
	private class MatchSummaryResultSetExtractor implements ResultSetExtractor {
		
		private DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		
		@Override
		public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
			Map<Integer, MatchSummary> matchSummaries = new LinkedHashMap<Integer, MatchSummary>();
			MatchSummary matchSummary = null;
			MatchTime matchTime = null;			
			Integer key = null;			
			while (rs.next()) {
				key = rs.getInt("MATCH_ID");
				
				if (!matchSummaries.containsKey(key)) {
					matchSummary = new MatchSummary();
					matchSummary.setAwayTeam(rs.getString("AWAY_TEAM_NAME").trim());
					matchSummary.setHomeTeam(rs.getString("HOME_TEAM_NAME").trim());
					
					String matchDateTime = rs.getString("MATCH_TIME").trim();
					DateTime dt = formatter.parseDateTime(matchDateTime).minusHours(8);
					
					matchSummary.setGameDate(dt);
					matchSummary.setKickOffTime(toTime(dt));
					matchSummary.setKickOffAwayPrice(rs.getDouble("KICK_OFF_AWAY_PRICE"));
					matchSummary.setKickOffDrawPrice(rs.getDouble("KICK_OFF_DRAW_PRICE"));
					matchSummary.setKickOffHomePrice(rs.getDouble("KICK_OFF_HOME_PRICE"));
					matchSummary.setKickOffOuHalfAGoal(rs.getDouble("KICK_OFF_OU_HF_PRICE"));
					matchSummary.setTimeFirstGoal(rs.getInt("TIME_FIRST_GOAL"));
					matchSummary.setBookieName(rs.getString("NAME").trim());
					matchSummaries.put(key, matchSummary);
				} else {
					// Update summary with best kick off prices available.
					Double kickOffAwayPrice = rs.getDouble("KICK_OFF_AWAY_PRICE");
					if (matchSummaries.get(key).getKickOffAwayPrice() < kickOffAwayPrice) {
						matchSummaries.get(key).setKickOffAwayPrice(kickOffAwayPrice);
					}
					
					Double kickOffDrawPrice = rs.getDouble("KICK_OFF_DRAW_PRICE");
					if (matchSummaries.get(key).getKickOffDrawPrice() < kickOffDrawPrice) {
						matchSummaries.get(key).setKickOffDrawPrice(kickOffDrawPrice);
					}
					
					Double kickOffHomePrice = rs.getDouble("KICK_OFF_HOME_PRICE");
					if (matchSummaries.get(key).getKickOffHomePrice() < kickOffHomePrice) {
						matchSummaries.get(key).setKickOffHomePrice(kickOffHomePrice);
					}
					
					Double kickOffOuHalfAGoal = rs.getDouble("KICK_OFF_OU_HF_PRICE");
					if (matchSummaries.get(key).getKickOffOuHalfAGoal() < kickOffOuHalfAGoal) {
						matchSummaries.get(key).setKickOffOuHalfAGoal(kickOffOuHalfAGoal);
					}
				}
				matchTime = new MatchTime();
				matchTime.setMinute(rs.getInt("MINUTE"));
				matchTime.setHtUnderHgPrice(rs.getDouble("HT_UNDER_HG_PRICE"));
				
				if (matchSummaries.get(key).getMatchMinutes().containsKey(matchTime.getMinute())) {
					// Minute exists compare prices...
					MatchTime currentMatchTime = matchSummaries.get(key).getMatchMinutes().get(matchTime.getMinute());
					if (currentMatchTime.getHtUnderHgPrice() < matchTime.getHtUnderHgPrice()) {
						matchSummaries.get(key).getMatchMinutes().get(matchTime.getMinute()).setHtUnderHgPrice(matchTime.getHtUnderHgPrice());
					}
				} else {
					// New match minute, add to map...
					matchSummaries.get(key).getMatchMinutes().put(matchTime.getMinute(), matchTime);					
				}
			}
			return new ArrayList<MatchSummary>(matchSummaries.values());
		}
		
		private String toTime(DateTime dateTime) {
			int hour = dateTime.getHourOfDay();
		    int min = dateTime.getMinuteOfHour();
			StringBuilder sb = new StringBuilder();
			
			if (hour < 10) {
				sb.append("0");
			}
			sb.append(String.valueOf(hour));
			sb.append(":");
			if (min == 0) {
				sb.append("0");
			}
			sb.append(String.valueOf(min));
			return sb.toString();
		}
	}

	@SuppressWarnings("rawtypes")
	private class MatchResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
			Map<String, Match> matches = new HashMap<String, Match>();
			Match match = null;
			String key = null;
			while(rs.next()) {
				match = new Match();
				match.setMatchId(rs.getInt("MATCH_ID"));
				match.setLeagueName(rs.getString("LEAGUE_NAME").trim());
				match.setLeagueId(rs.getInt("LEAGUE_ID"));
				match.setHomeTeamName(rs.getString("HOME_TEAM_NAME").trim());
				match.setAwayTeamName(rs.getString("AWAY_TEAM_NAME").trim());
				match.setHomeScore(rs.getInt("HOME_SCORE"));
				match.setAwayScore(rs.getInt("AWAY_SCORE"));
				match.setHomeTeamId(rs.getInt("HOME_TEAM_ID"));
				match.setAwayTeamId(rs.getInt("AWAY_TEAM_ID"));
				match.setFeedTypeId(rs.getInt("FEED_TYPE_ID"));
				match.setMatchTime(rs.getString("MATCH_TIME").trim());
				match.setRunningIndicator(rs.getInt("RUNNING_INDICATOR"));
				match.setTimeGameLive(rs.getString("TIME_GAME_LIVE").trim());
				match.setKoAwayPrice(rs.getDouble("KICK_OFF_AWAY_PRICE"));
				match.setKoDrawPrice(rs.getDouble("KICK_OFF_DRAW_PRICE"));
				match.setKoHomePrice(rs.getDouble("KICK_OFF_HOME_PRICE"));
				match.setKoOuHfPrice(rs.getDouble("KICK_OFF_OU_HF_PRICE"));
				match.setTimeFirstGoal(rs.getInt("TIME_FIRST_GOAL"));
				match.setMatchDate(new DateTime(rs.getDate("MATCH_DATE")));
				match.setBookieId(rs.getInt("BOOKIE_ID"));
				key = String.valueOf(match.getMatchId()) + "|" + rs.getString("NAME").trim();				
				matches.put(key, match);
			}
			return matches;
		}		
	}
}
