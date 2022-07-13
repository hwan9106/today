package kr.human.memo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.memo.dao.TestDAO;

@Service("testService")
public class TestServiceImpl implements TestService{
	@Autowired
	private TestDAO testDAO;
	@Override
	public String today() {
		return testDAO.today();
	}

}
