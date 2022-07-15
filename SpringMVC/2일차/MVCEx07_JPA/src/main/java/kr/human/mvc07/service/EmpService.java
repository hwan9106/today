package kr.human.mvc07.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.mvc07.dao.EmpRepository;
import kr.human.mvc07.vo.Emp;

@Service
public class EmpService {

	@Autowired
	private EmpRepository empRepository;
	
	// 1. 개수얻기
	public int selectCount() throws SQLException {
		return (int) empRepository.count();
	}
	// 2. 1개얻기
	public Optional<Emp> selectByIdx(int idx) throws SQLException {
		return empRepository.findById(idx);
	}
	// 3. 모두 얻기
	public List<Emp> selectList() throws SQLException {
		List<Emp> list = new ArrayList<>();
		empRepository.findAll().forEach(list::add);
		return list;
	}
	// 4. 저장
	public void insert(Emp empVO) throws SQLException {
		empRepository.save(empVO);
	}
	// 5. 수정
	public Emp update(Emp empVO) throws SQLException {
		Optional<Emp> vo = empRepository.findById(empVO.getIdx());
		if(!vo.isPresent()) {
			return null;
		}
		Emp empVO2 = vo.get();
		empVO2.setName(empVO.getName());
		empVO2.setRole(empVO.getRole());
		return empRepository.save(empVO2);
	}
	// 6. 삭제
	public Emp delete(int idx) throws SQLException {
		Optional<Emp> vo = empRepository.findById(idx);
		if(!vo.isPresent()) {
			return null;
		}
		Emp empVO2 = vo.get();
		empRepository.delete(empVO2);
		return empVO2;
	}
}
