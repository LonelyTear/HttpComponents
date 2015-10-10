<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/base.jsp"%>
${pager.queryString}
<input id="url" type="hidden" value="${pager.requestURI }?${pager.queryString}"/>
<form id="pager" action="#" onsubmit="return jumpPageTo(this);"
	style="font-size: 20px" method="post" data-meta="test test test">
	<div>
		<div id="pagerBar" class="col-sm-4 text-center"  data-result="" >
		  <small id="pageBanner">Page ${pager.currentPageNo} ------------------------------- SHOWING ${pager.start+1 }-${pager.end } OF ${pager.currentPageSize } ITEMS  ALL SUM ${pager.totalCount } SIZE</small>
		    <a href="javascript:void(0);" onclick='go2page(this,${pager.currentPageNo-1})'>&lt;</a>
		    	<c:forEach begin="${(pager.currentPageNo -4)<=0 ? 1: (pager.currentPageNo-3)}" 
		    			end="${(pager.currentPageNo+3)< pager.totalPageSize ? (pager.currentPageNo+3) : pager.totalPageSize}" 
		    			var="index">
					    	<c:choose>
							   		<c:when test="${pager.currentPageNo==index}">
							   			<a href="javascript:void(0);" onclick="go2page(this,${index})">${index }</a>
							   		</c:when>
							   		<c:otherwise>
							   			<a href="javascript:void(0);" onclick="go2page(this,${index})">${index }</a>
							   		</c:otherwise>
					    	</c:choose>
							   			<%-- <a href="javascript:void(0);" onclick="mv(this,${index})">${index}</a> --%>
		   		 </c:forEach>
		    <a href="javascript:void(0);" onclick='go2page(this,${pager.currentPageNo+1})'>&gt;</a>
			<input type="text" name="pageNumber" id="pageNumber" value="" /> 
			<input type="button"  id="jumpPage" value="跳转" onclick='jumpPageTo(this)' class="button green small "/> 
			 <!-- <a href="javascript:void(0);" onclick='jumpPageTo(this)'>跳转</a> -->
		</div>
	</div>
</form>
<script type="text/javascript">
	function enterPress(){
		if(event.keyCode == 13){//enter键
			alert("13");
		}
	}

	function jumpPageTo(thisDom){//@@@@@这里奇怪的事,如果我把方法改成jumpPage(thisDom),那么button的onclick会报错.....无语
		var index = $("#pageNumber").val();
		go2page(thisDom,index);
		return false;//阻止表单submit提交
	}
	
	function go2page(thisDom,index){
		//$('#pageNumber').val(index);// 点击后,设置新的当前页
		//$('#submit').val(index);// 点击后,设置新的当前页
		//var formObj=$(thisDom).parents('form');//提取最近的父form
		//var formid=formObj.attr('id');//获取父form的id
		//var url		=formObj.attr('action');//获取父form的action
		//var arg=$('#'+formid).serialize();//把from的表单数据序列化
		var url = $("#url").val();
		alert(url);
		display(url,index);
	}
	
	function display(url,index){
		$.post(url,
				{pageNumber:index},
				function(data,textStatus){
					$("#pageInfo").html(data);
				}
		);
	}
	
</script>