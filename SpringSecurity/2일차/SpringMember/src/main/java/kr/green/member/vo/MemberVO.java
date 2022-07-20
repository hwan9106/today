package kr.green.member.vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@XmlRootElement
@Data
public class MemberVO {
	private int idx;
	private String userid;
	private String password;
	private String username;
	private String nickname;
	private String email;
	private String gender;
	private String hp;
	private String zipcode;
	private String address1;
	private String address2;
	private String use;
	private Date   regDate;
	private Date   modiDate;
	private String col1;
	private String col2;
	private String col3;
	
}
