package kr.human.mvc06.vo;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@Data
public class EmpVO {
	private int idx;
	private String name;
	private String role;
}
