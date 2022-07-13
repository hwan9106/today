package kr.human.memo.service;

import kr.human.memo.vo.MemoVO;
import kr.human.memo.vo.PagingVO;

public interface MemoService {
	// 목록보기
	PagingVO<MemoVO> selectList(int currentPage, int pageSize, int blockSize);
	// 저장하기
	boolean insert(MemoVO memoVO);
	// 수정하기
	boolean update(MemoVO memoVO);
	// 삭제하기
	boolean delete(MemoVO memoVO);
}
