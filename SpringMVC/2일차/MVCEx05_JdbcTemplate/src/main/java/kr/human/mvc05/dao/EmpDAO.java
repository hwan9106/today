package kr.human.mvc05.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.human.mvc05.vo.EmpVO;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class EmpDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 1. 개수 얻기
	public int selectCount() throws SQLException {
		log.info("{}의 selectCount 호출!!!", this.getClass().getName());
		int count = 0;
		count = jdbcTemplate.queryForObject("select count(*) from emp", int.class);
		log.info("{}의 selectCount 리턴값!!!", this.getClass().getName(), count);
		return count;
	}
	// 2. 1개 얻기
	public EmpVO selectByIdx(int idx) throws SQLException {
		log.info("{}의 selectByIdx 호출 : {}", this.getClass().getName(), idx);
		EmpVO empVO = null;
		empVO = jdbcTemplate.queryForObject("select * from emp where idx=" + idx, EmpVO.class);
		log.info("{}의 selectByIdx 리턴값!!!", this.getClass().getName(), empVO);
		return empVO;
	}
	
	// 3. 모두 얻기
	public List<EmpVO> selectList() throws SQLException {
		log.info("{}의 selectList 호출", this.getClass().getName());
		List<EmpVO> list = null;
		
		list = jdbcTemplate.query("select * from emp order by idx desc", new EmpVOMapper());
		
		log.info("{}의 selectList 리턴값!!!", this.getClass().getName(), list);
		return list;
	}
	// 1개의 레코드를 EmpVO객체로 만들어주는 클래스를 작성해야 한다.
	static class EmpVOMapper implements RowMapper<EmpVO>{
		@Override
		public EmpVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			// ResultSet에서 데이터를 읽어 자바 객체에 넣 리턴해 준다.
			EmpVO empVO = new EmpVO();
			empVO.setIdx(rs.getInt("idx"));
			empVO.setName(rs.getString("name"));
			empVO.setRole(rs.getString("role"));
			return empVO;
		}
	}
	
	// 4. 저장
	public int insert(EmpVO empVO) throws SQLException {
		log.info("{}의 insert 호출 : {}", this.getClass().getName(), empVO);
		int count = jdbcTemplate.update("insert into emp values (emp_idx_seq.nextval,?,?)", empVO.getName(), empVO.getRole());
		log.info("{}의 insert 리턴값!!!", this.getClass().getName(), count);
		return count;
	}
	
	// 5. 수정
	public int update(EmpVO empVO) throws SQLException {
		log.info("{}의 update 호출 : {}", this.getClass().getName(), empVO);
		int count = jdbcTemplate.update("update emp set name=?, role=? where idx=?", new Object[] {empVO.getName(), empVO.getRole(), empVO.getIdx()});
		log.info("{}의 update 리턴값!!!", this.getClass().getName(), count);
		return count;
	}
	// 6. 삭제
	public int delete(int idx) throws SQLException {
		log.info("{}의 delete 호출 : {}", this.getClass().getName(), idx);
		int count = jdbcTemplate.update("delete from emp where idx=?", new Object[] {idx});
		log.info("{}의 delete 리턴값!!!", this.getClass().getName(), count);
		return count;
	}
}
