<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head>
<title> error </title>
<script language="javascript"  type="text/javascript"></script>
</head>
<%
	String message = (String)request.getAttribute("message");
	String action = (String)request.getAttribute("action");
%>
<script language="JavaScript">
	function process(){
		<%if(action!=null){%>
			form1.action == '<%=action%>';
			form1.submit();
		<%}else{%>
			history.go(-1);
		<%}%>	
	}
</script>
<body onLoad="downheight();" style="text-align:center;">
<%=message %>		
</body>
</html>
