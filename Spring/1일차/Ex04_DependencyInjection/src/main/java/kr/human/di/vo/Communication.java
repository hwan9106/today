package kr.human.di.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Communication {
	 
    private Messaging messaging;
    private Encryption encryption;
    public Communication(Encryption encryption) {
    	super();
    	this.encryption = encryption;
    }
    public Communication(Messaging messaging) {
    	super();
    	this.messaging = messaging;
    }
    /*
    // 생성자를 이용한 의존성 주입    
    public Communication(Encryption encryption) {
		this.encryption = encryption;
	}

    // Setter를 이용한 의존성 주입
    public void setMessaging(Messaging messaging){
        this.messaging = messaging;
    }
     */
    public void communicate(){
        messaging.sendMessage();
        encryption.encryptData();
    }
}
