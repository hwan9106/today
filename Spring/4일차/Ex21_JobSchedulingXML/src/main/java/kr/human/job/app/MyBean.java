package kr.human.job.app;

import org.springframework.stereotype.Component;

@Component("myBean")
public class MyBean {
 
    public void printMessage() {
        System.out.println("나는 스프링 스케줄러에 의해서 호출이 될겁니다.!!!!!!!!!!");
    }
    
    public void printMessage2() {
        System.out.println("나는 스프링 스케줄러에 의해서 호출이 될겁니다.~~~~~~~~");
    }
    
    public void printMessage3() {
        System.out.println("나는 스프링 스케줄러에 의해서 호출이 될겁니다.^^^^^^^^^^");
    }
}