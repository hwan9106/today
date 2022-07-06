package kr.human.di.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface PeopleDAO2 {
	HashMap<String, Object> selectByIdx(int idx) throws SQLException;
	List<HashMap<String, Object>> selectList() throws SQLException;
	void insert(HashMap<String, Object> map) throws SQLException;
}
