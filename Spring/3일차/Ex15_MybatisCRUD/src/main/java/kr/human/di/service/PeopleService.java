package kr.human.di.service;

import java.sql.SQLException;
import java.util.List;

import kr.human.di.vo.PeopleVO;

public interface PeopleService {
	//목록보기
	List<PeopleVO> selectList() ;
	//저장하기
	boolean insert(PeopleVO peopleVO) ;
	//수정하기
	boolean update(PeopleVO peopleVO) ;
	//삭제하기
	boolean delete(int idx) ;
}
