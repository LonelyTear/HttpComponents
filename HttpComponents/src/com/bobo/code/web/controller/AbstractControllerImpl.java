package com.bobo.code.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.bobo.code.model.Student;
import com.bobo.code.service.impl.StudentService;
//注释1 标注它是一个Controller
@Controller
//注释2 标注它直接用这个地址可以访问 
@RequestMapping("/abstractControllerImpl/go.do")
//最原始的Controller,已废用,因为要extends,且不能自定义
public class AbstractControllerImpl extends AbstractController {
	// 注释3 标注它必须存在,如果不存在就报错,默认为true
	@Autowired(required = true)
	// 注释4 标注它指向一个被@Service("studentService") 的类
	@Qualifier("studentService")
	StudentService studentService;

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	 @RequestMapping(method = { RequestMethod.GET })  //注释5  controller默认被调用方法
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Student student = new Student(1, "bobo", "兰亭",9000.0,new Date());
		Map map = new HashMap();
		map.put("student", student);
		map.put("other", "otherObject");
		// request.setAttribute("user",user);
		// return new ModelAndView("user/listUsers");
		// 默认会在前面加一层User路径/
		return new ModelAndView("jsp/student/listStudents.jsp", map);
	}

	 

	 
	
}
