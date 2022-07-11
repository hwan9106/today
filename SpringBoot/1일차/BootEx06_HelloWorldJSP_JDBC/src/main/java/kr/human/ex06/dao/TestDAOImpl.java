package kr.human.ex06.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("testDAO")
public class TestDAOImpl implements TestDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private DataSource dataSource;

	@Override
	public String selectNow() {
		String result = "";
		Connection connection = null;
		Statement  statement = null;
		ResultSet  resultSet = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select now()");
			resultSet.next();
			result = resultSet.getString(1);
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public Date selectToday() {
		Date date = null;
		date = jdbcTemplate.queryForObject("select now()", Date.class);
		return date;
	}

}
