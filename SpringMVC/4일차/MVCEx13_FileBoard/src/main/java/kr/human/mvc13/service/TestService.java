package kr.human.mvc13.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.mvc13.dao.TestDAO;

@Service
public class TestService {
	
	@Autowired 
	private TestDAO testDAO;
	
	public String selectToday() {
		return testDAO.selectToday();
	}
}
