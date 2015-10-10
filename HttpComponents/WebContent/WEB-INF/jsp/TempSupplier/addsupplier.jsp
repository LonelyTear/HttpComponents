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
                	<section class="scrollable" id="main_content_target" >
		              	<form action="${basePath}/!supplier/add.do" name="supplierForm" id="supplierForm" method="post" data-target="#main_content_target" data-el="#main_content_target" data-replace="true">
		              	<section class="scrollable padder">
		              		<div class="m-b-md">
				                <h3 class="m-b-none">增加供应商</h3>
				            </div>
				            <div class="row">
				            	<div class="col-sm-6">
					            	<section class="panel panel-default">
						                    <header class="panel-heading font-bold">基本信息</header>
						                    <div class="panel-body">
						                    	<div class="bs-example form-horizontal">
							                        <input name="id" type="text" value="${supplierBean.id }" class="form-control"  placeholder="供应商id" readonly="readonly" style="visibility: hidden;">
							                    	<div class="form-group">
							                          <label class="col-lg-3 control-label">供应商编码</label>
							                          <div class="col-lg-9">
							                            <input name="number" type="text" value="${supplierBean.number }" class="form-control required"  placeholder="供应商编码">
							                          </div>
							                        </div>
							                        <div class="form-group">
							                          <label class="col-lg-3 control-label">供应商名称</label>
							                          <div class="col-lg-9">
							                            <input name="name" type="text" value="${supplierBean.name }" class="form-control" placeholder="供应商名称">
							                          </div>
							                        </div>								                    
							                        <div class="form-group">
							                          <label class="col-lg-3 control-label">地址</label>
							                          <div class="col-lg-9">
							                          	<input name="address" type="text" value="${supplierBean.address }" class="form-control" placeholder="地址">
							                          </div>				                          
							                        </div>
							                        <div class="form-group">
							                          <label class="col-lg-3 control-label">所属部门</label>
							                           <div class="col-lg-9">
									                        <select class="form-control" name="department" id="department">
								                            	<option value="">请选择</option>
								                            	<c:forEach items="${departmentList}" var="departmentBean">
								                            		<option value="${departmentBean.dickey }" 
								                            			<c:if test="${departmentBean.dicval eq  supplierBean.department}"> selected </c:if>
								                            		>${departmentBean.dicval }</option>
								                            	</c:forEach>
							                           		</select>
						                           		</div>
							                        </div>
							                        <div class="form-group">
							                          <label class="col-lg-3 control-label">负责人</label>
							                          <div class="col-lg-9">
							                          	<input name="chief" type="text" value="${supplierBean.chief }" class="form-control" placeholder="负责人">
							                          </div>
							                        </div>
							                        <div class="form-group">
							                          <label class="col-lg-3 control-label">联系人</label>
							                          <div class="col-lg-9">
							                          	<input name="linkman" type="text" value="${supplierBean.linkman }" class="form-control" placeholder="联系人">
							                          </div>
							                        </div>
							                        <div class="form-group">
							                          <label class="col-lg-3 control-label">操作时间</label>
							                          <div class="col-lg-9">
							                            <input name="updatetime" type="text" id="updatetime" value='<fmt:formatDate value="${supplierBean.updatetime }" pattern="yyyy-MM-dd"/>' class="form-control" placeholder="操作时间">
							                          </div>
							                        </div>					                        
							                    </div>
							                </div>
						                    <footer class="panel-footer text-center bg-light lter">
						                        <button type="button" id="submitBtn" name="submitBtn" class="btn btn-success btn-s-xs">提交</button>
						                    </footer>
					                  </section>
					             </div>					             
				            </div>
		              	</section>
		              	</form>
		              	<jsp:include page="/WEB-INF/jsp/main_js_inc.jsp"></jsp:include>		              
		              	<script type="text/javascript">
							 $(document).ready(function(){
								 function validateForm(){
									 return $("#supplierForm").validate().form();
								 }
								 
								 $('#submitBtn').click(function (){
									 if(validateForm()){
										 var params=$('#supplierForm').serialize();
										 var postUrl=$('#supplierForm').attr('action');
										 alert(params);
										 alert(postUrl);
										 $.ajax({
								             type: "POST",
								             url: postUrl,
								             data: params,
								             success: function(data){
											 	 data=eval("("+data+")");
								                 if(data.flag){
								                	 $('#supplierForm').bjax({url: '${basePath}/!supplier/supplierlist.do'});
								                 }else{
									                 alert("程序发生错误,请联系运维人员!");
								                 }    
								             }
								         });	
									 }								 
								 });
								jQuery('#updatetime').datetimepicker({
									timepicker : false,
									lang : 'ch',
									format : 'Y-m-d',
									closeOnDateSelect:true,
									scrollInput:false,
									scrollMonth:false,
									scrollInput:false									
	  							});
							 });
		              	</script>
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