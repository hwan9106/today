package kr.human.di.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.human.di.vo.AnswerVO;
import kr.human.di.vo.QuestionVO;
import kr.human.di.vo.UserVO;

@Configuration
public class QuestionConfig {

	@Bean(name = "map")
	public Map<AnswerVO, UserVO> getMap(){
		Map<AnswerVO, UserVO> map = new HashMap<>();
		map.put(new AnswerVO(1, "자바", new Date(System.currentTimeMillis())), new UserVO(1, "한사람", "admin@xxxx.com"));
		map.put(new AnswerVO(2, "HTML", new Date(System.currentTimeMillis())), new UserVO(2, "두사람", "root@xxxx.com"));
		return map;
	}

	@Bean(name = "question1")
	public QuestionVO questionVO1() {
		return new QuestionVO(1, "주인장", getMap());
	}

	@Bean(name = "question2")
	public QuestionVO questionVO2() {
		Map<AnswerVO, UserVO> map = new HashMap<>();
		map.put(new AnswerVO(1, "자바", new Date(System.currentTimeMillis())), new UserVO(1, "한사람", "admin@xxxx.com"));
		map.put(new AnswerVO(2, "HTML", new Date(System.currentTimeMillis())), new UserVO(2, "두사람", "root@xxxx.com"));
		return new QuestionVO(2, "나그네", map);
	}
}
