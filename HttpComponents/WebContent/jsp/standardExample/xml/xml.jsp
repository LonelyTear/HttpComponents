<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/base.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/WEB-INF/jsp/common/baseJS.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/common/baseCSS.jsp"></jsp:include>
<title>JqueryXml</title>
</head>
<body>
	<a href="javascript:void(0);" onclick="callXml('http://127.0.0.1:8080/SpringMVC/jsp/standardExample/xml/salaryTaxRate.xml')">callXml('http://127.0.0.1:8080/SpringMVC/jsp/standardExample/xml/salaryTaxRate.xml')</a> <br/>
	<textarea  id="area" rows="60" cols="100"></textarea>
	
	<script type="text/javascript">
		function callXml(address){
			$.post(address,	{},
					function(data){
							//console.log(data);
							var info = ""	;
							$(data).find("taxrate").each(function(i){
						                var oid = $(this).attr("id");
						                var lower = $(this).children("lower").text();
						                var upper = $(this).children("upper").text();
						                var rate = $(this).children("rate").text();
						                ///后续操作。。。
						                info += "当收入在"+lower+"和"+upper+"之间,"+"id为"+oid+"税率为"+rate+"\n";
						   });
						   
						   var $rateList = $(data).find("rate");
						   var oids = $rateList.text();
						   info += "rateList 共有"+$rateList.size()+"个,如果直接提取rate List,那么结果值如下:"+oids;
						   $("#area").text(info);
						   
					}
			);
		} 
	</script>
</body>
</html>