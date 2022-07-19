package kr.human.security03.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestDAO {
	String selectToday();
}
