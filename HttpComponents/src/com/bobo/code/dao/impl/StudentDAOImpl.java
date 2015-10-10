package com.bobo.code.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bobo.code.dao.BaseDao;
import com.bobo.code.dao.StudentDAO;
import com.bobo.code.model.Student;
@Repository("studentDAO")
public class StudentDAOImpl  extends BaseDao implements StudentDAO{
	
	@Override
	public List<Student> select(Student student) {
		List<Student> stuList = null;
		stuList = getSqlMapClientTemplate().queryForList("student.select", student);
		return stuList;
	}

	@Override
	public List<Student> select(Map<String,Object> queryMap) {
		List<Student> stuList = null;
		stuList = getSqlMapClientTemplate().queryForList("student.select", queryMap);
		return stuList;
	}
	
	@Override
	public Integer delete(Student student) {
			return getSqlMapClientTemplate().delete("student.delete", student);
	}

	@Override
	public Integer insert(Student student) {
			return   (Integer) getSqlMapClientTemplate().insert("student.insertStudent", student);
	}

	@Override
	public Integer update(Student student) {
			return getSqlMapClientTemplate().update("student.updateStudent", student);
	}

	@Override
	public Integer count(Map<String, Object> queryMap) {
		return (Integer)getSqlMapClientTemplate().queryForObject("student.count", queryMap);
	}
}