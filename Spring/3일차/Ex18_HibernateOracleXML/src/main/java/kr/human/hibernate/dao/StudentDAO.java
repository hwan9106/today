package kr.human.hibernate.dao;

import java.util.List;
import org.hibernate.Session;
import kr.human.hibernate.vo.Student;

public interface StudentDAO {
	// 저장
	int insert(Session session,  Student student)  throws Exception;
	// 수정
	void update(Session session, Student student)  throws Exception;
	// 삭제
	void delete(Session session, int   id) throws Exception;
	// 모두 얻기
	List<Student> selectList(Session session)  throws Exception;
	// 1개 얻기
	Student selectById(Session session, int id)  throws Exception;
}
