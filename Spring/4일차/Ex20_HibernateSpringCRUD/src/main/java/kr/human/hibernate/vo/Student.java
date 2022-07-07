package kr.human.hibernate.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="STUDENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student{
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
