<%@ page language="java"
	import="java.util.*,com.csdl.cabexam.beans.ManagerInfo"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<meta http-equiv=X-UA-Compatible content=IE=EmulateIE8>

<style type="text/css">
.bg {
	background-color: #6ff;
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
    function checkNumberFile(){
       var filepath=$("#Xlsfile").val();
       var reg = /([.xls]{1})+$/;
	   flag = reg.test(filepath);
	   if(flag){
	      return true;
	   }else{
	      alert("没有选择文件或者文件不是.xls");
	      return false;
	   }
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
				style="border-bottom:solid #0cF 1px; margin-top:10px; margin-left:50px;">
				<fieldset>
					<legend>批量上传考生考场安排信息</legend>
				</fieldset>
				<div class="span12" style="margin-left:100px;margin-top:50px;">
					<!--  xls文件上传 -->
					<form action="doUpdateExamXls.action" class="form-horizontal"
						enctype="multipart/form-data" method="post"
						onsubmit="return checkNumberFile()">
						<div class="control-group">
							<label class="control-label" for="input01">xls文件:</label>
							<div class="controls">
								<input type="file" name="Xlsfile" id="Xlsfile"> <br>
								<p class="help-inline">请选择需要上传的文件</p>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<button type="submit" class="btn">更新考场安排</button>
							</div>
						</div>
					</form>
					
	         <c:if test="${(not empty errList)}">
			  <div class="span8" style="margin:10px;min-height: 600px; margin-left:8%;">
				<table class="table ">
					<tr>
						<th align="center" style="color:red" >错误行:</th>
					</tr>
				<c:forEach items="${errList}" var="err">
				    <tr>
						<th align="center">${err}</th>
					</tr>
				</c:forEach>
                </table>
              </div>
            </c:if>
					
				</div>
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
