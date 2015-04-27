<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML >
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
<link href="css/10kvcc.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.js"></script>
<meta http-equiv=X-UA-Compatible content=IE=EmulateIE8>

<style type="text/css">
.bg {
	background-color: #6ff;
}
</style>
<script>
	function show() {
		var date = new Date(); //日期对象 
		var now = "";
		now = date.getFullYear() + "年"; //读英文就行了 
		now = now + (date.getMonth() + 1) + "月"; //取月的时候取的是当前月-1如果想取当前月+1就可以了 
		now = now + date.getDate() + "日  ";
		now = now + date.getHours() + ":";
		now = now + date.getMinutes() + ":";
		now = now + date.getSeconds();
		document.getElementById("time").innerHTML = now; //div的html是now这个字符串 
		setTimeout("show()"); //设置过1000毫秒就是1秒，调用show方法 
	}
	function initSelect(optionValue, selectType) {
		var selectElement = document.getElementById(selectType);
		var optionsElements = selectElement.getElementsByTagName("option");
		for ( var i = 0; i < optionsElements.length; i++) {
			if (optionsElements[i].value == optionValue) {
				optionsElements[i].selected = true;
			}
		}
	}
	function initAllData(sexValue, educationValue,partyValue,physicalConditionValue,workLimitTimeValue,skillLevelValue,examineeSourceValue) {
		initSelect(sexValue, "sex");
		initSelect(educationValue, "education");
		initSelect(partyValue, "party");
		initSelect(physicalConditionValue, "physicalCondition");
		initSelect(workLimitTimeValue, "workLimitTime");
		initSelect(skillLevelValue, "skillLevel");
		initSelect(examineeSourceValue, "examineeSourceInput");
		show();
	}
</script>
</head>


