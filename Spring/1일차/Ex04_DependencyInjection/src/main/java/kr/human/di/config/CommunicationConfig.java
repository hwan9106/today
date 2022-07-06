package kr.human.di.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import kr.human.di.vo.ActiveMQMessaging;
import kr.human.di.vo.Communication;
import kr.human.di.vo.Encryption;
import kr.human.di.vo.Messaging;
import kr.human.di.vo.RSAEncryption;
@Configuration
public class CommunicationConfig {

	
	@Bean(name = "communication")
    public Communication communication() {
		//생성자를 통한 주입
        Communication communication = new Communication(encryption());
        //Setter를 통한 주입
        communication.setMessaging(messaging());
        return communication;
    }
 
    
    @Bean(name = "messaging")
    @Description("This bean will be injected via setter injection")
    public Messaging messaging() {
        return new ActiveMQMessaging();
    }
    
    @Bean(name = "encryption")
    public Encryption encryption() {
    	return new RSAEncryption();
    }
}
