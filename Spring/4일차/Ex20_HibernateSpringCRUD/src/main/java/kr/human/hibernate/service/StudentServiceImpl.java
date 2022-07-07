package kr.human.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.hibernate.dao.StudentDAO;
import kr.human.hibernate.vo.Student;

@Service("studentService")
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentDAO studentDAO;

	@Override
	public void insert(Student student) {
		studentDAO.insert(student);
	}

	@Override
	public List<Student> selectList() {
		return studentDAO.selectList();
	}

	@Override
	public Student selectById(int id) {
		return studentDAO.selectById(id);
	}

	@Override
	public void update(Student student) {
		studentDAO.update(student);
	}

	@Override
	public void delete(int id) {
		studentDAO.delete(id);
	}
}
