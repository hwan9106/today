package kr.human.mvc07.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="EMP")
@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlRootElement
public class Emp {
	@Id
	@GeneratedValue(generator = "generator", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "generator", sequenceName = "emp_idx_seq", allocationSize = 1)
	private int idx;
	@Column(name = "NAME", nullable = false)
	private String name;
	@Column(name = "ROLE", nullable = false)
	private String role;
}
