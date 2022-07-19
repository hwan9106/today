package kr.human.mvc09.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestDAO {
	String selectToday();
	List<HashMap<String, Object>> selectEmp();
}
