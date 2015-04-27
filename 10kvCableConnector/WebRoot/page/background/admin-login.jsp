<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html >
<html >
<head>
 <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>10kV电缆接头考证管理系统-后台登陆</title>
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/10kvcc.css" rel="stylesheet">
<script src="js/jquery.js"></script>
<script src="js/ajax.js"></script>
<style>

</style>
</head>
<body style="background-color:#615B63">
          <div id="myModal" class="modal" style="height:600px;">
				<div class="center">
                	<form action="doManagerLogin.action" method="post">
                        <table class="table" width="100%">
                            <tr>
                                <td style="text-align:right; vertical-align: middle;">用户名</td>
                                <td><input type="text" name="managerInfo.managerAccount" value="${requestScope.managererr.managerAccount }"><br></td>
                                <c:choose>
	                                <c:when test="${err==0 }"><td style="text-align: left; vertical-align: middle;color: red;">用户名不存在</td></c:when>
	                            	<c:when test="${err==1 }"><td style="text-align: left; vertical-align: middle;color: red;">密码错误</td></c:when>
	                                <c:when test="${err==2 }"><td style="text-align: left; vertical-align: middle;color: red;">验证码错误</td></c:when>
                            	</c:choose>
                            </tr>
                            <tr>
                                <td style="text-align:right; vertical-align: middle;">密码</td>
                                <td><input type="password" name="managerInfo.passwd" value="${requestScope.managererr.passwd }"><br></td>
                            </tr>
                            <tr>
                                <td style="text-align:right; vertical-align: middle;">验证码</td>
                                
                                <td><input type="text" class="input-small" name="randStr" id="loginStr" onkeyup="ajaxCheck('loginStr')">
                                	<img src="doGetRandImage.action" alt="验证码" onclick="this.src='doGetRandImage.action?a='+Math.random();"></p>
                              	</td>
                            </tr>
                            <tr>
                                <td colspan="2" style="text-align:center">
                                    <button class="btn btn-success " type="submit" >登录</button> &nbsp;&nbsp;&nbsp;&nbsp;
                                    <button class="btn btn-success " type="reset" >取消</button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
          </div>

</body>
</html>
