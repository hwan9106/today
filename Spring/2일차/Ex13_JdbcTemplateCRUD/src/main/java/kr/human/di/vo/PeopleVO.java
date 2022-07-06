package kr.human.di.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 create table people(
	idx int primary key auto_increment,
	name varchar(200) not null,
	age int default 0
);
 * */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PeopleVO {
	private int 	idx;
	private String  name;
	private int 	age;
}
