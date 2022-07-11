package kr.human.ex08.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;


@SuppressWarnings("serial")
@Entity
@Table(name="Student")
@Data
@NoArgsConstructor
public class Student implements Serializable{
	@Id
	@GeneratedValue(generator = "generator", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "generator", sequenceName = "student_id_seq", allocationSize = 1)
	private int id;
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;
	@Column(name = "SECTION", nullable = false)
	private String section;
	
	public Student(String firstName, String lastName, String section) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.section = section;
	}
	

}
