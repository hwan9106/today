package kr.human.di.vo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Bond {
 
    @Autowired
    //@Qualifier("mustang")
    @Qualifier("ferari") //동일한타입의 객체가 복수로 존재할 경우 지정해준다.
    private Car car;
     
    public void showCar(){
        car.getCarName();
    }
}
