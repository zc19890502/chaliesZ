<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.util.Map"%>

<html>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=request.getContextPath() %>/styles/style.css" type="text/css"> 
<title>商户查询结果</title>
</head>


<body>
<form name="mx"  method="post"> 
	
	
		
	<div id="content">
	<div id="head"></div>
	<div class="title2">退款信息</div>
	<div class="info" >
	<table width="100%" border="0" cellpadding="0" cellspacing="3" class="infoTable2">
		<%			
			Map map=(Map)request.getSession().getAttribute("map");
			if(map == null ){
		%>
			<tr>
				<td colspan="4">error:没有数据!</td>
			</tr>
		<%
			}
			String responseCode=(String)map.get("ResponseCode");
		%>
		
		<%
		if(!"0".equals(responseCode)){%>
			<tr>
				<td>响应码：</td>
				<td><%=responseCode %>&nbsp;</td>
				<td>错误信息：</td>
				<td><%=(String)map.get("Message") %>&nbsp;</td>
			</tr>
		<% }else{
		%>
			<tr>
				<td>商户号：</td>
				<td><%=(String)map.get("MerId") %>&nbsp;</td>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td>退款号：</td>
				<td><%=(String)map.get("MerRefId") %>&nbsp;</td>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td>退款状态：</td>
				<td><%=(String)map.get("RefStat") %>&nbsp;</td>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td>退款时间:</td>
				<td><%=(String)map.get("RefTime") %>&nbsp;</td>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td>商户私有信息:</td>
				<td><%=(String)map.get("Priv1") %>&nbsp;</td>
				<td colspan="2">&nbsp;</td>
			</tr>
			
		<%} %>
		</table>
		<input type="hidden" name="ChkValue" id="ChkValue" value="<%=(String)map.get("ChkValue") %>" />
	</div>
</div>

</form> 
</body>
</html>