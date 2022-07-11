package kr.green.batch.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.joda.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamResult {
	@XmlElement
    private String studentName;
	@XmlElement
	@XmlJavaTypeAdapter(type = LocalDate.class, value = LocalDateAdapter.class)
    private LocalDate dob;
	@XmlElement
    private double percentage;
}
