package kr.human.hibernate.service;

import java.util.List;

import kr.human.hibernate.vo.Student;

public interface StudentService {
	// 저장
	int insert( Student student);
	// 수정
	void update(Student student);
	// 삭제
	void delete(int   id);
	// 모두 얻기
	List<Student> selectList();
	// 1개 얻기
	Student selectById(int id);
}
