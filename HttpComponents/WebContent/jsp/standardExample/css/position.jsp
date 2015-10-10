<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/base.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/WEB-INF/jsp/common/baseJS.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/common/baseCSS.jsp"></jsp:include>
	<style type="text/css">
	 .back
       {
           width:153px;
           height:47px;
           background-image:url("/SpringMVC/image/tc.png");
           background-position:-87px -142px;
           background-repeat:no-repeat;
           /* background-attachment:fixed; */  /* 不能设置成fixed,不然位置显示不正常,fixed以后都通通会从左上上0 ,0 为标准 */
       }
	</style>
	<title>background-position.jsp</title>
</head>
<body>
	<div class="back">1</div>  
	<div class="back">2</div> 
	<div class="back">3</div> 
	<!-- <img alt="" src="" class="TCLogo"/> -->
</body>
</html>


<!-- // 绝对定位与相对定位是冲突的，只能生效一个。。
background-position:-82px -137px; // 绝对定位
background-position:top right; // 相对定位 -->