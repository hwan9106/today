package kr.human.di.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class EmpVO {
	private int id;
	private String name;
	
	public EmpVO() {
		
	}
	public EmpVO(int id) {
		this.id = id;
	}
	public EmpVO(String name) {
		this.name = name;
	}
	public EmpVO(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
}
