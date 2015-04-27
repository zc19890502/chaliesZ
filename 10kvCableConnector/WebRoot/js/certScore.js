var scoreflag=0;  //执行后不会吊销证书
var score=12;
    function ajaxCheckCertScore(){
        var  certId= $("#certId").val();  //用.attr("value")只能获得设置的默认值
        var  certScoreInJsp= $("#certscore").val();
        var url = "doCheckCertScore.action";        
        $.get(url,{attr1:certId,value1:certScoreInJsp},certScoreCallback,"text");  
    }  
    
    function certScoreCallback(certScoreNext){
    	score=certScoreNext;
    	 if(score==0){  //扣分超额
         	$("#certscoretip").css("color","red");
         	$("#certscoretip").text("执行此次扣分后将吊销证书");
         	scoreflag=1;
         }else{
	    	$("#certscoretip").css("color","black");
	    	$("#certscoretip").text("执行扣分后将剩余"+score+"分");
	    	scoreflag=0;
         }
    }
    
    function checkCertScore(){
    	var  a=$("#certscore");
    	var  b=$("#certscoretip");
    	var reg=/^([1]{0,1})+([0-9]{1})$/;
    	flag=reg.test(a.val())
    	if(flag){
    	    if(a.val()<=12){
    		   ajaxCheckCertScore();   		
    	    }else{
			b.css("color","red");
         	b.text("扣分在1到12之间");
    	    }
    	}else{
			b.css("color","red");
         	b.text("扣分在1到12之间");
		}  
    }
    
    function CertScoreSubmitCheck(){
    	checkCertScore();
    	if(scoreflag==1){
    		 if(confirm("执行此次扣分会吊销证书！")){
	             return true;
	          }else{
	             return false;
	          } 
    	}else{
    		if(confirm("执行此次扣分后剩余"+score+"分")){
	             return true;
	          }else{
	             return false;
	          }
    	}
    }
 
    
    
   