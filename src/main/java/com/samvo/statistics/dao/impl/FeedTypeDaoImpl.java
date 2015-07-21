package com.samvo.statistics.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.samvo.statistics.dao.FeedTypeDao;
import com.samvo.statistics.model.match.FeedType;

/**
 * @author Phil
 * 14 Jun 2015
 * Description -
 */
@Repository("feedTypeDao")
public class FeedTypeDaoImpl implements FeedTypeDao {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = Logger.getLogger(FeedTypeDaoImpl.class);

	
	/**
	 * JDBC Template.
	 */
    private NamedParameterJdbcTemplate jdbcTemplate;
    
    /**
     * Read feed types SQL.
     */
    private static final String READ_FEED_TYPES_SQL = "SELECT FEED_TYPE_ID, CODE, NAME FROM OPSFEED.FEED_TYPE";
       
    /**
     * Feed type row mapper.
     */
    private FeedTypeRowMapper rowMapper = new FeedTypeRowMapper();
    
    
    /**
     * 
     * @param dataSource
     */
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    
	@Override
	public List<FeedType> readAll() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("readAll()");
		}		
		return jdbcTemplate.query(READ_FEED_TYPES_SQL, rowMapper);
	}
	
	private class FeedTypeRowMapper implements RowMapper<FeedType> {

		@Override
		public FeedType mapRow(ResultSet rs, int result) throws SQLException {
			FeedType feedType = new FeedType();
			feedType.setCode(rs.getString("CODE"));
			feedType.setName(rs.getString("NAME"));
			feedType.setTypeId(rs.getInt("FEED_TYPE_ID"));
			return feedType;
		}		
	}
}
