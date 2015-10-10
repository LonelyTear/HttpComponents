<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<!-- 最好在上面添加isErrorPage="true"标记  -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div style="height:300;width:300;background-color: red" align="center">
	您已跳转到errorPage.jsp页面
	<br />
	<textarea cols="150" rows="100"><%=exception.toString()%></textarea>
	<a href="javascript: history.back();">返回</a>
	</div>
</body>
</html>