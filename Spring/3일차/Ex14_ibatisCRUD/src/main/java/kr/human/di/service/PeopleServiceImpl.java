package kr.human.di.service;

import java.sql.SQLException;
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
		List<PeopleVO> list = null;
			try {
				list = peopleDAO.selectList();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return list;
	}

	@Override
	public boolean insert(PeopleVO peopleVO) {
		boolean result = false;
		if(peopleVO!=null) {
			try {
				peopleDAO.insert(peopleVO);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
		return result;
	}

	@Override
	public boolean update(PeopleVO peopleVO) {
		boolean result = false;
		if(peopleVO!=null) {
			try {
				peopleDAO.update(peopleVO);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
		return result;
	}

	@Override
	public boolean delete(int idx) {
		boolean result = false;
		if(idx >0) {
			try {
				peopleDAO.delete(idx);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
