<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Entrance</title>
</head>
<body>
	<font  style="font-size:20px;font-color: red">
	<ul>
		<li> <a href="/SpringMVC/abstractControllerImpl/go.do">   						  /SpringMVC/abstractControllerImpl/go.do____[类注解形式@AbstractControllerImpl extends AbstractController]								</a></li>
		<hr/>
		<h1>User</h1>
		<li> <a href="/SpringMVC/user/selectUserById.do?id=1">		  /SpringMVC/user/selectUserById.do?id=1_____[类+方法注解#RequestMapping]		 </a></li>
		<li> <a href="/SpringMVC/user/selectUserById2.do">		  /SpringMVC/user/selectUserById2.do_____[类+方法注解 (带#PathVariable) #RequestMapping]		 </a></li>
		<li> <a href="/SpringMVC/user/login.do?id=1">		  /SpringMVC/user/login.do?id=1_____[有log4j测试日志,console打印,邮件发送 ]		 </a></li>
		<hr/>
		<h1>Teacher</h1>
		<li> <a href="/SpringMVC/teacher/listTeachers.do">  				  /SpringMVC/teacher/listTeachers.do____[类+方法注解#RequestMapping] 					</a></li>
		<li> <a href="/SpringMVC/teacher/teacher127hasStudents.do?id=1&name=sisi&password=123456">  				  /SpringMVC/teacher/teacher127hasStudents.do?id=1&name=sisi&password=123456____[类+方法注解#RequestMapping] 					</a></li>
		<li> <a href="/SpringMVC/teacher/inWhichRoom.do?id=1">  				  /SpringMVC/teacher/inWhichRoom.do?id=1____[类+方法注解#RequestMapping] 					</a></li>
		<li> <a href="/SpringMVC/teacher/travel.do">  				 /SpringMVC/teacher/travel.do____[类+方法注解#RequestMapping ] 					</a></li>
		<hr/>
		<h1>Student</h1>
		<li> <a href="/SpringMVC/student/insertOrUpdateStudent.do?name=boboOld&address=兰亭&birthDate=1988-01-03">/SpringMVC/student/insertOrUpdateStudent.do?name=boboOld&address=兰亭&birthDate=1988-01-03  (insert)   </a></li>
		<li> <a href="/SpringMVC/student/insertOrUpdateStudent.do?name=boboNew&address=兰亭&birthDate=1988-01-03&id=1">/SpringMVC/student/insertOrUpdateStudent.do?name=boboNew&address=兰亭&birthDate=1988-01-03&id=1 (update)    </a></li>
		<li> <a href="/SpringMVC/student/selectStudent.do?id=2">/SpringMVC/student/selectStudent.do?id=2 </a></li>
		<li> <a href="/SpringMVC/student/deleteStudentById.do?id=3">/SpringMVC/student/deleteStudentById.do?id=3</a></li>
		<li> <a href="/SpringMVC/jsp/main/studentMain.jsp">/SpringMVC/jsp/main/studentMain.jsp(jquery异步无刷新分页)</a></li>
		<hr/>
		<h1>BankAccount</h1>
		<li> <a href="/SpringMVC/bankaccount/remit.do?id=1&remitDestId=2&remitMoney=100"> /SpringMVC/bankaccount/remit.do?id=1&remitDestId=2&remitMoney=100 [id为1的向id为2的帐户汇款100元]    </a></li>
		<hr/>
		<h1>StandardExample<br/>
			<h2>jquery</h2>
		<li> <a href="/SpringMVC/jsp/standardExample/js/jsObjectMethod.jsp"> /SpringMVC/jsp/standardExample/js/jsObjectMethod.jsp  (js像java一样,new对象,再去调方法)  </a></li>
		<li> <a href="/SpringMVC/jsp/standardExample/jquery/$.ajax.jsp"> /SpringMVC/jsp/standardExample/jquery/$.ajax.jsp  () (jquery ajax 请求) </a></li>
		<li> <a href="/SpringMVC/jsp/standardExample/jquery/$.post.jsp"> /SpringMVC/jsp/standardExample/jquery/$.post.jsp  () (jquery post 请求) </a></li>
		<li> <a href="/SpringMVC/jsp/standardExample/jquery/checkbox radio.jsp"> /SpringMVC/jsp/standardExample/jquery/checkbox radio.jsp </a></li>
		<li> <a href="/SpringMVC/jsp/standardExample/jquery/select.jsp"> /SpringMVC/jsp/standardExample/jquery/select.jsp </a></li>
		<li> <a href="/SpringMVC/jsp/standardExample/jquery/$index.jsp"> /SpringMVC/jsp/standardExample/jquery/$index.jsp </a></li>
		<li> <a href="/SpringMVC/jsp/standardExample/jquery/$load.jsp"> /SpringMVC/jsp/standardExample/jquery/$load.jsp </a></li>
		<li> <a href="/SpringMVC/jsp/standardExample/jquery/validate.jsp"> /SpringMVC/jsp/standardExample/jquery/validate.jsp </a></li>
			<h2>css</h2>
		<li> <a href="/SpringMVC/jsp/standardExample/css/position.jsp"> /SpringMVC/jsp/standardExample/css/position.jsp </a></li>
		<li> <a href="">     </a></li>
		<li> <a href="">     </a></li>
		<li> <a href="">     </a></li>
		<hr/>
		<li> <a href="">     </a></li>
		<li> <a href="">     </a></li>
		<li> <a href="">     </a></li>
	</ul>
	</font>
</body>
</html>