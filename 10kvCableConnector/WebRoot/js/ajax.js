var flag1=0;   //用户名通过验证标志， 1为通过，0为不通过
var flag2=0;   //密码通过验证标志
var flag3=0;   //密码一致验证标志
var flag4=0;   //邮箱通过验证标志
var falg5=0;   //真实姓名通过验证标志
var flag6=0;   //身份证通过验证标志
var flag7=0;   //验证码通过验证标志

var flag8=0;   //登录验证码通过
var flag9=0;   //准考证唯一性通过验证
// Ajax调用函数发送action请求
function ajaxCheck(id){
            var  inputValue= $("#"+id).val();  //用.attr("value")只能获得设置的默认值
          //  alert(inputValue);
            var url = "doAjaxAction.action"; 
           // alert(url);
            
            $.get(url,{attr1:id,value1:inputValue},callback,"text");  
        }  
// ajax回调函数
    function callback(flag){  
        if(flag==0){  //用户通过验证
        	flag1=1;
        	document.getElementById("registeraccount").style.background="url(img/valid.png) no-repeat 98% center"; 
        	$("#tip1").text("");
        }else if(flag==1){
        	flag1=0;
        	document.getElementById("registeraccount").style.background="url(img/invalid.png) no-repeat 98% center";
        	$("#tip1").css("color","red");
        	$("#tip1").text("用户已经存在");
        }else if(flag==2){  //邮箱通过验证
        	flag4=1;
        	document.getElementById("registeremail").style.background="url(img/valid.png) no-repeat 98% center"; 
        	$("#tip4").text("");
        }else if(flag==3){
        	flag4=0;
        	document.getElementById("registeremail").style.background="url(img/invalid.png) no-repeat 98% center"; 
        	$("#tip4").css("color","red");
        	$("#tip4").text("邮箱已注册");
        }else if(flag==4){   //身份证通过验证
        	flag6=1;
        	document.getElementById("registeridnum").style.background="url(img/valid.png) no-repeat 98% center"; 
        	$("#tip5").css("color","black");
        	$("#tip5").text("");
        }else if(flag==5){
        	flag6=0;
        	document.getElementById("registeridnum").style.background="url(img/invalid.png) no-repeat 98% center"; 
        	$("#tip5").css("color","red");
        	$("#tip5").text("身份证被注册");
        }else if(flag==6){  //验证码通过验证
        	flag7=1;
        	document.getElementById("registerStr").style.background="url(img/valid.png) no-repeat 98% center"; 
        	//当验证码通过验证和所有输入通过验证提交按钮有效
        	if(flag1==1&flag2==1&flag3==1&flag4==1&flag5==1&flag6==1&flag7==1){
        		$("#registersubmit").removeAttr("disabled");
        	}
        }else if(flag==7){  //
        	flag7=0;
        	document.getElementById("registerStr").style.background="url(img/invalid.png) no-repeat 98% center"; 
        }
        else if(flag==8){  //
        	
        	flag8=1;
        	document.getElementById("loginStr").style.background="url(img/valid.png) no-repeat 98% center"; 
        }
        else if(flag==9){  //
        	flag8=0;
        	document.getElementById("loginStr").style.background="url(img/invalid.png) no-repeat 98% center"; 
        }
        //准考证重复性验证
        else if(flag==10){
        	flag9=0;
        	document.getElementById("numberInJsp").style.background="url(img/valid.png) no-repeat 98% center"; 	
        	$("#numbertip").css("color","black");
        	$("#numbertip").text("");
        }else if(flag==11){
        	flag9=1;
        	document.getElementById("numberInJsp").style.background="url(img/invalid.png) no-repeat 98% center"; 	
        	$("#numbertip").css("color","red");
        	$("#numbertip").text("准考证号已经存在");
        }
       
    }  
    
   function   numberSubmitCheck(id){
    	checkNumber(id);
    	if(flag9==1){
    		return false;
    	}else{
    		return true;
    	}
    }
    function checkNumber(id){
	    var  a=$("#"+id);
	    var  b=$("#numbertip");
	    var  inputValue= a.val();
	    var reg = /^([2]{1})+([0-9]{9})$/;
		flag = reg.test(inputValue);
		if(flag){
			ajaxCheck(id);
		}else{
			b.css("color","black");
         	b.text("年+月+4位编号");
         	document.getElementById(id).style.background="url(img/invalid.png) no-repeat 98% center"; 
		}   	
    }
    
    function checkaccount(id){
	    var  a=$("#"+id);
	    var  b=$("#tip1");
	    var  inputValue= a.val();
	    var reg = /^([a-zA-Z0-9_]{6,})+$/;
		flag = reg.test(inputValue);
		if(flag){
			ajaxCheck(id);
		}else{
			flag1=0;
			b.css("color","red");
         	b.text("6位以上数字、字母、下划线");
         	document.getElementById(id).style.background="url(img/invalid.png) no-repeat 98% center"; 
		}
    }
    
    function checkpasswd(){
    	var a=$("#password1");
    	var b=$("#tip2");
    	var reg = /^([a-zA-Z0-9]{6,12})$/;
    	flag=reg.test(a.val());
    	if(!flag){
    		flag2=0;
    		b.css("color","red");
    		b.text("密码为6-12位数字或字符且不能为空");
    		document.getElementById("password1").style.background="url(img/invalid.png) no-repeat 98% center";
    	}else{
    		flag2=1;
    		b.css("color","black");
    		b.text(""); 
    		document.getElementById("password1").style.background="url(img/valid.png) no-repeat 98% center";
    	}
    	
    }
    function checkcoherence(){
    	var a=$("#password1");
    	var b=$("#password2");
    	var c=$("#tip3");
    	if(a.val()!=""&&a.val()==b.val()){
    		flag3=1;
    		c.css("color","black");
    		c.text(""); 
    		document.getElementById("password2").style.background="url(img/valid.png) no-repeat 98% center";
    	}else{
    		flag3=0;
    		c.css("color","red");
    		c.text("密码为空或不一致");
    		document.getElementById("password2").style.background="url(img/invalid.png) no-repeat 98% center";
    	}
    }
    
    function checkemail(id){
    	  	//alert('emailcheck');
    	    var  a=$("#"+id);
    	    var  b=$("#tip4");
    	    var  inputValue= a.val();
    	    var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.([a-zA-Z0-9_-])+)+$/;
    		flag = reg.test(inputValue);
    		if(inputValue!=""&&flag){
    			ajaxCheck(id);
    		}else{
    			flag4=0;
    			b.css("color","red");
	         	b.text("格式错误");
	         	document.getElementById(id).style.background="url(img/invalid.png) no-repeat 98% center"; 
    		}
      }
      function checkname(id){
      	  var  b=$("#tip7");
    	  if($("#"+id).val()==null||$("#"+id).val()==''||$("#"+id).val().indexOf(' ')>=0){
    		  flag5=0;
    		  	b.css("color","red");
	         	b.text("姓名不能为空且中间不能有空格");
	         	document.getElementById(id).style.background="url(img/invalid.png) no-repeat 98% center"; 
    	  }else{
    		  flag5=1;
    		  flag4=1;
        	  document.getElementById(id).style.background="url(img/valid.png) no-repeat 98% center"; 
        	  $("#tip7").text("");
    	  }
      }
      function checkidnum(id){
	    	var  inputValue= $("#"+id).val();
	  	    var reg = /^([0-9]{17})([a-zA-Z0-9]{1})$/;
	  		flag = reg.test(inputValue);
	  		if(inputValue!=""&&flag){
	  			ajaxCheck(id);
	  		}else{
	  			
	  			 flag6=1;
	  			 var a=$("#tip5");
	     		 a.css("color","red");
	         	 a.text("身份证格式错误或为空");
	         	document.getElementById("registeridnum").style.background="url(img/invalid.png) no-repeat 98% center"; 
	  		}
      }
      function checkregisterall(){
    	  checkaccount('registeraccount');
    	  checkpasswd();
    	  checkcoherence();
    	  checkemail('registeremail');
    	  checkname('registername');
    	  checkidnum('registeridnum');
    	  ajaxCheck('registerStr');
    	  if(flag1==1&&flag2==1&&flag3==1&&flag4==1&&flag5==1&&flag6==1&&flag7==1){
    	  	 $("#registersubmit").attr("disabled","disabled");
    		  return true;
      	}else{
      		alert("信息不合法，请认真填写");
      		return false;
      	}
  
      }
      
      function checklogin(){  //验证码合法  不能登录
    	  if(flag8==1){
    		  return true;
    	  }else{
    		  alert("请正确填写验证码");
    		  return false;  
    	  }
      }
      
    //检测报名电话格式
     function checktel(id){
    	 var telInput=$("#"+id);
    	 var reg = /^[1][3568]([0-9]{9})$/;
    	 flag=reg.test(telInput.val());
    	 if(flag){ 
     		 document.getElementById(id).style.background="url(img/valid.png) no-repeat 98% center";	 
    	 }else{
    		 document.getElementById(id).style.background="url(img/invalid.png) no-repeat 98% center";	 
    	 }
    	 
     }
      
      //检测报名信息
      function checkexaminee(){
    	var a0 = $("#sexchose").val();             		      //性别
    	var a1 = $("#sextip");
    	var b0 = $("#photoInput").val();             	      //照片输入
    	var b2 = $("#photoOld").val();
    	var b1 = $("#phototip");           	        
    	var c0 = $("#placeInput").val();             		  //邮编输入
    	var c1 = $("#placetip");           		 
    	var d0 = $("#nationalityInput").val();       		  //民族输入
    	var d1 = $("#nationalitytip");     		
    	var e0 = $("#homesiteInput").val();          		  //现居住地址
    	var e1 = $("#homesitetip");       		      
    	var f0 = $("#telInput").val();                		  //电话
    	var f1 = $("#teltip");              		
    	var g0 = $("#birthInput").val();              		  //生日
    	var g1 = $("#birthtip");            		 
    	var h0 = $("#partyInput").val();              		  //政治面貌
    	var h1 = $("#partytip");            		  
    	var i0 = $("#educationInput").val();          		  //学历
    	var i1 = $("#educationtip");        		  
    	var j0 = $("#physicalConditionInput").val();           //健康状况
    	var j1 = $("#physicalConditiontip");        
    	var k0 = $("#workTimeInput").val();                    //工作时间
    	var k1 = $("#workTimetip");               
    	var l0 = $("#workLimitTimeInput").val();               //工作年限
    	var l1 = $("#workLimitTimetip"); 
    	
    	var m0 = $("#skillLevelInput").val();                  //技能等级
    	var m1 = $("#skillLeveltip");              
    	var n0 = $("#companyInput").val();                     //公司
    	var n1 = $("#companytip");                   
    	var o0 = $("#jobResumeInput").val();                   //简历
    	var o1 = $("#jobResumetip");                
    	var p0 = $("#idcardScanInput").val();                  //身份证扫描
    	var p2 = $("#idscanOld").val();
    	var p1 = $("#idcardScantip");              
    	var q0 = $("#companyScanInput").val();                 //单位盖章审核
    	var q2 = $("#companyScanOld").val();
    	var q1 = $("#companyScantip");             
    	var r0 = $("#workScanInput").val();                    //工作证明
    	var r2 = $("#workproofOld").val();
    	var r1 = $("#workScantip");    
    	var flag=0; 
    	
	 	var reg = /^[1][3568]([0-9]{9})$/;
    	
    	a1.css({"color":"black","font-size":"14px"});a1.text("");
    	b1.css({"color":"black","font-size":"14px"});b1.text("");
    	c1.css({"color":"black","font-size":"14px"});c1.text("");
    	d1.css({"color":"black","font-size":"14px"});d1.text("");
    	e1.css({"color":"black","font-size":"14px"});e1.text("");
    	f1.css({"color":"black","font-size":"14px"});f1.text("");
    	g1.css({"color":"black","font-size":"14px"});g1.text("");
    	h1.css({"color":"black","font-size":"14px"});h1.text("");
    	i1.css({"color":"black","font-size":"14px"});i1.text("");
    	j1.css({"color":"black","font-size":"14px"});j1.text("");
    	k1.css({"color":"black","font-size":"14px"});k1.text("");
    	l1.css({"color":"black","font-size":"14px"});l1.text("");
    	m1.css({"color":"black","font-size":"14px"});m1.text("");
    	n1.css({"color":"black","font-size":"14px"});n1.text("");
    	o1.css({"color":"black","font-size":"14px"});o1.text("");
    	p1.css({"color":"black","font-size":"14px"});p1.text("");
    	q1.css({"color":"black","font-size":"14px"});q1.text("");
    	r1.css({"color":"black","font-size":"14px"});r1.text("");
    	
    	if(a0==null||a0==''){a1.css({"color":"red"});a1.text("不能为空");flag++;}
    	if((b0==null||b0=='')&&(b2==null||b2=='')){b1.css({"color":"red"});b1.text("不能为空");flag++;}
    	if(c0==null||c0==''){c1.css({"color":"red"});c1.text("不能为空");flag++;}
    	if(d0==null||d0==''){d1.css({"color":"red"});d1.text("不能为空");flag++;}
    	if(e0==null||e0==''){e1.css({"color":"red"});e1.text("不能为空");flag++;}
    	if(f0==null||f0==''||!reg.test(f0)){f1.css({"color":"red"});f1.text("不能为空或格式错误");flag++;}  //电话为空
    	if(g0==null||g0==''){g1.css({"color":"red"});g1.text("不能为空");flag++;}
    	if(h0==null||h0==''){h1.css({"color":"red"});h1.text("不能为空");flag++;}
    	if(i0==null||i0==''){i1.css({"color":"red"});i1.text("不能为空");flag++;}
    	if(j0==null||j0==''){j1.css({"color":"red"});j1.text("不能为空");flag++;}
    	if(k0==null||k0==''){k1.css({"color":"red"});k1.text("不能为空");flag++;}
    	if(l0==null||l0==''){l1.css({"color":"red"});l1.text("不能为空");flag++;}
    	if(m0==null||m0==''){m1.css({"color":"red"});m1.text("不能为空");flag++;}
    	if(n0==null||n0==''){n1.css({"color":"red"});n1.text("不能为空");flag++;}
    	if(o0==null||o0==''){o1.css({"color":"red"});o1.text("不能为空");flag++;}
    	if((p0==null||p0=='')&&(p2==null||p2=='')){p1.css({"color":"red"});p1.text("不能为空");flag++;}
    	if((q0==null||q0=='')&&(q2==null||q2=='')){q1.css({"color":"red"});q1.text("不能为空");flag++;}
    	if((r0==null||r0=='')&&(r2==null||r2=='')){r1.css({"color":"red"});r1.text("不能为空");flag++;}
    		 
    	if(flag>0){
    		return false;
    	}else{
    		$("#submitRegis").attr("disabled","disabled");
    		alert('您的报名信息已成功提交！');
    		return true;
    	}	 
    	 
      }
      
      
      
      function tip(){    //点击真实姓名，清除其他框的提示
    		var a1=$("#tip1");
    		var b1=$("#registeraccount");
    		var a2=$("#tip2");
      		var b2=$("#password1");
      		var a3=$("#tip3");
      	    var b3=$("#password2");
      	    var a4=$("#tip4");
  	        var b4=$("#registeremail");
  	        var a5=$("#tip5");
  	        var b5=$("#registeridnum");	
        	if(b1.val()==null||b1.val()==''){
        		b1.css( "background-image", "none" );
        		a1.text("");
        	}
        	if(b2.val()==null||b2.val()==''){
        		b2.css( "background-image", "none" );
        		a2.text("");
        	}
        	if(b3.val()==null||b3.val()==''){
        		b3.css( "background-image", "none" );
        		a3.text("");
        	}
        	if(b4.val()==null||b4.val()==''){
        		b4.css( "background-image", "none" );
        		a4.text("");
        	}
        	if(b5.val()==null||b5.val()==''){
        		b5.css( "background-image", "none" );
        		a5.text("");
        	}
    	 }
      function tip1(){   //点击账号，清除其他框的提示
    	  var a1=$("#tip1");
    	  var b1=$("#registeraccount");
    	  var a2=$("#tip2");
    	  var b2=$("#password1");
    	  var a3=$("#tip3");
    	  var b3=$("#password2");
    	  var a4=$("#tip4");
    	  var b4=$("#registeremail");
    	  var a5=$("#tip5");
    	  var b5=$("#registeridnum");
    	  b1.css( "background-image", "none" );
    	  a1.css("color","black");
    	  a1.text("6位以上数字、字母、下划线");
    	  
    	  if(b2.val()==null||b2.val()==''){
    		  b2.css( "background-image", "none" );
    		  a2.text("");
    	  }
    	  if(b3.val()==null||b3.val()==''){
    		  b3.css( "background-image", "none" );
    		  a3.text("");
    	  }
    	  if(b4.val()==null||b4.val()==''){
    		  b4.css( "background-image", "none" );
    		  a4.text("");
    	  }
    	  if(b5.val()==null||b5.val()==''){
    		  b5.css( "background-image", "none" );
    		  a5.text("");
    	  }
      }
      function tip2(){    //点击密码，清除其他框的提示
    	    var a1=$("#tip1");
  		    var b1=$("#registeraccount");
  		    var a2=$("#tip2");
    		var b2=$("#password1");
    		var a3=$("#tip3");
    	    var b3=$("#password2");
    	    var a4=$("#tip4");
	        var b4=$("#registeremail");
	        var a5=$("#tip5");
	        var b5=$("#registeridnum");
		    b2.css( "background-image", "none" );
  		    a2.css("color","black");
      	    a2.text("密码为6-12位数字或字符");
	      	if(b1.val()==null||b1.val()==''){
	      		b1.css( "background-image", "none" );
	      		a1.text("");
	      	}
	      	if(b3.val()==null||b3.val()==''){
	      		b3.css( "background-image", "none" );
	      		a3.text("");
	      	}
	      	if(b4.val()==null||b4.val()==''){
	      		b4.css( "background-image", "none" );
	      		a4.text("");
	      	}
	      	if(b5.val()==null||b5.val()==''){
	      		b5.css( "background-image", "none" );
	      		a5.text("");
	      	}
  	 }
      function tip3(){    //点击确认密码，清除其他框的提示
    	    var a1=$("#tip1");
  		    var b1=$("#registeraccount");
  		    var a2=$("#tip2");
    		var b2=$("#password1");
    		var a3=$("#tip3");
    	    var b3=$("#password2");
    	    var a4=$("#tip4");
	        var b4=$("#registeremail");
	        var a5=$("#tip5");
	        var b5=$("#registeridnum");
  		    b3.css( "background-image", "none" );
    		a3.css("color","black");
        	a3.text("重复输入上面的密码");
        	if(b1.val()==null||b1.val()==''){
	      		b1.css( "background-image", "none" );
	      		a1.text("");
	      	}
	      	if(b2.val()==null||b2.val()==''){
	      		b2.css( "background-image", "none" );
	      		a2.text("");
	      	}
	      	if(b4.val()==null||b4.val()==''){
	      		b4.css( "background-image", "none" );
	      		a4.text("");
	      	}
	      	if(b5.val()==null||b5.val()==''){
	      		b5.css( "background-image", "none" );
	      		a5.text("");
	      	}
    	 }
      function tip4(){  
    	    var a1=$("#tip1");
  		    var b1=$("#registeraccount");
  		    var a2=$("#tip2");
    		var b2=$("#password1");
    		var a3=$("#tip3");
    	    var b3=$("#password2");
    	    var a4=$("#tip4");
	        var b4=$("#registeremail");
	        var a5=$("#tip5");
	        var b5=$("#registeridnum");
  		     b4.css( "background-image", "none" );
    		 a4.css("color","black");
        	 a4.text("");
        	 if(b1.val()==null||b1.val()==''){
 	      		b1.css( "background-image", "none" );
 	      		a1.text("");
 	      	}
 	      	if(b2.val()==null||b2.val()==''){
 	      		b2.css( "background-image", "none" );
 	      		a2.text("");
 	      	}
 	      	if(b3.val()==null||b3.val()==''){
 	      		b3.css( "background-image", "none" );
 	      		a3.text("");
 	      	}
 	      	if(b5.val()==null||b5.val()==''){
 	      		b5.css( "background-image", "none" );
 	      		a5.text("");
 	      	}
    	 }
      function tip5(){  
    	    var a1=$("#tip1");
  		    var b1=$("#registeraccount");
  		    var a2=$("#tip2");
    		var b2=$("#password1");
    		var a3=$("#tip3");
    	    var b3=$("#password2");
    	    var a4=$("#tip4");
	        var b4=$("#registeremail");
	        var a5=$("#tip5");
	        var b5=$("#registeridnum");
  		    b5.css( "background-image", "none" );
    		a5.css("color","black");
        	a5.text("");
        	if(b1.val()==null||b1.val()==''){
	      		b1.css( "background-image", "none" );
	      		a1.text("");
	      	}
	      	if(b2.val()==null||b2.val()==''){
	      		b2.css( "background-image", "none" );
	      		a2.text("");
	      	}
	      	if(b3.val()==null||b3.val()==''){
	      		b3.css( "background-image", "none" );
	      		a3.text("");
	      	}
	      	if(b4.val()==null||b4.val()==''){
	      		b4.css( "background-image", "none" );
	      		a4.text("");
	      	}
    	 }
      function tip6(){  
	  	    var a1=$("#tip1");
			var b1=$("#registeraccount");
			var a2=$("#tip2");
	  		var b2=$("#password1");
	  		var a3=$("#tip3");
	  	    var b3=$("#password2");
	  	    var a4=$("#tip4");
		    var b4=$("#registeremail");
		    var a5=$("#tip5");
		    var b5=$("#registeridnum");
	      	if(b1.val()==null||b1.val()==''){
		      		b1.css( "background-image", "none" );
		      		a1.text("");
		      	}
		      	if(b2.val()==null||b2.val()==''){
		      		b2.css( "background-image", "none" );
		      		a2.text("");
		      	}
		      	if(b3.val()==null||b3.val()==''){
		      		b3.css( "background-image", "none" );
		      		a3.text("");
		      	}
		      	if(b4.val()==null||b4.val()==''){
		      		b4.css( "background-image", "none" );
		      		a4.text("");
		      	}
		      	if(b5.val()==null||b5.val()==''){
		      		b5.css( "background-image", "none" );
		      		a5.text("");
		      	}
  	 }
      
      function checkPsw1(){
        var  a=$("#newPassword");
  	    var  b=$("#pswtip1");
  	    var reg = /^([a-zA-Z0-9]{6,12})$/;
      	flag=reg.test(a.val());
      	if(!flag){
      		b.css("color","red");
      		b.text("密码为6-12位数字或字符");
      		document.getElementById("newPassword").style.background="url(img/invalid.png) no-repeat 98% center";
      	}else{
      		b.css("color","black");
      		b.text(""); 
      		document.getElementById("newPassword").style.background="url(img/valid.png) no-repeat 98% center";
      	}
       return flag;
       
     }
     function clickPsw1(){
         var  a=$("#newPassword");
  	   var  b=$("#pswtip1");
  	   b.css("color","black");
         b.text(""); 
         document.getElementById("newPassword").style.background=""; 
     
     }
     function checkPsw2(){
        var  a=$("#password");
  	    var  b=$("#newPassword");
  	    var  c=$("#pswtip2");
  	    var flag=true;
      	if(a.val()==b.val()){
      		c.css("color","black");
      		c.text(""); 
      		document.getElementById("password").style.background="url(img/valid.png) no-repeat 98% center";
      	}else{
      		c.css("color","red");
      		document.getElementById("password").style.background="url(img/invalid.png) no-repeat 98% center";
      	    flag=false;
      	}
  	    return flag
     }
     function clickPsw2(){
         var  a=$("#password");
  	     var  b=$("#pswtip2");
  	     b.css("color","black");
         b.text(""); 
         document.getElementById("password").style.background=""; 
     
     }
     
     function checkPswOnsubmit(){
    	var flag1=checkPsw1();
    	var flag2=checkPsw2();
    	if(flag1&&flag2){
    		return true;
    	}else{
    		return false;
    	}
    	 
     }

      
      