package kr.green.member.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.member.dao.TestDAO;
import kr.green.member.vo.TestVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service("testService")
public class TestServiceImpl implements TestService{

	@Autowired
	private TestDAO testDAO;

	@Override
	public Date selectToday() {
		log.info("{}의 selectToday 호출", this.getClass().getName());
		Date today = null;
		try {
			today = testDAO.selectToday();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("{}의 selectToday 리턴 : {}", this.getClass().getName(), today);
		return today;
	}

	@Override
	public int selectSum(int num1, int num2) {
		log.info("{}의 selectSum 호출 : {}", this.getClass().getName(), num1 + " + " + num2);
		int result = 0;
		try {
			HashMap<String, Integer> map = new HashMap<>();
			map.put("num1", num1);
			map.put("num2", num2);
			result = testDAO.selectSum(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("{}의 selectSum 리턴 : {}", this.getClass().getName(), result);
		return result;
	}

	@Override
	public int selectMul(int num1, int num2) {
		log.info("{}의 selectMul 호출 : {}", this.getClass().getName(), num1 + " + " + num2);
		int result = 0;
		try {
			HashMap<String , Integer> map = new HashMap<>();
			map.put("num1", num1);
			map.put("num2", num2);
			result = testDAO.selectMul(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("{}의 selectMul 리턴 : {}", this.getClass().getName(), result);
		return result;
	}

	@Override
	public TestVO selectVO(TestVO testVO) {
		log.info("{}의 selectVO 호출 : {}", this.getClass().getName(), testVO);
		TestVO resultVO = null;
		try {
			resultVO = testDAO.selectVO(testVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("{}의 selectVO 리턴 : {}", this.getClass().getName(), resultVO);
		return resultVO;
	}
}
