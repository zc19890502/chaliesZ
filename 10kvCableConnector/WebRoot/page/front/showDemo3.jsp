<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>10kV电缆接头考证管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/10kvcc.css" rel="stylesheet">
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

</script>
  </head>
  
  
  <body onLoad="show()">


	<div class="container-fluid whole ">
	<div class="row-fluid">
    	<div class="span12 logo">

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
                         <div class="brand">10kV接线工证书管理系统</div>
						<div class="nav-collapse collapse navbar-responsive-collapse">
							<ul class="nav">
								<li >
									<a href="index.jsp"><i class="icon-home"></i>主&nbsp;&nbsp;页</a>	
								</li>
								<li  >
									 <a>当前时间:</a>
								</li>
                                <li>
                                	<a id="time"></a>
                                </li>	
							</ul>
							<ul class="nav pull-right">
                                
								<li>
									<a ><i class="icon-user"></i>当前用户:</a>
								</li>

								<li class="dropdown">
									 <a data-toggle="dropdown" class="dropdown-toggle" href="#"><strong>${ sessionScope.userSession.realName}</strong><strong class="caret"></strong></a>
									<ul class="dropdown-menu">
										<li>
											<a href="checkExamineeInfo.action">个人信息</a>
										</li>
										<li>
											<a href="doGotoUpdatePsw.action">修改密码</a>
										</li>
										<li>
											<a href="doExitSys.action">退出系统</a>
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
	<div class="row-fluid" style="background-color:#F0F0F0; margin-top: -20px;">
		<div class="span2" style="background-color: #E7F1F3;">
			<ul>
				<li>
					<a href="checkExamineeInfo.action">报名信息</a>
				</li>				
				<li>
					<a href="doCheckResult.action">考试信息</a>
				</li>
				<li>
					<a href="doGetPersonFile.action">基本资料</a>
				</li>
				<li>
					<a href="doGotoUpdatePsw.action">修改密码</a>
				</li>
				<li>
					<a href="../10kvCableConnector/page/front/showDemo.jsp">操作向导</a>
				</li>
			</ul>
		</div>
		<script type="text/javascript">
			function backPage(){
				location="../10kvCableConnector/page/front/showDemo2.jsp";
			}
			function nextPage(){
				location="../10kvCableConnector/page/front/showDemo4.jsp";
			}
		</script>
        <div class="span10" >
			<div style="font-size: x-large;">操作向导:</div>
			<div>&nbsp;</div>
			<div style="font-size: large;">4、如果审核通过将会出现以下页面：请点击<span style="color: red">进入网银缴费页面</span>进行网上缴费，或者<span style="color: red">上传理论考试缴费单</span>将缴费单扫描件上传</div>
			<div>&nbsp;&nbsp;&nbsp;&nbsp;<img src="../10kvCableConnector/images/3.jpg"></img></div>
			<div>&nbsp;</div>			
			<div style="margin-left: 135px;padding-left: 135px;"><input type="button" value="上一步" onclick="backPage()" /><input type="button" value="下一步" onclick="nextPage()" /></div>
		</div>
 	</div>
</div>

 		<div class="container-fluid whole">
			<div class=" footer">
				<br>
				<p>Copyright © 2014 By 长沙电力职业技术学院 All Rights Reserved. | 技术支持--<a href="#">湖南宇电智诚科技有限公司</a></p>
			</div>
		</div> 


<script src="js/bootstrap.js"></script>
<script src="js/jquery.js"></script>
</body>
</html>
