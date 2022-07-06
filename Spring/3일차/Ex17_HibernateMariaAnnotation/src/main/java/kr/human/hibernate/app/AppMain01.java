package kr.human.hibernate.app;

import java.util.List;

import kr.human.hibernate.service.StudentServiceImpl;
import kr.human.hibernate.vo.Student;

public class AppMain01 {
	public static void main(String[] args) {
		Student student = new Student();
		student.setId(0);
		student.setFirstName("한");
		student.setLastName("사람");
		student.setSection("자바프로그래머");
		/*
		StudentServiceImpl.getInstance().insert(student);
		StudentServiceImpl.getInstance().insert(student);
		StudentServiceImpl.getInstance().insert(student);
		 */
		List<Student> list = StudentServiceImpl.getInstance().selectList();
		if(list!=null && list.size()>0) {
			System.out.println("전체 : " + list.size() + "명");
			for(Student s : list) System.out.println(s);
			System.out.println("-".repeat(80));
		}
		
		Student student2 = StudentServiceImpl.getInstance().selectById(4);
		System.out.println("수정 전 : " + student2);
		student2.setSection("백앤드개발자");
		StudentServiceImpl.getInstance().update(student2);
		student2 = StudentServiceImpl.getInstance().selectById(4);
		System.out.println("수정 후 : " + student2);
		
		StudentServiceImpl.getInstance().delete(9);
		
		list = StudentServiceImpl.getInstance().selectList();
		if(list!=null && list.size()>0) {
			System.out.println("전체 : " + list.size() + "명");
			for(Student s : list) System.out.println(s);
			System.out.println("-".repeat(80));
		}
	}
}
