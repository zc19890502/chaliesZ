<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>welcome</title>
<script language="javascript" type="text/javascript">
	function goPay(){
		document.mx.action="<%=request.getContextPath()%>/page/pay/GoPay.jsp";
		document.mx.submit();
	}
	function goSplitPay(){
		document.mx.action="<%=request.getContextPath()%>/page/pay/GoSplitPay.jsp";
		document.mx.submit();
	}
	function goRefund(){
		document.mx.action="<%=request.getContextPath()%>/page/refund/GoRefund.jsp";
		document.mx.submit();
	}
	function goQuery(){
		document.mx.action="<%=request.getContextPath()%>/page/query/GoQuery.jsp";
		document.mx.submit();
	}
	function goMerBusi(){
		document.mx.action="<%=request.getContextPath()%>/page/mer/merbusi.jsp";
		document.mx.submit();
	}

	function goPayEdu(){
		document.mx.action="<%=request.getContextPath()%>/page/pay/GoPayEdu.jsp";
		document.mx.submit();
	}
	function goSplitPayEdu(){
		document.mx.action="<%=request.getContextPath()%>/page/pay/GoSplitPayEdu.jsp";
		document.mx.submit();
	}
	function goRefundEdu(){
		document.mx.action="<%=request.getContextPath()%>/page/refund/GoRefundEdu.jsp";
		document.mx.submit();
	}
</script>
</head>

<body>
	<form name="mx" action="login.action">
		<table>
			<tr><td><input type="button" name="test" value="发起账户分账支付(测试商户)" onClick="goPay();"></input></td></tr>
			<tr><td><input type="button" name="test" value="发起费用分账支付(测试商户)" onClick="goSplitPay();"></input></td></tr>
			<tr><td><input type="button" name="test" value="发起退款(测试商户)" onClick="goRefund();"></input></td></tr>
			<tr><td><input type="button" name="test" value="查询订单(测试商户)" onClick="goQuery();"></input></td></tr>
			<tr><td><input type="button" name="test" value="查询商户业务(测试商户)" onClick="goMerBusi();"></input></td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td><input type="button" name="test" value="发起账户分账支付(教育考试控台)" onClick="goPayEdu();"></input></td></tr>
			<tr><td><input type="button" name="test" value="发起费用分账支付(教育考试控台)" onClick="goSplitPayEdu();"></input></td></tr>
			<tr><td><input type="button" name="test" value="发起退款(教育考试控台)" onClick="goRefundEdu();"></input></td></tr>
		</table>
	</form>
</body>
</html>