<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/base.jsp"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/WEB-INF/jsp/common/baseJS.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/common/baseCSS.jsp"></jsp:include>
	<link rel="stylesheet" href="${basePath }/css/common/textarea.css" type="text/css" />
	<link rel="stylesheet" href="${basePath }/css/common/simplemodal.css" type="text/css" />
	<script type='text/javascript' src='${basePath }/js/jquery.simplemodal.js'></script>
	<style>  
	
	</style>
<title>localhost(本地环境)代理平台入口</title>
</head>
<body>
	<div style="height:11px">
		<div id="flag" style="height:10px;width:100%;background-color:#64FE2E;">	</div>
	</div>
		
	
	<form id="myform" action="${basePath }/.../....do" method="post">
		<input id="type"  name="type" type="hidden"  value="暂时无用"/>
		<div style="text-align: center;height: 85%; width: 29%;float: left;">
			<label  class="button  medium">请求报文:</label><br/>
			<textarea id="info" name="info" style="height:95%; width: 98%" class="textarea yyyyyy"></textarea>
		</div>
		<div style="text-align: center;height: 85%;width:69%;float: left;">
			<label  class="button  medium">返回报文:</label><br/>
			<textarea id="retInfo" name="retInfo" style="height:95%; width: 98%" readonly="readonly" class="textarea gggggg "></textarea>
		</div>
	</form>
	<!-- height:99%; width: 50% -->
	<div id="buttons">
		<div style="float: left;width:11%">
			<button  style='margin-bottom: 3px' class="button grey small" onclick="call('CommercialInsuranceQuery.do')">商业险投保查询</button> <br/>
			<button  style='margin-bottom: 3px' class="button grey small" onclick="call('CommercialInsuranceQueryCheck.do')">商业险投保查询校验</button>
		</div>
		<div style="float: left;width:11%">
			<button  style='margin-bottom: 3px' class="button grey small" onclick="call('CompulsoryInsurancePremiumCalculation.do')">交强险保费计算</button><br/>
			<button  style='margin-bottom: 3px' class="button grey small" onclick="call('CompulsoryInsuranceQueryCheck.do')">交强险保费计算校验</button>
		</div >
		<div style="float: left;width:11%">
			<button  style='margin-bottom: 3px' class="button grey small" onclick="call('NewCarReportBeijing.do')">北京新车报备</button> <br/>
			<button  style='margin-bottom: 3px' class="button grey small" onclick="call('IdCardCollectionBeijing.do')">北京身份证信息采集</button>
		</div>
		<div style="float: left;width:15%">
			<button  style='margin-bottom: 3px' class="button grey small" onclick="call('QueryPlatformReturnInfo.do')">根据车辆码查询返回平台报文</button><br/>
		</div>
		<div style="float: left;text-align:right;width:50%">
			<button  style='margin-bottom: 3px' class="button rosy small" onclick="showEnvDiv()">切换环境</button><br/>
		</div>
		<div >
		</div>
		<div style="clear:both;"></div>
	</div>
	<script type="text/javascript">
	
		var basePath = "/PlatformProxyTool";
		var proxy = "/localhost/";
		var method = "invoke.do";
		var envType ="localhost";
		
		function call(interfaceName){
			var url = basePath+proxy+method;
			var completeFlag = $("input[name='completeFlag']:checked").val();
			var info = $("#info").val();
			var param = {"env":envType,"interfaceName":interfaceName,"completeFlag":completeFlag,"info":info};
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
					$("#retInfo").text(json.retInfo);
					
				}
			);
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
	
	<div >
		<div>
			<br/>
			<hr/>
			<input id="c1" type="radio" class='completeFlag' name="completeFlag" value="1" checked="checked" /> <label for="c1">外围系统标准报文(默认)</label>	<br/>
			<input id="c0" type="radio" class='completeFlag' name="completeFlag" value="0"  /> <label for="c0">代理平台recvOut字段报文</label>	<br/>
			<hr/>
		</div>
	</div>
		
	<div id="simpleModalDiv" style="display:none; z-index:5000;width:500px;height:332px;  background-image:url('${basePath}/image/env.jpg') ">
		<div style="padding-left:240px;padding-top:190px;">
			<input id="y1" type="radio" class='env' name="env" value="localhost" checked="checked" />
				 <label for="y1">localhost本地</label>  <br/>
			<input id="y2" type="radio" class='env' name="env" value="120"  />
				 <label for="y2">120开发环境</label>		<br/>
			<input id="y3" type="radio" class='env' name="env" value="121"  />
				 <label for="y3">121测试环境</label>		<br/>
			<input id="y4" type="radio" class='env' name="env" value="122"  />
				 <label for="y4">122测试生产环境</label>	<br/>
		</div>
		<div>
			
		</div>
	</div>
	
</body>
</html>