<body
	onLoad="initAllData('${requestScope.examineeInfo.sex }',
	'${requestScope.examineeInfo.education }',
	'${requestScope.examineeInfo.party }',
	'${requestScope.examineeInfo.physicalCondition }',
	'${requestScope.examineeInfo.workLimitTime }',
	'${requestScope.examineeInfo.skillLevel }',
	'${requestScope.examineeInfo.examineeSource }')">
	<div class="container-fluid whole ">
		<div class="row-fluid">
			<div class="span12 logo"></div>
		</div>
		<div class="row-fluid">

			<div class="span12">
			<div class="navbar">
				<div class="navbar-inner">
					<div class="container-fluid">
						 <a data-target=".navbar-responsive-collapse" data-toggle="collapse" class="btn btn-navbar">
                         <span class="icon-bar"></span><span class="icon-bar"></span>
                         <span class="icon-bar"></span></a> 
                         <div class="brand">10kV接线工证书管理系统</div>
						<div class="nav-collapse collapse navbar-responsive-collapse">
							<ul class="nav">
								<li >
									<a href="index.jsp"><i class="icon-home"></i>主&nbsp;&nbsp;页</a>	
								</li>
								<li  >
									 <a>当前时间:</a>
								</li>
                                <li>
                                	<a id="time"></a>
                                </li>	
							</ul>
							<ul class="nav pull-right">
                                
								<li>
									<a ><i class="icon-user"></i>当前用户:</a>
								</li>

								<li class="dropdown">
									 <a data-toggle="dropdown" class="dropdown-toggle" href="#"><strong>${ sessionScope.userSession.realName}</strong><strong class="caret"></strong></a>
									<ul class="dropdown-menu">
										<li>
											<a href="checkExamineeInfo.action">个人信息</a>
										</li>
										<li>
											<a href="doGotoUpdatePsw.action">修改密码</a>
										</li>
										<li>
											<a href="doExitSys.action">退出系统</a>
										</li>
									</ul>
								</li>
							</ul>
						</div>
						
					</div>
				</div>
				
			</div>
		</div>
		</div>
		<div class="row-fluid" style="background-color:#F0F0F0; margin-top: -20px;">
		<div class="span2" style="background-color: #E7F1F3;">
			<ul>
				<li>
					<a href="checkExamineeInfo.action">报名信息</a>
				</li>
				
				<li>
					<a href="doCheckResult.action">考试信息</a>
				</li>
				<li>
					<a href="doGetPersonFile.action">基本资料</a>
				</li>
				<li>
					<a href="doGotoUpdatePsw.action">修改密码</a>
				</li>
				<li>
					<a href="../10kvCableConnector/page/front/showDemo.jsp">操作向导</a>
				</li>
			</ul>
		</div>
		<div class="span10" style="margin-top:30px;  min-height:600px;">
			<div class="row-fluid">
				<div class="span11">
					<form action="" method="post" class="form-horizontal"
						style="margin-left:5px;">
						<fieldset>
							<div>
								<legend>报名信息</legend>
							</div>
						</fieldset>
						<div class="control-group pull-left">
							<!-- Text input-->
							<input type="hidden" name="examineeInfo.userInfo.userInfoId"
								value="${requestScope.examineeInfo.userInfo.userInfoId }">
							<label class="control-label" for="input01">用户名：</label>
							<div class="controls">
								<input type="text" class="input-medium"
									value="${requestScope.examineeInfo.userInfo.account}"
									readonly="readonly"><br>
							</div>
						</div>

						<div class="control-group pull-right" style="margin-right:50px;">
							<!-- Text input-->
							<label class="control-label" for="input01">邮箱:</label>
							<div class="controls">
								<input type="text" class="input-medium"
									value="${requestScope.examineeInfo.userInfo.email }"
									readonly="readonly"><br>
							</div>
						</div>

						<div class="control-group pull-left">
							<!-- Text input-->
							<label class="control-label" for="input01">真实姓名:</label>
							<div class="controls">
								<input type="text" class="input-medium"
									value="${requestScope.examineeInfo.userInfo.realName }"
									readonly="readonly"><br>
							</div>
						</div>

						<div class="control-group pull-right" style="margin-right:50px;">
							<!-- Text input-->
							<label class="control-label" for="input01">身份证号:</label>
							<div class="controls">
								<input type="text" class="input-medium"
									value="${requestScope.examineeInfo.userInfo.idnum }"
									readonly="readonly"><br>
							</div>
						</div>


						<div class="control-group pull-left">
							<!-- Text input-->
							<label class="control-label" for="input01">性别:</label>
							<div class="controls">
								<select class="input-medium" name="sex" id="sex" disabled=true>
									<option value="1">男</option>
									<option value="0">女</option>
								</select><br>
							</div>
						</div>


						<div class="control-group pull-right" style="margin-right:50px;">
							<!-- Text input-->
							<label class="control-label" for="input01">民族:</label>
							<div class="controls">
								<input type="text" class="input-medium"
									name="examineeInfo.nationality"
									value="${requestScope.examineeInfo.nationality }"
									readonly="readonly"><br>
							</div>
						</div>
						
						<div class="control-group pull-left">
							<!-- Text input-->
							<label class="control-label" for="input01">参加工作时间:</label>
							<div class="controls">
								<input type="text" class="input-medium"
									name="examineeInfo.place"
									value="<fmt:formatDate value='${requestScope.examineeInfo.workDate }' type='date'/>"
									readonly="readonly"><br>
							</div>
						</div>
						
						<div class="control-group pull-right" style="margin-right:50px;">
							<!-- Text input-->
							<label class="control-label" for="input01">出生日期:</label>
							<div class="controls">
								<input type="text" class="input-medium"
									name="examineeInfo.nationality"
									value="<fmt:formatDate value='${requestScope.examineeInfo.birth }' type='date'/>"
									readonly="readonly"><br>
							</div>
						</div>

						<div class="control-group pull-left">
							<!-- Text input-->
							<label class="control-label" for="input01">邮编:</label>
							<div class="controls">
								<input type="text" class="input-medium"
									name="examineeInfo.place"
									value="${requestScope.examineeInfo.place }"
									readonly="readonly"><br>
							</div>
						</div>
						<div class="control-group pull-right" style="margin-right:50px;">
							<!-- Text input-->
							<label class="control-label" for="input01">邮寄通信地址:</label>
							<div class="controls">
								<input type="text" class="input-medium"
									name="examineeInfo.homesite"
									value="${requestScope.examineeInfo.homesite }"
									readonly="readonly"><br>
							</div>
						</div>

						<div class="control-group pull-left">
							<!-- Text input-->
							<label class="control-label" for="input01">电话:</label>
							<div class="controls">
								<input type="text" class="input-medium" name="examineeInfo.tel"
									value="${requestScope.examineeInfo.tel }" readonly="readonly"><br>
							</div>
						</div>

						<div class="control-group pull-right" style="margin-right:50px;">
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
							</div>
						</div>


						<div class="control-group pull-left">
							<!-- Text input-->
							<label class="control-label" for="input01">最高学历:</label>
							<div class="controls">
								<select class="input-medium" name="examineeInfo.education"
									id="education" disabled="disabled">
									<option value="7">博士研究生</option>
		                        	<option value="6">硕士研究生</option>
		                            <option value="5">本科</option>
		                            <option value="4">大专</option>
		                            <option value="3">高中</option>
		                            <option value="2">中专</option>
		                            <option value="1">初中</option>
		                            <option value="0">小学</option>
								</select>
							</div>
						</div>

						<div class="control-group pull-right" style="margin-right:50px;">
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
							</div>
						</div>

						<div class="control-group pull-left">
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
							</div>
						</div>

						<div class="control-group pull-right" style="margin-right:50px;">
							<!-- Text input-->
							<label class="control-label" for="input01">报考类别:</label>
							<div class="controls">
								<select class="input-medium" name="examineeInfo.skillLevel"
									id="skillLevel" disabled="disabled">
									<option value="1">冷缩</option>
										<option value="2">热缩</option>
										<option value="3">冷缩+热缩</option>
								</select>
							</div>
						</div>

						<div class="control-group pull-left">
							<!-- Text input-->
							<label class="control-label" for="input01">工作单位:</label>
							<div class="controls">
							<input type="text" class="input-medium" name="examineeInfo.company"
									value="${requestScope.examineeInfo.company }" readonly="readonly"><br>
							</div>
						</div>
					  <div class="control-group pull-right" style="margin-right:50px;">
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
								<textarea rows="7" cols="10" name="jobResume"
									readonly="readonly">${requestScope.examineeInfo.jobResume }</textarea>
							</div>
						</div>


						<div class="control-group">
							<!-- Text input-->
							<label class="control-label" for="input01">照片:</label>
							<div class="controls">
								<img id="photo" src="${requestScope.examineeInfo.photo }"
									width="100" height="100">
							</div>
						</div>

						<div class="control-group">
							<!-- Text input-->
							<label class="control-label" for="input01">身份证扫描件:</label>
							<div class="controls">
								<img id="idcardScan"
									src="${requestScope.examineeInfo.idcardScan }" width="100"
									height="100">
							</div>
						</div>
						<div class="control-group">
							<!-- Text input-->
							<label class="control-label" for="input01">工作证明扫描件:</label>
							<div class="controls">
								<img id="workingProof"
									src="${requestScope.examineeInfo.workingProof }" width="100"
									height="100">
							</div>
						</div>
						<div class="control-group">
							<!-- Text input-->
							<label class="control-label" for="input01">报名单位审核表扫描件:</label>
							<div class="controls">
								<img id="companyExamine"
									src="${requestScope.examineeInfo.companyExamine }"
									width="100" height="100">
							</div>
						</div>

						<div align=center>
							<button type="button" onclick="history.back()" class="btn btn-success btn-large">返回</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	  </div>
	</div>
	<div class="container-fluid whole">
		<div class=" footer">
			<br>
			<p>
				Copyright © 2014 By 长沙电力职业技术学院 All Rights Reserved. | 技术支持--<a >湖南宇电智诚科技有限公司</a>
			</p>
		</div>
	</div>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.js"></script>
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
