package com.koreait.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.koreait.vo.CardVO;

public class TransactionDAO {
	
//	DBCPTemplate 객체
	JdbcTemplate template;
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

//	트랜잭션 객체
//	PlatformTransactionManager transactionManager;
//	public void setTransactionManager(PlatformTransactionManager transactionManager) {
//		this.transactionManager = transactionManager;
//	}
	
//	트랜잭션 객체 대신 트랜잭션 템플릿을 사용한다.
	TransactionTemplate transactionTemplate;
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	public void buyTicket(final CardVO vo) {
		System.out.println("TransactionDAO 클래스의 buyTicket() 메소드 실행");
		System.out.println("consumerId : " + vo.getConsumerId());
		System.out.println("amount : " + vo.getAmount());

		/*
//		트랜잭션 상태를 처리할 객체를 생성하고 PlatformTransactionManager 객체를 사용해 초기화시킨다.
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);
		
//		try ~ catch로 트랜잭션을 처리한다.
		try {
			
			template.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					String query = "insert into card (consumerId, amount) values (?, ?)";
					PreparedStatement pstmt = con.prepareStatement(query);
					pstmt.setString(1, vo.getConsumerId());
					pstmt.setString(2, vo.getAmount());
					return pstmt;
				}
			});
			
			template.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					String query = "insert into ticket (consumerId, countnum) values (?, ?)";
					PreparedStatement pstmt = con.prepareStatement(query);
					pstmt.setString(1, vo.getConsumerId());
					pstmt.setString(2, vo.getAmount());
					return pstmt;
				}
			});
			
//			정상 처리되면 트랜잭션을 commit 시킨다.
			transactionManager.commit(status);
			
		} catch (Exception e) {
//			e.printStackTrace();
//			정상 처리가 되지않으면 트랜잭션을 rollback 시킨다.
			transactionManager.rollback(status);
		}
		*/
		
//		트랜잭션 템플릿 코딩
		try {
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					
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
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}









