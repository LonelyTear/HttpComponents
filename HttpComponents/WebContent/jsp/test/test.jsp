<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/base.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/WEB-INF/jsp/common/baseJS.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/common/baseCSS.jsp"></jsp:include>
<title>JsonController</title>
</head>
<body>
	<a href="javascript:void(0);" onclick="callJsonControllerRetObject('xxxx')">/SpringMVC/json/object.do</a> <br/>
	<div id="pageInfo">prepare</div>
	<script type="text/javascript">
		 
		 
		/* "http://127.0.0.1:8080/SpringMVC/teacher/listTeachers.do" */
		function callJsonControllerRetObject(address){//基础方法
			$.ajax({
				type : "get",
				url : "http://127.0.0.1:8080/SpringMVC/teacher/inWhichRoom.do",
				/* 当返回格式用标准的json时,取得的返回数据data不用再var obj = eval("("+data+")")进行加工,
				但是此时取得的data不易展示,打印出来的都是Object,因为已经被jquery作过特殊处理了,
				所以尽量还是用text吧,这样我们alert(data)可以看到原始数据来进行详细分析*/
				dataType : "html",
				data:{id:1},	
				success : function(data) {
					$("#pageInfo").html(data);
					console.log(data);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
		}
	</script>
</body>
</html>