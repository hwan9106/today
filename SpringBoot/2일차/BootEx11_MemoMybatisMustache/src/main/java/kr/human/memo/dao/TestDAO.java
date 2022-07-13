package kr.human.memo.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestDAO {
	String today();
}
