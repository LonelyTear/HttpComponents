<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/base.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/WEB-INF/jsp/common/baseJS.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/common/baseCSS.jsp"></jsp:include>
<title>select.jsp</title>
</head>
<body>
	<a href="http://www.cnblogs.com/liuling/archive/2013/02/07/sdafsd.html" >$.ajax,$.get,$.post资料</a><br/>
	<a href="http://blog.sina.com.cn/s/blog_4f925fc30100la36.html" >$.ajax资料</a><br/>
	<a href="http://www.cnblogs.com/mybest/archive/2011/12/13/2285730.html" >$相关链接error</a><br/>
	<input id="url" type="text" size="100" value="${basePath }/jsp/standardExample/beCalledJsp/jump.jsp"/> <br/>
	<input id="param" type="text" size="100" value="param"/> <br/>
	
	<select id="selectItem" style="background-color:transparent;">
　　<option value="0">--Select--</option>
　　<option value="1">1</option>
　　<option value="2">2</option>
　　<option value="3">3</option>
	</select>

	<script type="text/javascript">
		$(function(){
			var url = $("#url").val();
			var param = {"param":$("#param").val()};
			$("#selectItem").change(function(){
				var thisSelectItem = this;//只有在change直属方法能得到,在方法的其它方法体内,this会失去其原来意思
				$.post(url,
					param,
					function(data,textStatus){
						alert( $(thisSelectItem).val());
						//alert( $(thisSelectItem).val() ); //不能用alert( $(this).val() );
					}
				);
			});
		})
	</script>
</body>
</html>