package kr.human.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("kr.human.aop")
@EnableAspectJAutoProxy //자동으로 Aspect 어노테이션을 찾아서 등록해준다.
public class AppConfig {

}
