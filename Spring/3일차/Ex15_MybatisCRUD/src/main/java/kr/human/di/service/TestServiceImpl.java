package kr.human.di.service;

import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.di.dao.TestDAO;
import kr.human.di.vo.TestVO;

@Service("testService")
public class TestServiceImpl implements TestService{
	@Autowired
	private TestDAO testDAO;
	
	@Override
	public String today() {
		String today = "";
		try {
			today= testDAO.today();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return today;
	}

	@Override
	public int sum(int num1, int num2) {
		int result = 0 ;
		HashMap<String, Integer> map = new HashMap<>();
		map.put("num1", num1);
		map.put("num2", num2);
		try {
			result = testDAO.sum(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int mul(int num1, int num2) {
		int result = 0 ;
		HashMap<String, Integer> map = new HashMap<>();
		map.put("num1", num1);
		map.put("num2", num2);
		try {
			result = testDAO.mul(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public TestVO vo(int num1, int num2) {
		TestVO testVO = new TestVO();
		testVO.setNum1(num1);
		testVO.setNum2(num2);
		try {
			testVO= testDAO.vo(testVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return testVO;
	}
	
}
