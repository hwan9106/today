package kr.green.batch.vo;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;

// 객체를 받아 SQL명령을 완성해주는 클래스
public class ExamResultItemPreparedStatementSetter implements ItemPreparedStatementSetter<ExamResult> {

	@Override
	public void setValues(ExamResult item, PreparedStatement ps) throws SQLException {
		ps.setString(1, item.getStudentName());
		ps.setDate(2, new java.sql.Date(item.getDob().toDate().getTime()));
		ps.setDouble(3, item.getPercentage());
	}

}
