package kr.green.member.vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@XmlRootElement
@Data
public class TestVO {
	private int 	num1;
	private int 	num2;
	private int 	sum;
	private int 	mul;
	private Date 	today;
}
