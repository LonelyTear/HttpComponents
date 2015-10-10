<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/base.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<jsp:include page="/WEB-INF/jsp/common/baseJS.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/common/baseCSS.jsp"></jsp:include>
		<style>
		.black_overlay{
		display: none;
		position: absolute;
		top: 0%;
		left: 0%;
		width: 100%;
		height: 100%;
		background-color: black;
		z-index:1001;
		-moz-opacity: 0.8;
		opacity:.80;
		filter: alpha(opacity=80);
		}
		.white_content {
		display: none;
		position: absolute;
		top: 10%;
		left: 10%;
		width: 80%;
		height: 80%;
		border: 16px solid lightblue;
		background-color: white;
		z-index:1002;
		overflow: auto;
		}
		.white_content_small {
		display: none;
		position: absolute;
		top: 20%;
		left: 30%;
		width: 40%;
		height: 50%;
		border: 16px solid lightblue;
		background-color: white;
		z-index:1002;
		overflow: auto;
		}
		</style>
	<title>studentAddUpdate</title>
</head>
	<!--弹出层时背景层DIV-->
	<div id="fade" class="black_overlay"></div> <!-- 底层 -->
	<div id="showDiv" class="white_content"> <!-- 弹出层 -->
		<div style="text-align: center; cursor: default; height: 40px;">
			<div style="text-align: right;">
				<button class="button grey small" onclick="CloseDiv('showDiv','fade')">关闭</button>
			</div>
			<div id="showLayer"></div>
		</div>
	</div>
	
	<script type="text/javascript">
			//弹出隐藏层
			function ShowDiv(show_div,bg_div){
				//document.getElementById(show_div).style.display='block';
				//document.getElementById(bg_div).style.display='block';
				$("#"+show_div).show(500);
				$("#"+bg_div).show(500);
			};

			//关闭弹出层
			function CloseDiv(show_div,bg_div){
				//document.getElementById(show_div).style.display='none';
				//document.getElementById(bg_div).style.display='none';
				$("#"+show_div).hide(500);
				$("#"+bg_div).hide(500);
			};
	</script>
</body>
</html>