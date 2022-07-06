package kr.human.di.vo;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionVO {
	private int id;
	private String name;
	private Map<AnswerVO, UserVO> map;
	
	//문제 : QuestionVO클래스를 스프링에 등록하여 사용하는 예제를 완성하시오
	//	  	XML과 Annotation 두가지로 만들어 보세요!!!
}
