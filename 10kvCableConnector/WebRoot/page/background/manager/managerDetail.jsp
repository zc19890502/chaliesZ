<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
if(null==request.getSession().getAttribute("managerSession")){
	response.sendRedirect(basePath+"isManageLogin.action");
}
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
	<title>10kV电缆接头考证管理系统</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/bootstrap.css" rel="stylesheet">
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.js"></script>
	<script src="js/jquery-1.8.0.js"></script>
	<meta http-equiv=X-UA-Compatible content=IE=EmulateIE8>
<script type="text/javascript">
function initSelect(optionValue){
	var selectElement = document.getElementById("managerPower");
	var optionsElements = selectElement.getElementsByTagName("option");
	for(var i=0;i<optionsElements.length;i++){
		if(optionsElements[i].value==optionValue){
			optionsElements[i].selected = true;
		}
	}
}

</script>
  </head>
 
  <body onload="initSelect('${requestScope.managerInfo.powerGrade }')">
<div class="container-fluid" style="max-width:1200px; margin:0 auto; min-width:800px;">
	<div class="row-fluid">
		<div class="span12" >
			<div class="row-fluid">
				<div class="span12" style=" height:240px; background:url(images/head_back.png) no-repeat;">
				</div>
			</div>
		</div>
		<div class="span12" style="max-width:1200px; margin:0 auto; min-width:800px; margin-top:-10px; height:auto; background-color:#cff;">
        	<div class="row-fluid">
            	<div class="span8">
                	<br><br>
                	 <form action="doUpdateManager.action" method="post" class="form-horizontal" style="margin-left:30%;">
                           <fieldset  >
                              <div id="legend" class="">
                                <legend >管理员个人信息</legend>
                              </div>
                                <div class="control-group">
                                  <!-- Text input-->
                                   <input type="hidden" name="managerInfo.managerId" value="${requestScope.managerInfo.managerId }">
                                  <label class="control-label" for="input01">账号：</label>
                                  <div class="controls">
                                   <input type="text" name="managerInfo.managerAccount" disabled="disabled" value="${requestScope.managerInfo.managerAccount }"><br>
                                  </div>
                                </div>
                                
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">真实姓名:</label>
                                  <div class="controls">
                                    <input type="text" name="managerInfo.realName" disabled="disabled" value="${requestScope.managerInfo.realName }"><br>
                                    <p class="help-inline"></p>
                                  </div>
                                </div>
                                
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">邮箱:</label>
                                  <div class="controls">
                                    <input type="text" name="managerInfo.email" disabled="disabled" value="${requestScope.managerInfo.email }"><br>
                                    <p class="help-inline"></p>
                                  </div>
                                </div>
                               
                               <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">权限:</label>
                                  <div class="controls">
                                    <select name="managerInfo.powerGrade" id="managerPower" disabled="disabled">
    			                      <option value="0">超级管理员</option>
    			                      <option value="1">普通管理员</option>
                                    </select>
                                 <p class="help-inline"></p>
                                  </div>
                                </div>
                              
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">&nbsp;</label>
                                  <div class="controls">
                                    <button type="button" onclick="history.back()" class="btn btn-success btn-large">返回</button>
                                  </div>
                                </div>
                                
                                </fieldset>
                    </form>
                </div>
            </div>
        </div>
        <div class="span12" style="max-width:1200px; margin:0 auto; min-width:800px;">
        	<div class="row-fluid" style="background-color:#3CF; height:60px; text-align:center">
            	<p></p>
                <br>
                <p>Copyright © 2014 By 长沙电力职业技术学院 All Rights Reserved. | 技术支持--<a >湖南宇电智诚科技开发有限公司</a></p>
            </div>
        </div>
	</div>
</div>

</body>
</html>
