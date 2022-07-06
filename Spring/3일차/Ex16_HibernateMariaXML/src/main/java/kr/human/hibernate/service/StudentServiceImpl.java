package kr.human.hibernate.service;

import java.util.List;

import org.hibernate.Session;

import kr.human.hibernate.app.HibernateUtil;
import kr.human.hibernate.dao.StudentDAOImpl;
import kr.human.hibernate.vo.Student;

public class StudentServiceImpl implements StudentService{
	private static StudentService instance = new StudentServiceImpl();
	private StudentServiceImpl() {}
	public static StudentService getInstance() {
		return instance;
	}
	//---------------------------------------------------------------------------------
	@Override
	public int insert(Student student) {
		int id = 0;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			id = StudentDAOImpl.getInstance().insert(session, student);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return id;
	}
	@Override
	public void update(Student student) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			StudentDAOImpl.getInstance().update(session, student);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}
	@Override
	public void delete(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			StudentDAOImpl.getInstance().delete(session, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}
	@Override
	public List<Student> selectList() {
		List<Student> list = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			list = StudentDAOImpl.getInstance().selectList(session);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public Student selectById(int id) {
		Student student = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			student = StudentDAOImpl.getInstance().selectById(session, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return student;
	}
}
