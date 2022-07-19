package kr.human.mvc09.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.mvc09.dao.TestDAO;

@Service
public class TestService {

	@Autowired
	TestDAO testDAO;
	
	public String selectToday() {
		return testDAO.selectToday();
	}
	
	public List<HashMap<String, Object>> selectEmp(){
		return testDAO.selectEmp(); 
	}
}
