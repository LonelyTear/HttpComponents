<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/base.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/WEB-INF/jsp/common/baseJS.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/common/baseCSS.jsp"></jsp:include>
<title>listStudents</title>
</head>
<body>
	<div id="pageInfo">
		<form id="pager" action="${basePath }/student/selectStudentByAjax.do" style="font-size: 20px" method="post" data-meta="test test test">
			  <div style="height:20px;width:100%;background-color: green">  </div>
			  <div>
				   	单个学生信息:<br/>
				  	学生名字:>>${requestScope.student.name }      <br/>
				  	其它信息:>>${requestScope.other }      <br/>
					__________________________________________________________<br/>
					__________________________________________________________<br/>
			  </div>
			<div style="height:350px">
				Page中多个学生信息:<br/>
					<table border="1">
						<thead>
							<tr>
								<!--删除 <label class="checkbox m-n i-checks"><input type="checkbox"><i></i></label> -->
								<th style="width: 15px;"></th>
								<th>编号</th>
								<th>姓名</th>
								<th>地址</th>
								<th>学费</th>
								<th>生日</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${pager!=null}">
								<c:forEach var="obj" items="${pager.result}" varStatus="status">
									<tr>
										<td><div >
												<label><input type="radio" name="checkcode"
													value="${obj.id }"></label>
											</div></td>
										<td>${obj.id }</td>
										<td>${obj.name }</td>
										<td>${obj.address }</td>
										<td>${obj.tuition }</td>
										<td><fmt:formatDate value="${obj.birthDate }"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
				<jsp:include page="/WEB-INF/jsp/common/page/pager.jsp"></jsp:include>
				<script>
				
					function displayNotUsed(url,index){//普通分布是这么分的,暂时没用到
						alert($("#pageBanner").html());
						$.post(url,
								{pageNumber:index},
								function(data,textStatus){
									alert(data);
									var obj = eval("("+data+")");//这里要加上 括号和双引号 的原因未知，就当是json语法，只能死记硬背了
									$("#pager tbody").empty();
									var pager = obj.pager;
									$.each(pager.result,function(index,stu){
										var content ='';
										content += '<tr>';
										content +=	'<td><div ><label><input type="radio" name="checkcode"	value="'+ stu.id +'"><i></i></label>	</div></td>';
										content += 	'<td>'+stu.id+'</td>';
										content += 	'<td>'+stu.name+'</td>';
										content += 	'<td>'+stu.address+'</td>';
										content +=	'<td>'+stu.tuition+'</td>';
										content += 	'<td>'+stu.birthDate+'</td>';
										content += '</tr>';
										$("#pager tbody").append(content);
									}); 
								}
						);
					}
				</script>
		</form>
	</div>
	
</body>
</html>