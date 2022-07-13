package kr.human.mvc03.vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@XmlRootElement
@Data
public class TestVO {
	private String name;
	private int age;
	private boolean gender;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birth;
	
}
