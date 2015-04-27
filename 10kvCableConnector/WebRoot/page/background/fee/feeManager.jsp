<%@ page language="java"
	import="java.util.*,com.csdl.cabexam.beans.ManagerInfo"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/myTld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	if (null == request.getSession().getAttribute("managerSession")) {
		response.sendRedirect(basePath + "isManageLogin.action");
	}
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>后台首页</title>
<link href="css/bootstrap.css" rel="stylesheet">
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

	function selectAll() {
		var obj = document.fom.elements;
		for ( var i = 0; i < obj.length; i++) {
			if (obj[i].name == "delid") {
				obj[i].checked = true;
			}
		}
	}
	
	var req;
	//创建 ajax 核心对象
	function createXMLHttpRequest(){
	//为真就是IE
		if(window.ActiveXObject){
			req=new ActiveXObject("Microsoft.XMLHTTP");
		}else if(window.XMLHttpRequest){
			req=new XMLHttpRquest();				
		}
	
	}
	function getBatch(obj){
 
		//再这里调用 servlet...
		if(obj!=""){
			var url="queryExraBatch.action?year="+obj;
			//可以将中文转为英文和数字的组合
			url=window.encodeURI(url);
			url=window.encodeURI(url);
			
			createXMLHttpRequest();
			//设置回调函数的名称
			req.onreadystatechange=haolejiaowo;
			//初始化
			req.open("get",url);
			//发送
			req.send(null);
		}else{
			var c=document.getElementById("batch");
			c.options.length=0;
		}		
	}
	//服务器 执行完成以后 自动调用 这个函数
	function haolejiaowo(){	 
		if(req.readyState==4){
			if(req.status==200){
				//取服务器的数据 都是字符串
				var data=req.responseText;
				var cs=data.split("|");
				var c=document.getElementById("batch");
				c.options.length=0;
					//给下拉框赋值
				for(i=0;i<cs.length;i++){
					c.options[i]=new Option(cs[i],cs[i]);
				}
			}
		}
	}
	
	function feeManager(page){
		var year = document.getElementById("year").value;
		var batch= document.getElementById("batch").value;
		var type = document.getElementById("type").value;
		var state = document.getElementById("state").value;
		location="feeManager.action?page="+page+"&year="+year+"&batch="+batch+"&type="+type+"&state="+state;
	}
</script>
</head>

