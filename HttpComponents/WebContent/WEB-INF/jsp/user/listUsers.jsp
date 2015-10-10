<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ListUser</title>
</head>
<body>
<div style="height:20px;width:100%;background-color: yellow">  </div>
  	单个用户信息:<br/>
  	用户id:>>${requestScope.user.id }      <br/>
  	用户username:>>${requestScope.user.username }      <br/>
  	用户password:>>${requestScope.user.password }      <br/>
</body>
</html>