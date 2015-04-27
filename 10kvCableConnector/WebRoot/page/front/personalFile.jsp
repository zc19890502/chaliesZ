<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
					<a href="../10kvCableConnector/page/front/showDemo.jsp">操作演示</a>
				</li>
			</ul>
		</div>
        <div class="span9" style="margin-top:60px; margin-left:5%; min-height:600px;">
        	
        	<c:if test="${empty requestScope.examineeInfo and empty requestScope.examInfo and empty requestScope.certInfoList }">
				<h4>亲爱的<strong style="color:red;">${ sessionScope.userSession.realName}</strong>，
	        	</h4>
	        	您还未报名，请点击<a href="gotoSignUp.action">报名入口</a>报名！
        	</c:if>
        	
			<c:if test="${not empty requestScope.examineeInfo }">
        	<div>
				<h4>基本信息</h4>
				<hr style="border:1px solid #0f0f0f;">
				<table class="table">
					<tr>
						<td>姓名：</td><td>${userSession.realName }</td>
						<td>性别：</td><td>
						<c:if test="${examineeInfo.sex==0 }">女</c:if>
						<c:if test="${examineeInfo.sex==1 }">男</c:if>
						</td>
					</tr>
					<tr>
						<td>用户名：</td><td>${userSession.account }</td>
						<td>邮箱：</td><td>${userSession.email }</td>
					</tr>
					<tr>
						<td>身份证号码：</td><td>${userSession.idnum }</td>
						<td>联系电话：</td><td>${examineeInfo.tel }</td>
					</tr>
					<tr>
						<td>民族：</td><td>${examineeInfo.nationality }</td>
						<td>邮寄通信地址：</td><td>${examineeInfo.homesite }</td>
					</tr>
					<tr>
						<td>政治面貌：</td>
						<td>
						<c:choose>
							<c:when test="${examineeInfo.party==0 }">中共党员</c:when>
							<c:when test="${examineeInfo.party==1 }">共青团员</c:when>
							<c:when test="${examineeInfo.party==2 }">群众</c:when>
							<c:when test="${examineeInfo.party==3 }">民主党派</c:when>
						</c:choose>
						</td>
						<td>学历：</td>
						<td>
						<c:choose>
							<c:when test="${examineeInfo.education==0 }">小学</c:when>
							<c:when test="${examineeInfo.education==1 }">初中</c:when>
							<c:when test="${examineeInfo.education==2 }">中专</c:when>
							<c:when test="${examineeInfo.education==3 }">高中</c:when>
							<c:when test="${examineeInfo.education==4 }">大专</c:when>
							<c:when test="${examineeInfo.education==5 }">本科</c:when>
							<c:when test="${examineeInfo.education==6 }">硕士研究生</c:when>
							<c:when test="${examineeInfo.education==7 }">博士研究生</c:when>
						</c:choose>
						</td>
					</tr>
					<tr>
						<td>工作单位：</td><td>${examineeInfo.company }</td>
						<td>报考类别：</td>
						<td>
						<c:choose>
							<c:when test="${examineeInfo.skillLevel=='1' }">冷缩</c:when>
							<c:when test="${examineeInfo.skillLevel=='2' }">热缩</c:when>
							<c:when test="${examineeInfo.skillLevel=='3' }">冷缩+热缩</c:when>
						</c:choose>	
						</td>
					</tr>
				</table>
				<br><br>
			</div>
			</c:if>

			<c:if test="${not empty requestScope.examInfo }">
            <div>
				<h4>考试成绩信息 </h4>
				<hr style="border:1px solid #0f0f0f;">
	        	<table class="table">
	        		<tr>
	        			<th>考试名称</th>
						<th>理论考试时间</th>
						<th>考试地点</th>
						<th>理论考试成绩</th>
						<th>冷缩(中)</th>
						<th>冷缩(终)</th>
						<th>热缩(中)</th>
						<th>热缩(终)</th>
	        		</tr>
	        		<tr>
	        			<td>10kV接线工证书考试</td>
						<td><fmt:formatDate value="${requestScope.extra.theoryExamDate }" pattern="yyyy-MM-dd HH:mm:ss" type="date"/></td>
						<td>${examInfo.theoryExamPlace }</td>
						<td>${examInfo.theoryScore }</td>
						<td>${examInfo.coldMidScore }</td>
						<td>${examInfo.coldTemScore }</td>
						<td>${examInfo.hotMidScore }</td>
						<td>${examInfo.hotTemScore }</td>
	        		</tr>
	        	</table>
	        	<br><br>
			</div>
			</c:if>
			<c:if test="${not empty requestScope.certInfoList }">
			<div>
				<h4>证书信息</h4>
				<hr style="border:1px solid #0f0f0f;">
					<form id="fom" name="fom">
			          <table class="table table-hover ">
			            <thead>
			                 <tr>
			                   <th align="center" style="text-align: center;">考生姓名</th> 
			                   <th align="center" style="text-align: center;">证书编号</th>
			                   <th align="center" style="text-align: center;">身份证号码</th>
			                   <th align="center" style="text-align: center;">证书剩余分数</th>
			                   <th align="center" style="text-align: center;">证书状态</th>
			                   <th align="center" style="text-align: center;">吊销日期 </th>
			                   <th align="center" style="text-align: center;">发证日期 </th>
			               	</tr> 
			           </thead>
		                    <tbody>
		                    <c:forEach items="${requestScope.certInfoList }" var="certInfo">
		                    <tr>  
			                    <td align="center" style="text-align: center;">${userSession.realName }</td> 
			                    <td align="center" style="text-align: center;">${certInfo.certificationNum }</td>
			                    <td align="center" style="text-align: center;">${userSession.idnum }</td>  
			                    <td align="center" style="text-align: center;">${certInfo.remainingScore}</td>
								<td align="center" style="text-align: center;">
								<c:choose>
									<c:when test="${certInfo.certificationState==1 }">正常</c:when>
									<c:when test="${certInfo.certificationState==2 }">吊销</c:when>
								</c:choose>
								</td>
								<td align="center" style="text-align: center;"><fmt:formatDate value="${certInfo.revokeDate}" type="date"/></td>
								<td align="center" style="text-align: center;"><fmt:formatDate value="${certInfo.certificationGrantDate}" type="date"/></td>
		                    </tr>  
		                    </c:forEach>
		                    </tbody>
		                </table>
            	</form>
				</div>
				</c:if>
			</div>  
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
