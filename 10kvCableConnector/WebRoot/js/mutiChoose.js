function selectAll(){
   var checklist = document.getElementsByName ("mutiNumberId");  //得到所有的单选框元素
   //alert(checklist[i].getAttribute("id"));   //各个元素中id的属性值
   var cookieStr =  getCookieByName("mutiNumberId");        //得到cookie
   //alert("得到的cookie="+cookieStr);
   if(document.getElementById("controlAll").checked)
   {
      
     //将得到的 不重复的 id属性全部写入cookie中   
     for(var i=0;i<checklist.length;i++) {
          checklist[i].checked = true;      //选中选框,可以使true/false,也可以是1/0
          var ids = cookieStr.split("%");
          var flag=0;   //如果cookie中不存在id，则flag=0;如果存在，则flag=1;
          var idvalue=checklist[i].getAttribute("id");
	          for(var j=0;j<ids.length;j++){
	              if(ids[j].trim()==idvalue.trim()){
	                 flag=1;
	              }
	          }
	          //不存在添加
	          if(flag==0){
	              cookieStr=cookieStr+"%"+idvalue;
	          }  
          } 
   }else{
     for(var i=0;i<checklist.length;i++){
         checklist[i].checked = false;   //所有选框都取消
         var ids = cookieStr.split("%");   
         //如果cookie中存在id，则删除id;
         var idvalue=checklist[i].getAttribute("id");
	          for(var j=0;j<ids.length;j++){
	              if(ids[j].trim()==idvalue.trim()){
	                 //如果存在则删除
	                 cookieStr=cookieStr.replace("%"+idvalue, "");
	              }
	          } 

          }  
       }
       //将cookieStr写入cookie中
        document.cookie="mutiNumberId="+cookieStr;
  }

function checkFile(id){
	  var idvalue=document.getElementById(id).getAttribute("id");
	  if(document.getElementById(id).checked){
	  		addToCookie(idvalue,"mutiNumberId");
	  		//ToDo如果此时所有的按钮都被选中，将"全选框"也选中
	  		var checklist = document.getElementsByName ("mutiNumberId");
	  		var flag =true;
	  		for(var i=0;i<checklist.length;i++){
	  		    if(!checklist[i].checked){
	  		       flag=false;
	  		       break;
	  		    }
	  		}
	  		//如果全部被选中,置位全选框
	  		if(flag==true){
	  		    document.getElementById("controlAll").checked=true;
	  		}
	  }else{
	        deleteFromCookie(idvalue,"mutiNumberId");
	        //ToDo  取消全选框选则
	         document.getElementById("controlAll").checked=false; 
	  }
	}

//如果不存在，则将 id属性的值写入到cookie中
function addToCookie(idvalue,CookieName){
     var cookieStr =  getCookieByName(CookieName);        //得到cookie
     var ids = cookieStr.split("%");
     var flag=0;   //如果cookie中不存在id,则flag=0;如果存在,则flag=1;
     for(var i=0;i<ids.length;i++){
         if(ids[i].trim()==idvalue.trim()){
            flag=1;
         }
     }
     //不存在添加
     if(flag==0){
         cookieStr=cookieStr+"%"+idvalue.trim();
     }  
     document.cookie=CookieName+"="+cookieStr;
}

//如果存在，则将 id属性的值从cookie中删除
function  deleteFromCookie(idvalue,CookieName){
     var cookieStr =  getCookieByName(CookieName);        //得到cookie
     var ids = cookieStr.split("%");
     //如果cookie中存在id，则删除id;
     for(var j=0;j<ids.length;j++){
         if(ids[j].trim()==idvalue.trim()){
            //如果存在则删除
            cookieStr=cookieStr.replace("%"+idvalue, "");
         }
     } 
     document.cookie=CookieName+"="+cookieStr;


}




function  getCookieByName(CookieName){
   var strCookie=document.cookie; 
   var arrCookie=strCookie.split(";");
   //如果有  找到cookie
   var CookieVal;
   var flag=0;
   for(var i=0;i<arrCookie.length;i++){ 
	    var arr=arrCookie[i].split("="); 
	    //flag==1找到名称为mutiNumberId的cookie，并返回它的值 
	    if(CookieName.trim()==arr[0].trim()){   //此处必须去空格  
	        CookieVal=arr[1];
	        flag=1; 
	        break; 
        } 
     } 
     //如果没有  新建cookie
     if(flag==0){
        document.cookie=CookieName+"=0";
        CookieVal="0";
     }
     
     return CookieVal;
}

function cookieTest(){
/* 	//document.cookie="mutiNumberId="; 
	//获取cookie字符串 
	var strCookie=document.cookie; 
    //将多cookie切割为多个名/值对 
	var arrCookie=strCookie.split(";"); 
	var mutiNumberId;
	//遍历cookie数组，处理每个cookie对 
	for(var i=0;i<arrCookie.length;i++){ 
	    var arr=arrCookie[i].split("="); 
	    //找到名称为mutiNumberId的cookie，并返回它的值 
	    if("mutiNumberId"==arr[0]){ 
	         mutiNumberId=arr[1]; 
	         break; 
        } 
     }   */
     getCookieByName("mutiNumberabcdefg");

}

//初始化选中需要的单选框
function setCheck(){
    var cookieStr =  getCookieByName("mutiNumberId");
    var ids = cookieStr.split("%");
    
    var checklist = document.getElementsByName ("mutiNumberId");
    //如果cookie中有id则将页面上的单选框选上,否则取消
    for(var i=0;i<checklist.length;i++) {
        var idvalue=checklist[i].getAttribute("id");
        var flag=false;  //id存在为true;id不存在为false
	    for(var j=0;j<ids.length;j++){
	              if(ids[j].trim()==idvalue.trim()){
	                 checklist[i].checked = true; 
	                 flag=true;
	              }              
	          }
	    //不存在,取消选则
	    if(!flag){
	    	checklist[i].checked = false; 
	    	document.getElementById("controlAll").checked=false; 
	    }
    }
    
	var date = new Date(); //日期对象 
	var now = ""; 
	now = date.getFullYear()+"年"; //读英文就行了 
	now = now + (date.getMonth()+1)+"月"; //取月的时候取的是当前月-1如果想取当前月+1就可以了 
	now = now + date.getDate()+"日  "; 
	now = now + date.getHours()+":"; 
	now = now + date.getMinutes()+":"; 
	now = now + date.getSeconds(); 
	document.getElementById("time").innerHTML = now; //div的html是now这个字符串 
	setTimeout("setCheck()"); //设置过1000毫秒就是1秒，调用show方法 
}
