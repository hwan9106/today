package kr.human.di.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan("kr.human.di")//"kr.human.di" 패키지에 있는 클래스들을 검색해서 자바빈을 자동으로 등록해라~!
@PropertySource(value = { "classpath:application.properties" }) //프로퍼티 파일을 사용하겠다.
public class AppConfig {
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
