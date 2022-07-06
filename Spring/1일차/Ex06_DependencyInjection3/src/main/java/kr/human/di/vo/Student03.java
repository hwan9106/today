package kr.human.di.vo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Student03 {
	private int id;
	private String studentName;
	
	public void setId(int id) {
		this.id=id;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public void diplayInfo() {
		System.out.println("Student Name is " + studentName + " and Roll Number is " + id);
	}
}
