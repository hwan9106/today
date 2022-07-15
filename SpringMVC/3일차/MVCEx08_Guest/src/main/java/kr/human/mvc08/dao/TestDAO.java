package kr.human.mvc08.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestDAO {
	String selectToday();
}
