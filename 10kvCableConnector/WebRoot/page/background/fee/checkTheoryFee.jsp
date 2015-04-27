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


function feePass(ordId){
	if(ordId==""||ordId==undefined){
		alert("未生成缴费订单！不能审核通过");
	}else{
		if(confirm("确定缴费通过审核吗？")){
			document.getElementById("feeCheckForm").action="doCheckTheoryFeePass.action?ordId="+ordId;
			document.getElementById("feeCheckForm").submit();
			return true;
		}else 
			return false;
	}
}
function feeFail(){
	if(confirm("确定缴费没有通过审核吗？")){
		document.getElementById("feeCheckForm").action="doCheckTheoryFeeFail.action";
		document.getElementById("feeCheckForm").submit();
		return true;
	}else 
		return false;	
}
function refund(userInfoId){
	location="refund.acton?userInfoId="+userInfoId;
}
</script>
  </head>
 
  
  <body>
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
                	 <form id="feeCheckForm" action="" method="post" class="form-horizontal" style="margin-left:30%;" method="post" action="doRegister.action">
                           <fieldset  >
                              <div id="legend" class="">
                                <legend >考生详细信息</legend>
                              </div>
                                <div class="control-group">
                                  <!-- Text input-->
                                   <input type="hidden" name="examineeInfo.userInfo.userInfoId" value="${requestScope.examineeInfo.userInfo.userInfoId }">
                                  <label class="control-label" for="input01">用户名：</label>
                                  <div class="controls">
                                   <input type="text"  value="${requestScope.examineeInfo.userInfo.account }" readonly="readonly"><br>
                                  </div>
                                </div>
                                
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">真实姓名:</label>
                                  <div class="controls">
                                    <input type="text" value="${requestScope.examineeInfo.userInfo.realName }" readonly="readonly"><br>
                                    <p class="help-inline"></p>
                                  </div>
                                </div>
                                
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">身份证号:</label>
                                  <div class="controls">
                                    <input type="text"  value="${requestScope.examineeInfo.userInfo.idnum }" readonly="readonly"><br>
                                    <p class="help-inline"></p>
                                  </div>
                                </div>
                                
                                 <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">邮编:</label>
                                  <div class="controls">
                                    <input type="text" name="examineeInfo.place" value="${requestScope.examineeInfo.place }" readonly="readonly"><br>
                                    <p class="help-inline"></p>
                                  </div>
                                </div>
                                
                                
                                 <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">电话:</label>
                                  <div class="controls">
                                    <input type="text" name="examineeInfo.tel" value="${requestScope.examineeInfo.tel }" readonly="readonly"><br>
                                    <p class="help-inline"></p>
                                  </div>
                                </div>                                                               
                                
                            
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">工作单位:</label>
                                  <div class="controls">
                                    <input type="text" name="examineeInfo.company" value="${requestScope.examineeInfo.company }" readonly="readonly"><br>
                                    <p class="help-inline"></p>
                                  </div>
                                </div>
                                
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">缴费单:</label>
                                  <div class="controls">
                                     <img id="FeePhoto" src="${requestScope.examineeInfo.theoryFeePath }" width="400" height="300" > 
                                    <p class="help-inline"></p>
                                  </div>
                                </div>
                                
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">
                                  <c:if test="${order==null}">
                                  	(未生成订单):
                                  </c:if>
                                  <c:if test="${order!=null}">
                                  	(已生成订单):
                                  </c:if>
                                  </label>
                                </div>
                               
                             <c:if test="${order!=null}">
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">                                  
	                              	金额:
                                  </label>
                                  <div class="controls">
                                    <input type="text" name="examineeInfo.company" value="${order.price }" readonly="readonly"><br>
                                  	<p class="help-inline"></p>
                                  </div>
                                </div>
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">                                  
	                              	状态:
                                  </label>
                                  <div class="controls">
                                    <c:if test="${order.state==1 }">
                                    	<input type="text" name="examineeInfo.company" value="未交费" readonly="readonly"><br>
                                    </c:if>
                                  	<c:if test="${order.state==2 }">
	                                    <input type="text" name="examineeInfo.company" value="已交费" readonly="readonly"><br>
                                    </c:if>
                                  	<c:if test="${order.state==3 }">
	                                    <input type="text" name="examineeInfo.company" value="申请退费" readonly="readonly"><br>
                                    </c:if>
                                  	<c:if test="${order.state==4 }">
	                                    <input type="text" name="examineeInfo.company" value="已退费" readonly="readonly"><br>
                                    </c:if>
                                  	<p class="help-inline"></p>
                                  </div>
                                </div>
                                <div class="control-group">
                                  <!-- Text input-->
                                  <div class="controls">
                                    <c:if test="${order.state==1 }">
                                    	<a href="bGQueryOrder.action?OrdId=${order.ordId }" >缴费查询</a><br>
                                    </c:if>
                                  	<c:if test="${order.state==2 }">
	                                     <a href="refund.action?userInfoId=${order.userInfo.userInfoId }" target="_blank">退还缴费</a><br>
                                    </c:if>
                                  	<c:if test="${order.state==3 }">
	                                    <a href="refund.action?userInfoId=${order.userInfo.userInfoId }" target="_blank">批准退费</a><br>
                                    </c:if>
                                  	<p class="help-inline"></p>
                                  </div>
                                </div>
                              </c:if>  
                                  <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">审查意见:</label>
                                  <div class="controls">
                                <input type="text" name="examineeInfo.advice" placeholder="请填写审核意见"><br>
                                    <p class="help-inline"></p>
                                  </div>
                                </div> 
                                
                                <input type="hidden" name="examineeInfo.examineeInfoId" value="${requestScope.examineeInfo.examineeInfoId }"/>

                                
                               <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">&nbsp;</label>
                                  <div class="controls">
                                  	<input type="button" id="checkButton1" value="审查通过" class="btn btn-success " onclick="feePass(${order.ordId })" />
                                    <input type="button" id="checkButton2" value="审查不通过" class="btn btn-success " onclick="feeFail()"/>
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
