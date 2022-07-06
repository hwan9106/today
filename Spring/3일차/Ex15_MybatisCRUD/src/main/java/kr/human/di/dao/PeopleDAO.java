package kr.human.di.dao;

import java.sql.SQLException;
import java.util.List;

import kr.human.di.vo.PeopleVO;

public interface PeopleDAO {
	PeopleVO selectByIdx(int idx) throws SQLException;
	List<PeopleVO> selectList() throws SQLException;
	void insert(PeopleVO peopleVO) throws SQLException;
	void update(PeopleVO peopleVO) throws SQLException;
	void delete(int idx) throws SQLException;
}
