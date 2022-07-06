package kr.human.di.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("testDAO")
public class TestDAOImpl implements TestDAO {

	@Autowired
	private DataSource dataSource;
	
	@Override
	public String getMessage() {
		String message = null;
		Connection 	connection = null;
		Statement  	statement = null;
		ResultSet	resultSet = null;
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select '재미없는 Spring!!!!' ");
			resultSet.next();
			message = resultSet.getString(1);
			connection.commit();
		}catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
		return message;
	}

	@Override
	public int getResult() {
		int result = 0;
		Connection 	connection = null;
		Statement  	statement = null;
		ResultSet	resultSet = null;
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select 34*56 ");
			resultSet.next();
			result = resultSet.getInt(1);
			connection.commit();
		}catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
	public Date getToday() {
		Date today = null;
		Connection 	connection = null;
		Statement  	statement = null;
		ResultSet	resultSet = null;
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select now() ");
			resultSet.next();
			today = resultSet.getDate(1);
			connection.commit();
		}catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
		return today;
	}

}
