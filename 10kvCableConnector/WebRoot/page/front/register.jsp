<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
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
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<meta http-equiv=X-UA-Compatible content=IE=EmulateIE8>
	<script charset="utf-8" type="text/javascript" src="js/ajax.js" ></script>
  </head>
  
<!--  <c:if test="${requestScope.err==0 }">注册失败,用户名已存在</c:if>
  <c:if test="${requestScope.err==1 }">注册失败,身份证已存在</c:if>
  <c:if test="${requestScope.err==2 }">注册失败,验证码错误</c:if> --> 
  <body>
	<div class="container-fluid  whole" >
        <div class="logo">
        </div>
    </div> 
    <div class="container-fluid  whole1" style=" background-color: #f0f0f0; min-height: 600px;">
            	<div class="span11">
                	<br><br>
                	 <form class="form-horizontal" style="margin-left:10%;" method="post" action="doRegister.action" onsubmit="return checkregisterall()">
                            <fieldset  >
                              <div id="legend" class="">
                                <legend>用户注册<font style="font-size:10px;color:red;">&nbsp;&nbsp;&nbsp;(*为必填项)</font></legend>
                              </div>
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">用户名</label>
                                  <div class="controls">
                                    <input type="text" class="input-medium" name="userInfo.account" id="registeraccount" onblur="checkaccount('registeraccount')" >
                                    <p class="help-inline" id="tip1"><font style="color:red;font-size:20px;">*</font></p>
                                  </div>
                                </div>
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">密码</label>
                                  <div class="controls">
                                    <input type="password" class="input-medium" name="userInfo.passwd" id="password1" onblur="checkpasswd()" >
                                    <p class="help-inline" id="tip2"><font style="color:red;font-size:20px;">*</font></p>
                                  </div>
                                </div>
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">确认密码</label>
                                  <div class="controls">
                                    <input type="password" class="input-medium" name="userInfo.passwd1" id="password2"  onblur="checkcoherence()" >
                                    <p class="help-inline" id="tip3"><font style="color:red;font-size:20px;">*</font></p>
                                  </div>
                                </div>
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">电子邮箱</label>
                                  <div class="controls">
                                    <input type="text"  class="input-medium" placeholder="125487@qq.com" name="userInfo.email" id="registeremail"  onblur="checkemail('registeremail')" >
                                    <p class="help-inline" id="tip4"><font style="color:red;font-size:20px;">*</font></p>
                                  </div>
                                </div>
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">真实姓名</label>
            						
                                    <!-- File Upload -->
                                    <div class="controls">
                                    <input type="text"  class="input-medium"  name="userInfo.realName" id="registername"  onblur="checkname('registername')" >
                                    <p class="help-inline" id="tip7"><font style="color:red;font-size:20px;">*</font></p>
                                    </div>
                                </div>
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">身份证号码</label>
                                  <div class="controls">
                                    <input type="text"  class="input-medium" name="userInfo.idnum" id="registeridnum"  onblur="checkidnum('registeridnum')" >
                                    <p class="help-inline" id="tip5"><font style="color:red;font-size:20px;">*</font></p>
                                  </div>
                                </div>
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">验证码</label>
                                  <div class="controls">
                                    <input type="text"  class="input-small" name="randStr" id="registerStr"  onkeyup="ajaxCheck('registerStr')">
                                    <p class="help-inline"><img src="doGetRandImage.action" alt="验证码" onclick="this.src='doGetRandImage.action?a='+Math.random();"><font style="color:red;font-size:20px;">*</font></p>
                                  </div>
                                </div>
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">&nbsp;</label>
                                  <div class="controls">
                                    <button type="submit"  class="btn btn-success input-medium" id="registersubmit">提交</button>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="index.jsp">退出</a>
                                  </div>
                                </div>
                                </fieldset>
                                
                    </form>
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