<body onLoad="show()">

	<div class="container-fluid"
		style="max-width:1200px; margin:0 auto; min-width:900px; ">
		<div class="row-fluid">
			<div class="span12"
				style="height:240px; border:1px; background:url(images/head_back.png) no-repeat;">

			</div>
		</div>
		<div class="row-fluid">

			<div class="span12">
				<div class="navbar">
					<div class="navbar-inner">
						<div class="container-fluid">
							<a data-target=".navbar-responsive-collapse"
								data-toggle="collapse" class="btn btn-navbar"> <span
								class="icon-bar"></span><span class="icon-bar"></span> <span
								class="icon-bar"></span> </a>
							<div class="brand">10kV电缆接头考证管理系统</div>
							<div class="nav-collapse collapse navbar-responsive-collapse">
								<ul class="nav">
									<li><a href="isManageLogin.action"><i
											class="icon-home"></i>主&nbsp;&nbsp;页</a></li>
									<li style="margin-left:100px;">&nbsp;</li>
									<li><a>当前时间:</a></li>
									<li><a id="time"></a></li>
								</ul>
								<ul class="nav pull-right">
									<li><a><i class="icon-user"></i>当前用户:</a></li>

									<li class="dropdown"><a data-toggle="dropdown"
										class="dropdown-toggle" href="#">${
											sessionScope.managerSession.realName}<strong class="caret"></strong>
									</a>
										<ul class="dropdown-menu">
											<li><a
												href="doQueryManagerById.action?managerId=${ sessionScope.managerSession.managerId}">个人信息</a>
											</li>
											<li><a href="doManagerExitSys.action">退出系统</a></li>
										</ul></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid"
			style="border: 1px #ccc solid; border-bottom:hidden; background-color:#F0F0F0; margin-top:-20px;">
			<div class="span2" style="min-height: 600px;">

				<div class="accordion" id="accordion-49699" style="margin-top:30px;">

					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" href="doExtraTableInfo.action">开放报名</a>
						</div>
					</div>

					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent="#accordion-49699" href="#accordion-element-292849">
								考生报名审核<i class="icon-chevron-down"></i> </a>
						</div>
						<div id="accordion-element-292849"
							class="accordion-body collapse ">
							<div class="accordion-inner">
								<a href="doQueryAllExammer.action">所有报名考生</a>
							</div>
							<div class="accordion-inner">
								<a href="doQueryExammerByState.action?checkState=0">待初审考生</a>
							</div>
							<div class="accordion-inner ">
								<a href="doQueryExammerByState.action?checkState=7">初审未通过考生</a>
							</div>
							<div class="accordion-inner ">
								<a href="doQueryExammerByState.action?checkState=8">初审通过考生</a>
							</div>

							<c:if test="${sessionScope.managerSession.powerGrade==0 }">
								<div class="accordion-inner">
									<a href="doQueryExammerByState.action?checkState=8">待复审考生</a>
								</div>
								<div class="accordion-inner">
									<a href="doQueryExammerByState.action?checkState=2">复审未通过考生</a>
								</div>
								<div class="accordion-inner ">
									<a href="doQueryExammerByState.action?checkState=1">复审通过考生</a>
								</div>
							</c:if>
						</div>
					</div>

					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent="#accordion-49699" href="#accordion-element-292860">
								缴费管理 <i class="icon-chevron-down"></i> </a>
						</div>
						<div id="accordion-element-292860"
							class="accordion-body collapse in">
							<div class="accordion-inner">
								<a href="doQueryTheoryFeeByState.action">理论考试缴费管理</a>
							</div>
							<div class="accordion-inner">
								<a href="doQueryOperateFeeByState.action">操作考试缴费管理</a>
							</div>
							<div class="accordion-inner">
								<a href="feeManager.action">缴费管理</a>
							</div>
							<div class="accordion-inner">
								<a href="feeStatistics.action?Mark=y">缴费统计</a>
							</div>
						</div>
					</div>
					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent="#accordion-49699" href="#accordion-element-292850">
								考试信息管理<i class="icon-chevron-down"></i> </a>
						</div>
						<div id="accordion-element-292850" class="accordion-body collapse">
							<div class="accordion-inner">
								<a href="doNumberTableInfo.action">准考证管理</a>
							</div>
							<div class="accordion-inner ">
								<a href="doQueryAllExamManage.action">考场安排</a>
							</div>
						</div>
					</div>
					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent="#accordion-49699" href="#accordion-element-292858">
								考试成绩管理<i class="icon-chevron-down"></i> </a>
						</div>
						<div id="accordion-element-292858" class="accordion-body collapse">
							<div class="accordion-inner ">
								<a href="doTheoryScoreTableInfo.action">理论成绩管理</a>
							</div>
							<div class="accordion-inner ">
								<a href="doAllScoreTableInfo.action">操作成绩管理</a>
							</div>
							<div class="accordion-inner ">
								<a href="doScoreTableInfo.action">历年成绩管理</a>
							</div>
						</div>
					</div>

					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent="#accordion-49699" href="#accordion-element-292851">
								证书信息管理<i class="icon-chevron-down"></i> </a>
						</div>
						<div id="accordion-element-292851" class="accordion-body collapse">
							<div class="accordion-inner ">
								<a href="doGetAllCertInfo.action">证书列表</a>
							</div>
							<div class="accordion-inner ">
								<a href="doUploadCertInfoXls.action">证书录入</a>
							</div>
							<div class="accordion-inner ">
							<a href="doAllRecord.action">年度评价</a>
						</div>
						</div>
					</div>
					<c:if test="${sessionScope.managerSession.powerGrade==0 }">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-parent="#accordion-49699"
								href="doQueryAllManager.action">管理员信息管理</a>
						</div>
					</c:if>
					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent="#accordion-49699" href="#accordion-element-292852">
								公告信息管理<i class="icon-chevron-down"></i> </a>
						</div>
						<div id="accordion-element-292852" class="accordion-body collapse">
							<div class="accordion-inner">
								<a href="doGetAllNotice.action">所有公告</a>
							</div>

						</div>
					</div>

				</div>
			</div>
			<div class="span9"
				style="height:150px; border-bottom:solid #0cF 1px; margin-top:10px; margin-left:50px;">
				<form class="form-inline"
					action="feeManager.action" method="post">
					<fieldset>
						<legend>缴费管理</legend>
						<!-- 这里是判断搜索条件是否为空 -->

						<table style="margin-left:40px; text-align:right;border:1px; "
							cellpadding="10">
							<tr>
								<td>选择年份:</td>
								<td>
									<select class="input-small" id="year" name="year" onchange="getBatch(this.value)">
										<option value="" >全部</option>
										<c:forEach items="${years}" var="pageYear">										
											<option value="${pageYear}" <c:if test="${pageYear==year}">selected="selected"</c:if>>${pageYear}</option>
										</c:forEach>
									</select>
								</td>
								<td>选择批次:</td>
								<td>
									<select class="input-small" id="batch" name="batch">
										<option value="" >全部</option>
										<c:forEach items="${batchs}" var="pageBatch">
											<option value="${pageBatch}" <c:if test="${pageBatch==batch}">selected="selected"</c:if>>${pageBatch}</option>
										</c:forEach>
									</select>
								</td>
								<td>缴费科目:</td>
								<td>
									<select class="input-small" id="type" name="type">
											<option value="">全部</option>
											<option value="0" <c:if test="${type=='0'}">selected="selected"</c:if>>理论</option>
											<option value="1" <c:if test="${type==1}">selected="selected"</c:if>>冷缩</option>
											<option value="2" <c:if test="${type==2}">selected="selected"</c:if>>热缩</option>
											<option value="3" <c:if test="${type==3}">selected="selected"</c:if>>冷缩+热缩</option>
									</select>
								</td>
								<td>订单状态:</td>
								<td>
									<select class="input-small" id="state" name="state">
											<option value="">全部</option>
											<option value="1" <c:if test="${state==1}">selected="selected"</c:if>>已支付</option>
											<option value="2" <c:if test="${state==2}">selected="selected"</c:if>>未支付</option>
											<option value="3" <c:if test="${state==3}">selected="selected"</c:if>>申请退款</option>
											<option value="4" <c:if test="${state==4}">selected="selected"</c:if>>已退款</option>
									</select>
								</td>
								<td style="float: right">
									<input type="submit"
									class="btn btn-success " value="查询" />
								</td>
							</tr>
						</table>

					</fieldset>
				</form>

			</div>

			<div class="span8" style="margin-top:10px; margin-left:100px">
				<form id="fom" name="fom">
					<table class="table table-hover ">
						<thead>
							<tr>
								<th align="center">订单号</th>
								<th align="center">姓名</th>
								<th align="center">工作单位</th>
								<th align="center">类别</th>
								<th align="center">批次</th>
								<th align="center">金额</th>
								<th align="center">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${orders }" var="order">
								<tr>
									<td>${order.ordId }</td>
									<td>${order.userInfo.realName }</td>
									<td>${order.examineeInfo.company }</td>
									<td>
										<c:if test="${order.type==0 }">
											理论
										</c:if>
										<c:if test="${order.type==1 }">
											冷缩
										</c:if>
										<c:if test="${order.type==2 }">
											热缩
										</c:if>
										<c:if test="${order.type==3 }">
											冷缩+热缩
										</c:if>
									</td>
									<td>${order.batch }</td>
									<td>${order.price }</td>
									<td>
										<!-- <a href="">查看</a> -->
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
				<div class="span8" align="right">
					<a href="feeManager.action">首页</a>
					<a href="#" onclick="feeManager(${page-1});return false;">上一页</a>
					<span>${page}/${maxPage}</span>
					<a href="#" onclick="feeManager(${page+1});return false;">下一页</a>
					<a href="feeManager.action?page=${maxPage}">尾页</a>
				</div>
				<div style="height:100px;"></div>
			</div>

			<div class="span12"
				style="max-width:1200px; margin:0 auto; min-width:800px; margin-top:7px;">
				<div class="row-fluid"
					style="background-color:#3CF; height:60px; text-align:center">
					<p></p>
					<br>
					<p>
						Copyright © 2014 By 长沙电力职业技术学院 All Rights Reserved. | 技术支持--<a
							href="#">湖南宇电智诚科技有限公司</a>
					</p>
				</div>
			</div>
		</div>

	</div>
	<script src="js/bootstrap.js"></script>
	<script src="js/jquery.js"></script>
</body>
</html>
