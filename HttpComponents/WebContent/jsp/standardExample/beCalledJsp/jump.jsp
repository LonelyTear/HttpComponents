<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jump.jsp</title>
</head>
<body>
	<div id="forever" style="height:20px;width:100%;background-color:yellow;">
		url中带的参数type的值为:${param.type } <br/>
		成功跳转到jump.jsp页面.
	</div>
	
	<div class="forever" style="height:20px;width:100%;background-color:green;">
		url中带的参数type的值为:${param.type } <br/>
		成功跳转到jump.jsp页面.
	</div>
</body>
</html>