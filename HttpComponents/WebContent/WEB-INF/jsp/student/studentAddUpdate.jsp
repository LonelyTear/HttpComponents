<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/base.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/WEB-INF/jsp/common/baseJS.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/common/baseCSS.jsp"></jsp:include>
<title>studentAddUpdate</title>
</head>
<body>
	<div id="pageInfo">
	
	<c:choose>
		<c:when test="${student != null }">
			<c:set var="url" value="${basePath }/student/insertOrUpdateStudent.do" scope="request"/>
		</c:when>
		<c:otherwise>
			<c:set var="url" value="${basePath }/student/insertOrUpdateStudent.do" scope="request"/>
		</c:otherwise>
	</c:choose>
	
		<form id="pager" action="${url}" style="font-size: 20px" method="post" data-meta="test test test">
				<div style="height:20px;width:100%;background-color: green">  </div>
				<div style="height:350px">
							AddUpdate<br/>
							<c:if test="${student != null }"	>
										编号:<input type="text" name="id" value="${student.id }" /> <br/>
							</c:if>
										姓名:<input type="text" name="name" value="${student.name }" /> <br/>
										地址:<input type="text" name="address" value="${student.address }" /> <br/>
										学费:<input type="text" name="tuition" value="${student.tuition }" /> <br/>
										生日:<input type="text" name="birthDate" value='<fmt:formatDate value="${student.birthDate }"
														pattern="yyyy-MM-dd" />'  id="birthDate"/> <br/>
							
							<c:choose>  
								   <c:when test="${student!=null}">
										<input type="submit" value="更新"/>
								   </c:when>  
								   <c:otherwise> 
								   		<input type="submit" value="添加"/>    
								   </c:otherwise>  
							</c:choose> 
			</div>
				<script>
					$(function(){
						$("#birthDate").datepicker({dateFormat: "yy-mm-dd"});//$datapicker在定义时间格式时必须用yy-mm-dd,如果用yyyy会显示两次20152015
					})
				</script>
		</form>
	</div>
	
</body>
</html>