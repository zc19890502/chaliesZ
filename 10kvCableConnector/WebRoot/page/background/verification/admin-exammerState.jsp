<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../top.jsp"%>
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
						考生报名审核<i class="icon-chevron-down"></i>
					</a>
				</div>
				<div id="accordion-element-292849"
					class="accordion-body collapse in">
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
						缴费管理 <i class="icon-chevron-down"></i>
					</a>
				</div>
				<div id="accordion-element-292860" class="accordion-body collapse">
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
						考试信息管理<i class="icon-chevron-down"></i>
					</a>
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
						考试成绩管理<i class="icon-chevron-down"></i>
					</a>
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
						证书信息管理<i class="icon-chevron-down"></i>
					</a>
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
						公告信息管理<i class="icon-chevron-down"></i>
					</a>
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
			action="getcheckStateExamineeInfoByCondition.action" method="post">
			<fieldset>
				<legend>考生信息管理</legend>
				<!-- 这里是判断搜索条件是否为空 -->
				<c:choose>
					<c:when
						test="${empty requestScope.realName && empty requestScope.homesite && empty requestScope.company} ">
						<table style="margin-left:40px; text-align:right;border:1px; "
							cellpadding="10">
							<tr>
								<td>考生姓名:</td>
								<td><input type="text" class="input-small"
									placeholder="考生姓名" id="realName" name="c_realName"></td>
								<td>工作单位:</td>
								<td><input type="text" class="input-small"
									placeholder="工作单位" id="company" name="c_company"> <input
									type="hidden" class="input-small" placeholder="工作单位"
									id="checkState" name="checkState">
								</td>
								<td style="float: right"><input type="submit"
									class="btn btn-success " value="查询" /></td>
							</tr>
						</table>
					</c:when>
					<c:otherwise>
						<table style="margin-left:40px; text-align:right;border:1px; "
							cellpadding="10">
							<tr>
								<td>考生姓名:</td>
								<td><input type="text" class="input-small"
									placeholder="考生姓名" id="realName" name="c_realName"
									value="${requestScope.realName }"></td>
								<td>工作单位:</td>
								<td><input type="text" class="input-small"
									placeholder="工作单位" id="company" name="c_company"
									value="${requestScope.company }"> <input type="hidden"
									class="input-small" placeholder="工作单位" id="checkState"
									name="checkState" value="${requestScope.checkState }">
								</td>
								<td style="float: right"><input type="submit"
									class="btn btn-success " value="查询" /></td>
							</tr>
						</table>
					</c:otherwise>
				</c:choose>
			</fieldset>
		</form>
	</div>

	<div class="span8" style="margin-top:10px; margin-left:100px">
		<form id="fom" name="fom">
			<table class="table table-hover ">
				<thead>
					<tr>
						<th align="center">姓名</th>
						<th align="center">性别</th>
						<th align="center">报考类别</th>
						<th align="center">工作单位</th>
						<th align="center">考生状态</th>
						<th align="center">操作</th>
					</tr>

				</thead>
				<tbody>
					<s:splitPage pageSize="10"
						page="getcheckStateExamineeInfoByCondition.action?realName=${requestScope.realName }&homesite=${requestScope.homesite }&company=${requestScope.company }&checkState=${requestScope.checkState }"
						data="${ requestScope.examineeInfoList }">
						<c:choose>
							<c:when test="${empty splitData.examineeInfoId}"></c:when>
							<c:otherwise>
								<tr>
									<td align="center">${splitData.userInfo.realName}</td>

									<c:choose>
										<c:when test="${splitData.sex==0 }">
											<td align="center">女</td>
										</c:when>
										<c:when test="${splitData.sex==1 }">
											<td align="center">男</td>
										</c:when>
									</c:choose>

									<c:choose>
										<c:when test="${splitData.skillLevel=='1'}">
											<td align="center">冷缩</td>
										</c:when>
										<c:when test="${splitData.skillLevel=='2'}">
											<td align="center">热缩</td>
										</c:when>
										<c:when test="${splitData.skillLevel=='3'}">
											<td align="center">冷缩+热缩</td>
										</c:when>
										<c:otherwise>
											<td></td>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${not empty splitData.company}">
											<td align="center">${splitData.company}</td>
										</c:when>
										<c:otherwise>
											<td></td>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${splitData.checkState==0 }">
											<td align="center">未审核</td>
										</c:when>
										<c:when test="${splitData.checkState==1 }">
											<td align="center">复审通过</td>
										</c:when>
										<c:when test="${splitData.checkState==2 }">
											<td align="center">复审未通过</td>
										</c:when>
										<c:when test="${splitData.checkState==3 }">
											<td align="center">理论缴费成功</td>
										</c:when>
										<c:when test="${splitData.checkState==4 }">
											<td align="center">理论缴费失败</td>
										</c:when>
										<c:when test="${splitData.checkState==5 }">
											<td align="center">操作缴费失败</td>
										</c:when>
										<c:when test="${splitData.checkState==6 }">
											<td align="center">操作缴费失败</td>
										</c:when>
										<c:when test="${splitData.checkState==8 }">
											<td align="center">初审通过，等待复审</td>
										</c:when>
										<c:when test="${splitData.checkState==7 }">
											<td align="center">初审未通过</td>
										</c:when>
									</c:choose>

									<td align="center"><c:choose>
											<c:when
												test="${splitData.checkState==0 or splitData.checkState==7 }">
												<a
													href='doQueryExamineeFile.action?examineeId=${splitData.examineeInfoId }'>初审</a>
											</c:when>
											<c:when
												test="${splitData.checkState==8 and sessionScope.managerSession.powerGrade==0 }">
												<a
													href='doQueryExamineeFile.action?examineeId=${splitData.examineeInfoId }'>复审</a>
											</c:when>
											<c:when test="${splitData.checkState==8 }">
												<a
													href='doQueryExammeeChecked.action?examineeId=${splitData.examineeInfoId }'>查看</a>
											</c:when>
											<c:when
												test="${splitData.checkState==2 and sessionScope.managerSession.powerGrade==0  }">
												<a
													href='doQueryExamineeFile.action?examineeId=${splitData.examineeInfoId }'>复审</a>
											</c:when>
											<c:when test="${splitData.checkState==1 }">
												<a
													href='doQueryExammeeChecked.action?examineeId=${splitData.examineeInfoId }'>查看</a>
											</c:when>
										</c:choose>
									</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</s:splitPage>
					</tr>
					<tr>
						<td colspan="12" style="text-align:right; border-bottom:none;">
							<button type="button" class="btn"
								onclick="javascript:location.href='downCheckFirXls.action?fileName=xls/down/checkFir.xls'">下载通过复审考生excel</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<div style="height:100px;"></div>
	</div>

	<%@include file="../foot.jsp"%>