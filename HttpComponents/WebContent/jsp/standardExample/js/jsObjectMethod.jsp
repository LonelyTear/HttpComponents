<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/base.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/WEB-INF/jsp/common/baseJS.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/common/baseCSS.jsp"></jsp:include>
<title>JS selfObject method</title>
</head>
<body>
	<div style="height:20px;width:100%;background-color:teal;"></div>
	 (像java一样,new对象,再去调方法) <br/>
	 <button id="submit" onclick="clickMe()">click</button> <br/>
	<script type="text/javascript">
		function clickMe(){
			var my = new myjs();
			my.testAlert("3");
		}
		
		var myjs = function(){//该方法亦可
			this.init =  function(){
				alert("init");
			};
			
			//在其它js中使用
			//var myjs = new myjs(	);
			//myjs.alert();即可调用本方法
			
			this.testAlert =  function(number){
				this.init();
				alert("run the method myjs.alert(3) OK。 the number is :"+number);
			};
		}
	
		//------------------------------------------------------------
	
		function myjsOther(){//意思同上
			this.init =  function(){
				alert("init");
			};
			
			//在其它js中使用
			//var myjsOther = new myjsOther(	);
			//myOther.alert();即可调用本方法
			
			this.alert =  function(number){
				this.init();
				alert("run the method myjsOther.alert(3) OK。 the number is :"+number);
			};
		}
		
		//-------------------------------------------------------------
	</script>
</body>
</html>