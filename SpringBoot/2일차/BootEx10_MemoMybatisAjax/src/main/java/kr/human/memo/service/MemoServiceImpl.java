package kr.human.memo.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.human.memo.dao.MemoDAO;
import kr.human.memo.vo.MemoVO;
import kr.human.memo.vo.PagingVO;

@Service("memoService")
@Transactional
public class MemoServiceImpl  implements MemoService{

	@Autowired
	private MemoDAO memoDAO;

	@Override
	public PagingVO<MemoVO> selectList(int currentPage, int pageSize, int blockSize) {
		PagingVO<MemoVO> pagingVO = null;
		try {
			int totalCount = memoDAO.selectCount();
			pagingVO = new PagingVO<>(totalCount, currentPage, pageSize, blockSize);
			HashMap<String, Integer> hashMap = new HashMap<>();
			hashMap.put("startNo", pagingVO.getStartNo());
			hashMap.put("pageSize", pagingVO.getPageSize());
			List<MemoVO> list = memoDAO.selectList(hashMap);
			pagingVO.setList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pagingVO;
	}

	@Override
	public boolean insert(MemoVO memoVO) {
		boolean result = false;
		if(memoVO!=null) {
			try {
				memoDAO.insert(memoVO);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean update(MemoVO memoVO) {
		boolean result = false;
		if(memoVO!=null) {
			try {
				MemoVO dbVO = memoDAO.selectByIdx(memoVO.getIdx()); // DB에서 원본 가져오기
				if(dbVO!=null && dbVO.getPassword().equals(memoVO.getPassword())) { // 비번이 같으면
					memoDAO.update(memoVO);
				}
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean delete(MemoVO memoVO) {
		boolean result = false;
		if(memoVO!=null) {
			try {
				MemoVO dbVO = memoDAO.selectByIdx(memoVO.getIdx()); // DB에서 원본 가져오기
				if(dbVO!=null && dbVO.getPassword().equals(memoVO.getPassword())) { // 비번이 같으면
					memoDAO.delete(memoVO.getIdx());
				}
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
