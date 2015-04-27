<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<link href="css/10kvcc.css" rel="stylesheet">
	<link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.js"></script>
	<script src="js/ajax.js"></script>
	<meta http-equiv=X-UA-Compatible content=IE=EmulateIE9>
	
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
	initSelect(sexValue, "sexchose");
	initSelect(educationValue, "educationInput");
	initSelect(partyValue, "partyInput");
	initSelect(physicalConditionValue, "physicalConditionInput");
	initSelect(workLimitTimeValue, "workLimitTimeInput");
	initSelect(skillLevelValue, "skillLevelInput");
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

	<div class="container-fluid  whole" >
	        <div class="logo">
	        </div>
	</div>
	
	<div class="container-fluid  whole" >
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
								<li>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</li>
								<li  >
									 <a>当前时间:</a>
								</li>
                                <li>
                                	<a id="time"></a>
                                </li>	
							</ul>
						</div>
						
					</div>
				</div>
				
			</div>
		</div>
        <div class="span12" style="max-width:1000px; margin:0 auto; min-width:800px; margin-top:-10px; height:auto; background-color:#cff">
              <form action="doAddExaminee.action" method="post" enctype="multipart/form-data" class="form-horizontal" style="margin-left:10px;" onsubmit="return checkexaminee()">
              <input type="hidden" name="examineeInfo.userInfo.userInfoId" value="${sessionScope.userSession.userInfoId }">
                <fieldset  class="span5" >
                  <div id="legend">
                    <legend>在线报名<font style="font-size:10px;color:red;">&nbsp;&nbsp;&nbsp;(*为必填项)</font></legend>
                  </div>
               	  	<div class="control-group">
                      <!-- Text input-->
                      <label class="control-label" for="input01">姓名</label>
                      <div class="controls">
                        <input type="text" disabled class="input-medium" value="${sessionScope.userSession.realName }">
                        <p class="help-inline">  </p>
                      </div>
                    </div>
                    
                    <div class="control-group">
                      <!-- Text input-->
                      <label class="control-label" for="input01" >电子邮箱</label>
                      <div class="controls">
                        <input type="text"  class="input-medium" value="${sessionScope.userSession.email }" readonly="readonly">
                        <p class="help-inline" id="emailtip" style="color:red;">请核对您的电子邮箱！</p>
                      </div>
                    </div>
                    
                    <div class="control-group">
                      <!-- Text input-->
                      <label class="control-label" for="input01">身份证号码</label>
                      <div class="controls">
                        <input type="text"  class="input-medium" value="${sessionScope.userSession.idnum }" readonly="readonly">
                        <p class="help-inline" id="idcardtip" style="color:red;">请核对您的身份证号码！</p>
                      </div>
                    </div>
                    
                    <div class="control-group">
                      <!-- Text input-->
                      <label class="control-label" for="input01">性别<font style="color:red;font-size:20px">*</font></label>
                      <div class="controls">
                        <select class="input-medium" name="examineeInfo.sex" id="sexchose">
							<option value="1">男</option>
							<option value="0">女</option>
						</select>
						<p class="help-block" id="sextip"></p>
                      </div>
                    </div>
                    
                    <div class="control-group">
                      <!-- Text input-->
                      <label class="control-label" for="input01">照片<font style="color:red;font-size:20px">*</font></label>
	
                        <!-- File Upload -->
                        <div class="controls">
                        <input class="input-file" id="photoInput" type="file" name="picLoad" onclick="document.getElementById('phototip').innerText='只支持.jpg、.png格式，小于200k'">
                        <p class="help-block" id="phototip">只支持.jpg、.png格式，小于200k</p>
                        <c:if test="${!empty examineeInfo.photo }">
                        	<img alt="photo" width="40" height="40" src="${examineeInfo.photo }">
                        </c:if>
                        <input type="hidden" name="examineeInfo.photo" id="photoOld" value="${examineeInfo.photo }">
                     	</div>
                    </div>
                    <div class="control-group">
                      <!-- Text input-->
                      <label class="control-label" for="input01">邮编</label>
                      <div class="controls">
                        <input type="text" placeholder="410000" class="input-medium" id="placeInput" name="examineeInfo.place" value="${examineeInfo.place }" onclick="document.getElementById('placetip').innerText=''">
                        <p class="help-inline" id="placetip"></p>
                      </div>
                    </div>
                     <div class="control-group">
                      <!-- Text input-->
                      <label class="control-label" for="input01">民族<font style="color:red;font-size:20px">*</font></label>
                      <div class="controls">
                        <input type="text" placeholder="汉族" class="input-medium" id="nationalityInput" name="examineeInfo.nationality" value="${examineeInfo.nationality }" onclick="document.getElementById('nationalitytip').innerText=''">
                        <p class="help-inline" id="nationalitytip"></p>
                      </div>
                    </div>
                    <div class="control-group">
                      <!-- Text input-->
                      <label class="control-label" for="input01">邮寄通信地址<font style="color:red;font-size:20px">*</font></label>
                      <div class="controls">                     
                        <input type="text"  class="input-medium" name="examineeInfo.homesite" id="homesiteInput" value="${examineeInfo.homesite }" onclick="document.getElementById('homesitetip').innerText=''">
                        <p class="help-inline" id="homesitetip"></p>
                      </div>
                    </div>
                    <div class="control-group">
                      <!-- Text input-->
                      <label class="control-label" for="input01">联系电话<font style="color:red;font-size:20px">*</font></label>
                      <div class="controls">
                        <input type="text" placeholder="13100000000" class="input-medium" id="telInput" name="examineeInfo.tel" value="${examineeInfo.tel }" onkeyup="checktel('telInput')" onclick="document.getElementById('teltip').innerText=''">
                        <p class="help-inline" id="teltip"></p>
                      </div>
                    </div>
                    <div class="control-group">
                      <!-- Text input-->
                      <label class="control-label" for="input01">出生日期<font style="color:red;font-size:20px">*</font></label>
                      <div class="controls  date form_date" data-date-format="yyyy-M-dd">
                            <input type="text" id="birthInput" name="birTime" value='<fmt:formatDate value="${examineeInfo.birth }"/>'" class="input-medium" readonly onclick="document.getElementById('birthtip').innerText=''">
                            <span class="add-on"><i class="icon-remove"></i></span>
                            <span class="add-on"><i class="icon-th"></i></span>
                            <p class="help-inline" id="birthtip"></p>
                        </div>
                    </div>
                    
                    <div class="control-group">
                      <!-- Text input-->
                      <label class="control-label" for="input01">政治面貌<font style="color:red;font-size:20px">*</font></label>
                      <div class="controls">
                        <select class="input-medium" name="examineeInfo.party" id="partyInput" onclick="document.getElementById('partytip').innerText=''">
                        	<option value="0">中共党员</option>
                            <option value="1">共青团员</option>
                            <option value="2">群众</option>
                            <option value="3">民主党派</option>
                        </select>
                        <p class="help-inline" id="partytip"></p>
                      </div>
                    </div>
                    <div class="control-group">
                      <!-- Text input-->
                      <label class="control-label" for="input01">最高学历</label>
                      <div class="controls">
                        <select class="input-medium" name="examineeInfo.education" id="educationInput" onclick="document.getElementById('educationtip').innerText=''">
                        	<option value="7">博士研究生</option>
                        	<option value="6">硕士研究生</option>
                            <option value="5">本科</option>
                            <option value="4">大专</option>
                            <option value="3">高中</option>
                            <option value="2">中专</option>
                            <option value="1">初中</option>
                            <option value="0">小学</option>
                        </select>
                        <p class="help-inline" id="educationtip"></p>
                      </div>
                    </div>
                </fieldset>
                
                
                <fieldset  class="span5" >
                  <div id="legend" class="">
                    <legend class="">&nbsp;</legend>
                  </div>
 
                    
                    <div class="control-group">
                      <!-- Text input-->
                      <label class="control-label" for="input01">健康状况</label>
                      <div class="controls">
                      
                      	<select class="input-medium" name="examineeInfo.physicalCondition" id="physicalConditionInput" onclick="document.getElementById('physicalConditiontip').innerText=''">
                        	<option value="健康">健康</option>
                            <option value="良好">良好</option>
                            <option value="较差">较差</option>
                        </select>
                        <p class="help-inline" id="physicalConditiontip"></p>
                      </div>
                    </div>
                    
                    <div class="control-group">
                      <!-- Text input-->
                      <label class="control-label" for="input01">参加工作时间<font style="color:red;font-size:20px">*</font></label>
                      <div class="controls  date form_date" data-date-format="yyyy-M-dd">
                            <input type="text" name="workTime" class="input-medium" id="workTimeInput" value='<fmt:formatDate value="${examineeInfo.workDate }"/>'" readonly onclick="document.getElementById('workTimetip').innerText=''" >
                            <span class="add-on"><i class="icon-remove"></i></span>
                            <span class="add-on"><i class="icon-th"></i></span>
                            <p class="help-inline" id="workTimetip"></p>
                        </div>
                    </div>
                    
                    
                    <div class="control-group">
                      <!-- Text input-->
                      <label class="control-label" for="input01">从事电缆制作年限</label>
                      <div class="controls">
                        <select class="input-medium" name="examineeInfo.workLimitTime" id="workLimitTimeInput" onclick="document.getElementById('workLimitTimetip').innerText=''">
                        	<option value="0">1年及以下</option>
                        	<option value="1">1-2年</option>
                            <option value="2">2-3年</option>
                            <option value="3">3-5年</option>
                            <option value="4">5年以上</option>
                        </select>
                        <p class="help-inline" id="workLimitTimetip" ></p> 
                      </div>
                    </div>
                    <div class="control-group">
                      <!-- Text input-->
                      <label class="control-label" for="input01">报考类别</label>
                      <div class="controls">
                      		<select class="input-medium"name="examineeInfo.skillLevel" class="input-medium" id="skillLevelInput" onclick="document.getElementById('skillLeveltip').innerText=''">
								<option value="1">冷缩</option>
								<option value="2">热缩</option>
								<option value="3">冷缩+热缩</option>
							</select>
                            <p class="help-inline" id="skillLeveltip"></p> 
                      </div>
                    </div>
                    <div class="control-group">
                      <!-- Text input-->
                      <label class="control-label" for="input01">单位类别</label>
                      <div class="controls">
                      		<select class="input-medium" name="examineeInfo.examineeSource" class="input-medium" id="examineeSourceInput" >
									<option value="fengs">分电力公司</option>
									<option value="nongdiangs">农电服务公司</option>
									<option value="shehuidw">社会施工单位</option>
							</select>
                      </div>
                    </div>
                    <div class="control-group">
                      <!-- Text input-->
                      <label class="control-label" for="input01">工作单位<font style="color:red;font-size:20px">*</font> </label>

                        <div class="controls">
                        <input type="text" class="input-medium" name="examineeInfo.company" id="companyInput" value="${examineeInfo.company }" onclick="document.getElementById('companytip').innerText=''">
                            <p class="help-inline" id="companytip"></p> 
                      	</div>
                    </div>

                    
                    <div class="control-group">
                      <!-- Text input-->
                      <label class="control-label" for="input01">身份证扫描件<font style="color:red;font-size:20px">*</font></label>
                      <div class="controls">
                        <input class="input-file" id="idcardScanInput" type="file" name="idLoad" onclick="document.getElementById('idcardScantip').innerText=''">
                        <p class="help-block" id="idcardScantip">只支持.jpg、.png格式，小于200k</p>
                        <c:if test="${!empty examineeInfo.idcardScan }">
                        	<img alt="photo" width="40" height="40" src="${examineeInfo.idcardScan }">
                        </c:if>
                        <input type="hidden" name="examineeInfo.idcardScan" id="idscanOld" value="${examineeInfo.idcardScan }">
                      </div>
                    </div>
                    <div class="control-group">
                      <!-- Text input-->
                      <label class="control-label" for="input01">报名单位审核表扫描件<font style="color:red;font-size:20px">*</font></label>
                      <div class="controls">
                        <input class="input-file" id="companyScanInput" type="file" name="compLoad" onclick="document.getElementById('companyScantip').innerText=''">
                        <p class="help-block" id="companyScantip">只支持.jpg、.png格式，小于200k</p><font color="red">需单位盖章</font>
                       	<c:if test="${!empty examineeInfo.companyExamine }">
                       		<img alt="photo" width="40" height="40" src="${examineeInfo.companyExamine }">
                        </c:if>
                        <input type="hidden" name="examineeInfo.companyExamine" id="companyScanOld" value="${examineeInfo.companyExamine }">
                      </div>
                    </div>
                    <div class="control-group">
                      <!-- Text input-->
                      <label class="control-label" for="input01">工作证明扫描件<font style="color:red;font-size:20px">*</font></label>
                      <div class="controls">
                        <input class="input-file" id="workScanInput" type="file" name="workLoad" onclick="document.getElementById('workScantip').innerText=''">
                        <p class="help-block" id="workScantip">只支持.jpg、.png格式，小于200k</p>
                        <c:if test="${!empty examineeInfo.workingProof }">
                      	  <img alt="photo" width="40" height="40" src="${examineeInfo.workingProof }">
                        </c:if>
                        <input type="hidden" name="examineeInfo.workingProof" id="workproofOld" value="${examineeInfo.workingProof }">
                      </div> 
                    </div>
                    <div class="control-group">
                      <!-- Text input-->
                      <label class="control-label" for="input01">工作简历</label>
                      <div class="controls">
                        <textarea cols="130" rows="10" class="input-xlarge" id="jobResumeInput" name="examineeInfo.jobResume" onclick="document.getElementById('jobResumetip').innerText='分条填写，只填写工作经历'">
                        	${examineeInfo.jobResume }
                        </textarea>
                        <p class="help-block" id="jobResumetip">
                        	分条填写，只填写工作经历
                        </p>
                      </div>
                    </div>
                </fieldset>
                <div class="span12" style=" margin:0 auto; margin-left:0">
                	<div class="span4"></div>
                	<div class="span4" >
                		<h4>请确认您所填写的资料完整、有效</h4>
                    	<button class=" btn btn-block btn-success" id="submitRegis" type="submit">提交报名</button>
                    </div>
                    <div class="span4" style=" margin-bottom:20px;"></div>
                </div>
              </form>
        </div>
	</div>
    </div>
		<div class="container-fluid whole">
		<div class=" footer">
			<br>
			<p>Copyright © 2014 By 长沙电力职业技术学院 All Rights Reserved. | 技术支持--<a href="#">湖南宇电智诚科技有限公司</a></p>
		</div>
	</div>    
    
    <script type="text/javascript" src="js/jquery-1.8.3.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap-datetimepicker.js" charset="UTF-8"></script>

<script type="text/javascript">
	$('.form_date').datetimepicker({
        //language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });
</script>
</body>
</html>
