package kr.human.job.app;

import org.springframework.stereotype.Component;

@Component("myBean")
public class MyBean {
	
    public void printMessage() {
        System.out.println("나는 스프링 스케쥴러에 의해서 호출이 될겁이다.!!!!!");
    }
	
    public void printMessage2() {
    	System.out.println("나는 스프링 스케쥴러에 의해서 호출이 될겁이다.~~~~~~");
    }
	
    public void printMessage3() {
    	System.out.println("나는 스프링 스케쥴러에 의해서 호출이 될겁이다.^^^^^^^");
    }
}