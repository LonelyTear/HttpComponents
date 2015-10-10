<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/base.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/WEB-INF/jsp/common/baseJS.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/common/baseCSS.jsp"></jsp:include>
<title>checkbox&nbsp;radio.jsp</title>
</head>
<body>
	<form action="">
		<input type="radio" name="sex" value="male" checked="checked"/> Male
		<input type="radio" name="sex" value="female" /> Female
		<hr/>
		I have a bike:
		<input type="checkbox" name="vehicle" value="Bike" />
		<br />
		I have a car:
		<input type="checkbox" name="vehicle" value="Car" checked="checked" />
		<br />
		I have an airplane:
		<input type="checkbox" name="vehicle" value="Airplane" />
	</form>
	<button class="radio">radio</button>
	<button class="checkbox">checkbox</button>
	<button class="all">__all__</button>
	
	<script type="text/javascript">
		$(function(){
			$(".radio").click(function() {
				//form:checked一定不要加空格,意为过滤选择器,即选取:checked的input[name='sex']的元素
				var $checkedObj = $("input[name='sex']:checked");
				console.log("被选中的radio共有"+ $checkedObj.size() +"个");
				$.each( $checkedObj , function(index,obj){
					alert("第"+index+"个被选中的radio的值为 "+$(obj).val());
				});
			});
			
			
			$(".checkbox").click(function() {
				//form:checked一定不要加空格,意为过滤选择器,即选取:checked的input[name='vehicle']的元素
				var $checkedObj = $("input[name='vehicle']:checked");
				console.log("被选中的checkbox共有"+ $checkedObj.size() +"个");
				$.each($checkedObj,function(index,obj){
					alert("第"+index+"个被选中的checkbox的值为 "+$(obj).val());
				});
			});
			
			
			$(".all").click(function() {
				//form :checked一定要加空格,意为后代选择器,即form的内容中带有:checked的元素
				var $checkedObj = $("form :checked");
				console.log("被选中的元素共有"+ $checkedObj.size() +"个");
				$.each($checkedObj,function(index,obj){
					alert("第"+index+"个被选中的元素的值为 "+$(obj).val());
				});
			});
		})
	</script>
</body>
</html>