<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.chinapay.edu.bean.GoQueryBean" %>
<%@ page import="com.chinapay.edu.util.GetPath" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>GoQueryTrans</title>
</head>

<%
GoQueryBean bean = (GoQueryBean)request.getAttribute("GoQueryBean");
if(bean == null ){
	out.println("error:没有数据!");
}
out.println("bean.MerId="+bean.getMerId());


%>
<body>

<form name="mx" action="<%=GetPath.getEduQueryBgUrl() %>" method="post"> 
<table>
<tr><td>MerId:<input name="MerId" value="<%=bean.getMerId() %>"></input></td></tr>
<tr><td>BusiId:<input name="BusiId" value="<%=bean.getBusiId() %>"></input></td></tr>
<tr><td>OrdId:<input name="OrdId" value="<%=bean.getOrdId() %>"></input></td></tr>
<tr><td>Param1:<input name="Param1" value="<%=bean.getParam1() %>"></input></td>
    <td>Param2:<input name="Param2" value="<%=bean.getParam2() %>"></input></td></tr>
<tr><td>Param3:<input name="Param3" value="<%=bean.getParam3() %>"></input></td>
    <td>Param4:<input name="Param4" value="<%=bean.getParam4() %>"></input></td></tr>
<tr><td>Param5:<input name="Param5" value="<%=bean.getParam5() %>"></input></td>
    <td>Param6:<input name="Param6" value="<%=bean.getParam6() %>"></input></td></tr>
<tr><td>Param7:<input name="Param7" value="<%=bean.getParam7() %>"></input></td>
    <td>Param8:<input name="Param8" value="<%=bean.getParam8() %>"></input></td></tr>
<tr><td>Param9:<input name="Param9" value="<%=bean.getParam9() %>"></input></td>
    <td>Param10：<input name="Param10" value="<%=bean.getParam10() %>"></input></td></tr>
<tr><td>ChkValue:<input name="ChkValue" value="<%=bean.getChkValue() %>"></input></td></tr>
<tr><td><input type="submit" name="submit" value="query to edu"></input></td></tr>
</table>
</form> 
</body>
</html>