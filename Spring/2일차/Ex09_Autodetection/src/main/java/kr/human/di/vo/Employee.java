package kr.human.di.vo;



import org.joda.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
	private int id;	 
    private String name;
    private LocalDate assessmentDate;
}
