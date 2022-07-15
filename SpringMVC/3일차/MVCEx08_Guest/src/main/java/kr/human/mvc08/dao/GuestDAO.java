package kr.human.mvc08.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.human.mvc08.vo.GuestVO;

@Mapper
public interface GuestDAO {
	List<GuestVO> selectList();
	void insert(GuestVO guestVO);
	void reply(GuestVO guestVO);
	void updateSeq(HashMap<String, Integer> map);
	void update(GuestVO guestVO);
	GuestVO selectByIdx(int idx);
	void delete1(int idx); // Y비교
	void delete2(int idx); // Y비교 않하는 놈
	void updateDel(int idx);
	List<GuestVO> selectSeqList(HashMap<String, Integer> map);
	List<GuestVO> selectDelList();
}
