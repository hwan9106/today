package kr.human.hibernate.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.human.hibernate.vo.Student;

@Repository("studentDAO")
@Transactional // 이러면 트랜젝션을 알아서 함
public class StudentDAOImpl implements StudentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void insert(Student student) {
		sessionFactory.openSession().save(student); //persist도 저장, save도 저장
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Student> selectList() {
		return sessionFactory.openSession().createQuery("FROM Student ORDER BY id desc").list();
	}

	@Override
	public Student selectById(int id) {
		return sessionFactory.openSession().find(Student.class, id);
	}

	@Override
	public void update(Student student) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			
			Student student2 = (Student) session.get(Student.class, student.getId());
			student2.setFirstName(student.getFirstName());
			student2.setLastName(student.getLastName());
			student2.setSection(student.getSection());
			session.update(student2);
			
			session.getTransaction().commit();
		}catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	@Override
	public void delete(int id) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			
			Student student2 = (Student) session.get(Student.class, id);
			session.delete(student2);
			
			session.getTransaction().commit();
		}catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	
}
