package kr.human.email.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.spring.VelocityEngineUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;

@Service("mailService")
public class MailServiceImpl implements MailService{

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private VelocityEngine velocityEngine;
	@Autowired
	private Configuration freemarkerConfiguration;
	
	@Override
	public void sendEmail() {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				// 보낼 메일의 양식을 만든다.
				helper.setFrom("hwan9532@naver.com"); // XML 인증 계정과 반드시 같아야 한다.
				helper.setTo("hwan9532@naver.com");
				helper.setSubject("나는 제목입니다.");
				// 템플릿에 바뀌어 저장될 내용을 맵에 저장한다.
	            Map<String, Object> map  = new HashMap<>();
	            map.put("from", "한사람");
	            map.put("nick", "투덜이");
	            map.put("age", "33");
	            
	            helper.setText( getFreeMarkerTemplateContent(map) , true);
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
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				// 보낼 메일의 양식을 만든다.
				helper.setFrom("hwan9532@naver.com"); // XML 인증 계정과 반드시 같아야 한다.
				helper.setTo(toAddress);
				helper.setSubject(subject);
				// 템플릿에 바뀌어 저장될 내용을 맵에 저장한다.
	            Map<String, Object> map  = new HashMap<>();
	            map.put("from", "두사람");
	            map.put("nick", "팡팡이");
	            map.put("age", "32");
	            
	            helper.setText( getVelocityTemplateContent(map) , true);
				
				
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
	public String getVelocityTemplateContent(Map<String, Object> model){
        StringBuffer content = new StringBuffer();
        try{
            content.append(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/vmtemplates/velocity_mailTemplate.html","UTF-8" ,model));
            return content.toString();
        }catch(Exception e){
            System.out.println("Exception occured while processing velocity template:"+e.getMessage());
        }
          return "";
    }
 
 
    public String getFreeMarkerTemplateContent(Map<String, Object> model){
        StringBuffer content = new StringBuffer();
        try{
         content.append(FreeMarkerTemplateUtils.processTemplateIntoString( 
                 freemarkerConfiguration.getTemplate("fm_mailTemplate.html"),model));
         return content.toString();
        }catch(Exception e){
            System.out.println("Exception occured while processing fmtemplate:"+e.getMessage());
        }
          return "";
    }
}
