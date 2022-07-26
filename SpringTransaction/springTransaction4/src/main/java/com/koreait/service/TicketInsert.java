package com.koreait.service;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.koreait.dao.TransactionDAO;
import com.koreait.vo.CardVO;

public class TicketInsert implements TransactionService {

	private TransactionDAO dao;
//	트랜잭션을 2개 사용해야 하므로 이놈을 2로 만든다.
	private TransactionTemplate transactionTemplate2;
	
	public TransactionDAO getDao() {
		return dao;
	}
	public void setDao(TransactionDAO dao) {
		this.dao = dao;
	}
	public TransactionTemplate getTransactionTemplate2() {
		return transactionTemplate2;
	}
	public void setTransactionTemplate2(TransactionTemplate transactionTemplate2) {
		this.transactionTemplate2 = transactionTemplate2;
	}

	@Override
	public void execute(final CardVO cardVO) {
		
//		ticket.jsp의 입력과 상관없이 트랜잭션을 실행한다.
//		외부 트랜잭션이 있을 때와 없을 때를 구분해서 실행시켜 보자
//		propagationBehavior 속성 지정에 따라 트랜잭션 처리가 달라진다.
//		외부 트랜잭션(transactionTemplate2) 0, 내부 트랜잭션(transactionTemplate1) 0이면 모두 적용된다.
//		외부 트랜잭션이 있고(0), 내부 트랜잭션이 1이면 모두 적용된다.
//		외부 트랜잭션이 없고, 내부 트랜잭션이 1이면 모두 적용되지 않는다.
//		외부 트랜잭션이 있고(0), 내부 트랜잭션이 2이면 모두 적용된다.
//		외부 트랜잭션이 없고, 내부 트랜잭션이 2이면 IllegalTransactionStateException 예외가 발생된다.
//		3, 4, 5는 각각 0, 1, 2의 반대이다.
		
//		외부 트랜잭션이 없을 경우
//		cardVO.setAmount("1");
//		dao.buyTicket(cardVO);
//		cardVO.setAmount("5");
//		dao.buyTicket(cardVO);
		
//		트랜잭션 템플릿 코딩 => 외부 트랜잭션
		try {
			
			transactionTemplate2.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					
					cardVO.setAmount("1"); // 정상 실행
					dao.buyTicket(cardVO);
					cardVO.setAmount("5"); // 오류 발생
					dao.buyTicket(cardVO);
					
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
