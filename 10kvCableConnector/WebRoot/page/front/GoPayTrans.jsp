<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>GoPayTrans</title>
</head>

<body onload="document.mx.submit();">
<div style="display:none">
<form name="mx" action="${actionUrl}" METHOD=POST> 
<table>
<tr><td>MerId:<input name="MerId" value="${bean.merId}"></td></tr>
<tr><td>BusiId:<input name="BusiId" value="${bean.busiId}"></td></tr>
<tr><td>OrdId:<input name="OrdId" value="${bean.ordId}"></td></tr>
<tr><td>OrdAmt:<input name="OrdAmt" value="${bean.ordAmt}"></td></tr>
<tr><td>CuryId:<input name="CuryId" value="${bean.curyId}"></td></tr>
<tr><td>Version:<input name="Version" value="${bean.version}"></td></tr>
<tr><td>BgRetUrl:<input name="BgRetUrl" value="${bean.bgRetUrl}"></td></tr>
<tr><td>PageRetUrl:<input name="PageRetUrl" value="${bean.pageRetUrl}"></td></tr>
<tr><td>GateId:<input name="GateId" value="${bean.gateId}"></td></tr>
<tr><td>Param1:<input name="Param1" value="${bean.param1}"></td>
    <td>Param2:<input name="Param2" value="${bean.param2}"></td></tr>
<tr><td>Param3:<input name="Param3" value="${bean.param3}"></td>
    <td>Param4:<input name="Param4" value="${bean.param4}"></td></tr>
<tr><td>Param5:<input name="Param5" value="${bean.param5}"></td>
    <td>Param6:<input name="Param6" value="${bean.param6}"></td></tr>
<tr><td>Param7:<input name="Param7" value="${bean.param7}"></td>
    <td>Param8:<input name="Param8" value="${bean.param8}"></td></tr>
<tr><td>Param9:<input name="Param9" value="${bean.param9}"></td>
    <td>Param10：<input name="Param10" value="${bean.param10}"></td></tr>
<tr><td>OrdDesc:<input name="OrdDesc" value="${bean.ordDesc}"></td>
	<td>ShareType:<input name="ShareType" value="${bean.shareType}"></td></tr>    
<tr><td>ShareData：<input name="ShareData" value="${bean.shareData}^${bean.ordAmt}"></td></tr>
<tr><td>Priv1：<input name="Priv1" value="${bean.priv1}"></td></tr>
<tr><td>CustomIp：<input name="CustomIp" value="${bean.customIp}"></td></tr>
<tr><td>ChkValue:<input name="ChkValue" value="${bean.chkValue}"></td></tr>
</table>
</form> 
</div>
</body>
</html>