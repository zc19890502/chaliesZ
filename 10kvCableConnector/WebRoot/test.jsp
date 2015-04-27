<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <button onclick="javascript:location.href='doScoreTableInfo.action'">成绩信息列表</button> 
   <button onclick="javascript:location.href='doGenerateXlsTable.action'">生成xls</button> 
    <!-- <a  href='doGenerateXlsTable.action'>生成Xls</a> -->
    
   <button onclick="javascript:location.href='http://localhost:8080/10kvCableConnector/doDownloadXls.action?fileName=xls/test.xls'">下载</button> 
    <!-- <a href="http://localhost:8080/10kvCableConnector/doDownloadXls.action?fileName=xls/test.xls" target="_self" >下载</a> -->
  
   <form action="doUploadXls.action" enctype="multipart/form-data" method="post">
        xls文件:<input type="file" name="Xlsfile">
        <input type="submit" value="上传" />
   </form>
   <a  href='doUpdateScore.action'>更新成绩</a>
  
  
  </body>
</html>
