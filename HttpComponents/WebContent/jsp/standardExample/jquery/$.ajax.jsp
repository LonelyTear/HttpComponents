<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/base.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/WEB-INF/jsp/common/baseJS.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/common/baseCSS.jsp"></jsp:include>
<title>$.ajax.jsp</title>
</head>
<body>
	<a href="http://www.cnblogs.com/liuling/archive/2013/02/07/sdafsd.html" >$.ajax,$.get,$.post资料</a><br/>
	<a href="http://blog.sina.com.cn/s/blog_4f925fc30100la36.html" >$.ajax资料</a><br/>
	<a href="http://www.cnblogs.com/mybest/archive/2011/12/13/2285730.html" >$相关链接error</a><br/>
	<input id="url" type="text" size="100" value="${basePath }/jsp/standardExample/beCalledJsp/jump.jsp"/> <br/>
	<input id="param" type="text" size="100" value="weather"/> <br/>
	<button id="submit" onclick="clickMe()">click</button>
	<script type="text/javascript">
		function clickMe(){
			var url = $("#url").val();
			var jsonParam = {"param":$("#param").val()};
			$.ajax({
				//这个async:false 同步的意思是当JS代码加载到当前AJAX的时候会把页面里所有的代码停止加载，页面出去假死状态，当这个AJAX执行完毕后才会继续运行其他代码页面假死状态解除。 
				//而async:true 异步代码运行中的时候其他代码一样可以运行。 
				async: false,
				type : "post",
				url : url, //basePath+"/jump.jsp"
				dataType : "html",
				data:jsonParam,
				beforeSend: function (XMLHttpRequest) {
					// 禁用按钮防止重复提交
					$("#submit").attr({ disabled: "disabled" });
					//在beforeSend中如果返回false可以取消本次ajax请求
					//在这里可以作验证
					$.each(this, function(index, content){// this为调用本次AJAX请求时传递的options参数
						console.log(index+" : "+content);
					});
					return true;
				},
				success : function(data , textStatus) {
					alert("success data:"+ data);
					alert("succss textStatus :"+ textStatus);
					window.location.href = basePath+"/jump.jsp";
				},
				//请求成功或失败时均调用
				complete: function(XMLHttpRequest, textStatus) {
					alert("complete XMLHttpRequest.readyState : "+XMLHttpRequest.readyState );
					alert("complete XMLHttpRequest.status : "+XMLHttpRequest.status );
					alert("complete textStatus : "+textStatus);
					$("#submit").removeAttr("disabled");
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("error XMLHttpRequest.readyState : "+XMLHttpRequest.readyState );
					alert("error XMLHttpRequest.status : "+XMLHttpRequest.status );//4XX
					alert("error textStatus : "+textStatus);
					alert("error errorThrown : "+errorThrown);
				}
			});
		}
	</script>
</body>
</html>