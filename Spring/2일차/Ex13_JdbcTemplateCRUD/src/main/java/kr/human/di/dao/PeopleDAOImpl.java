package kr.human.di.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.human.di.vo.PeopleVO;

@Repository("peopleDAO")
public class PeopleDAOImpl implements PeopleDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("deprecation")
	@Override
	public PeopleVO selectByIdx(int idx) throws Exception{
		// 첫번째 인수 : SQL명령
		// 두번째 인수 : ?에 매칭될 데이터 배열(Object타입이다)
		// 세번째 인수 : RowMapper를 구현 객체
		return jdbcTemplate.queryForObject("select * from people where idx=?", new Object[] {idx}, new PeopleMapper());
	}

	@Override
	public List<PeopleVO> selectList()  throws Exception{
		return jdbcTemplate.query("select * from people order by idx desc", new PeopleMapper() );
	}

	@Override
	public boolean insert(PeopleVO peopleVO)  throws Exception{
		return jdbcTemplate.update("insert into people (name, age) values (?,?)",peopleVO.getName(), peopleVO.getAge()) > 0;
	}

	@Override
	public boolean update(PeopleVO peopleVO) throws Exception {
		return jdbcTemplate.update("update people set name=?, age=? where idx=?",peopleVO.getName(), peopleVO.getAge(), peopleVO.getIdx()) > 0;
	}

	@Override
	public boolean delete(PeopleVO peopleVO) throws Exception {
		return jdbcTemplate.update("delete from people where idx=?",peopleVO.getIdx()) > 0;
	}

}
