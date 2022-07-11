package kr.human.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonVO {
	private String name;
	private int age;
	private boolean gender;
	private List<String> hobby;
}
