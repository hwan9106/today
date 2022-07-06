package kr.human.di.vo;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component //빈을 자동으로 등록하라~!
public class Application {
	
	//자동으로 주입해라~! 
	@Resource(name="applicationUser")
    private ApplicationUser applicationUser;

}
