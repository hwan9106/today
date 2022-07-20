package kr.green.member.service;

import java.util.HashMap;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.green.member.dao.MemberDAO;
import kr.green.member.dao.MemberRoleDAO;
import kr.green.member.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MemberRoleDAO memberRoleDAO;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public MemberVO loginOk(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO logoutOk(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(MemberVO memberVO) {
		log.info("insert 호출 : " + memberVO);
		if(memberVO!=null) {
			// DB에 저장한다.
			memberDAO.insert(memberVO);
			memberRoleDAO.insert(memberVO.getUserid());
			// 메일을 발송한다.
			sendEmail(memberVO);
		}
	}

	@Override
	public MemberVO update(MemberVO memberVO) {
		if(memberVO!=null) {
			// DB에서 정보를 받아와 비번이 일치할때만 수정을 실행한다.
			MemberVO dbVO = memberDAO.selectByIdx(memberVO.getIdx());
			if(dbVO!=null) { // db에 해당 idx의 멤버가 존재하면
				String dbPassword = dbVO.getPassword(); // 암호화된 내용을 DB에서 가져옴
				if(bCryptPasswordEncoder.matches(memberVO.getPassword(), dbPassword)) { // 암호화된 비번 일치여부 확인
					// 회원 정보 수정
					memberDAO.update(memberVO);
					// 수정된 정보를 다시 얻는다.
					dbVO = memberDAO.selectByIdx(memberVO.getIdx());
					return dbVO;
				}
			}
		}
		return null;
	}

	@Override
	public void delete(MemberVO memberVO) {
		if(memberVO!=null) {
			MemberVO vo = memberDAO.selectUserId(memberVO.getUserid()); // 해당 아이디의 정보 가져오기
			if(vo!=null) { // 있다면
				String dbPassword = vo.getPassword(); // 암호화된 내용을 DB에서 가져옴
				if(bCryptPasswordEncoder.matches(memberVO.getPassword(), dbPassword)) { // 암호화된 비번 일치여부 확인
					// 회원 탈퇴
					memberDAO.delete(vo.getIdx());
				}
			}
		}
	}

	@Override
	public void updatePassword(MemberVO memberVO) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userid", memberVO.getUserid());	
		// password는 암호화해서 저장되어야 한다.
		String encryptPassword = bCryptPasswordEncoder.encode(memberVO.getPassword());
		map.put("password", encryptPassword);		
		memberDAO.updatePassword(map);
	}

	@Override
	public MemberVO updateUse(String userid, String col1) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("use", "1");
		map.put("userid", userid);
		map.put("col1", col1);
		memberDAO.updateUse(map); // DB의 use값을 변경하고
		return memberDAO.selectUserId(userid); // 변경된 값을 리턴
	}

	@Override // 동일한 아이디의 개수를 리턴한다. 0이면 없는 아이디 그외의 수면 존재하는 아이디  
	public int idCheck(String userid) {
		return memberDAO.selectCountByUserId(userid);
	}

	@Override // 아이디 찾기 : 이름과 전화번호를 넘겨서 DB에서 가져온다.
	public MemberVO idSearch(MemberVO memberVO) {
		MemberVO vo = null;
		if(memberVO!=null) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("username", memberVO.getUsername());
			map.put("hp", memberVO.getHp());
			vo = memberDAO.selectByUsername(map);
		}
		return vo;
	}
	
	// 임시비밀번호를 만들어주는 메서드
	public String makePassword(int length) {
		Random random = new Random();
		String password="";
		String str="~@!#$%^&*+-*";
		for(int i=0;i<length;i++) {
			// case의 개수가 많을수록 나타날 확율이 높아진다.
			switch (random.nextInt(20)) { // 0(숫자), 1(영어소문자), 2(영어 대문자), 3(특수문자)
			case 0: case 18: case 19:
				password += (char)('0' + random.nextInt(10));
				break;
			case 1: case 4: case 5: case 6: case 7: case 8: case 9: case 10:
				password += (char)('a' + random.nextInt(26));
				break;
			case 2: case 11: case 12: case 13: case 14: case 15: case 16: case 17:
				password += (char)('A' + random.nextInt(26));
				break;
			case 3:
				password += str.charAt(random.nextInt(str.length()));
				break;
			}
		}
		return password;
	}
	
	
	

	@Override
	public MemberVO passwordSearch(MemberVO memberVO) {
		MemberVO vo = null;
		if(memberVO!=null) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("userid", memberVO.getUserid());
			map.put("hp", memberVO.getHp());
			vo = memberDAO.selectByUserId(map);
		}
		return vo;
	}
	
	// 회원 가입시 인증 메일 보내는 메서드 
	public void sendEmail(MemberVO memberVO) {
		String subject = "회원 가입을 축하드립니다.";
		String to = memberVO.getEmail();
		String content = "반갑습니다. " + memberVO.getUsername() + "님!!!<br>"
                + "회원 가입을 축하드립니다.<br> "
        		+ "회원 가입을 완료하려면 다음의 링크를 클릭해서 인증하시기 바랍니다.<br>"
                + "<a href='http://localhost:8081/member/confirm?userid="+memberVO.getUserid()+"&col1="+memberVO.getCol1()+"'>인증</a><br>";
		
        MimeMessagePreparator preparator = getMessagePreparator(to, subject, content);
        try {
            mailSender.send(preparator);
            System.out.println("메일 보내기 성공 ***************************************************");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
	
	// 메일 내용 완성
    private MimeMessagePreparator getMessagePreparator(String to, String subject, String content) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
 
            public void prepare(MimeMessage mimeMessage) throws Exception {
            	MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            	helper.setFrom("hwan9532@naver.com");
            	helper.setTo(to);
            	helper.setSubject(subject);
            	helper.setText(content, true);
            }
        };
        return preparator;
    }

	@Override // 비번이 일치하는 객체 리턴하기
	public MemberVO selectByUserId(MemberVO memberVO) {
		MemberVO vo = null;
		if(memberVO!=null) {
			vo = memberDAO.selectUserId(memberVO.getUserid());
			if(vo!=null) {
				String dbPassword = vo.getPassword(); // 암호화된 내용을 DB에서 가져옴
				if(!bCryptPasswordEncoder.matches(memberVO.getPassword(), dbPassword)) { // 암호화된 비번 일치여부 확인
					return null; // 일치하지않으면 null리턴
				}
			}
		}
		return vo; // 일치하면 vo리턴
	}

}
