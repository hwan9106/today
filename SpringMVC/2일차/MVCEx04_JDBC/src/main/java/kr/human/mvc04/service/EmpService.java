package kr.human.mvc04.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.mvc04.dao.EmpDAO;
import kr.human.mvc04.vo.EmpVO;

@Service
public class EmpService {

	@Autowired
	private EmpDAO empDAO;
	
	// 1. 개수얻기
	public int selectCount() throws SQLException {
		return empDAO.selectCount();
	}
	// 2. 1개얻기
	public EmpVO selectByIdx(int idx) throws SQLException {
		return empDAO.selectByIdx(idx);
	}
	// 3. 모두 얻기
	public List<EmpVO> selectList() throws SQLException {
		return empDAO.selectList();
	}
	// 4. 저장
	public int insert(EmpVO empVO) throws SQLException {
		return empDAO.insert(empVO);
	}
	// 5. 수정
	public int update(EmpVO empVO) throws SQLException {
		return empDAO.update(empVO);
	}
	// 6. 삭제
	public int delete(int idx) throws SQLException {
		return empDAO.delete(idx);
	}
}
