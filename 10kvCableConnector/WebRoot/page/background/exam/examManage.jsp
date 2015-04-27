<%@ page language="java"
	import="java.util.*,com.csdl.cabexam.beans.ManagerInfo"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <%@ taglib prefix="s" uri="/struts-tags" %> --%>
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
<script src="js/mutiChoose.js"></script>
<meta http-equiv=X-UA-Compatible content=IE=EmulateIE8>

<style type="text/css">
.bg {
	background-color: #6ff;
}
</style>

</head>

<body onLoad="setCheck()">

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
								class="icon-bar"></span>
							</a>
							<div class="brand">10kV电缆接头考证管理系统</div>
							<div class="nav-collapse collapse navbar-responsive-collapse">
								<ul class="nav">
									<li><a href="isManageLogin.action"><i
											class="icon-home"></i>主&nbsp;&nbsp;页</a></li>
									<li style="margin-left:100px;">&nbsp;</li>
									<li><a>当前时间:</a></li>
									<li><a id="time"></a></li>
								</ul>
								<ul class="nav pull-right">


									<li><a><i class="icon-user"></i>当前用户:</a></li>

									<li class="dropdown"><a data-toggle="dropdown"
										class="dropdown-toggle" href="#">${
											sessionScope.managerSession.realName}<strong class="caret"></strong>
									</a>
										<ul class="dropdown-menu">
											<li><a
												href="doQueryManagerById.action?managerId=${ sessionScope.managerSession.managerId}">个人信息</a>
											</li>
											<li><a href="doManagerExitSys.action">退出系统</a></li>
										</ul></li>
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
							class="accordion-body collapse">
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
						<div id="accordion-element-292850" class="accordion-body collapse in">
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
						<div id="accordion-element-292851" class="accordion-body collapse">
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
				<form class="form-inline"
					action="doQueryExamManageByCondition.action" method="post">
					<fieldset>
						<legend>考务管理</legend>
						<table style="margin-left:40px; text-align:right;border:1px; "
							cellpadding="10">
							<tr>
								<td>考生姓名:</td>
								<td><input type="text" class="input-small"
									placeholder="考生姓名" id="realName" name="realName"
									value="${requestScope.realName }">
								</td>
								<td>准考证号:</td>
								<td><input type="text" class="input-small"
									placeholder="准考证号" id="number" name="number"
									value="${requestScope.number }">
								</td>
								<td style="float: right"><input type="submit"
									class="btn btn-success " value="查询" />
							</tr>
						</table>
					</fieldset>
				</form>
			</div>
			<div class="span8"
				style="margin:10px;min-height: 600px; margin-left:8%;">
				<form id="fom" name="fom">
					<table class="table ">
						<thead>
							<tr>
								<th align="center"><input type="checkbox" name="controlAll"
									id="controlAll" value="全选" onchange="selectAll()">全选</th>
								<th align="center">考试ID</th>
								<th align="center">姓名</th>
								<th align="center">准考证号</th>
								<th align="center">考试时间</th>
								<th align="center">考场号</th>
								<th align="center">考试地点</th>
								<th align="center" colspan="2">操作</th>
							</tr>
						</thead>
						<tody> <s:splitPage pageSize="10"
							page="doQueryAllExamManage.action?realName=${requestScope.realName }&number=${requestScope.number }"
							data="${examManageList}">
							<tr>
								<td align="center"><input type="checkbox"
									name="mutiNumberId" id="${splitData.examInfoId}"
									onchange="checkFile(${splitData.examInfoId})">
								</td>
								<td align="center">${splitData.examInfoId}</td>
								<td align="center">${splitData.userInfo.realName}</td>
								<td align="center">${splitData.number}</td>
								<%--<td align="center"><fmt:formatDate value="${splitData.theoryExamDate}" type="date"/></td>
                    <td align="center">${splitData.theoryExamRoom}</td>
                    <td align="center">${splitData.theoryExamPlace}</td>
                    --%>
								<td align="center"><fmt:formatDate
										value="${splitData.theoryExamDate}" pattern="yyyy-MM-dd HH:mm:ss" type="date" />
								</td>
								<td align="center">${splitData.theoryExamRoom}</td>
								<td align="center">${splitData.theoryExamPlace}</td>
								<td align="center"><a
									href="doQueryExamManageById.action?examInfoId=${splitData.examInfoId}">
										修改 </a>
								</td>
								<c:if
									test="${(not empty splitData.number) and (not empty splitData.theoryExamDate) and (not empty splitData.theoryExamRoom) and (not empty splitData.theoryExamPlace) }">
									<td align="center"><a
										href="doDownloadAdmissionById.action?userInfoId=${splitData.userInfo.userInfoId }&examInfoId=${splitData.examInfoId}&fileName=temp/${splitData.userInfo.realName}_${splitData.number}.doc"
										target="_self">准考证 </a>
									</td>
								</c:if>
							</tr>
						</s:splitPage>
						<tr>
							<td colspan="9" style="text-align:right; border-bottom: none;">
								<button type="button" class="btn"
									onclick="javascript:location.href='doDownloadChoosedAdmission.action?fileName=temp/zip/准考证.zip'">下载选中的准考证</button>
								<button type="button" class="btn"
									onclick="javascript:location.href='doDownloadExamManageXls.action?fileName=xls/down/examManage.xls'">下载全部xls</button>
								<button type="button" class="btn"
									onclick="javascript:location.href='doMangeExam.action'">批量上传</button>
							</td>
						</tr>
						</tody>
					</table>
				</form>
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
