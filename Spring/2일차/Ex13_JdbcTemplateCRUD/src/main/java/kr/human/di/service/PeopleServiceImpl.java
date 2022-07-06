package kr.human.di.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.di.dao.PeopleDAO;
import kr.human.di.vo.PeopleVO;

@Service("peopleService")
public class PeopleServiceImpl implements PeopleService {

	@Autowired
	private PeopleDAO peopleDAO;

	@Override
	public List<PeopleVO> selectList() {
		try {
			return peopleDAO.selectList();
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean insert(PeopleVO peopleVO) {
		try {
			return peopleDAO.insert(peopleVO);
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean update(PeopleVO peopleVO) {
		try {
			return peopleDAO.update(peopleVO);
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(PeopleVO peopleVO) {
		try {
			return peopleDAO.delete(peopleVO);
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public PeopleVO selectByIdx(int idx){
		PeopleVO peopleVO = null; 
		try {		
			peopleVO = peopleDAO.selectByIdx(idx);
			System.out.println(peopleVO);
		}catch (Exception e) {
			;
		}
		return peopleVO;
	}
}
