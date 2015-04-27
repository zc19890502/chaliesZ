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
	<div class="title2">订单信息</div>
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
				<td>业务标识：</td>
				<td><%=(String)map.get("BusiId") %>&nbsp;</td>
			</tr>
			<tr>
				<td>商户订单号：</td>
				<td><%=(String)map.get("OrdId") %>&nbsp;</td>
				<td>订单金额：</td>
				<td><%=(String)map.get("OrdAmt") %>&nbsp;</td>
			</tr>
			<tr>
				<td>币种：</td>
				<td><%=(String)map.get("CuryId") %>&nbsp;</td>
				<td>版本：</td>
				<td><%=(String)map.get("Version") %>&nbsp;</td>
			</tr>
			<tr>
				<td>网关:</td>
				<td><%=(String)map.get("GateId") %>&nbsp;</td>
				<td>订单描述:</td>
				<td><%=(String)map.get("OrdDesc") %>&nbsp;</td>
			</tr>
			<tr>
				<td>参数1:</td>
				<td><%=(String)map.get("Param1") %>&nbsp;</td>
				<td>参数2:</td>
				<td><%=(String)map.get("Param2") %>&nbsp;</td>
			</tr>
			<tr>
				<td>参数3:</td>
				<td><%=(String)map.get("Param3") %>&nbsp;</td>
				<td>参数4:</td>
				<td><%=(String)map.get("Param4") %>&nbsp;</td>
			</tr>
			<tr>
				<td>参数5:</td>
				<td><%=(String)map.get("Param5") %>&nbsp;</td>
				<td>参数6:</td>
				<td><%=(String)map.get("Param6") %>&nbsp;</td>
			</tr>
			<tr>
				<td>参数7:</td>
				<td><%=(String)map.get("Param7") %>&nbsp;</td>
				<td>参数8:</td>
				<td><%=(String)map.get("Param8") %>&nbsp;</td>
			</tr>
			<tr>
				<td>参数9:</td>
				<td><%=(String)map.get("Param9") %>&nbsp;</td>
				<td>参数10</td>
				<td><%=(String)map.get("Param10") %>&nbsp;</td>
			</tr>
			<tr>
				<td>分账类型:</td>
				<td><%=(String)map.get("ShareType") %>&nbsp;</td>
				<td>分账数据：</td>
				<td><%=(String)map.get("ShareData") %>&nbsp;</td>
			</tr>
			<tr>
				<td>商户私有信息：</td>
				<td><%=(String)map.get("Priv1") %>&nbsp;</td>
				<td>支付状态：</td>
				<td><%=(String)map.get("PayStat") %>&nbsp;</td>
			</tr>
			<tr>
				<td>支付时间：</td>
				<td><%=(String)map.get("PayTime") %>&nbsp;</td>
				<td>累积退款次数：</td>
				<td><%=(String)map.get("RefNum") %>&nbsp;</td>
			</tr>
			<tr>
				<td>累计退款额：</td>
				<td><%=(String)map.get("RefAmt") %>&nbsp;</td>
				<td>退款成功时间：</td>
				<td><%=(String)map.get("RefTime") %>&nbsp;</td>
			</tr>
		</table>
	</div>
</div>
	<%} %>
</form> 
</body>
</html>