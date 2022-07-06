package kr.human.di.dao;

import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.human.di.vo.TestVO;

@Repository("testDAO")
public class TestDAOImpl implements TestDAO{
	
	@Autowired
	private SqlMapClient sqlMapClient;
	
	@Override
	public String today() throws SQLException {
		
		return (String) sqlMapClient.queryForObject("test.today");
	}

	@Override
	public int sum(HashMap<String, Integer> map) throws SQLException {
		return (int) sqlMapClient.queryForObject("test.sum",map);
	}

	@Override
	public int mul(HashMap<String, Integer> map) throws SQLException {
		return (int) sqlMapClient.queryForObject("test.mul",map);
	}

	@Override
	public TestVO vo(TestVO testVO) throws SQLException {
		return (TestVO) sqlMapClient.queryForObject("test.vo",testVO);
	}
	
}
