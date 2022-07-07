package kr.human.email.service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailServiceImpl implements MailService{

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendEmail() {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				// 보낼 메일의 양식을 만든다.
				mimeMessage.setFrom("hwan9532@naver.com"); // XML 인증 계정과 반드시 같아야 한다.
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("hwan9532@naver.com"));
				mimeMessage.setSubject("나는 제목입니다.");
				mimeMessage.setText("아마도 나는 내용일거다. <h1>태그가 먹을까?</h1>"); // 텍스트로 전송
			}
		};
		try {
			mailSender.send(preparator);
			System.out.println("메일 발송 성공!!!");
		}catch (Exception e) {
			System.err.print("에러 : " + e.getMessage());
		}
	}

	@Override
	public void sendEmail(String toAddress, String subject, String content) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				// 보낼 메일의 양식을 만든다.
				mimeMessage.setFrom("hwan9532@naver.com"); // XML 인증 계정과 반드시 같아야 한다.
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
				mimeMessage.setSubject(subject);
				// mimeMessage.setText(content);
				mimeMessage.setContent(content, "text/html;charset=UTF-8"); // HTML로 전송하기
				
				/*
				Multipart multipart = new MimeMultipart();
	            MimeBodyPart bodyPart = new MimeBodyPart();
	            bodyPart.setContent(content, "text/html;charset=UTF-8");
	            multipart.addBodyPart(bodyPart);
	            mimeMessage.setContent(multipart); // 내용
				 */
			}
		};
		try {
			mailSender.send(preparator);
			System.out.println("메일 발송 성공!!!");
		}catch (Exception e) {
			System.err.print("에러 : " + e.getMessage());
		}
		
	}
}
