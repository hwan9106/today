package kr.human.di.dao;

import java.util.List;

import kr.human.di.vo.PeopleVO;

public interface PeopleDAO {
	// 1. 1개 얻기
	PeopleVO selectByIdx(int idx) throws Exception;
	// 2. 모두얻기
	List<PeopleVO> selectList() throws Exception;
	// 3. 저장
	boolean insert(PeopleVO peopleVO) throws Exception;
	// 4. 수정
	boolean update(PeopleVO peopleVO) throws Exception;
	// 5. 삭제
	boolean delete(PeopleVO peopleVO) throws Exception;
}
