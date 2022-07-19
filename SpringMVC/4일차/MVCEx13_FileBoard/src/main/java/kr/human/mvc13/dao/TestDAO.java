package kr.human.mvc13.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestDAO {
	String selectToday();
}
