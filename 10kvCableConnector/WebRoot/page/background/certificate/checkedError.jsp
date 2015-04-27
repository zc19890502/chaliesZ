<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	if (null == request.getSession().getAttribute("managerSession")) {
		response.sendRedirect(basePath + "isManageLogin.action");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>审核错误</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
<script type="text/javascript">
	function tishi(errState) {
		if ("cannotCheck" == errState) {
			alert("信息已经审核或已经有成绩信息，不能审核通过");
			location.href = "doQueryAllExammer.action";
		} else if ("cannotnoChecked" == errState) {
			alert("已经考试并生成升级，不能取消审核");
			location.href = "doQueryAllExammer.action";
		}
	}
</script>
<body onload="tishi('${requestScope.errorState }')">

</body>
</html>
