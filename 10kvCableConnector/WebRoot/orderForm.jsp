<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单申请</title>
</head>
<body>

<form name="order" method="post" action="order.do">
<table>
<tr><td>商户号</td><td><input type="text" name="mer_id" value="808080290000001"></td></tr>
<tr><td>订单号</td><td><input type="text" name="ord_id" value=""></td></tr>
<tr><td>考试号</td><td><input type="text" name="busi_id" value="00010001"></td></tr>
<tr><td>金&nbsp;&nbsp;额</td><td><input type="text" name="ord_amt" value="100">分</td></tr>
<tr><td>币&nbsp;&nbsp;种</td><td><input type="text" name="cury_id" value="156"></td></tr>
<tr><td>版本号</td><td><input type="text" name="version" value="20100401"></td></tr>
<tr><td>后台通知URL</td><td><input type="text" name="bg_ret_url" value="http://localhost:9090/cpedumer/payres.do"></td></tr>
<tr><td>前台返回URL</td><td><input type="text" name="page_ret_url" value="http://localhost:9090/cpedumer/payres.do"></td></tr>
<tr><td>网关ID</td><td><input type="text" name="gate_id" value=""></td></tr>
<tr><td>PARAM1</td><td><input type="text" name="param1" value=""></td></tr>
<tr><td>PARAM2</td><td><input type="text" name="param2" value=""></td></tr>
<tr><td>PARAM3</td><td><input type="text" name="param3" value=""></td></tr>
<tr><td>PARAM4</td><td><input type="text" name="param4" value=""></td></tr>
<tr><td>PARAM5</td><td><input type="text" name="param5" value=""></td></tr>
<tr><td>PARAM6</td><td><input type="text" name="param6" value=""></td></tr>
<tr><td>PARAM7</td><td><input type="text" name="param7" value=""></td></tr>
<tr><td>PARAM8</td><td><input type="text" name="param8" value=""></td></tr>
<tr><td>PARAM9</td><td><input type="text" name="param9" value=""></td></tr>
<tr><td>PARAM10</td><td><input type="text" name="param10" value=""></td></tr>
<tr><td>分账数据</td><td><input type="text" name="share_data" value=""></td></tr>
<tr><td>私有域</td><td><input type="text" name="priv1" value=""></td></tr>
<tr><td colspan="2">
	<input type="reset" name="reset" value="重置">
	<input type="submit" name="submit" value="提交">
</td></tr>

</table>
</form>
</body>
</html>