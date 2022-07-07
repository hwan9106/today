package kr.human.hibernate.service;

import java.util.List;

import kr.human.hibernate.vo.Student;

public interface StudentService {
		// 1. 저장
		void insert(Student student);
		// 2. 모두보기
		List<Student> selectList();
		// 3. 1개보기
		Student selectById(int id);
		// 4. 수정하기
		void update(Student student);
		// 5. 삭제하기
		void delete(int id);
}
