package com.koreait.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.dao.TransactionDAO;
import com.koreait.vo.CardVO;
@Service
public class TicketInsert implements TransactionService {

	@Autowired
	private TransactionDAO transactionDAO;
	
	@Override
	public void execute(final CardVO cardVO) {
		try {
			transactionDAO.buyCard(cardVO);
		}catch (Exception e) {
			;
		}
	}

}
