package kr.human.mvc13.service;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.human.mvc13.dao.FileBoardDAO;
import kr.human.mvc13.dao.UpFileDAO;
import kr.human.mvc13.vo.CommVO;
import kr.human.mvc13.vo.FileBoardVO;
import kr.human.mvc13.vo.PagingVO;
import kr.human.mvc13.vo.UpFileVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service("fileBoardService")
public class FileBoardServiceImpl implements FileBoardService{

	@Autowired
	private FileBoardDAO fileBoardDAO;
	@Autowired
	private UpFileDAO upFileDAO;
	
	@Override
	public PagingVO<FileBoardVO> selectList(CommVO commVO) {
		log.info("{}의 selectList 호출 : {}", this.getClass().getName(), commVO);
		PagingVO<FileBoardVO> pagingVO = null;
		try {
			//전체 개수
			int totalCount = fileBoardDAO.selectCount();
			// 페이징 객체 생성 : 계산 완료
			pagingVO = new PagingVO<>(commVO.getCurrentPage(), commVO.getPageSize(), commVO.getBlockSize(), totalCount);
			// 목록 가져오기
			HashMap<String, Integer> map = new HashMap<>();
			map.put("startNo", pagingVO.getStartNo());
			map.put("endNo", pagingVO.getEndNo());
			List<FileBoardVO> list = fileBoardDAO.selectList(map);
			// 글이 존재한다면
			if(list!=null && list.size()>0) {
				// 글을 반복하면서 첨부파일의 목록을 가져와서 대입한다.
				for(FileBoardVO vo : list) {
					// 첨부 파일의 목록을 가져온다.
					List<UpFileVO> fileList = upFileDAO.selectByRef(vo.getIdx());
					// vo에 넣는다.
					vo.setFileList(fileList);
				}
				// 완성된 리스트를 페이징 객체에 넣어준다.
				pagingVO.setList(list);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("{}의 selectList 리턴값 : {}", this.getClass().getName(), pagingVO);
		return pagingVO;
	}
	@Override
	public FileBoardVO selectByIdx(int idx) {
		log.info("{}의 selectByIdx 호출 : {}", this.getClass().getName(), idx);
		FileBoardVO vo = null;
		try {
			// 해당 글번호의 글을 가져온다.
			vo = fileBoardDAO.selectByIdx(idx);
			//해당 글이 존재 한다면 첨부파일의 목록을 가져온다
			if(vo!=null) vo.setFileList(upFileDAO.selectByRef(vo.getIdx()));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("{}의 selectByIdx 리턴 : {}", this.getClass().getName(), vo);
		return vo;
	}
	@Override
	public void insert(FileBoardVO fileBoardVO) {
		log.info("{}의 insert 호출 : {}", this.getClass().getName(), fileBoardVO);
		// 컨트롤러에 있는 파일을 저장하는 부분을 유틸리티 클래스로 빼주면 컨트롤러가 간단해 진다.
		if(fileBoardVO!=null) {
			// 1. 글을 조장한다.
			fileBoardDAO.insert(fileBoardVO);
			// 저장을 했으면 저장된 idx값을 얻어온다
			int ref = fileBoardDAO.selectSeq();
			// 2. 첨부 파일의 정보도 저장해 주어야 한다.
			List<UpFileVO> list = fileBoardVO.getFileList();
			if(list!=null && list.size()>0) {
				for(UpFileVO vo : list) {
					vo.setRef(ref); // 원본글번호
					upFileDAO.insert(vo);
				}
			}
		}
	}
	@Override
	public void delete(FileBoardVO fileBoardVO, String uploadPath) {
		log.info("{}의 update 호출 : {}", this.getClass().getName(), fileBoardVO + "\n" + uploadPath);
		FileBoardVO dbVO = fileBoardDAO.selectByIdx(fileBoardVO.getIdx());
		if(dbVO!=null && dbVO.getPassword().equals(fileBoardVO.getPassword())) { // DB의 비번과 입력한 비번이 같은 경우에만
			// 글삭제
			fileBoardDAO.delete(fileBoardVO.getIdx());
			// 모든 첨부파일의 목록을 읽어온다.
			List<UpFileVO> list = upFileDAO.selectByRef(fileBoardVO.getIdx());
			if(list!=null && list.size()>0) {
				for(UpFileVO vo : list) {
					// DB 파일 삭제
					upFileDAO.deleteByIdx(vo.getIdx());
					// 실제 파일삭제
					File file = new File(uploadPath + File.separator + vo.getSaveFileName());
					file.delete();
				}
			}
		}
	}
	
	@Override
	public void update(FileBoardVO fileBoardVO, String[] delFiles, String realPath) {
		log.info("{}의 update 호출 : {}", this.getClass().getName(), fileBoardVO + "\n" + Arrays.toString(delFiles) + "\n" + realPath);
		FileBoardVO dbVO = fileBoardDAO.selectByIdx(fileBoardVO.getIdx());
		if(dbVO!=null && dbVO.getPassword().equals(fileBoardVO.getPassword())) { // DB의 비번과 입력한 비번이 같은 경우에만
			// 1. 글수정
			fileBoardDAO.update(fileBoardVO);
			// 2. 새롭게 첨부된 첨부 파일의 정보도 저장해 주어야 한다.
			List<UpFileVO> list = fileBoardVO.getFileList();
			if(list!=null && list.size()>0) {
				for(UpFileVO vo : list) {
					vo.setRef(fileBoardVO.getIdx()); // 원본글번호
					upFileDAO.insert(vo);
				}
			} 
			// 3, 이미 첨부되었던 파일 삭제
			log.info("{}의 update delFiles : {}", this.getClass().getName(), delFiles);
			if(delFiles!=null && delFiles.length>0) {
				for(String t : delFiles ) {
					int idx = Integer.parseInt(t);
					if(idx>0) {
						// 실제 파일을 삭제하려면
						// 1. 해당 글번호의 글을 읽어와서
						UpFileVO fileBoardFileVO = upFileDAO.selectByIdx(idx);
						if(fileBoardFileVO!=null) {
							// 2. 실제 서버의 파일을 삭제해 주어야 한다.
							File file = new File(realPath + File.separator + fileBoardFileVO.getSaveFileName());
							file.delete(); // 실제 파일삭제
							upFileDAO.deleteByIdx(idx); // DB에서만 삭제된다.
						}
					}
				}
			}
		}
	}
}
