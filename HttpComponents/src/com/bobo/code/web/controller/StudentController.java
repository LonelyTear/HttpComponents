package com.bobo.code.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kingtool.JSONUtil;
import kingtool.Obj2MapTool;
import kingtool.constants.ConstantKing;
import kingtool.constants.Enums;
import kingtool.pager.Pager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bobo.code.model.Student;
import com.bobo.code.service.impl.StudentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//注释1 标注它是一个Controller
@Controller
@RequestMapping(value = StudentController.DIR)
public class StudentController {
	protected static final String DIR = "student/*";
	Logger logger = Logger.getLogger(StudentController.class);
	// 注释3 标注它必须存在,如果不存在就报错,默认为true
	@Autowired(required = true)
	// 注释4 标注它指向一个被@Service("studentService") 的类
	@Qualifier("studentService")
	StudentService studentService;

	//带不带前缀 /selectStudentById.do 是同样的效果
	@RequestMapping(value = "insertOrUpdateStudent.do", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView insertOrUpdateStudent(Student student , final ModelMap model) {
//		Student student2 = new Student(1, "bobo", "123456789",9000.0,new Date());
		System.out.println(student);
		studentService.insertOrUpdateStudent(student);
//		System.out.println("listTeachers");
//		studentService.select(student);
		return new ModelAndView("jsp/student/studentsList.jsp", "student", student);
	}
	
	@RequestMapping(value = "toUpdatePage.do",method = {RequestMethod.POST, RequestMethod.GET})  
	public ModelAndView toUpdatePage(Student stu , final ModelMap model) {
		Map<String, Object> queryMap = Obj2MapTool.getMap(stu);
		List<Student> stuList = studentService.select(queryMap);
		Student student = null;
		if(stuList != null && stuList.size() != 0){
			student = stuList.get(0);
		}
		return new ModelAndView("jsp/student/studentAddUpdate.jsp", "student", student);//jsp页面用  ${requestScope.student} 取值
	}
	
	@RequestMapping(value = "selectStudent.do",method = {RequestMethod.POST, RequestMethod.GET})  
	public ModelAndView selectStudent(Integer pageNumber, Student student , final ModelMap model) {
		//【起】用pager
		Pager pager = new Pager(pageNumber, Pager.DEFAULT_PAGE_SIZE);//第一页
		
		//【承】用queryMap
		Map<String, Object> queryMap = Obj2MapTool.getMap(student);
		int totalCount=studentService.count("studentCount", queryMap);//这里必须先Count不能直接查询
		pager.setTotalCount(totalCount);
		
		queryMap.put(Pager.SKIP_RESULT, pager.getStart());
		queryMap.put(Pager.MAX_RESULT, pager.getPageSize());//mysql参数,其实是 where limit start , lines
		
		//【转】用list和totalCount
		List<Student> stuList = studentService.select(queryMap);
		pager.setResult(stuList);
		
		//【合】用pager
		model.put("pager", pager); //功能同下
//		model.addAttribute("stuList", stuList);//功能同下
		
		return new ModelAndView("jsp/student/studentsList.jsp", "stuList", stuList);//jsp页面用  ${requestScope.stuList} 取值
	}

	@RequestMapping(value = "selectStudentByAjax.do", method = {RequestMethod.POST, RequestMethod.GET})
	//暂时无用
	public void selectStudentByAjax(Integer pageNumber, Student student , HttpServletRequest req, HttpServletResponse resp) {
		//【起】用pager
		Pager pager = new Pager(pageNumber, Pager.DEFAULT_PAGE_SIZE);//第一页
		
		//【承】用queryMap
		Map<String, Object> queryMap = Obj2MapTool.getMap(student);
		int totalCount=studentService.count("studentCount", queryMap);//这里必须先Count不能直接查询
		pager.setTotalCount(totalCount);
		
		queryMap.put(Pager.SKIP_RESULT, pager.getStart());
		queryMap.put(Pager.MAX_RESULT, pager.getPageSize());//mysql
		
		//【转】用list和totalCount
		List<Student> stuList = studentService.select(queryMap);
		pager.setResult(stuList);
//		
//		// Map  json 形式返回
		Map<String, Object> map = new HashMap<String, Object>(3);
//		map.put(ConstantKing.STATUS, ConstantKing.SUCCESS);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		map.put("pager",pager);
//		map.put("data", JSONUtil.listToJson(stuList));
		String strMapJson = gson.toJson(map).toString();//最终整合成json
		try {
			PrintWriter pw = resp.getWriter();
			pw.print(strMapJson);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "deleteStudentById.do", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView deleteStudentById(Student student , final ModelMap model) {
		System.out.println(student);
		Integer effectCount = studentService.delete(student);
		return new ModelAndView("jsp/student/studentsList.jsp");
	}
	
	
	@RequestMapping(value = "htmlData.do", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView htmlData(Integer pageNumber, Student student , HttpServletRequest req, HttpServletResponse resp,ModelMap model ) {
		//【起】用pager
		Pager pager = new Pager(pageNumber, Pager.DEFAULT_PAGE_SIZE,req);//第一页
		
		//【承】用queryMap
		Map<String, Object> queryMap = Obj2MapTool.getMap(student);
		int totalCount=studentService.count("studentCount", queryMap);//这里必须先Count不能直接查询
		pager.setTotalCount(totalCount);
		
		queryMap.put(Pager.SKIP_RESULT, pager.getStart());
		queryMap.put(Pager.MAX_RESULT, pager.getPageSize());//mysql
		
		//【转】用list和totalCount
		List<Student> stuList = studentService.select(queryMap);
		pager.setResult(stuList);
		model.addAttribute("pager",pager);
		return new ModelAndView("jsp/student/studentsInfo.jsp");
	}
	
	@RequestMapping(value = "studentDetail.do", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView htmlData(Student student ,ModelMap model ) {
		Map<String, Object> queryMap = Obj2MapTool.getMap(student);
		List<Student> stuList = studentService.select(queryMap);
		Student studentRet = null;
		if(stuList != null && stuList.size() != 0){
			studentRet = stuList.get(0);
		}
		return new ModelAndView("jsp/student/studentDetail.jsp", "student", studentRet);//jsp页面用  ${requestScope.student} 取值
	}
	
	
	public StudentService getStudentService() {
		return studentService;
	}
	
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
}
