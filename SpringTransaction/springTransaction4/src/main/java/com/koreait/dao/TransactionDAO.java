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
	
//	트랜잭션 객체 대신 트랜잭션 템플릿을 사용한다. => 트랜잭션을 2개 사용해야 하므로 이놈을 1로 수정
	TransactionTemplate transactionTemplate1;
	public void setTransactionTemplate1(TransactionTemplate transactionTemplate1) {
		this.transactionTemplate1 = transactionTemplate1;
	}
	
	public void buyTicket(final CardVO vo) {
		System.out.println("TransactionDAO 클래스의 buyTicket() 메소드 실행");
		System.out.println("consumerId : " + vo.getConsumerId());
		System.out.println("amount : " + vo.getAmount());

//		트랜잭션 템플릿 코딩 => 내부 트랜잭션
		try {
			
			transactionTemplate1.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					
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
					
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}









