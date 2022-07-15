package kr.human.mvc08.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.mvc08.dao.TestDAO;

@Service
public class TestService {

	@Autowired
	TestDAO testDAO;
	
	public String selectToday() {
		return testDAO.selectToday();
	}
}
