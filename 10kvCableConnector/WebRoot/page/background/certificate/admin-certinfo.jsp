<%@ page language="java"
	import="java.util.*,com.csdl.cabexam.beans.ManagerInfo"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/myTld"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	if (null == request.getSession().getAttribute("managerSession")) {
		response.sendRedirect(basePath + "isManageLogin.action");
	}
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>10kV电缆接头考证管理系统</title>
<link href="css/bootstrap.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.js"></script>
<meta http-equiv=X-UA-Compatible content=IE=EmulateIE8>

<style type="text/css">
.bg {
	background-color: #6ff;
}
</style>
<script>
	function show() {
		var date = new Date(); //日期对象 
		var now = "";
		now = date.getFullYear() + "年"; //读英文就行了 
		now = now + (date.getMonth() + 1) + "月"; //取月的时候取的是当前月-1如果想取当前月+1就可以了 
		now = now + date.getDate() + "日  ";
		now = now + date.getHours() + ":";
		now = now + date.getMinutes() + ":";
		now = now + date.getSeconds();
		document.getElementById("time").innerHTML = now; //div的html是now这个字符串 
		setTimeout("show()"); //设置过1000毫秒就是1秒，调用show方法 
	}
</script>
</head>

<body onLoad="show()">

	<div class="container-fluid"
		style="max-width:1200px; margin:0 auto; min-width:900px; ">
		<div class="row-fluid">
			<div class="span12"
				style="height:240px; border:1px; background:url(images/head_back.png) no-repeat;">

			</div>
		</div>
		<div class="row-fluid">

			<div class="span12">
				<div class="navbar">
					<div class="navbar-inner">
						<div class="container-fluid">
							<a data-target=".navbar-responsive-collapse"
								data-toggle="collapse" class="btn btn-navbar"> <span
								class="icon-bar"></span><span class="icon-bar"></span> <span
								class="icon-bar"></span> </a>
							<div class="brand">10kV接线工证书管理系统</div>
							<div class="nav-collapse collapse navbar-responsive-collapse">
								<ul class="nav">
									<li><a href="isManageLogin.action"><i
											class="icon-home"></i>主&nbsp;&nbsp;页</a>
									</li>
									<li style="margin-left:100px;">&nbsp;</li>
									<li><a>当前时间:</a>
									</li>
									<li><a id="time"></a>
									</li>
								</ul>
								<ul class="nav pull-right">
									<li><a><i class="icon-user"></i>当前用户:</a>
									</li>

									<li class="dropdown"><a data-toggle="dropdown"
										class="dropdown-toggle" href="#">${
											sessionScope.managerSession.realName}<strong class="caret"></strong>
									</a>
										<ul class="dropdown-menu">
											<li><a
												href="doQueryManagerById.action?managerId=${ sessionScope.managerSession.managerId}">个人信息</a>
											</li>
											<li><a href="doManagerExitSys.action">退出系统</a>
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
		<div class="row-fluid"
			style="border: 1px #ccc solid; border-bottom:hidden; background-color:#F0F0F0; margin-top:-20px;">
			<div class="span2" style="min-height: 600px;">

				<div class="accordion" id="accordion-49699" style="margin-top:30px;">

					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" href="doExtraTableInfo.action">开放报名</a>
						</div>
					</div>

					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent="#accordion-49699" href="#accordion-element-292849">
								考生报名审核<i class="icon-chevron-down"></i>
							</a>
						</div>
						<div id="accordion-element-292849"
							class="accordion-body collapse ">
							<div class="accordion-inner">
								<a href="doQueryAllExammer.action">所有报名考生</a>
							</div>
							<div class="accordion-inner">
								<a href="doQueryExammerByState.action?checkState=0">待初审考生</a>
							</div>
							<div class="accordion-inner ">
								<a href="doQueryExammerByState.action?checkState=7">初审未通过考生</a>
							</div>
							<div class="accordion-inner ">
								<a href="doQueryExammerByState.action?checkState=8">初审通过考生</a>
							</div>

							<c:if test="${sessionScope.managerSession.powerGrade==0 }">
								<div class="accordion-inner">
									<a href="doQueryExammerByState.action?checkState=8">待复审考生</a>
								</div>
								<div class="accordion-inner">
									<a href="doQueryExammerByState.action?checkState=2">复审未通过考生</a>
								</div>
								<div class="accordion-inner ">
									<a href="doQueryExammerByState.action?checkState=1">复审通过考生</a>
								</div>
							</c:if>
						</div>
					</div>

					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent="#accordion-49699" href="#accordion-element-292860">
								缴费管理 <i class="icon-chevron-down"></i>
							</a>
						</div>
						<div id="accordion-element-292860" class="accordion-body collapse">
							<div class="accordion-inner">
								<a href="doQueryTheoryFeeByState.action">理论考试缴费管理</a>
							</div>
							<div class="accordion-inner">
								<a href="doQueryOperateFeeByState.action">操作考试缴费管理</a>
							</div>
							<div class="accordion-inner">
								<a href="feeManager.action">缴费管理</a>
							</div>
							<div class="accordion-inner">
								<a href="feeStatistics.action?Mark=y">缴费统计</a>
							</div>
						</div>
					</div>
					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent="#accordion-49699" href="#accordion-element-292850">
								考试信息管理<i class="icon-chevron-down"></i>
							</a>
						</div>
						<div id="accordion-element-292850" class="accordion-body collapse">
							<div class="accordion-inner">
								<a href="doNumberTableInfo.action">准考证管理</a>
							</div>
							<div class="accordion-inner ">
								<a href="doQueryAllExamManage.action">考场安排</a>
							</div>
						</div>
					</div>
					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent="#accordion-49699" href="#accordion-element-292858">
								考试成绩管理<i class="icon-chevron-down"></i>
							</a>
						</div>
						<div id="accordion-element-292858" class="accordion-body collapse">
							<div class="accordion-inner ">
								<a href="doTheoryScoreTableInfo.action">理论成绩管理</a>
							</div>
							<div class="accordion-inner ">
								<a href="doAllScoreTableInfo.action">操作成绩管理</a>
							</div>
							<div class="accordion-inner ">
								<a href="doScoreTableInfo.action">历年成绩管理</a>
							</div>
						</div>
					</div>

					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent="#accordion-49699" href="#accordion-element-292851">
								证书信息管理<i class="icon-chevron-down"></i>
							</a>
						</div>
						<div id="accordion-element-292851" class="accordion-body collapse in">
							<div class="accordion-inner ">
								<a href="doGetAllCertInfo.action">证书列表</a>
							</div>
							<div class="accordion-inner ">
								<a href="doUploadCertInfoXls.action">证书录入</a>
							</div>
							<div class="accordion-inner ">
							<a href="doAllRecord.action">年度评价</a>
							</div>
						</div>
					</div>
					<c:if test="${sessionScope.managerSession.powerGrade==0 }">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-parent="#accordion-49699"
								href="doQueryAllManager.action">管理员信息管理</a>
						</div>
					</c:if>
					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent="#accordion-49699" href="#accordion-element-292852">
								公告信息管理<i class="icon-chevron-down"></i>
							</a>
						</div>
						<div id="accordion-element-292852" class="accordion-body collapse">
							<div class="accordion-inner">
								<a href="doGetAllNotice.action">所有公告</a>
							</div>

						</div>
					</div>
				</div>
			</div>
			<div class="span9"
				style="height:150px; border-bottom:solid #0cF 1px; margin-top:10px; margin-left:50px;">
				<form class="form-inline" action="doQueryCertByCondition.action"
					method="post">
					<fieldset>
						<legend>证书管理</legend>
						<table style="margin-left:40px; text-align:right;border:1px; "
							cellpadding="10">
							<tr>
								<td>姓名:</td>
								<td><input type="text" class="input-small" id="realName"
									name="c_realName" value="${requestScope.realName }"></td>
								<td>证书编号:</td>
								<td><input type="text" class="input-small" id="certnum"
									name="c_certnum" value="${requestScope.certnum }"></td>
								<td>身份证号:</td>
								<td><input type="text" class="input-small" id="idnum"
									name="c_idnum" value="${requestScope.idnum }"></td>
								<td style="float: right"><input type="submit"
									class="btn btn-success " value="查询" />
							</tr>
						</table>
					</fieldset>
				</form>
			</div>
			<div class="span9" style="margin-top:10px; margin-left:50px">
				<form id="fom" name="fom">
					<table class="table table-hover ">
						<thead>
							<tr>
								<th align="center" style="text-align: center;">考生姓名</th>
								<th align="center" style="text-align: center;">证书编号</th>
								<th align="center" style="text-align: center;">身份证号码</th>
								<th align="center" style="text-align: center;">证书状态</th>
								<th align="center" style="text-align: center;">吊销日期</th>
								<th align="center" style="text-align: center;">发证日期</th>
								<th align="center" style="text-align: center;">剩余分数</th>
								<th align="center" style="text-align: center;">详情</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<s:splitPage pageSize="10"
									page="doGetAllCertInfo.action?realName=${requestScope.realName }&certnum=${requestScope.certnum }&idnum=${requestScope.idnum }"
									data="${ requestScope.certInfoList }">
									<tr>
										<td align="center" style="text-align: center;">${splitData.userInfo.realName
											}</td>
										<td align="center" style="text-align: center;">${splitData.certificationNum
											}</td>
										<td align="center" style="text-align: center;">${splitData.userInfo.idnum
											}</td>
										<td align="center" style="text-align: center;"><c:choose>
												<c:when test="${splitData.certificationState==1 }">正常</c:when>
												<c:when test="${splitData.certificationState==2 }">吊销</c:when>
											</c:choose>
										</td>
										<td align="center" style="text-align: center;"><fmt:formatDate
												value="${splitData.revokeDate}" pattern="yyyy-MM-dd" type="date" /></td>
										<td align="center" style="text-align: center;"><fmt:formatDate
												value="${splitData.certificationGrantDate}" pattern="yyyy-MM-dd" type="date" />
										</td>
										<td align="center" style="text-align: center;">${splitData.remainingScore
											}</td>
										<td align="center" style="text-align: center;"><button
												type="button" class="btn"
												onclick="javascript:location.href='doGetCertInfoDetail.action?certInfoId=${splitData.certificateInfoId }'">详情</button>
										</td>
									</tr>
								</s:splitPage>
							</tr>
							<tr>
								<td colspan="12" style="text-align:right; border-bottom:none;">
									<button type="button" class="btn"
										onclick="javascript:location.href='doDownloadCertXls.action?fileName=xls/down/certInfo.xls'">下载所有正常excel</button>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
				<div style="height:100px;"></div>
			</div>

			<div class="span12"
				style="max-width:1200px; margin:0 auto; min-width:800px;">
				<div class="row-fluid"
					style="background-color:#3CF; height:60px; text-align:center">
					<p></p>
					<br>
					<p>
						Copyright © 2014 By 长沙电力职业技术学院 All Rights Reserved. | 技术支持--<a>湖南宇电智诚科技开发有限公司</a>
					</p>
				</div>
			</div>
		</div>

	</div>
	<script src="js/bootstrap.js"></script>
	<script src="js/jquery.js"></script>
</body>
</html>
