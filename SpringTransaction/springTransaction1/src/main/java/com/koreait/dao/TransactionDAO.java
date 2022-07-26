package com.koreait.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.koreait.vo.CardVO;

public class TransactionDAO {
	
//	DBCPTemplate 객체
	JdbcTemplate template;
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public void buyTicket(final CardVO vo) {
		System.out.println("TransactionDAO 클래스의 buyTicket() 메소드 실행");
		System.out.println("consumerId : " + vo.getConsumerId());
		System.out.println("amount : " + vo.getAmount());
		
		template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				String sql = "insert into card (consumerId, amount) values (?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getConsumerId());
				pstmt.setString(2, vo.getAmount());
				return pstmt;
			}
		});
		
		template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				String sql = "insert into ticket (consumerId, countnum) values (?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getConsumerId());
				pstmt.setString(2, vo.getAmount());
				return pstmt;
			}
		});
			
	}
	
}
