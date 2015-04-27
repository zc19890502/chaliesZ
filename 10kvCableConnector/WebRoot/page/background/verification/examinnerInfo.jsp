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


<script>
function initSelect(optionValue, selectType) {
	var selectElement = document.getElementById(selectType);
	var optionsElements = selectElement.getElementsByTagName("option");
	for ( var i = 0; i < optionsElements.length; i++) {
		if (optionsElements[i].value == optionValue) {
			optionsElements[i].selected = true;
		}
	}
}


function initAllData(sexValue, educationValue,partyValue,physicalConditionValue,workLimitTimeValue,skillLevelValue) {
	initSelect(sexValue, "sexchose");
	initSelect(educationValue, "education");
	initSelect(partyValue, "party");
	initSelect(physicalConditionValue, "physicalCondition");
	initSelect(workLimitTimeValue, "workLimitTime");
	initSelect(skillLevelValue, "skillLevel");
}
 </script>
</head>
 
  
  <body  onLoad="initAllData('${requestScope.examineeInfo.sex }',
	'${requestScope.examineeInfo.education }',
	'${requestScope.examineeInfo.party }',
	'${requestScope.examineeInfo.physicalCondition }',
	'${requestScope.examineeInfo.workLimitTime }',
	'${requestScope.examineeInfo.skillLevel }')">
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
                	 <form form action="" method="post" class="form-horizontal" style="margin-left:30%;" method="post" action="doRegister.action">
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
                                  <label class="control-label" for="input01">邮箱:</label>
                                  <div class="controls">
                                    <input type="text"  value="${requestScope.examineeInfo.userInfo.email }" readonly="readonly"><br>
                                    <p class="help-inline"></p>
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
                                  <label class="control-label" for="input01">性别:</label>
                                  <div class="controls">
                                    <select name="sex" id="sexchose"  disabled=true>
    			                         <option value="1">男</option>
    			                         <option value="0">女</option>
    		                        </select><br>
                                    <p class="help-inline"></p>
                                  </div>
                                </div>
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">民族:</label>
                                  <div class="controls">
                                    <input type="text" name="examineeInfo.nationality" value="${requestScope.examineeInfo.nationality }" readonly="readonly"><br>
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
                                  <label class="control-label" for="input01">邮寄通信地址:</label>
                                  <div class="controls">
                                    <input type="text" name="examineeInfo.homesite" value="${requestScope.examineeInfo.homesite }" readonly="readonly"><br>
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
                                  <label class="control-label" for="input01">政治面貌:</label>
                                  <div class="controls">
                                   <select class="input-medium" name="examineeInfo.party"
										id="party" disabled="disabled">
									<option value="0">中共党员</option>
									<option value="1">共青团员</option>
									<option value="2">群众</option>
									<option value="3">民主党派</option>
									</select>
                                    <p class="help-inline"></p>
                                  </div>
                                </div>
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">最高学历:</label>
                                  <div class="controls">
                                    <select name="education" id="education" disabled=true>
    			                        <option value="7">博士研究生</option>
			                        	<option value="6">硕士研究生</option>
			                            <option value="5">本科</option>
			                            <option value="4">大专</option>
			                            <option value="3">高中</option>
			                            <option value="2">中专</option>
			                            <option value="1">初中</option>
			                            <option value="0">小学</option>
    		                        </select><br>
                                    <p class="help-inline"></p>
                                  </div>
                                </div>
                                
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">身体状况:</label>
                                  <div class="controls">
                                    <select class="input-medium"
										name="examineeInfo.physicalCondition"
										id="physicalCondition" disabled="disabled">
										<option value="健康">健康</option>
										<option value="良好">良好</option>
										<option value="较差">较差</option>
									</select>
                                    <p class="help-inline"></p>
                                  </div>
                                </div>
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">从事电缆制作年限:</label>
                                  <div class="controls">
										<select class="input-medium" name="examineeInfo.workLimitTime"
											id="workLimitTime" disabled="disabled">
											<option value="0">1年及以下</option>
				                        	<option value="1">1-2年</option>
				                            <option value="2">2-3年</option>
				                            <option value="3">3-5年</option>
				                            <option value="4">5年以上</option>
										</select>
										<p class="help-inline"></p>
                                  </div>
                                </div>
                                
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">报考类别:</label>
                                  <div class="controls">
										<select class="input-medium" name="examineeInfo.skillLevel"
											id="skillLevel" disabled="disabled">
											<option value="1">冷缩</option>
											<option value="2">热缩</option>
											<option value="3">冷缩+热缩</option>
										</select>
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
			                      <label class="control-label" for="input01">单位类别</label>
			                      <div class="controls">
			                      		<select class="input-medium" name="examineeInfo.examineeSource" disabled="disabled" class="input-medium" id="examineeSourceInput" >
												<option value="fengs">分电力公司</option>
												<option value="nongdiangs">农电服务公司</option>
												<option value="shehuidw">社会施工单位</option>
										</select>
			                      </div>
			                      </div>
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">工作经历:</label>
                                  <div class="controls">
                                    <textarea rows="7" cols="20" name="jobResume"  readonly="readonly">${requestScope.examineeInfo.jobResume }</textarea> 
                                    <p class="help-inline"></p>
                                  </div>
                                </div>
                                
                              
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">照片:</label>
                                  <div class="controls">
                                     <img id="photo"  src="${requestScope.examineeInfo.photo }" width="100" height="100"> 
                                    <p class="help-inline"></p>
                                  </div>
                                </div>
                                
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">身份证扫描件:</label>
                                  <div class="controls">
                                  <img id="idcardScan"  src="${requestScope.examineeInfo.idcardScan }" width="100" height="100"> 
                                    <p class="help-inline"></p>
                                  </div>
                                </div>
                                
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">工作证明扫描件:</label>
                                  <div class="controls">
                                   <img id="workingProof" src="${requestScope.examineeInfo.workingProof }" width="100" height="100"> 
                                    <p class="help-inline"></p>
                                  </div>
                                </div>
                                
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">报名单位审核表扫描件:</label>
                                  <div class="controls">
                                  <img  id="companyExamine" src="${requestScope.examineeInfo.companyExamine }" width="100" height="100"> 
                                    <p class="help-inline"></p>
                                  </div>
                                </div>
                                 <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">审核意见:</label>
                                  <div class="controls">
                                  
                                   <input type="text" name="checkAdvice" id="checkAdvice"><br>
                                    <p class="help-inline"></p>
                                  </div>
                                </div>
                                <div class="control-group">
                                  <!-- Text input-->
                                  <label class="control-label" for="input01">&nbsp;</label>
                                  <div class="controls">
                                  <c:choose>
                                 	<c:when test="${requestScope.examineeInfo.checkState==1 and sessionScope.managerSession.powerGrade==0}">
                                    	<input type="button" value="复审不通过" class="btn btn-success "  onclick="javascript:if(confirm('确定要不通过复审吗？')){var checkAdvice = document.getElementById('checkAdvice').value;location.href='doVerifyNotPass.action?examineeId=${requestScope.examineeInfo.examineeInfoId}&finalVerfyState=3&checkAdvice='+checkAdvice}"/>
                                  	</c:when>
                                 	<c:when test="${requestScope.examineeInfo.checkState==8 }">
                                    	<input type="button" value="初审不通过" class="btn btn-success "  onclick="javascript:if(confirm('确定要不通过初审吗？')){var checkAdvice = document.getElementById('checkAdvice').value;location.href='doVerifyNotPass.action?examineeId=${requestScope.examineeInfo.examineeInfoId}&checkAdvice='+checkAdvice}"/>
                                  	</c:when>
                                  </c:choose>
                                    <button type="button" onclick="javascript:location.href=history.back()" class="btn btn-success">返回</button>
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

<script type="text/javascript">
            $(function(){
                $("#photo").click(function(){
                    var width = $(this).width();
                    if(width==100)
                    {
                        $(this).width(400);
                        $(this).height(300);
                    }
                    else
                    {
                        $(this).width(100);
                        $(this).height(100);
                    }
                });
                $("#idcardScan").click(function(){
                    var width = $(this).width();
                    if(width==100)
                    {
                        $(this).width(400);
                        $(this).height(300);
                    }
                    else
                    {
                        $(this).width(100);
                        $(this).height(100);
                    }
                });
                $("#workingProof").click(function(){
                    var width = $(this).width();
                    if(width==100)
                    {
                        $(this).width(400);
                        $(this).height(300);
                    }
                    else
                    {
                        $(this).width(100);
                        $(this).height(100);
                    }
                });
                $("#companyExamine").click(function(){
                    var width = $(this).width();
                    if(width==100)
                    {
                        $(this).width(400);
                        $(this).height(300);
                    }
                    else
                    {
                        $(this).width(100);
                        $(this).height(100);
                    }
                });
            });
    </script>
</body>
</html>
