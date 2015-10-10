package com.bobo.code.web.controller.technology.xml;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bobo.code.model.Student;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//注释1 标注它是一个Controller
@Controller
@RequestMapping(value = XmlController.DIR)
public class XmlController {
	private static int adder = 0;
	protected static final String DIR = "xml/*";
	
	/** 基本ajax_xml	 */
	@RequestMapping(value = "object.do", method = {RequestMethod.POST, RequestMethod.GET})
	public void object( HttpServletRequest req, HttpServletResponse resp) throws IOException{
		System.out.println(++adder);
		Student stu = new Student(1,"bobo","兰亭",3000.0,new Date());
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();//创建带格式化的gson
		String jsonStr = gson.toJson(stu);//gson和json-lib还有一个优雅的地方在于对于那些null,不再放入json中
//		得到的字符串必须是 --> {'键':'值','键':'值'} 而不是   {键:值，键:值}
		System.out.println(jsonStr);
		PrintWriter pw = resp.getWriter();
		pw.print(jsonStr);
		pw.close();
	}
	
	@RequestMapping(value = "list.do", method = {RequestMethod.POST, RequestMethod.GET})
	public void list( HttpServletRequest req, HttpServletResponse resp) throws IOException{
		System.out.println(++adder);
		Student stu1 = new Student(1,"bobo","浙江",3000.0,null);
		Student stu2 = new Student(2,"sisi","上海",8000.0,null);
		ArrayList list = new ArrayList<Student>();
		list.add(stu1);
		list.add(stu2);
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		System.out.println(jsonStr);
		PrintWriter pw = resp.getWriter();
		pw.print(jsonStr);
		pw.close();
	}
	
	@RequestMapping(value = "map.do", method = {RequestMethod.POST, RequestMethod.GET})
	public void map( HttpServletRequest req, HttpServletResponse resp) throws IOException{
		System.out.println(++adder);
		Student stu1 = new Student(1,"bobo","浙江",3000.0,null);
		Student stu2 = new Student(2,"sisi","上海",8000.0,null);
		TreeMap<String,Student> map = new TreeMap<String,Student>();
		map.put("bobo",stu1);
		map.put("sisi",stu2);
		Gson gson = new Gson();
		String jsonStr = gson.toJson(map);
		System.out.println(jsonStr);
		PrintWriter pw = resp.getWriter();
		pw.print(jsonStr);
		pw.close();
	}
	
	@RequestMapping(value = "map_list.do", method = {RequestMethod.POST, RequestMethod.GET})
	public void map_list( HttpServletRequest req, HttpServletResponse resp) throws IOException{
		System.out.println(++adder);
		
		Student stu11 = new Student(1,"bobo","浙江",3000.0,null);
		Student stu12 = new Student(2,"sisi","上海",8000.0,null);
		ArrayList list1 = new ArrayList<Student>();
		list1.add(stu11);
		list1.add(stu12);
		
		Student stu21 = new Student(3,"haha","北京",3000.0,null);
		Student stu22 = new Student(4,"hehe","广州",8000.0,null);
		ArrayList list2 = new ArrayList<Student>();
		list2.add(stu21);
		list2.add(stu22);
		
		TreeMap<String,ArrayList> map = new TreeMap<String,ArrayList>();
		map.put("teacher1",list1);
		map.put("teacher2",list2);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(map);
		System.out.println(jsonStr);
		PrintWriter pw = resp.getWriter();
		pw.print(jsonStr);
		pw.close();
	}
}

