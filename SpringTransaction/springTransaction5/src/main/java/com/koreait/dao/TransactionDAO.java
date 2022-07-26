package com.koreait.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.koreait.vo.CardVO;

@Repository
@Transactional
public class TransactionDAO {
	
	//	DBCPTemplate 객체
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public void buyCard(final CardVO vo){
		System.out.println("TransactionDAO 클래스의 buyCard() 메소드 실행");
		System.out.println("consumerId : " + vo.getConsumerId());
		System.out.println("amount : " + vo.getAmount());
		System.out.println("jdbcTemplate : " + jdbcTemplate);
		
		jdbcTemplate.update("insert into card (CONSUMERID, AMOUNT) values (?, ?)", vo.getConsumerId(),vo.getAmount());
		jdbcTemplate.update("insert into ticket (CONSUMERID, COUNTNUM) values (?, ?)", vo.getConsumerId(),vo.getAmount());
	}
}









