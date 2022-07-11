package kr.green.batch.vo;

import org.joda.time.LocalDate;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class ExamResultFieldSetMapper implements FieldSetMapper<ExamResult>{

	@Override // FieldSet으로 넘어온 값을 VO로 만들어 리턴해주는 맵퍼 클래스
	public ExamResult mapFieldSet(FieldSet fieldSet) throws BindException {
		ExamResult examResult = new ExamResult();
		examResult.setStudentName(fieldSet.readString(0));
		examResult.setDob(new LocalDate(fieldSet.readDate(1,"dd/MM/yyyy")));
		examResult.setPercentage(fieldSet.readDouble(2));
		return examResult;
	}

}
