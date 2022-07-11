package kr.human.ex08.dao;

import org.springframework.data.repository.CrudRepository;

import kr.human.ex08.vo.Student;

public interface StudentDAO extends CrudRepository<Student, Integer>{

}
