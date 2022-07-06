package kr.human.di.service;

import java.util.List;

import kr.human.di.vo.PeopleVO;

public interface PeopleService {
	// 1. 모두얻기
	List<PeopleVO> selectList();

	// 2. 저장
	boolean insert(PeopleVO peopleVO);

	// 3. 수정
	boolean update(PeopleVO peopleVO);

	// 4. 삭제
	boolean delete(PeopleVO peopleVO);
	
	// 5. 1개 얻기
	PeopleVO selectByIdx(int idx) ;
}
