<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/myTld"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
if(null==request.getSession().getAttribute("managerSession")){
	response.sendRedirect(basePath+"isManageLogin.action");
}
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>后台首页</title>
<link href="css/bootstrap.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.js"></script>
<meta http-equiv=X-UA-Compatible content=IE=EmulateIE8>

<style type="text/css">
	.bg {
		background-color:#6ff;
	}
</style>
<script>
  function show(){ 
	var date = new Date(); //日期对象 
	var now = ""; 
	now = date.getFullYear()+"年"; //读英文就行了 
	now = now + (date.getMonth()+1)+"月"; //取月的时候取的是当前月-1如果想取当前月+1就可以了 
	now = now + date.getDate()+"日  "; 
	now = now + date.getHours()+":"; 
	now = now + date.getMinutes()+":"; 
	now = now + date.getSeconds(); 
	document.getElementById("time").innerHTML = now; //div的html是now这个字符串 
	setTimeout("show()"); //设置过1000毫秒就是1秒，调用show方法 
   }
   
    
   function selectAll(){
	var obj = document.fom.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "delid"){
			obj[i].checked = true;
		}
	}
}


</script>
</head>

<body onLoad="show()">

<div class="container-fluid" style="max-width:1200px; margin:0 auto; min-width:900px; ">
	<div class="row-fluid">
    	<div class="span12" style="height:240px; border:1px; background:url(images/head_back.png) no-repeat;">

        </div>
    </div>
	<div class="row-fluid">
		<div class="span12">
			<div class="navbar">
				<div class="navbar-inner">
					<div class="container-fluid">
						 <a data-target=".navbar-responsive-collapse" data-toggle="collapse" class="btn btn-navbar">
                         <span class="icon-bar"></span><span class="icon-bar"></span>
                         <span class="icon-bar"></span></a> 
                         <div class="brand">10kV电缆接头考证管理系统</div>
						<div class="nav-collapse collapse navbar-responsive-collapse">
							<ul class="nav">
								<li >
									<a href="isManageLogin.action"><i class="icon-home"></i>主&nbsp;&nbsp;页</a>	
								</li>
								<li style="margin-left:100px;">
									&nbsp;
								</li>
								<li>
									 <a>当前时间:</a>
								</li>
                                <li>
                                	<a id="time"></a>
                                </li>	
							</ul>
							<ul class="nav pull-right">
                            	<li>
                                	
                                </li>
                                
								<li>
									<a ><i class="icon-user"></i>当前用户:</a>
								</li>

								<li class="dropdown">
									 <a data-toggle="dropdown" class="dropdown-toggle" href="#">${ sessionScope.managerSession.realName}<strong class="caret"></strong></a>
									<ul class="dropdown-menu">
										<li>
											<a href="doQueryManagerById.action?managerId=${ sessionScope.managerSession.managerId}">个人信息</a>
										</li>
										<li>
											<a href="doManagerExitSys.action">退出系统</a>
										</li>
									</ul>
								</li>
							</ul>
						</div>
						
					</div>
				</div>
				
			</div>
		</div>
	</div>