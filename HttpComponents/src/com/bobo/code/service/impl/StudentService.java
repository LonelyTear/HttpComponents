package com.bobo.code.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bobo.code.dao.StudentDAO;
import com.bobo.code.model.Student;


/**
* @Service用于标注业务层组件\n
* @Controller用于标注控制层组件(如struts中的action)\n
* @Repository用于标注数据访问组件,即DAO组件\n
* @Component泛指组件，当组件不好归类的时候,我们可以使用这个注解进行标注. \n
* 如果只用@Service注解,那么bean名默认为studentService,即类名首字母小写  
*/

@Service("studentService")
public class StudentService {
	@Autowired(required = true)
	@Qualifier("studentDAO")
	StudentDAO studentDAOImpl;


	public StudentDAO getStudentDAOImpl() {
		return studentDAOImpl;
	}


	public void setStudentDAOImpl(StudentDAO studentDAOImpl) {
		this.studentDAOImpl = studentDAOImpl;
	}


	public void insertOrUpdateStudent(Student student){
		if( student.getId() == null){//没有id,那么肯定是新增
			studentDAOImpl.insert(student);
		}else{//已有id,那么肯定是更新
			studentDAOImpl.update(student);
		}
	}
	
	public List select(Student student){
		List<Student> stuList = studentDAOImpl.select(student);
		return stuList;
	}

	public List<Student> select(Map<String, Object> queryMap) {
		List<Student> stuList = studentDAOImpl.select(queryMap);
		return stuList;
	}

	public Integer delete(Student student) {
		Integer effectCount = studentDAOImpl.delete(student);
		return effectCount;
	}


	public int count(String string, Map<String, Object> queryMap) {
		Integer effectCount = studentDAOImpl.count(queryMap);
		return effectCount;
	}


	
}
