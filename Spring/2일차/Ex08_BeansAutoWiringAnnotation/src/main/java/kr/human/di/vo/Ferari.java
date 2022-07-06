package kr.human.di.vo;

import org.springframework.stereotype.Component;

@Component
public class Ferari implements Car{
 
    public void getCarName() {
        System.out.println("This is Ferari");
    }
 
}
