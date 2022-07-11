package kr.human.ex06.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.ex06.dao.TestDAO;

@Service("testService")
public class TestServiceImpl implements TestService{
	
	@Autowired
	private TestDAO testDAO;

	@Override
	public String selectNow() {
		return testDAO.selectNow();
	}

	@Override
	public Date selectToday() {
		return testDAO.selectToday();
	}
}
