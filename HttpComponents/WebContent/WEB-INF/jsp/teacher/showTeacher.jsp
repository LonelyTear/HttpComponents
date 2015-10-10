<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>showTeacher</title>
</head>
<body>
	<div style="height:20px;width:100%;background-color:maroon;">	</div>
		现在在哪个教室:>>${requestScope.classRoom }  <br/>
		<br/>
		
		<hr/>
		 teacher/travel.do返回后会将后台@ModelAttribute("provinceList")传给select框:
		 <select name="province">
			<c:forEach items="${requestScope.provinceList}" var="province"		varStatus="status">
				<option value="${province.id}">${province.name }</option>
			</c:forEach>
		</select>
		<hr/>
		
		<button onclick="justUrlChange()">点击-循环改变URL地址,但并不和后台交互</button>
		<script type="text/javascript">
		
			function justUrlChange(){
				for(i=0;i<5;i++){
					  var j = i+10;
					  var stateObject = {id: j};//json格式
					  var title = "Wow Title "+i;
					  var newUrl = "/SpringMVC/teacher/inWhichRoom.do?id="+i;
					  history.pushState(stateObject,title,newUrl);
					  /* 第一个参数，是一个Json对象 , 在你储存有关当前URl的任意历史信息.
					  第二个参数,title 就相当于传递一个文档的标题 .
					  第三个参数是用来传递新的URL. 你将看到浏览器的地址栏发生变化而当前页面并没刷新。 */
				}
			}
			
			/* 现在运行，点击浏览器的返回按钮，查看URL是怎么改变的。
			对于每次URL的改变，是因为它存储了历史状态“id”以及对应的值。
			但是我们怎么重新获得历史状态，并且在此基础上做些事情呢？
			我们需要对“popstate”添加事件监听器，这将会在每次历史对象的状态改变的时候触发。 */
			window.addEventListener('popstate', function(event) {//给浏览器添加 "返回" 监听事件 ,且popstate必须小写
				 readState(event.state);//弹出的都是j的信息
			});
	
			/* 现在你会看到无论什么时候你点击返回按钮，
			一个popstate事件就会被触发。
			我们的事件侦听器然后检索历史状态对象与之关联的URL,并提示id的值 */
			function readState(data){
			  alert(data.id);
			}
		</script>
</body>
</html>