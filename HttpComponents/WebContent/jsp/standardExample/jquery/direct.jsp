<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/base.jsp"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/jsp/common/baseJS.jsp"></jsp:include>
	<jsp:include page="/jsp/common/baseCSS.jsp"></jsp:include>
	<!-- form validate必须导入的js -->
	<script type="text/javascript" src="${basePath }/js/jquery.validate.js"></script>
	<link rel="stylesheet" href="${basePath }/css/common/textarea.css" type="text/css" />
	<link rel="stylesheet" href="${basePath }/css/common/simplemodal.css" type="text/css" />
	<script type='text/javascript' src='${basePath }/js/jquery.simplemodal.js'></script>
	<script type='text/javascript' src='${basePath }/js/format/formatXml.js'></script>
	<style type="text/css">  
			label.error{   background: url("${basePath }/image/error.gif")  no-repeat; 
					padding-left:24px;
					font-size: 15px;
					color: red;
					font-family: "Microsoft YaHei" ! important}
	</style>
<title>直连保监平台</title>
</head>
<body>
	<div style="height:11px">
		<div id="flag" style="height:10px;width:100%;background-color:#64FE2E;">	</div>
	</div>
		
	
	<form id="myform" action="${basePath }/.../....do" method="post">
		<input id="type"  name="type" type="hidden"  value="暂时无用"/>
		<div style="text-align: center;height: 85%; width: 40%;float: left;">
			<label id="formatXml" class="button  medium" onclick="formatXml()">格式化请求报文:</label><br/>
			<textarea id="info" name="info" style="height:95%; width: 98%" class="textarea yyyyyy"></textarea>
		</div>
		<div style="text-align: center;height: 85%;width:60%;float: left;">
			<label  class="button  medium">返回报文:</label><br/>
			<textarea id="retInfo" name="retInfo" style="height:95%; width: 98%" readonly="readonly" class="textarea gggggg "></textarea>
		</div>
	</form>
	<!-- height:99%; width: 50% -->
	<div id="buttons">
		<div style="float: left;width:11%">
			<button  style='margin-bottom: 3px' class="button grey small" onclick="connect()">直接和平台通信</button> <br/>
		</div>
		<div style="float: left;text-align:right;width:86%">
			 <form style="float: left" id="addressForm">
					<select id="env" name="env" style='margin-bottom: 5px' class="button grey small">
						<option value="">选择环境</option>
						<option value="localhost">localhost(本地环境)</option>
						<option value="120" selected>120(开发环境)</option>
						<option value="121">121(测试环境)</option>
						<option value="122">122(测试生产环境)</option>
					</select>
					
					<select id="prod" name="prod" style='margin-bottom: 5px' class="button grey small">
						<option value="">选择产品险种</option>
						<option value="030001" selected>交强险</option>
						<option value="033001">商业险</option>
						<option value="035501">商业险(商改)</option>
					</select>
					
					<select id="dpt" name="dpt" style='margin-bottom: 5px' class="button grey small">
						 <option value="">选择机构(省市)</option>
						 <option value="110000">(11)北京</option>
						 <option value="210000">(21)辽宁</option>
						 <option value="310000">(31)上海</option>
						 <option value="320000">(32)江苏</option>
						 <option value="330000">(33)浙江</option>
						 <option value="340000">(34)安徽</option>
						 <option value="370000">(37)山东</option>
						 <option value="410000">(41)河南</option>
						 <option value="420000">(42)湖北</option>
						 <option value="440000">(44)广东</option>
						 <option value="510000">(51)四川</option>
						 <option value="720000">(72)宁波</option>
						 <option value="730000">(73)青岛</option>
						 <option value="740000">(74)深圳</option>
						 <option value="750000">(75)苏州</option>
						 <option value="760000">(76)大连</option>
					</select>
				 	<input id="address" name="address" type="text" value="" size="60" class="textarea"/>
			 </form>


			<!-- <button style='margin-bottom: 3px' class="button blue small"
						onclick="changeDpt()">切换机构地址.</button> -->
			
			<!-- <button  style='margin-bottom: 3px' class="button blue small" onclick="changeRequest()">切换请求类型.</button> -->
		
			</div>
		<div >
		</div>
		<div style="clear:both;"></div>
	</div>
	<script type="text/javascript">
	
		var basePath = "/PlatformProxyTool";
		var proxy = "/direct/";
		var env ="";
		var dpt ="";
		var prod ="";

		$(function(){
			$("#env").change(function(){queryAddress()});
			$("#prod").change(function(){queryAddress()});
			$("#dpt").change(function(){queryAddress()});
		});
		
		function queryAddress(){
			var method = "query.do";
			var url = basePath+proxy+method;
			env = $("#env").val();
			prod = $("#prod").val();
			dpt = $("#dpt").val();
			if((env == "") || (prod == "") ||(dpt == "") ){
				return ;
			}else{ 
				var param = {"env":env,"cProdNo":prod,"cDptCde":dpt,"cReqType":"0001","info":""};
				$.post(url,param,function(data,textStatus){
					var json = eval("("+data+")");
					if(json.errorCode == "0000"){
						$("#flag").hide();
						$("#flag").css("background-color","#64FE2E");
						$("#flag").show(1000);
					}else{
						$("#flag").hide();
						$("#flag").css("background-color","red");
						$("#flag").show(1000);
					}
					$("#address").val(json.respInfo);
					//$("#address-error").text("");
					validateAddress();
				});
		 	} 
				
		}
		
		
		function validateAddress(){
			var validFlag = true;
			var validFlag = $("#addressForm").validate({
				rules:{
					address:{
						required:true,
						//url:true
					}
				},
				messages:{
					address:{
						required:"网址必填",
						//url:"网址不正确"
					}
				},
				submitHandler: function(form){
		            //form.submit(); //没有这一句表单不会提交
		        },
		        errorClass: "error",//提示样式
			}).form();
			return validFlag;
		}
		
	 	function connect(){
	 		var flag = validateAddress();
	 		if(flag == false){
	 			return ;
	 		}
	 		var method = "connect.do";
			var address = $("#address").val();
			var info = $("#info").val();
			//alert("address= "+ address);
			if((address == "") || (info == "") ){
				return false;
			}else{
				var url = basePath+proxy+method;
				var param = {"address":address,"info":info};
				$("#buttons button").attr("disabled","disabled");
				setTimeout('$("#buttons button").removeAttr("disabled");', 600 );
				$.post(url,
					param,
					function(data,textStatus){
						var json = eval("("+data+")");
						if(json.errorCode == "0000"){
							$("#flag").hide();
							$("#flag").css("background-color","#64FE2E");
							$("#flag").show(1000);
						}else{
							$("#flag").hide();
							$("#flag").css("background-color","red");
							$("#flag").show(1000);
						}
						$("#retInfo").text(json.respInfo);
					}
				); 
			}
		} 
		
		
	 	function showEnvDiv(){
			var options = {
					opacity:50,
					autoResize:true,
					close:true,
					
					//closeHTML:"<a>close</a>",
					//closeClass:"button",
					//overlayId:"#parentDiv",
					overlayClose:true,
					/* onOpen: function (dialog) {
						dialog.overlay.fadeIn('slow', function () {
							dialog.data.hide();
							dialog.container.fadeIn('slow', function () {
								dialog.data.slideDown('slow');	 
							});
						});
					}, */
					onShow: function (dialog) {
						$("input[name='env'][value='"+envType+"']").attr("checked",true);
						// Access elements inside the dialog
						// Useful for binding events, initializing other plugins, etc.
						
						// For example:
/* 						$("input.env", dialog.data).click(function () {
							envType = $(this).val();
							//var envType = $("input[name='env']:checked").val();
							$("input[name='env'][value='"+$(this).val()+"']").attr("checked",true);
							return true;
						}); */
						$("input[name='env']", dialog.data).change(function () {
							envType = $(this).val();
							//$("#y5",dialog.data).val(envType);
							//var envType = $("input[name='env']:checked").val();
							//$("input[name='env'][value='"+$(this).val()+"']").attr("checked",true);
							return true;
						});
					},
					/* onClose: function (dialog) {
						 dialog.data.fadeOut('slow', function () {
							dialog.container.hide('slow', function () {
								dialog.overlay.slideUp('slow', function () {
									$.modal.close();
								});
							});
						}); 
					},  */
					escClose:true};
			$("#simpleModalDiv").modal(options);
		}
			
		
	</script>
	
		
	<%-- <div id="simpleModalDiv" style="display:none; z-index:5000;width:700px;height:450px;  background-image:url('${basePath}/image/env.jpg') ">
			<div style="padding-left:240px;padding-top:10px;">
				 <button >(11)北京</button>
				 <input id="dpt_110000" type="text" value="" size="50"/><br/>
				 <button >(21)辽宁</button>
				 <input id="dpt_210000" type="text" value="" size="50"/><br/>
				 <button >(31)上海</button>
				 <input id="dpt_310000" type="text" value="" size="50"/><br/>
				 <button >(32)江苏</button>
				 <input id="dpt_320000" type="text" value="" size="50"/><br/>
				 <button >(33)浙江</button>
				 <input id="dpt_330000" type="text" value="" size="50"/><br/>
				 <button >(34)安徽</button>
				 <input id="dpt_340000" type="text" value="" size="50"/><br/>
				 <button >(37)山东</button>
				 <input id="dpt_370000" type="text" value="" size="50"/><br/>
				 <button >(41)河南</button>
				 <input id="dpt_410000" type="text" value="" size="50"/><br/>
				 <button >(42)湖北</button>
				 <input id="dpt_420000" type="text" value="" size="50"/><br/>
				 <button >(44)广东</button>
				 <input id="dpt_440000" type="text" value="" size="50"/><br/>
				 <button >(51)四川</button>
				 <input id="dpt_510000" type="text" value="" size="50"/><br/>
				 <button >(72)宁波</button>
				 <input id="dpt_720000" type="text" value="" size="50"/><br/>
				 <button >(73)青岛</button>
				 <input id="dpt_730000" type="text" value="" size="50"/><br/>
				 <button >(75)深圳</button>
				 <input id="dpt_740000" type="text" value="" size="50"/><br/>
				 <button >(75)苏州</button>
				 <input id="dpt_750000" type="text" value="" size="50"/><br/>
				 <button >(75)大连</button>
				 <input id="dpt_760000" type="text" value="" size="50"/><br/>
			</div>
		<div>
			
		</div>
	</div> --%>
	
</body>
</html>