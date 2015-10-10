<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/base.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/WEB-INF/jsp/common/baseJS.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/common/baseCSS.jsp"></jsp:include>
<title>$.post.jsp</title>
</head>
<body>
	<form id="myform" action="" method="post">
		<input id="url" type="text" size="100" value="${basePath }/jsp/standardExample/beCalledJsp/jump.jsp"/> <br/>
		<input id="param" type="text" size="100" value="param"/> <br/>
		<button id="submit" >click</button>
	</form>

	<script type="text/javascript">
		$(function(){
			var url = $("#url").val();
			var param = {"param":$("#param").val()};
			$("#submit").click(function(){//没有onclick(function(){}) ,只有click和dbclick
				$.post(url,
					param,
					function(data,textStatus){
						alert(data);
					}
				);
			});
		})
	</script>
</body>
</html>