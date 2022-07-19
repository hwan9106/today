package kr.human.mvc13.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
//@XmlRootElement
public class FileBoardVO {
	private int idx;
	private String name;
	private String password;
	private String subject;
	private String content;
	private Date   regDate;
	private String ip;
	
	// 첨부파일의 정보를 저장할 필드 추가
	private List<UpFileVO> fileList;
}
