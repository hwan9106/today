package kr.human.hibernate.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Entity
@Table(name="STUDENT")
@Data
public class Student implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	@Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
	@Column(name = "LAST_NAME", nullable = false)
    private String lastName;
	@Column(name = "SECTION", nullable = false)
    private String section;
}
