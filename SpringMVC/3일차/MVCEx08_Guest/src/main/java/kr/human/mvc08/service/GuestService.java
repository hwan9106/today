package kr.human.mvc08.service;

import java.util.List;

import kr.human.mvc08.vo.GuestVO;

public interface GuestService {
	// 목록보기
	List<GuestVO> selectList();
	// 원본글 저장
	void insert(GuestVO guestVO);
	// 답변 저장
	void reply(GuestVO guestVO);
	// 수정
	void update(GuestVO guestVO);
	// 삭제
	void delete(GuestVO guestVO);
	
}
