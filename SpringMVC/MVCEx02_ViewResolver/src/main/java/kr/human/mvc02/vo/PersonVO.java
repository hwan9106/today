package kr.human.mvc02.vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name="person") //루트 태그 이름
@XmlType(propOrder = {"name","age","gender","birth"}) //태그가 나타나는 순서
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonVO {
	@XmlElement //태그로 쓰겠다.
	private String name;
	@XmlAttribute // 속성으로 쓰겠다.
	private int age;
	@XmlElement
	@XmlJavaTypeAdapter(GenderAdpater.class) //XML로 만들떄와 객체로 읽을때 모양 지정
	private Boolean gender;
	@XmlElement
	@XmlJavaTypeAdapter(BirthAdpater.class) //XML로 만들떄와 객체로 읽을때 모양 지정
	private Date birth; 
}
