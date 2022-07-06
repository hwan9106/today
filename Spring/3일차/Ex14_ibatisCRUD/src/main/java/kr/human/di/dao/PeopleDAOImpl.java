package kr.human.di.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.human.di.vo.PeopleVO;
import lombok.extern.slf4j.Slf4j;

@Repository("peopleDAO")
@Slf4j
public class PeopleDAOImpl implements PeopleDAO{
	
	@Autowired
	private SqlMapClient sqlMapClient;

	@Override
	public PeopleVO selectByIdx(int idx) throws SQLException {
		return (PeopleVO) sqlMapClient.queryForObject("people.selectByIdx",idx);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PeopleVO> selectList() throws SQLException {
		return sqlMapClient.queryForList("people.selectList");
	}

	@Override
	public void insert(PeopleVO peopleVO) throws SQLException {
		Object object = sqlMapClient.insert("people.insert",peopleVO);
		log.info("저장 : {}",object);
	}

	@Override
	public void update(PeopleVO peopleVO) throws SQLException {
		Object object = sqlMapClient.update("people.update",peopleVO);
		log.info("수정 : {}",object);
	}

	@Override
	public void delete(int idx) throws SQLException {
		Object object = sqlMapClient.delete("people.delete",idx);
		log.info("삭제 : {}",object);
	}

}
