package kr.human.mvc13.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.human.mvc13.vo.FileBoardVO;

@Mapper
public interface FileBoardDAO {
	int selectCount();
	FileBoardVO selectByIdx(int idx);
	List<FileBoardVO> selectList(HashMap<String, Integer> map);
	void insert(FileBoardVO vo);
	void update(FileBoardVO vo);
	void delete(int idx);
	int selectSeq();
}
