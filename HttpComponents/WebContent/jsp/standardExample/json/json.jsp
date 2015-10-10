<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/base.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/WEB-INF/jsp/common/baseJS.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/common/baseCSS.jsp"></jsp:include>
<title>JsonController</title>
</head>
<body>
	<a href="javascript:void(0);" onclick="callJsonControllerRetObject('http://127.0.0.1:8080/SpringMVC/json/object.do')">/SpringMVC/json/object.do</a> <br/>
	<a href="javascript:void(0);" onclick="callJsonControllerRetList('http://127.0.0.1:8080/SpringMVC/json/list.do')">/SpringMVC/json/list.do</a> <br/>
	<a href="javascript:void(0);" onclick="callJsonControllerRetMap('http://127.0.0.1:8080/SpringMVC/json/map.do')">/SpringMVC/json/map.do</a> <br/>
	<a href="javascript:void(0);" onclick="callJsonControllerRetMap_List('http://127.0.0.1:8080/SpringMVC/json/map_list.do')">/SpringMVC/json/map_list.do</a> <br/>
	<a href="javascript:void(0);" onclick="callTest('http://127.0.0.1:8080/SpringMVC/json/map.do')">/SpringMVC/json/callTest.do(实际调用后台/SpringMVC/json/map.do)</a> <br/>
	<textarea  id="area" rows="60" cols="100"></textarea>
	
	<script type="text/javascript">
		 
		 
		/* "http://127.0.0.1:8080/SpringMVC/json/object.do" */
		function callJsonControllerRetObject(address){//基础方法
			$.ajax({
				type : "post",
				url : address,
				/* 当返回格式用标准的json时,取得的返回数据data不用再var obj = eval("("+data+")")进行加工,
				但是此时取得的data不易展示,打印出来的都是Object,因为已经被jquery作过特殊处理了,
				所以尽量还是用text吧,这样我们alert(data)可以看到原始数据来进行详细分析*/
				dataType : "text",
				success : function(data) {
					console.log(data);
					// $.parseJSON(data); 功能同下
					var json = eval("("+data+")");//得到的字符串必须是 --> {'键':'值','键':'值'} 而不是   {键:值，键:值}
					/* var json = JSON.parse(data); //本方法和上一行同样效果 */
					$("#area").text(json.birthDate);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
		}
		
		function callJsonControllerRetList(address){
			$.post(address,	{},
					function(data,textStatus){
						console.log(data);
						var json = eval("("+data+")");//这里要加上 括号 的原因未知，就当是json语法，只能死记硬背了
						var container = "";
						
						/* $(json).each(function(i,obj){ // 该方法和下面的each基本一致,不过该方法一般主要用于遍历html的Dom节点时使用,如$("li").each(function(i,obj){alert(this);})
							alert(i);
							alert(json[i].name);
						}); */
						$.each(json,function(i,obj){
							container += i + "的信息如下:\n";
							container +="id="+obj.id+"  ,  ";
							container +="name="+obj.name+"\n";
						});
						$("#area").text(container);
					}
			//[{"id":1,"name":"bobo","address":"浙江","tuition":3000.0},{"id":2,"name":"sisi","address":"上海","tuition":8000.0}]
			);
		} 
		
		function callJsonControllerRetMap(address){
			$.post(address,	{},
					function(data,textStatus){
						console.log(data);
						var json = eval("("+data+")");//这里要加上 括号 的原因未知，就当是json语法，只能死记硬背了
						var container = "";
						
						/* $(json).each(function(i,obj){ // 该方法和下面的each基本一致,不过该方法一般主要用于遍历html的Dom节点时使用,如$("li").each(function(i,obj){alert(this);})
							alert(i);
							alert(json[i].name);
						}); */
						
						$.each(json,function(i,obj){//我们会神奇地发现,这里的 【i】会变成json的key值//$$$$$5星好评
							container += i + "的信息如下:\n";
							container +="id="+obj.id+"  ,  ";
							container +="name="+obj.name+"\n";
						});
						$("#area").text(container);
						//___________以上和list方法100%相同________________
						//list和map其实返回的是一个意思,只不过map能用key名取值,而list则只能用序号
						//[{"id":1,"name":"bobo","address":"浙江","tuition":3000.0},{"id":2,"name":"sisi","address":"上海","tuition":8000.0}]
						//{"bobo":{"id":1,"name":"bobo","address":"浙江","tuition":3000.0},"sisi":{"id":2,"name":"sisi","address":"上海","tuition":8000.0}}
						//json里如果有map的话可以直接用key名取值,如json.bobo
						alert(json.bobo.name);
						
						/* for(var name in json){//如果要遍历json map的key值,也可以用该方式,但鉴于有上面的$.each(),此方式基本作废
							alert(name);//alert key
							alert(json[name].address);//alert bobo的address,和alert sisi的address
						} */
					}
			);
		} 
		
		function callJsonControllerRetMap_List(address){
			$.post(address,	{},
					function(data,textStatus){
						console.log(data);
						var json = eval("("+data+")");//这里要加上 括号 的原因未知，就当是json语法，只能死记硬背了
						var container = "";
						
						/* $(json).each(function(i,obj){ // 该方法和下面的each基本一致,不过该方法一般主要用于遍历html的Dom节点时使用,如$("li").each(function(i,obj){alert(this);})
							alert(i);
							alert(json[i].name);
						}); */
						
						$.each(json,function(i,obj){
							container +="teacher | "+ i +"教的2个学生如下:\n";//我们会神奇地发现,这里的 【i】会变成json的key值//$$$$$5星好评
							$.each(obj,function(j,stu){
								container +="id="+stu.id+"  ,  ";
								container +="name="+stu.name+"\n";
							})
						});
						$("#area").text(container);
/* 						{"teacher1":[
						               {"id":1,"name":"bobo","address":"浙江","tuition":3000.0},
						               {"id":2,"name":"sisi","address":"上海","tuition":8000.0}],
						  "teacher2":[{"id":1,"name":"haha","address":"北京","tuition":3000.0},
						                 {"id":2,"name":"hehe","address":"广州","tuition":8000.0}
						 ]}  */
						alert(json.teacher1[0].name);
					}
			);
		} 
		
		//见 http://www.frontopen.com/1394.html
		function callTest(address){
			callJsonControllerRetMap.call(callJsonControllerRetList,address);//用map方法来替换list方法
		}
	</script>
</body>
</html>