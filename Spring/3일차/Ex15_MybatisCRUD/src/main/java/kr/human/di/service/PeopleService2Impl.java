package kr.human.di.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.di.dao.PeopleDAO2;
import kr.human.di.vo.PeopleVO;

@Service("peopleService2")
public class PeopleService2Impl implements PeopleService2 {
	
	@Autowired
	private PeopleDAO2 peopleDAO2;

	@Override
	public HashMap<String, Object> selectByIdx(int idx) {
		HashMap<String, Object> map = null;
		try {
			map= peopleDAO2.selectByIdx(idx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public List<HashMap<String, Object>> selectList() {
		List<HashMap<String, Object>> list =null;
		try {
			list= peopleDAO2.selectList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean insert(HashMap<String, Object> map) {
		boolean result = false;
		try {
			peopleDAO2.insert(map);
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	

}
