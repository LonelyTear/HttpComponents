<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>listStudents</title>
</head>
<body>
  <div style="height:20px;width:100%;background-color: fuchsia;">  </div>
	多个银行帐号信息:每次id为1的帐户,向id为2的帐户汇款100元<br/>
	<table border="1">
		<thead>
			<tr>
				<td>主键</td>
				<td>姓名</td>
				<td>余额</td>
			</tr>
		</thead>
		<c:forEach items="${requestScope.accountList}" var="account"	varStatus="status">
			<tbody>
				<tr >
					<td>${account.id }</td>
					<td>${account.name }</td>
					<td>${account.balance }</td>
				</tr>
			</tbody>
		</c:forEach>
	</table>

</body>
</html>