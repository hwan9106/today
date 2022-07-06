package kr.human.di.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("testDAO2")
public class TestDAOImpl2 implements TestDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	@Override
	public String getMessage() {
		
		return jdbcTemplate.queryForObject("select '반갑다 JDBCTemple'", String.class);
	}

	@Override
	public int getResult() {
		
		return jdbcTemplate.queryForObject("select 25*84", int.class);
	}

	@Override
	public Date getToday() {
		
		return jdbcTemplate.queryForObject("select now()", Date.class);
	}
	
}
