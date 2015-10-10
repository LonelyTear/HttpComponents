package com.bobo.code.dao;

import java.util.List;
import java.util.Map;

import com.bobo.code.model.Student;

public interface StudentDAO {

	public List<Student> select(Student student);

	public List<Student> select(Map<String, Object> queryMap);
	
	public Integer delete(Student student);

	public Integer insert(Student student);

	public Integer update(Student student);

	public Integer count(Map<String, Object> queryMap);


}
