package kr.human.di.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import kr.human.di.vo.PeopleVO;


// ResultSet을 받아 VO를 만들어서 리턴해주는 기능
public class PeopleMapper implements RowMapper<PeopleVO>{

	@Override
	public PeopleVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		PeopleVO peopleVO = new PeopleVO();
		
		peopleVO.setIdx(rs.getInt("idx"));
		peopleVO.setName(rs.getString("name"));
		peopleVO.setAge(rs.getInt("age"));
		
		return peopleVO;
	}

}
