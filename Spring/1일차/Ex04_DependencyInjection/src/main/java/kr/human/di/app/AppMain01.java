package kr.human.di.app;

import kr.human.di.vo.ActiveMQMessaging;
import kr.human.di.vo.Communication;
import kr.human.di.vo.Encryption;
import kr.human.di.vo.Messaging;
import kr.human.di.vo.RSAEncryption;


//기존에 하던 자바 DI방식
public class AppMain01 {
	public static void main(String[] args) {
		Messaging messaging = new ActiveMQMessaging();
		Encryption encryption = new RSAEncryption();
		
		Communication communication = new Communication(encryption); //생성자를 이용한 주입
		
		
		communication.setMessaging(messaging); //Setter를 통해서 주십
		communication.communicate(); // 사용
	}
}
