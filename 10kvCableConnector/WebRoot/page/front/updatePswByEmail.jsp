<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<script src="js/ajax.js"></script>
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
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid" style="background-color:#F0F0F0; margin-top: -20px;">
			<div class="span2">
				<c:choose>
					<c:when test="${requestScope.errState=='noThisUser' }">您还未注册</c:when>
					<c:when test="${requestScope.errState=='noinput' }">请输入身份证和密码</c:when>
				</c:choose>
			</div>
        	<div class="span8" style="margin-top:30px;  min-height:600px;">
        		<form  action="doResetPasswd.action" name="form1" method="post" class="form-horizontal" style="margin-left:5%;" onsubmit="return checkPswOnsubmit()">
               		<fieldset  >
                  		<div id="legend" class="">
                    	<legend>重新设置密码</legend>
                  		</div>
                  		<br>
                  		<div class="control-group">
                      		<!-- Text input-->
                      		<label class="control-label" for="input01">身份证:</label>
                      		<div class="controls">
                       			<input type="password" placeholder="请输入身份证" id="idnum" name="idNum"><br>
                      		</div>
                    	</div>
                  		<div class="control-group">
                      		<!-- Text input-->
                      		<label class="control-label" for="input01">新密码:</label>
                      		<div class="controls">
                       			<input type="password" placeholder="请输入新密码" id="newPassword" name="newPasswsd"  onchange="checkPsw1()" onclick="clickPsw1()"><br>
                      		    <p class="help-inline" id="pswtip1"></p>
                      		</div>
                    	</div>
                   		<div class="control-group">
                      		<!-- Text input-->
                      		<label class="control-label" for="input01">确认密码:</label>
                      		<div class="controls">
                      			<input type="password" placeholder="请输再次入新密码" id="password" onkeyup="checkPsw2()" onclick="clickPsw2()"><br>
                      		    <p class="help-inline" id="pswtip2"></p>
                      		</div>
                    	</div>
                    	<div class="control-group">
	                      <label class="control-label" for="input01">&nbsp;</label>
	                      <div class="controls">
	                        <input type="submit" value="修改密码" class="btn btn-success "  />
	                      </div>
	                    </div>
                	</fieldset>
             	</form>
			</div>
 		</div>
	</div>
	<div class="container-fluid whole">
		<div class=" footer">
			<br>
			<p>Copyright © 2014 By 长沙电力职业技术学院 All Rights Reserved. | 技术支持--<a href="#">湖南宇电智诚科技有限公司</a></p>
		</div>
	</div> 
</body>
</html>





        