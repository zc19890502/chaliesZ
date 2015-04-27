<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.js"></script>
	<script src="js/ajax.js"></script>
	<meta http-equiv=X-UA-Compatible content=IE=EmulateIE8>
	
	<style type="text/css">
	form table td {
		width:50px;
		height:40px;
	}
		
	</style>
  </head>
 <body>
 
 	<div class="container-fluid  whole" >
        <div class="logo">
        </div>
    </div>      
    <div class="container-fluid whole">
		<div class="pull-left login">
        	 <c:if test="${empty userSession }">
        	 <div  class="heading1">
                 <h4 style="padding:2px;">&nbsp;&nbsp;<i class="icon-user"></i>&nbsp;&nbsp;考生登陆</h4>
             </div>
             
				<form action="dologin.action" method="post" onsubmit="return checklogin()" >
					<table width="100%" cellspacing="3" cellpadding="2"  style="margin-top:20px; font-size:14px; margin-left:5px;">

						<tr>
							<td align="right" >考试名称</td>
							<td>
								<select class="input-medium">
										<option name="">2014年10kV接线工考试</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">&nbsp;&nbsp;账&nbsp;&nbsp;号&nbsp;&nbsp;</td>
							<td>
								<input type="text" placeholder="用户名/邮箱/身份证号码" class="input-medium" name="userInfo.account">
							</td>
						</tr>
						<tr>
							<td align="right">密&nbsp;&nbsp;码&nbsp;&nbsp;</td>
							<td>
								<input type="password" placeholder="密码" class="input-medium" name="userInfo.passwd">
							</td>
						</tr>        
						<tr>
							<td align="right">验证码&nbsp;&nbsp;</td>
							<td>
								<input type="text"  class="input-mini"  id="loginStr" name="randStr"  onkeyup="ajaxCheck('loginStr')">
								<p class="help-inline"><img src="doGetRandImage.action" alt="验证码" onclick="this.src='doGetRandImage.action?a='+Math.random();"></p>
							</td>
						</tr>
						<tr>
								<c:choose>
  									<c:when test="${requestScope.err==0 }"><td colspan="2" align="center" style="color: red">用户名不存在</td></c:when>
  									<c:when test="${requestScope.err==1 }"><td colspan="2" align="center" style="color: red">密码错误</td></c:when>
  								</c:choose>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<button class="btn btn-success"  style="width:80px" type="submit">登陆</button>
							<p class="help-inline" ><a href="">忘记密码？</a>
							</td>
							
						</tr>
					</table>
			  </form>
			  </c:if>
			  <c:if test="${!empty userSession }">
			  	<div  class="heading1">
                 <h3>&nbsp;&nbsp;</h3>
             </div>
        	<div id="login">
				<h4 align="center">欢迎您：${ sessionScope.userSession.realName }</h4>
				<table width="200" align="center" cellpadding="5" height="130" style=" font-size: 18px;" >

					<tr>
						<td style="text-align:left"><a href="doGetPersonFile.action">基本资料</a></td>
						<td style="text-align:center"><a href="doExitSys.action">退出登录</a></td>
					</tr>
					<tr>
						<td style="text-align:left"><a href="checkExamineeInfo.action">报名信息</a></td>
						<td style="text-align:center"><a href="doCheckResult.action">考试信息</a></td>
						
					</tr>

				</table>
				<br>
			  </div>
			  </c:if>
               <div class="insert-block">
                 <div class="insert">
                 	<c:if test="${!empty userSession }">
                     <a href="javascript:alert('您已经登陆！');"><img src="images/regist.png" width="180" height="70"></a>
                     </c:if>
                     <c:if test="${empty userSession }">
                     <a href="isUserLogin.action"><img src="images/regist.png" width="180" height="70"></a>
                     </c:if>
                 </div>
                 <div class="insert">
                 	<c:if test="${!empty userSession }">
                     <a href="javascript:alert('您不是管理员！');"><img src="images/regist.png" width="180" height="70"></a>
                     </c:if>
                     <c:if test="${empty userSession }">
                     <a href="isManageLogin.action"><img src="images/admin-login.png" width="180" height="70"></a>
                     </c:if>
                     
                 </div>
                 <div class="insert">
                     <a href="#"><img src="images/check.png" width="180" height="70"></a>
                 </div>
              </div>
			  <div class="insert-block">
				<p>&nbsp;</p>
				<p>联系方式 </p>
				<p>联系方式 </p>
				<p>联系方式 </p>
				<p>联系方式 </p>
			  </div>
		</div>
		<div style="width:745px; height:700px; float:right;   border:1px solid #9ff;">
			<div class="heading">
				<div class=" row-fluid">
					<div class="span12"  style="background-color:#77C9F9;">
						<h4 style="margin-top:5px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<c:choose>
							<c:when test="${requestScope.noticeType==1 }">最新消息</c:when>
							<c:when test="${requestScope.noticeType==2 }">通知公告</c:when>
							<c:when test="${requestScope.noticeType==3 }">成绩公告</c:when>
						</c:choose>
						<a href="index.jsp"><font style="font-size: 14px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;返回首页</font></a>
						</h4>
					</div>
				</div>
			</div>
			<div class="span8" style="margin-left:50px;">
				<table class="table table-hover ">
					<c:forEach items="${requestScope.noticeList}" var="notice">
						<tr>
							<td ><i class="icon-hand-right"></i></td>
							<td><a href="${notice.noticeContent }" title="${notice.noticeHead }">${fn:substring(notice.noticeHead,"0","30") }...</a></td>	
							<td><fmt:formatDate value="${notice.noticeDate }" type="date"/></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		  </div>	
    </div>
	
	<div class="container-fluid whole">
		<div class=" footer">
			<br>
			<p>Copyright © 2014 By 长沙电力职业技术学院 All Rights Reserved. | 技术支持--<a >湖南宇电智诚科技开发有限公司</a></p>
		</div>
	</div>    
</body>
</html>
