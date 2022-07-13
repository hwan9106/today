package kr.human.memo.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.human.memo.vo.MemoVO;

@Mapper
public interface MemoDAO {
	int selectCount() throws SQLException;
	MemoVO selectByIdx(int idx) throws SQLException;
	List<MemoVO> selectList(HashMap<String, Integer> map) throws SQLException;
	void insert(MemoVO memoVO) throws SQLException;
	void update(MemoVO memoVO) throws SQLException;
	void delete(int idx) throws SQLException;
}
