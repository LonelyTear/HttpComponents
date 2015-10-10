package com.bobo.code.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bobo.code.model.User;
import com.bobo.code.service.impl.UserService;
//注释1 标注它是一个Controller
@Controller
@RequestMapping(value = UserController.DIR)
public class UserController {
	protected static final String DIR = "user/*";
	private static Logger logger = Logger.getLogger(UserController.class);
	// 注释3 标注它必须存在,如果不存在就报错,默认为true
	@Autowired(required = true)
	// 注释4 标注它指向一个被@Service("userService") 的类
	@Qualifier("userService")
	UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

//	@RequestMapping(value = "/queryById.do", method = RequestMethod.GET)
//	public ModelAndView queryById(	 String id , final ModelMap model) {
//		System.out.println(id);
//		User user = new User("1", "bobo1", "123456789");
////		System.out.println("listTeachers");
////		userService.select(user);
//		return new ModelAndView("jsp/user/listUsers.jsp", "user", user);
//	}
	//带不带前缀 /如 /selectUserById.do 是同样的效果
	@RequestMapping(value = "selectUserById.do", method = RequestMethod.GET)
	public ModelAndView selectByIdWithTail(	 String id , final ModelMap model) {
		System.out.println(id);
		User user = new User("2", "sisi2", "987654321");
//		System.out.println("listTeachers");
//		userService.select(user);
		return new ModelAndView("jsp/user/listUsers.jsp", "user", user);
	}
	
	@RequestMapping(value = "selectUserById{id}.do", method = RequestMethod.GET)
	public ModelAndView selectByIdWithPath(	 @PathVariable String id , final ModelMap model) {
		System.out.println(id);
		User user = new User("2", "bobo2", "123456789");
//		System.out.println("listTeachers");
//		userService.select(user);
		return new ModelAndView("jsp/user/listUsers.jsp", "user", user);
	}
	
	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public ModelAndView login(String id , final ModelMap model) {
		logger.warn("中文 log4j: user id is "+id+" , login success" , new Exception("测试用,非异常,请忽视,请忽视,请忽视"));
		User user = new User("2", "bobo2", "123456789");
//		System.out.println("listTeachers");
//		userService.select(user);
		return new ModelAndView("jsp/user/listUsers.jsp", "user", user);
	}

}
