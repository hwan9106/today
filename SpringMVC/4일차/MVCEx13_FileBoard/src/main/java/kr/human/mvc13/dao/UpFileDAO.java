package kr.human.mvc13.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.human.mvc13.vo.UpFileVO;
@Mapper
public interface UpFileDAO {
	void insert(UpFileVO vo);
	void deleteByIdx(int idx);
	void deleteByRef(int ref);
	List<UpFileVO> selectByRef(int ref);
	UpFileVO selectByIdx(int idx);
}
