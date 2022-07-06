package kr.human.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.di.dao.TestDAO;
import kr.human.di.vo.TestVO;

@Service("testService")
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDAO testDAO; //순수 JDBC API 이용
	@Autowired
	private TestDAO testDAO2; //JDBCTemplate 이용
	@Override
	public TestVO selectTest() {
		TestVO testVO = new TestVO();
		testVO.setMessage(testDAO.getMessage());
		testVO.setResult(testDAO.getResult());
		testVO.setToday(testDAO.getToday());
		return testVO;
	}

	@Override
	public TestVO selectTest2() {
		TestVO testVO = new TestVO();
		testVO.setMessage(testDAO2.getMessage());
		testVO.setResult(testDAO2.getResult());
		testVO.setToday(testDAO2.getToday());
		return testVO;
	}

}
