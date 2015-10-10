<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/base.jsp"%>
	<jsp:include page="/WEB-INF/jsp/common/baseJS.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/common/baseCSS.jsp"></jsp:include>
	
	<!--  相当于该页面是"后台",只是被前台页面用jquery的方法html(本页面)在分页展示时调用了-->
		<div id="modal" style="height:20px;width:100%;background-color: green">  </div>
		<div style="height:350px">
			Page中多个学生信息:<br/>
				<table border="1" >
					<thead>
						<tr>
							<!--删除 <label class="checkbox m-n i-checks"><input type="checkbox"><i></i></label> -->
							<th ></th>
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
								<tr  onclick="oneClick()" ondblclick="doubleClick(${obj.id })" 
								onmouseover="over(this)" onmouseout="out(this)">
									<td>
										<div >
											<label><input type="radio" name="ide" 	value="${obj.id }"></label>
											<input type="checkbox" name="id" 	value="${obj.id }">------
											<a href="${basePath }/student/toUpdatePage.do?id=${obj.id}">【更新】</a>
											<a href="${basePath }/student/deleteStudentById.do?id=${obj.id}">【删除】</a>
										</div>
									</td>
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
			<jsp:include page="/WEB-INF/jsp/student/popLayer.jsp"></jsp:include>
			<script>
				$(function(){
					addColor();
				})
				function oneClick(){
				}
				
				function doubleClick(id){
					$.ajax({
						type : "get",
						url : "${basePath}/student/studentDetail.do",
						dataType : "html",
						data:{id:id},	
						success : function(data) {
							$("#showLayer").html(data);
							ShowDiv('showDiv','fade');
						}
					});
				}
				
				function over(thisDom){
					addColor();
					$(thisDom).css("background-color","#00ff00");
				}
				
				function out(thisDom){
					addColor();
					$(thisDom).css("background-color","#00ff00");
				}
				
				function addColor(){
					$("tbody tr").each(function(i,obj){
						if(i%2 == 0){
							//$(obj).addClass("xxx");
							//$(obj).removeClass("xxx");
							$(obj).css("background-color","#FFFF99");
						}else{
							$(obj).css("background-color","#FFFF5C");
						}
					})
				}
			</script>
			