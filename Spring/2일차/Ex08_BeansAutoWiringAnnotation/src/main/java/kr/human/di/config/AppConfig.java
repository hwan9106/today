package kr.human.di.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("kr.human.di.vo")//"kr.human.di.vo" 패키지에 있는 클래스들을 검색해서 자바빈을 자동으로 등록해라~!
public class AppConfig {

}
