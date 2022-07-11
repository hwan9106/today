package kr.human.ex08.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.ex08.dao.StudentDAO;
import kr.human.ex08.vo.Student;

@Service
public class StudentService {

	@Autowired
	private StudentDAO studentDAO;
	// 모두 얻기
	public List<Student> selectAll(){
		List<Student> list = new ArrayList<>();
		studentDAO.findAll().forEach(list::add);
		return list;
	}
	// 1개 얻기
	public Optional<Student> selectById(int id) {
		return studentDAO.findById(id);
	}
	// 저장하기
	public Student insert(Student student) {
		return studentDAO.save(student);
	}
	
	// 수정하기
	public Student uodate(Student student) {
		Optional<Student> vo = studentDAO.findById(student.getId());
		if(!vo.isPresent()) {
			return null;
		}
		Student student2 = vo.get();
		student2.setFirstName(student.getFirstName());
		student2.setLastName(student.getLastName());
		student2.setSection(student.getSection());
		return studentDAO.save(student2);
	}
	
	// 삭제하기
	public Student delete(Student student) {
		Optional<Student> vo = studentDAO.findById(student.getId());
		if(!vo.isPresent()) {
			return null;
		}
		Student student2 = vo.get();
		studentDAO.delete(student2);
		return student2;
	}
}
