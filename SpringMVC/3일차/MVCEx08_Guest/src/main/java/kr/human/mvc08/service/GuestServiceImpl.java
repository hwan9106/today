package kr.human.mvc08.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.mvc08.dao.GuestDAO;
import kr.human.mvc08.vo.GuestVO;

@Service("guestService")
public class GuestServiceImpl implements GuestService {

	@Autowired
	private GuestDAO guestDAO;

	@Override //모두 얻기 ===> 목록보기
	public List<GuestVO> selectList() {
		return guestDAO.selectList();
	}

	@Override
	public void insert(GuestVO guestVO) {
		if(guestVO!=null) guestDAO.insert(guestVO);	
	}

	@Override // 답변 저장
	public void reply(GuestVO guestVO) {
		if(guestVO!=null) {
			// ref가 같으면서 나의 seq보다 큰값들 모두 seq를 1씩 증가시킨다.
			HashMap<String, Integer> map  = new HashMap<>();
			map.put("ref", guestVO.getRef());
			map.put("seq", guestVO.getSeq());
			guestDAO.updateSeq(map);
			// 그리고 그자리에 답변을 저장한다.
			// ref는 그대로
			// seq는 +1
			guestVO.setSeq(guestVO.getSeq()+1);
			// lev도 +1
			guestVO.setLev(guestVO.getLev()+1);
			// 저장
			guestDAO.reply(guestVO);
		}
		
	}

	@Override
	public void update(GuestVO guestVO) {
		if(guestVO!=null) {
			// 비번이 맞을때만 수정한다.
			// DB에서 원본을 가져온다.
			GuestVO dbVO = guestDAO.selectByIdx(guestVO.getIdx());
			// 비번을 비교한다.
			if(dbVO!=null && dbVO.getPassword().equals(guestVO.getPassword())) {
				guestDAO.update(guestVO);
			}
		}
	}

	@Override
	public void delete(GuestVO guestVO) {
		if(guestVO!=null) {
			// DB에서 원본을 가져온다.
			GuestVO dbVO = guestDAO.selectByIdx(guestVO.getIdx());
			// 비번을 비교한다.
			if(dbVO!=null && dbVO.getPassword().equals(guestVO.getPassword())) {
				// 자식이 있으면 삭제 표시만 하고 자식이 없으면 지운다.
				HashMap<String, Integer> map  = new HashMap<>();
				map.put("ref", dbVO.getRef());
				map.put("seq", dbVO.getSeq());
				// ref가 같으면서 seq가 크거나 같은 리스트
				List<GuestVO> list = guestDAO.selectSeqList(map);
				int childCount = 0;
				if(list!=null) {
					int lev = list.get(0).getLev(); // 나의 레벨을 가져온다.
					for(int i=1;i<list.size();i++) {
						if(lev>=list.get(i).getLev()) break; // 같은레벨이 나오면 형제이므로 탈출
						childCount++;
					}
				}
				if(childCount==0) { // 자식이 없다면 지운다.
					guestDAO.delete2(guestVO.getIdx()); // 'Y'표시 없이 삭제
				}else { // 자식이 있다면 삭제 표시만 한다.
					guestDAO.updateDel(guestVO.getIdx()); // 삭제값을 'Y'로 변경
				}
				// 자식들이 모두 삭제표시가 있다면 몽땅 지워야 한다.
				List<GuestVO> delList = guestDAO.selectDelList(); // 삭제 표시된 목록
				if(delList!=null) { // 삭제 표시된 놈들이 있다면
					for(GuestVO vo : delList) {
						// 현재 값의 자식들을 구해서 자식의 개수가 없으면 삭제
						HashMap<String, Integer> map2  = new HashMap<>();
						map2.put("ref", vo.getRef());
						map2.put("seq", vo.getSeq());
						// ref가 같으면서 seq가 크거나 같은 리스트
						List<GuestVO> list2 = guestDAO.selectSeqList(map2);
						int count = 0;
						if(list2!=null) {
							int lev = list2.get(0).getLev(); // 나의 레벨을 가져온다.
							for(int i=1;i<list2.size();i++) {
								if(lev>=list2.get(i).getLev()) break; // 같은레벨이 나오면 형제이므로 탈출
								count++;
							}
						}
						if(count==0) { // 삭제 표시가 있으면서 자식이 없다면
							guestDAO.delete1(vo.getIdx());
						}// end if
					} // end for
				}// endif
			}// endif
		}// endif
	}// end method
}
