package kr.human.di.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("kr.human.di")//"kr.human.di" 패키지에 있는 클래스들을 검색해서 자바빈을 자동으로 등록해라~!
public class AppConfig {
	@Autowired
    public DataSource dataSource;
}
