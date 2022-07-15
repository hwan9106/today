package kr.human.mvc06.dao;


import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.human.mvc06.vo.EmpVO;
@Mapper
public interface EmpDAO {

	int selectCount() throws SQLException;
	EmpVO selectByIdx(int idx) throws SQLException;
	List<EmpVO> selectList() throws SQLException;
	void insert(EmpVO empVO) throws SQLException;
	void update(EmpVO empVO) throws SQLException;
	void delete(int idx) throws SQLException;
}
