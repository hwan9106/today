package kr.human.di.vo;

public class ActiveMQMessaging implements Messaging{

	@Override
	public void sendMessage() {
		System.out.println("Sending Message via Active MQ");
	}
	
}
