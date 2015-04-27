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
					<a href="../10kvCableConnector/page/front/showDemo.jsp">操作向导</a>
				</li>
			</ul>
		</div>
        <div class="span10" style="margin-top:30px;  min-height:600px;">
        	<h4>亲爱的<strong style="color:red;">${ sessionScope.userSession.realName}</strong>，
        	</h4>
        	<c:choose>
        		<c:when test="${requestScope.examineeState=='outofDate' }">
					报名时间已经截止！！
				</c:when>
				<c:when test="${requestScope.examineeState=='noInfoCanSignUp' }">
					您还未报名，请点击<a href="gotoSignUp.action">报名入口</a>报名！
				</c:when>
				<c:when test="${requestScope.examineeState=='waitCheckedCanUpdate' }">
					您已经报名，待审核，可以点击<a href="gotoSignUp.action">报名入口</a>再次修改报名信息，报名之后会删除之前报名的所有信息！
				</c:when>
				<c:when test="${requestScope.examineeState=='initpassCheckCannoutSignup' }">
					您已经报名，初审已通过 ,请等待复审。。
				</c:when>
				<c:when test="${requestScope.examineeState=='initnoCheckCanUpdate' }">
					您已经报名，但是审核没通过，可以点击<a href="gotoSignUp.action">报名入口</a>再次报名，报名之后会删除之前报名的所有信息！
				</c:when>
				<c:when test="${requestScope.examineeState=='noCheckedCanUpdate' }">
					您已经报名，但是审核没通过，可以点击<a href="gotoSignUp.action">报名入口</a>再次报名，报名之后会删除之前报名的所有信息！
				</c:when>
				<c:when test="${requestScope.examineeState=='checkedCannotSignUp' }">
					您已经报名，审核已通过，还未缴费，可能通过以下两种方式进行缴费：
					<br/>
					 <span style="margin-left: 20px">一、网银缴费请点击<a href="doPayPage.action">进入网银缴费页面</a>。</span>
					<br/>
					 <span style="margin-left: 20px"> 二、通过银行转账点击<a href="doGotoFee.action">上传理论考试缴费单</a>。</span>
				</c:when>
				<c:when test="${requestScope.examineeState=='thoryFeeSuccessCannotSignup' }">
					您已经报名，审核已通过，理论考试缴费单审核通过，缴费成功。
					</br>
					还未进行理论考试，请注意考试时间!
					</br>
					如果您需要退还考试费用请点击
					<a href="readyRefund.action">申请退费</a>
				</c:when>
				<c:when test="${requestScope.examineeState=='refOrder' }">
					您的退费申请已经提交，请等待审核。
				</c:when>
				<c:when test="${requestScope.examineeState=='refOk' }">
					您的退费申请审核,请查询金额是否退还。
				</c:when>
				<c:when test="${requestScope.examineeState=='thoryFeeFailCannotSignup' }">
					您已经报名，审核已通过，理论考试缴费单审核未通过，缴费失败，点击<a href="doGotoFee.action">重新上传理论考试缴费单</a>重新上传缴费记录。
				</c:when>
				<c:when test="${requestScope.examineeState=='hasExamCanSignUp' }">
					您的成绩未及格，无法生成证书，可以点击<a href="gotoSignUp.action">报名入口</a>再次报名，报名之后会删除之前报名的所有信息以及成绩信息！
				</c:when>
				<c:when test="${requestScope.examineeState=='hasNoExamCanNotSignUp' }">
					您好，理论成绩信息正在录入，请耐心等待...
				</c:when>
				<c:when test="${requestScope.examineeState=='operateFeeCanUp' }">
					您理论考试已通过，操作考试还未缴费，可能通过以下两种方式进行缴费：
					<br/>
					 <span style="margin-left: 20px">一、网银缴费请点击<a href="doPayPage.action">进入网银缴费页面</a>。</span>
					<br/>
					 <span style="margin-left: 20px"> 二、通过银行转账点击<a href="doGotoFee.action">上传操作考试缴费单</a>。</span>
				</c:when>
				<c:when test="${requestScope.examineeState=='operateFeeCannotUp' }">
					您好，您理论成绩未通过。
				</c:when>
				<c:when test="${requestScope.examineeState=='operatFeeSuccessConnotSignup' }">
					您好，操作考试缴费单已经审核通过，请注意操作考试时间通知
				</c:when>
				<c:when test="${requestScope.examineeState=='operatFeeFailConnotSignup' }">
					您好，操作考试缴费单审核未通过，请点击<a href="doGotoFee.action">重新上传操作考试缴费单</a>重新上传缴费记录。
				</c:when>
				<c:when test="${requestScope.examineeState=='hasValidCertCanSignUp' }">
					您好，恭喜获得证书！！可以点击<a href="doGetPersonFile.action">查看证书</a>查看证书详情<br>
					 可以点击<a href="gotoSignUp.action">报名入口</a>再次报名，<font color="red">报名之后会删除之前报名的所有信息、成绩信息以及证书信息！，谨慎操作</font>
				</c:when>
			</c:choose>
			 <c:if test="${not empty requestScope.extraInfo }">
					<div class="span11">
						<table class="table">
							<tr>
								<th>考试名称</th>
								<th>报名截止日期</th>
								<th>当前批次</th>
							</tr>
							<tr>
								<td>
									10kV接线工证书
									<c:if test="${requestScope.examineeInfo.skillLevel=='1' }">冷缩</c:if>
									<c:if test="${requestScope.examineeInfo.skillLevel=='2' }">热缩</c:if>
									<c:if test="${requestScope.examineeInfo.skillLevel=='3' }">冷缩+热缩</c:if>
									考试
								</td>
								<td><fmt:formatDate value="${requestScope.extraInfo.signLimitDate }" type="date"/></td>
								<td>${requestScope.extraInfo.examBatch }</td>
							</tr>
						</table>
					</div>
				</c:if>
				<c:if test="${not empty requestScope.examineeInfo }">
					<div class="span11">
						<table class="table">
							<tr>
								<th>考生姓名</th>
								<th>考生来源</th>
								<th>审核状态</th>
								<th>审核意见</th>
							</tr>
							<tr>
								<td>${requestScope.examineeInfo.userInfo.realName }</td>
								<td>
									<c:if test="${requestScope.examineeInfo.examineeSource=='0' }">系统内</c:if>
									<c:if test="${requestScope.examineeInfo.examineeSource=='1' }">系统外</c:if>
								</td>
								<td>
									<c:choose>
										<c:when test="${requestScope.examineeInfo.checkState=='0' }">待审核</c:when>
										<c:when test="${requestScope.examineeInfo.checkState=='1' }">复审通过，未缴费</c:when>
										<c:when test="${requestScope.examineeInfo.checkState=='2' }">复审未通过</c:when>
										<c:when test="${requestScope.examineeInfo.checkState=='3' }">理论考试缴费成功</c:when>
										<c:when test="${requestScope.examineeInfo.checkState=='4' }">理论考试缴费失败</c:when>
										<c:when test="${requestScope.examineeInfo.checkState=='5' }">操作考试缴费成功</c:when>
										<c:when test="${requestScope.examineeInfo.checkState=='6' }">操作考试缴费失败</c:when>
										<c:when test="${requestScope.examineeInfo.checkState=='7' }">初审不通过</c:when>
										<c:when test="${requestScope.examineeInfo.checkState=='8' }">初审通过</c:when>
									</c:choose>
								</td>
								<td>${requestScope.examineeInfo.advice }</td>
							</tr>
						</table>
					</div>
				</c:if>
				<c:if test="${not empty requestScope.examInfo }">
					<div class="span11">
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
								<td><fmt:formatDate value="${requestScope.examInfo.theoryExamDate }" pattern="yyyy-MM-dd HH:mm:ss" type="date"/></td>
								<td>${examInfo.theoryExamPlace }</td>
								<td>${examInfo.theoryScore }</td>
								<td>${examInfo.coldMidScore }</td>
								<td>${examInfo.coldTemScore }</td>
								<td>${examInfo.hotMidScore }</td>
								<td>${examInfo.hotTemScore }</td>
							</tr>
						</table>
					</div>
				</c:if>
			
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
