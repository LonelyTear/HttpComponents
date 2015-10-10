<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%-- <link rel="stylesheet" href="${staticPath }/css/font.css" type="text/css" /> --%>
<%
 String appContext = request.getContextPath();
 String basePath = request.getScheme()+"://"+request.getServerName()+":"+ request.getServerPort() + appContext; 
 String staticPath = basePath+"/static1";
%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<c:set var="staticPath" value="${basePath}/static1" /> 
<c:set var="webInfoPath" value="${basePath}/WEB-INF" /> 

<script>
	basePath = "/SpringMVC";
</script>




