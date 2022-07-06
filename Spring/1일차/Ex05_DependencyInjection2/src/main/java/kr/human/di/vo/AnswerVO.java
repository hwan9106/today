package kr.human.di.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//기본타입 1개와 객체타입 2개를 인수로 가지는 클래스
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnswerVO {
	private int id;
	private String answer;
	private Date postedDate;
}	
