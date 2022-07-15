package kr.human.mvc07.dao;

import org.springframework.data.repository.CrudRepository;

import kr.human.mvc07.vo.Emp;

public interface EmpRepository extends CrudRepository<Emp, Integer>{

}
