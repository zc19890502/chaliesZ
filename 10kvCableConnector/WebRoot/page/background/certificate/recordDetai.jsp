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
<link href="css/bootcss/bootstrap.min.css" rel="stylesheet">
<link href="css/bootcss/bootstrap-theme.min.css" rel="stylesheet">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<meta http-equiv=X-UA-Compatible content=IE=EmulateIE8>
<style type="text/css">
table {
	text-align: center;
}
table th{
	text-align: center;
}


</style>

</head>

<body>

	<div class="container-fluid"
		style="max-width:1200px; margin:0 auto; min-width:900px; ">
		<div class="row-fluid">
		<div class="input" align="center"><h1>10kV电缆头制作安装2014年度个人评价表</h1></div>
		<table class="table table-bordered">
			<tr>
				<th>姓名</th>
				<td>${record.name }</td>
				<th>性别</th>
				<td>
					<c:if test="${record.sex==1 }">男</c:if>
					<c:if test="${record.sex==0 }">女</c:if>
				</td>
				<th>工作单位</th>
				<td>${record.company }</td>
			</tr>
			<tr>
				<th>作业范围</th>
				<td>
					<c:if test="${record.kind==0 }">冷缩</c:if>
					<c:if test="${record.kind==1 }">热缩</c:if>
					<c:if test="${record.kind==2 }">冷缩+热缩</c:if>
				</td>
				<th>证书编号</th>
				<td>${record.certNumber }</td>
				<th>身份证号码</th>
				<td>${record.idNumber }</td>
			</tr>
			<tr>
				<th colspan="2">2014年制作电缆头总数量</th>
				<th>近三年累计数量</th>
				<th>合格数量</th>
				<th>不合格数量</th>
				<th>扣分情况</th>
			</tr>
			<tr>
				<td colspan="2">${record.yearCount }</td>
				<td>${record.threeYearCount }</td>
				<td>${record.passCount }</td>
				<td>${record.failCount }</td>
				<td>${record.deduction }</td>
			</tr>
			<tr>
				<td colspan="6" style="text-align: left;">
				<h4 style="font-weight: bold">专家评价考核:</h4>
				<br><br><br><br><br><br>
				<p style="text-align: right;margin-right: 100px">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日</p>
				</td>
			</tr>
			<tr>
				<td colspan="6" style="text-align: left;">
				<h4 style="font-weight: bold">审批:</h4>
				<br><br><br><br><br><br>
				<p style="text-align: right;margin-right: 100px">盖章:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日</p>
				</td>
			</tr>
			<tr>
				<td colspan="6" style="text-align: left;">
				<h4 style="font-weight: bold">附件:制作接头明细</h4>
				<br><br><br><br><br><br>
				<p style="text-align: right;margin-right: 100px">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日</p>
				</td>
			</tr>
			
		</table>
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
	
</body>
</html>
