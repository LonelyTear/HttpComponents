package com.bobo.code.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bobo.code.model.Province;
import com.bobo.code.model.Teacher;

@Controller
@RequestMapping(value=TeacherController.DIR)
public class TeacherController {
	Logger logger = Logger.getLogger(TeacherController.class);
	//@ModelAttribute 可以作为各种下拉列表的参数,不需要每次都map.addAttrubute("key",value);
	protected static final String DIR = "teacher/*";
	//ModelAttribute 标注该 classRoom会作为model.setAttribute("classRoom",本方法return值)返回给View层.
	//只要是到达本Controller的所有请求都会返回该attribute以供View用${requestScope.classRoom}显示
	 //如果不指定 key 的别名,那么默认以返回值 的类型作为key,
	 //举例说明 Room getClassRoom() { return new Room() ;} 那么默认为 model.setAttribute("room", new Room() ;}
	@ModelAttribute("classRoom") //此处的class 为 key ,
	 protected String getClassRoom() throws Exception { // [在哪个教室] 
		  return  "未知"; //此处return 为 value
	}
	
	// 标注 它直接用teacher/listTeachers.do的GET方式  即可以调用到该方法
	@RequestMapping(value = "/listTeachers.do", method = RequestMethod.GET)
	public ModelAndView listTeachers(	final ModelMap model) {
		return new ModelAndView("jsp/teacher/listTeachers.jsp");
	}
	
	
	/*  请求地址 http://127.0.0.1:8080/SpringMVC/teacher127/hasStudents.do?id=1&name=sisi&password=123456 
	的id,name,password会被赋值到本方法的参数对应的同名属性中去,
	  1. 路径中的{identify}可通过 @Pathvariable注解绑定它传过来的值到方法的参数上,如teacher127中的127会被赋值到{identify}中
	  2. 如果指定@RequestParam,则还能将指定请求参数绑定到方法参数上,如把id=1 绑定到teacherId =1上 */
	@RequestMapping(value = "/teacher{identify}hasStudents.do", method = RequestMethod.GET)
	 protected String hasStudents(
			 	@PathVariable String identify,
			 	HttpServletRequest request,
				HttpServletResponse response,
				Teacher teacher,
				@RequestParam("id") Long teacherId
				) throws Exception {
			System.out.println("teacher identify = " + identify);
		    System.out.println("teacher name = "+ teacher.getName());
		    System.out.println("teacher Id = "+ teacherId);
		  //List<Student> stuList = service.getStudentsByTeacherId(id); //虚拟方法
			return "jsp/teacher/hasStudents.jsp";
	}
	
	 @RequestMapping(value = "/inWhichRoom.do", method = RequestMethod.GET)
	 protected String inWhichRoom(@RequestParam("id") String id, ModelMap map , 
			 @ModelAttribute("room") String room) throws Exception {
		 System.out.println(id);
		 logger.info("inWhichRoom");
		room = "钢琴教室";//String ,Integer等类型无法改变@ModelAttribute的值,前台取值仍为"未知"
		return "jsp/teacher/showTeacher.jsp";
	}
	 
	 
	@ModelAttribute("provinceList")
	//这里的返回值不能是Interface如List,Map,必须是可以实例化的类如ArrayList,HashMap
	protected ArrayList<Province> getProvinces() throws Exception {
		ArrayList<Province> provinceList  = new ArrayList<Province>	();
		provinceList.add(new Province("1","杭州"));
		provinceList.add(new Province("2","北京"));
		provinceList.add(new Province("3","上海"));
		return provinceList;
	}
	 @RequestMapping(value = "/travel.do", method = RequestMethod.GET)
	 protected ModelAndView travel( ModelMap map , 
			 @ModelAttribute("provinceList") ArrayList<Province> provinceList) throws Exception {
		/*方法参数注解@ModelAttribute("provinceList") List<Province>  provinceList 
		必须指向已存在的注解方法@ModelAttribute("provinceList")  List<Province> getProvinces() 才能被使用.
		意为修改完Province对象的属性后再返还给View层*/
		provinceList.add(new Province("4","广州"));
		return new ModelAndView("jsp/teacher/showTeacher.jsp");
	}
	 
}
