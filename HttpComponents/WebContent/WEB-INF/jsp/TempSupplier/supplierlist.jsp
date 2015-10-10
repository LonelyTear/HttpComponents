<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/base.jsp"%>
<!DOCTYPE html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title><c:out value="${SessSysAppBean.apptitle}"></c:out></title>
  <meta name="description" content="<c:out value="${SessSysAppBean.appdesc}"></c:out>" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <jsp:include page="/WEB-INF/jsp/headinc.jsp"></jsp:include>
</head>
<body class="">
  <section class="vbox">
    <jsp:include page="/WEB-INF/jsp/main/header.jsp"></jsp:include>
    <section>
      <section class="hbox stretch">
        <!-- .aside -->
        <aside class="bg-black dk aside hidden-print" id="nav">          
          <section class="vbox">
          	<!-- left -->
            <jsp:include page="/WEB-INF/jsp/main/left.jsp"></jsp:include>
            
            <jsp:include page="/WEB-INF/jsp/main/footer.jsp"></jsp:include>
            
          </section>
        </aside>
        <!-- /.aside -->
        <section id="content">
          <section class="hbox stretch">
            <section>
              <section class="vbox">
              	<!-- target main begin-->
              		<!--删了 class="vbox"-->
                	<section class="scrollable" id="main_content_target" >
		              	<form action="${basePath}/!supplier/supplierlist.do" class="form-horizontal" method="post" id="queryForm" name="supplierForm" data-target="#main_content_target" data-el="#main_content_target" data-replace="true">	
			              	<section class="panel panel-default">
			                <header class="panel-heading">
			                  	供应商列表
			                </header>
			                <div class="row wrapper">
			                  <div class="col-sm-2">
			                    <input name="name" type="text" class="form-control"  placeholder="供应商名称">
			                  </div>
			                  <div class="col-sm-2">
			                    <input name="number" type="text" class="form-control" placeholder="供应商编码">
			                  </div>
			                  <div class="col-sm-2">
			                    <input name="linkman" type="text" class="form-control" placeholder="联系人">
			                  </div>
			                  <div class="col-sm-1">
			                  	<button type="button" id="queryBtn" name="queryBtn" class="btn btn-success btn-s-xs" >检索</button>
			                  </div>		                
			                </div>
			                <div class="table-responsive">
			                  <table class="table table-striped b-t b-light">
			                    <thead>
			                      <tr>
			                      <!--删除 <label class="checkbox m-n i-checks"><input type="checkbox"><i></i></label> -->
			                        <th style="width:15px;"></th> 
			                        <th>编号</th>
			                        <th>供应商编码</th>
			                        <th>供应商名称</th>
			                        <th>地址</th>
			                        <th>所属部门</th>
			                        <th>负责人</th>
			                        <th>联系人</th>
			                        <th>更新时间</th>
			                      </tr>
			                    </thead>
			           			  <c:if test="${pager!=null}">
			                        <c:forEach var="supplierBean" items="${pager.result}" varStatus="status">
				                      <tr>
				                      		<td><div class="radio i-checks"><label ><input type="radio" name="checkcode" value="${supplierBean.id }"><i></i></label></div></td>
					                        <td>${supplierBean.id }</td>
					                        <td>${supplierBean.number}</td>
					                        <td>${supplierBean.name }</td>
					                        <td>${supplierBean.address }</td>
					                        <td>${supplierBean.department }</td>
					                        <td>${supplierBean.chief }</td>
					                        <td>${supplierBean.linkman }</td>
					                        <td><fmt:formatDate value="${supplierBean.updatetime }" pattern="yyyy-MM-dd HH:mm:ss"/>  </td>
				                      </tr>		                      
			                        </c:forEach>
			                       </c:if>
			                    </tbody>
			                  </table>
			                </div>
			                <footer class="panel-footer">
			                  <div class="row">
			                  	<div class="btn-group col-sm-4 text-center" id="toolbarBtn">
				                  <button type="button" id="addBtn" class="btn btn-default" data-target="#main_content_target" data-el="#main_content_target" data-replace="true" href="${basePath}/!supplier/go2page.do">增加</button>
				                  <button type="button" id="editBtn" class="btn btn-default" data-target="#main_content_target" data-el="#main_content_target" data-replace="true" href="${basePath }/!supplier/go2page.do">编辑</button>
				                  <button type="button" id="dropBtn" class="btn btn-default" data-target="#main_content_target" data-el="#main_content_target" data-replace="true" href="${basePath }/!supplier/drop.do">删除</button>
				                </div>	                    
				                <!-- include pager jsp -->
			                    <jsp:include page="/WEB-INF/jsp/common/page/pager_inc.jsp"></jsp:include>
			                  </div>
			                </footer>
			                <jsp:include page="/WEB-INF/jsp/main_js_inc.jsp"></jsp:include>
			                <script type="text/javascript">
				                $(document).ready(function(){
				                	//查询
				                	$('#queryBtn').click(function (e){
										var params=$('#queryForm').serialize();
										var postUrl=$('#queryForm').attr('action');
										alert(params);
										alert(postUrl);
										$(this).bjax({type:'POST',url: postUrl ,data:params});
									 });
				                	
				                	//准备新增
				                	$('#addBtn').click(function (e){              		
					                    if($(this).attr('data-toggle') !=undefined) return ;
					                    $(this).bjax({url: $(this).attr('href') || $(this).attr('data-url') });
					                    e.preventDefault();
				                	});
				                	
				                	//准备修改
				                	$('#editBtn').click(function (e){
				                		var checkedObj=$('[name="checkcode"]:checked');
					                	if(checkedObj.length==0){
					                		alert("请选择一条您要修改的数据");
					                		return false;    		
					                	}
					                	var id= $(checkedObj).val();
					                    if($(this).attr('data-toggle') !=undefined) return ;
					                    var dataUrl=$(this).attr('href') || $(this).attr('data-url');
					                    dataUrl+="?id="+id;
					                    $(this).bjax({url: dataUrl });
					                    e.preventDefault();
				                	});
				                	
				                	//删除
				                	$('#dropBtn').click(function (e){
					                	var checkedObj=$('[name="checkcode"]:checked');     
					                	if(checkedObj.length==0){
						                	alert("请选择一条或多条您要删除的数据");
					                		return false;    		
					                	}
					                	var id= $(checkedObj).val();
					                    var btnObj=$(this);				                
					                    if($(this).attr('data-toggle') !=undefined) return ;
					                    var postUrl=$(this).attr('href') || $(this).attr('data-url');
					                    $.ajax({
								             type: "POST",
								             url: postUrl,
								             data: "id="+id,
								             success: function(data){
											 	 data=eval("("+data+")");
								                 if(data.flag){
								                	 $(btnObj).bjax({url: '${basePath}/!supplier/supplierlist.do'});
								                 }else{
									                 alert("程序发生错误,请联系运维人员!");
								                 }    
								             }
								         });
					                    e.preventDefault();
				                	});	
				                });	                	
			                </script>
			              </section>
		              </form>		              
		            </section>
                <!-- target main end-->
                <jsp:include page="/WEB-INF/jsp/main/ft_music.jsp"></jsp:include>
              </section>
            </section>
            <!-- connected inc -->
            <jsp:include page="/WEB-INF/jsp/main/connected.jsp"></jsp:include>
            <!-- / side content -->
          </section>
          <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen,open" data-target="#nav,html"></a>
        </section>
      </section>
    </section>    
  </section>
  
  <jsp:include page="/WEB-INF/jsp/minc.jsp"></jsp:include>
</body>
</html> --%>