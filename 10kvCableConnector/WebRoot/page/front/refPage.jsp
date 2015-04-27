<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
		background-color:#6ff;
	}

</style>
<script> 
	function createIframe(){ 
		var date = new Date(); //日期对象 
		var now = ""; 
		now = date.getFullYear()+"年"; //读英文就行了 
		now = now + (date.getMonth()+1)+"月"; //取月的时候取的是当前月-1如果想取当前月+1就可以了 
		now = now + date.getDate()+"日  "; 
		now = now + date.getHours()+":"; 
		now = now + date.getMinutes()+":"; 
		now = now + date.getSeconds(); 
		document.getElementById("time").innerHTML = now; //div的html是now这个字符串 
		setTimeout("show()"); //设置过1000毫秒就是1秒，调用show方法 
		//mask遮罩层 
		var newMask=document.createElement("div"); 
		newMask.id="mDiv"; 
		newMask.style.position="absolute"; 
		newMask.style.zIndex="1"; 
		_scrollWidth=Math.max(document.body.scrollWidth,document.documentElement.scrollWidth); 
		_scrollHeight=Math.max(document.body.scrollHeight,document.documentElement.scrollHeight); 
		// _scrollHeight = Math.max(document.body.offsetHeight,document.documentElement.scrollHeight); 
		newMask.style.width=_scrollWidth+"px"; 
		newMask.style.height=_scrollHeight+"px"; 
		newMask.style.top="0px"; 
		newMask.style.left="0px"; 
		newMask.style.background="#33393C"; 
		//newMask.style.background = "#FFFFFF"; 
		newMask.style.filter="alpha(opacity=40)"; 
		newMask.style.opacity="0.40"; 
		newMask.style.display='none'; 
		var objDiv=document.createElement("DIV"); 
		objDiv.id="div1"; 
		objDiv.name="div1"; 
		objDiv.style.width="280px"; 
		objDiv.style.height="100px"; 
		objDiv.style.left=(_scrollWidth-280)/2+"px"; 
		objDiv.style.top=(_scrollHeight-100)/2+"px"; 
		objDiv.style.position="absolute"; 
		objDiv.style.zIndex="2"; //加了这个语句让objDiv浮在newMask之上 
		objDiv.style.display="none"; //让objDiv预先隐藏 
		objDiv.innerHTML=' <div id="drag" style="position:absolute;height:20px;width:100%;z-index:10001;top:0; background-color:#0033FF;cursor:move ;" align="right"></div>'; 
		//更改了X按钮为触发关闭事件。 
		objDiv.style.border="solid #0033FF 3px;"; 
		var frm=document.createElement("iframe"); 
		frm.id="ifrm"; 
		frm.name="ifrm"; 
		frm.style.position="absolute"; 
		frm.style.width="100%"; 
		frm.style.height=80; 
		frm.style.top=20; 
		frm.style.display=''; 
		frm.frameborder=0; 
		objDiv.appendChild(frm); 
		// newMask.appendChild(objDiv); //问题出在这里：你把frame所在的div变成了 newMask的子元素，当newMask透明度更改时，当然会影响到frame 
		document.body.appendChild(newMask); 
		document.body.appendChild(objDiv); 
		var objDrag=document.getElementById("drag"); 
		var drag=false; 
		var dragX=0; 
		var dragY=0; 
		objDrag.attachEvent("onmousedown",startDrag); 
		function startDrag(){ 
		if(event.button==1&&event.srcElement.tagName.toUpperCase()=="DIV"){ 
		objDrag.setCapture(); 
		objDrag.style.background="#0000CC"; 
		drag=true; 
		dragX=event.clientX; 
		dragY=event.clientY; 
		} 
		}; 
		objDrag.attachEvent("onmousemove",Drag); 
		function Drag(){ 
			if(drag){ 
				var oldwin=objDrag.parentNode; 
				oldwin.style.left=oldwin.offsetLeft+event.clientX-dragX; 
				oldwin.style.top=oldwin.offsetTop+event.clientY-dragY; 
				oldwin.style.left=event.clientX-100; 
				oldwin.style.top=event.clientY-10; 
				dragX=event.clientX; 
				dragY=event.clientY; 
			} 
		}; 
		objDrag.attachEvent("onmouseup",stopDrag); 
		function stopDrag(){ 
		objDrag.style.background="#0033FF"; 
		objDrag.releaseCapture(); 
		drag=false; 
		}; 
	}
	function htmlEditor(){
		var frm=document.getElementById("ifrm"); 
		var objDiv=document.getElementById("div1"); 
		var mDiv=document.getElementById("mDiv"); 
		mDiv.style.display=''; 
		var iframeTextContent=''; 
		iframeTextContent+=' <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">'; 
		iframeTextContent+=' <html xmlns="http://www.w3.org/1999/xhtml">'; 
		iframeTextContent+=' <head>'; 
		iframeTextContent+=' <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />'; 
		iframeTextContent+=' </head>'; 
		iframeTextContent+=' <body>'; 
		iframeTextContent+=' <table>';
		iframeTextContent+=' <tr>'; 
		iframeTextContent+=' <td> 支付完成后,请点击<input type="button" id="btGo" value="确定" /> </td>'; 
		iframeTextContent+=' </tr>'; 
		iframeTextContent+=' </table>'; 
		iframeTextContent+=' </body>'; 
		iframeTextContent+=' </html>'; 
		frm.contentWindow.document.designMode='off'; 
		frm.contentWindow.document.open(); 
		frm.contentWindow.document.write(iframeTextContent); 
		frm.contentWindow.document.close(); 
		objDiv.style.display = ""; //显示浮动的div 
		var oi = document.getElementById("oi").value;
		var objGo=frm.contentWindow.document.getElementById("btGo"); 
		objGo.attachEvent("onclick",function (){ 
			HideIframe(mDiv,objDiv); 
			queryOrder(oi);
		}); 
	}
	
	function HideIframe(mDiv,objDiv){ 
		mDiv.style.display='none'; 
		objDiv.style.display = "none"; //隐藏浮动的div 
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
	
	function queryOrder(obj){
		//再这里调用 servlet...
		var url="goQueryOrder.action?OrdId="+obj;
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
		
	}
	//服务器 执行完成以后 自动调用 这个函数
	function haolejiaowo(){
	 
		if(req.readyState==4){
			if(req.status==200){
				//取服务器的数据 都是字符串
				var data=req.responseText;
				if(data=="ok"){
					location="doCheckResult.action";
				}
				htmlEditor();
				/*queryOrder(data);*/
			}
		}	
	}
</script>
<script>
</script>
  </head>
  
  
  <body onLoad="createIframe()"> 
		<div class="container-fluid whole ">
			<div class="row-fluid">
		    	<div class="span12 logo">
		
		        </div>
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
		        <div class="span10" >
		        	<fieldset  class="span5" style="width: 80%" >
		        		<div id="legend">
		                    <legend>订单信息</legend>
		                </div>		                
		                <form id="jumpForm" action="upRefund.action" METHOD=POST >
			                <table align="center" width="100%" >
			                	<tr>
			                		<td align="center">
			                			<label class="control-label" for="input01">退款原因：</label>
			                		</td>
			                	</tr>
			                	<tr>	
			                		<td align="center">
			                			<textarea name="refExplain" rows="5" cols="5"></textarea>
			                		</td>
			                	</tr>
			                	<tr>			                		
			                		<td align="center">
			                			<input type="submit" name="test" value="申请退款" />
			                		</td>
			                	</tr>
			                </table>			               
						</form>
		        	</fieldset>
				</div>
		 	</div>
		</div>

 		<div class="container-fluid whole">
			<div class=" footer">
				<br>
				<p>Copyright © 2014 By 长沙电力职业技术学院 All Rights Reserved. | 技术支持--<a href="#">湖南宇电智诚科技有限公司</a></p>
			</div>
		</div> 


<script src="js/bootstrap.js"></script>
<script src="js/jquery.js"></script>
</body>
</html>
