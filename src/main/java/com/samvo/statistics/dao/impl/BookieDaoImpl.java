package com.samvo.statistics.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.samvo.statistics.dao.BookieDao;
import com.samvo.statistics.model.match.Bookie;

/**
 * @author Phil
 * 14 Jun 2015
 * Description -
 */
@Repository("bookieDao")
public class BookieDaoImpl implements BookieDao {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = Logger.getLogger(BookieDaoImpl.class);
	
	/**
	 * JDBC Template.
	 */
    private NamedParameterJdbcTemplate jdbcTemplate;
    
    /**
     * SQL statements.
     */
    private static final String INSERT_BOOKIE_SQL = "INSERT INTO OPSFEED.BOOKIE (NAME) VALUES (:name);";

    private static final String READ_BOOKIES_BY_NAME_SQL = "SELECT BOOKIE_ID, NAME FROM OPSFEED.BOOKIE WHERE NAME = :name";
    
    private static final String READ_BOOKIES_BY_ID_SQL = "SELECT NAME FROM OPSFEED.BOOKIE WHERE BOOKIE_ID = :id";    
    
    private static final String READ_ALL_BOOKIES_SQL = "SELECT BOOKIE_ID, NAME FROM OPSFEED.BOOKIE";
    
    /**
     * Row mapper.
     */
    private BookieRowMapper bookieRowMapper = new BookieRowMapper();
    
    /**
     * 
     * @param dataSource
     */
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    
	@Override
	public Bookie createBookie(String name) {
		if (LOGGER.isDebugEnabled()) {LOGGER.debug(String.format("createBookie(name=%s", name));}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", name);
		jdbcTemplate.update(INSERT_BOOKIE_SQL, paramMap);
		return jdbcTemplate.queryForObject(READ_BOOKIES_BY_NAME_SQL, paramMap, bookieRowMapper);
	}

	@Override
	public List<Bookie> readAll() {
		if (LOGGER.isDebugEnabled()) {LOGGER.debug("readAll()");}
		return jdbcTemplate.query(READ_ALL_BOOKIES_SQL, bookieRowMapper);
	}
	
	private class BookieRowMapper implements RowMapper<Bookie> {
		@Override
		public Bookie mapRow(ResultSet rs, int rowNum) throws SQLException {
			Bookie bookie = new Bookie();
			bookie.setId(rs.getInt("BOOKIE_ID"));
			bookie.setName(rs.getString("NAME").trim());
			return bookie;
		}		
	}

	@Override
	public String readById(Integer id) {
		if (LOGGER.isDebugEnabled()) {LOGGER.debug(String.format("readById(id=%s)", String.valueOf(id)));}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		String result = jdbcTemplate.queryForObject(READ_BOOKIES_BY_ID_SQL, paramMap, String.class);
		if (StringUtils.isNotBlank(result)) {
			return result.trim();
		} else {
			return result;
		}
	}
}
