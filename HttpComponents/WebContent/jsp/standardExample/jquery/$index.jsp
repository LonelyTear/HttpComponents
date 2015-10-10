<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/base.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/WEB-INF/jsp/common/baseJS.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/common/baseCSS.jsp"></jsp:include>
<title>$index().jsp</title>
</head>
<body>
	<ul>
	  <li id="foo">foo</li>
	  <li id="bar">bar</li>
	  <li id="baz">baz</li>
	</ul>
	<a href="http://tool.oschina.net/uploads/apidocs/jquery/index2.html">$("xxx").index("xxx")相关资料</a>
	<button id="submit" onclick="clickMe()">click</button>
	<script type="text/javascript">
		function clickMe(){
			var i = $('li').index($("#bar")); //1，传递一个DOM对象，返回这个对象在原先集合中的索引位置
			alert(i);
		}
	</script>
</body>
</html>