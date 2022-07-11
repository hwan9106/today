package kr.green.batch.vo;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;

// 객체를 받아 SQL명령을 완성해주는 클래스
public class HanjaVOItemPreparedStatementSetter implements ItemPreparedStatementSetter<HanjaVO> {

	@Override
	public void setValues(HanjaVO item, PreparedStatement ps) throws SQLException {
		ps.setString(1, item.getD());
		ps.setString(2, item.getS());
		ps.setString(3, item.getN());
		ps.setString(4, item.getQ());
		ps.setString(5, item.getE1());
		ps.setString(6, item.getE2());
		ps.setString(7, item.getE3());
		ps.setString(8, item.getE4());
		ps.setString(9, item.getA());
	}

}
