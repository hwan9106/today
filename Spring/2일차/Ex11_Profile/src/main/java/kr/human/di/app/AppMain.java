package kr.human.di.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import kr.human.di.config.AppConfig;
import kr.human.di.service.FileService;


public class AppMain {
	public static void main(String[] args) {
		//설정파일을 동적으로 읽을때는 설정파일의 클래스 이름을 지정하지않는다.
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //context.getEnvironment().setActiveProfiles("Development");
        context.getEnvironment().setActiveProfiles("Production");//여기에서 사용할 설정파일을 지정해준다.
        
        context.scan("kr.human.di");  //스캔할 경로 지정
        
        context.refresh(); //다시 읽어라
        
        context.close();
	} 
}
