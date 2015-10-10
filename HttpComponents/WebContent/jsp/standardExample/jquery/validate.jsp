<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/base.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/jsp/common/baseJS.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/common/baseCSS.jsp"></jsp:include>

<!-- form validate必须导入的js -->
<script type="text/javascript" src="${basePath }/js/jquery.validate.js"></script>
<!-- form validate 英文转中文提示 -->
<script type="text/javascript" src="${basePath }/js/common/message_cn.js"></script>

<style type="text/css">
	span.error{   background: url("/SpringMVC/image/error.gif")  no-repeat; 
					padding-left:24px;
					font-size: 15px;
					color: red;
					font-family: "Microsoft YaHei" ! important}
	span.valid{  background: url("/SpringMVC/image/success.gif") no-repeat;
					padding-left:24px;
					font-size: 15px;
					color: green;
					font-family: "Microsoft YaHei" ! important}
</style>
<title>validate.jsp</title>
</head>
<body>
	<span class="valid"></span>
	<span class="error"></span>
	<script type="text/javascript">
		
		$.validator.addMethod(
			"selfFormula",//自定义方法名
			function(value,element,param){//验证规则
				return value == eval(param);
			},
			'请输入该公式{0}+{1}计算后的正确结果'//验证提示
		)
	
		$(function() {
			$("#signupForm").validate({
				rules:{
					address:{
						required:true,
						rangelength:[1,20]//输入长度必须介于 1 和 20 之间的字符串")(汉字算一个字符)
					},
					email:{
						required:true,
						email:true
					},
					url:{
						required:true,
						url:true
					},
					birthDate:{
						required:true,
						dateISO:true
					},
					age:{
						required:true,
						digits:true,
						range:[1,100],
						min:1,
						max:100
					},
					tuition:{
						required:true,
						number:true
					},
					password:{
						required:true,
						minlength:5,//输入长度最小是5的字符串(汉字算一个字符)
						maxlength:10 //输入长度最多是10的字符串(汉字算一个字符)
					},
					confirm_password:{
						required:true,
						minlength:5,
						equalTo:"#password"
					},
					selfRule:{
						required:true,
						selfFormula:"3+2"
					}
				},
				messages:{
					email:{
						required:"请输入Email地址",
						email:"请输入正确的email地址"
					},
					password:{
						required:"请输入密码",
						minlength: $.validator.format("不得少于{0}字符.")
					},
					confirm_password:{
						required:"请输入确认密码",
						minlength:"确认密码不能小于5个字符",
						equalTo:"两次输入密码不一致不一致"
					}
				},
				errorClass: "error",//提示样式
				errorElement: "span",//提示dom元素类型,默认label会换行,div也会换行 
				errorPlacement: function(error, element) {   //提示位置自定义
					element.after(error);
					//error.appendTo(element.parent());
				},
				//success 会和 errorClass冲突,还是别用了,没有对勾凑合着这样吧
				/* success: function(label) {
					label.html("").addClass("valid");
				},
				success:"valid"; */
				submitHandler: function(form){
		            alert("submit");    
		            form.submit(); //没有这一句表单不会提交
		        }
			});
		});
	</script>
	
	<form id="signupForm" method="post" action="">
		<p>
			<label for="name">name(字符串)</label> 
			<input id="name"	name="name" />
		</p>
		<p>
			<label for="address">address(字符串)</label> 
			<input id="address"	name="address" />
		</p>
		<p>
			<label for="email">EMail(邮件)</label> 
			<input id="email" name="email" />
		</p>
		<p>
			<label for="url">URL(网址)</label> 
			<input id="url" name="url" />
		</p>
		<p>
			<label for="birthDate">birthDate(出生日期)</label> 
			<input id="birthDate" name="birthDate" /> 
		</p>
		<p>
			<label for="age">age(年龄)</label> 
			<input id="age" name="age" /> 
		</p>
		<p>
			<label for="tuition">tuition(学费)</label> 
			<input id="tuition" name="tuition" /> 
		</p>
		<p>
			<label for="password">Password(密码)</label> 
			<input id="password"	name="password" type="password" />
		</p>
		<p>
			<label for="confirm_password">Confirm Password(确认密码)</label>
			 <input	id="confirm_password" name="confirm_password" type="password" />
		</p>
		<p>
			<label for="selfRule">自定义验证(计算动态公式值是否正常,公式可以从通过某个元素的text()或ajax获得)</label>
			 <input	id="selfRule" name="selfRule" type="text" />
		</p>
		<p>
			<input class="submit" type="submit" value="验证并提交" />
		</p>
	</form>

</body>
</html>