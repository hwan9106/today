package kr.human.hibernate.dao;

import java.util.List;

import org.hibernate.Session;

import kr.human.hibernate.vo.Student;

public class StudentDAOImpl implements StudentDAO {
	private static StudentDAO instance = new StudentDAOImpl();
	private StudentDAOImpl() {}
	public static StudentDAO getInstance() {
		return instance;
	}
	//---------------------------------------------------------------------------------
	@Override
	public int insert(Session session, Student student) throws Exception {
		int id = 0;
		id = (int) session.save(student);
		return id;
	}

	@Override
	public void update(Session session, Student student) throws Exception {
		Student dbStudent = (Student) session.get(Student.class, student.getId());
		dbStudent.setSection(student.getSection());
        // session.update(student); // 호출하지 않아도 수정이 된다.
	}

	@Override
	public void  delete(Session session, int id) throws Exception {
		Student student = (Student) session.get(Student.class, id);
        session.delete(student);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> selectList(Session session) throws Exception {
		// 테이블의 필드명이 아니라 VO의 필드명 사용!!!
		return (List<Student>) session.createQuery("FROM Student ORDER BY firstName").list();
	}

	@Override
	public Student selectById(Session session, int id) throws Exception {
		return (Student) session.get(Student.class, id);
	}

}
