package com.csdl.cabexam.actions.excel.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import sun.misc.BASE64Encoder;
import com.csdl.cabexam.beans.ExamInfo;
import com.csdl.cabexam.beans.ScoreExcel;
import com.csdl.cabexam.dao.ExamInfoDao;
import com.csdl.cabexam.dao.MutiTableDao;
import com.csdl.cabexam.dao.imp.ExamInfoDaoImp;
import com.csdl.cabexam.dao.imp.ExamineeInfoDaoImp;
import com.csdl.cabexam.dao.imp.MutiTableDaoImp;
import com.csdl.cabexam.util.ExcelRead;
import com.csdl.cabexam.util.ZipFileUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.mail.iap.Response;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class doDownloadChoosedAdmission extends ActionSupport {
	private ScoreExcel se;
	private int userInfoId;
	private int examInfoId;
	public ScoreExcel getSe() {
		return se;
	}

	public void setSe(ScoreExcel se) {
		this.se = se;
	}
	
	 public int getUserInfoId() {
		return userInfoId;
	}

	public void setUserInfoId(int userInfoId) {
		this.userInfoId = userInfoId;
	}

	private String fileName;
	    
    public void setFileName(String fileName) {
            this.fileName = fileName;
    }
    public InputStream getInputStream() {
    	// return ServletActionContext.getServletContext().getResourceAsStream("/" + fileName); 
    	return ServletActionContext.getServletContext().getResourceAsStream("temp/zip/准考证.zip");
    }
   
    public String getFileName() {
    	String [] str= fileName.split("/");
    	return str[str.length-1];
	}
	
	
    
    
    
	public String  download() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();	
		//通过cookie读取到被选中的考试id,并将id进行排序
		Cookie[] cookies =  request.getCookies();
		boolean flag=true;
		String cookieStr="";
		for(Cookie co:cookies){
			co.getName();
			co.getValue();	
			if(co.getName().trim().equals("mutiNumberId")){
				cookieStr=co.getValue();
				flag=false;
			}
		}
        int[] idArry=sortStrArryToNumArry(cookieStr,"%");   //得到排序后examid的数组
        
        //将cookie清空
        String str = "0";
        Cookie cookie = new Cookie("mutiNumberId",str);  
        cookie.setMaxAge(31536000/365);
        response.addCookie(cookie);
        
		//生成多个文件暂存的位置，以及zip压缩文件的暂存位置
        String manyDocTempUrl=request.getServletContext().getRealPath("temp\\list\\");
		File file = new File(manyDocTempUrl);
		if(!file.exists()){
			file.mkdirs();
		}else{
			file = new File(manyDocTempUrl);
			doDownloadChoosedAdmission d =new doDownloadChoosedAdmission();
			d.deleteFile(file);
		}
		String zipTempUrl=request.getServletContext().getRealPath("temp\\zip\\");
		file = new File(zipTempUrl);
		if(!file.exists()){
			file.mkdirs();
		}else{
			file = new File(zipTempUrl);
			doDownloadChoosedAdmission d =new doDownloadChoosedAdmission();
			d.deleteFile(file);
		}
        
        //通过id生成多个文件生成到指定的位置
		System.out.println(idArry.length);
		if(idArry.length>1){
			for(int i=1;i<idArry.length;i++){
			    getMutiDoc(idArry[i],manyDocTempUrl);
			}
			//将文件夹打包成zip,放到zip文件暂存位置
			//CompressFiles2Zip(manyDocTempUrl.replace("\\", "/"),zipTempUrl.replace("\\", "/"));
			CompressFiles2Zip(manyDocTempUrl,zipTempUrl);
			
			//删除存放多个文件的文件夹
			file = new File(manyDocTempUrl);
			doDownloadChoosedAdmission d =new doDownloadChoosedAdmission();
			d.deleteFile(file);
			
			return "success";
		}else{
			return "empty";
		}	
	}
	
//将字符串转成数字数组并进行排序
public int[] sortStrArryToNumArry(String str,String regex){
	//先将字符串转成字符串数组
	String[] strArry = str.split(regex);
	int[] intArry=new int[strArry.length];
	//先将字符串转成字符串数组
	for(int i=0;i<strArry.length;i++){
		intArry[i]=Integer.parseInt(strArry[i]);
		
	}
	//进行冒泡排序
	 for(int i=0;i<intArry.length-1;i++){
	         for(int j=i+1;j<intArry.length;j++){
	               if (intArry[i]>intArry[j]){
	                       int temp=intArry[i];
	                       intArry[i]=intArry[j];
	                       intArry[j]=temp;
	               }
	      }
   }
             
	return intArry;
}
//通过examid 将doc文件生成在指定的文件夹
 public boolean getMutiDoc(int examid,String manyDocTempUrl){
	    boolean flag=true;
	    HttpServletRequest  request = ServletActionContext.getRequest();
		//ftl路径
		String ftlUrl = request.getServletContext().getRealPath("images\\ftl\\");
		//ftlUrl=ftlUrl.replace("\\", "/");
		//manyDocTempUrl=manyDocTempUrl.replace("\\", "/");  正反斜杠都可以

        ExamInfoDao ei = new ExamInfoDaoImp();
        ExamInfo e= ei.getExamInfoByExamInfoId(examid);
        Integer userid=e.getUserInfo().getUserInfoId();
	    MutiTableDao mt=new MutiTableDaoImp();
	    ScoreExcel se = mt.getAdmissionINfoById(userid,examid);
	   
	    Map<String,String> dataMap = new HashMap<String,String>();
	    if(se.getTheoryExamDate()!=null&&!se.getTheoryExamDate().equals("")){
	        dataMap.put("year", se.getTheoryExamDate().toString().substring(0, 4));
	    }else{
	    	dataMap.put("year", "2011");
	    }
	    dataMap.put("number", se.getNumber());
	    dataMap.put("realName", se.getRealName());
	    if(se.getSex().trim().equals("1")){	
	    	dataMap.put("sex", "男");
	    }else{
	    	dataMap.put("sex", "女");
	    }
	    dataMap.put("idnum", se.getIdnum());
	    dataMap.put("company", se.getCompany());
	    
	    dataMap.put("grade", "10KV");
	    if(se.getTheoryExamDate()!=null&&!se.getTheoryExamDate().equals("")){
	    	dataMap.put("examDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(se.getTheoryExamDate()));
	    }else{
	    	dataMap.put("examDate", "2014-07-01");
	    }
	    	
	    if(se.getTheoryExamRoom()!=null&&!se.getTheoryExamRoom().equals("")){
	    	dataMap.put("theoryExamRoom", se.getTheoryExamRoom());
	    }else{
	    	dataMap.put("theoryExamRoom", "22");
	    }
	    if(se.getTheoryExamPlace()!=null&&!se.getTheoryExamPlace().equals("")){
	    	dataMap.put("theoryExamPlace", se.getTheoryExamPlace());
	    }else{
	    	dataMap.put("theoryExamPlace", "22");
	    }

	    dataMap.put("operateExamRoom", "22");


	    dataMap.put("operateExamPlace", "33");

	    if(se.getPicture()!=null&&!se.getPicture().equals("")){
	    	String pictureUrl=request.getServletContext().getRealPath("/"+se.getPicture()).replace("\\", "/");
	        String pictureLostUrl=request.getServletContext().getRealPath("/images/LostImage.png").replace("\\", "/");
	    	if(new File(pictureUrl).exists()){
	    		dataMap.put("picture",getImageStr(pictureUrl)) ;
		    } else{
		    	//TODO 图片丢失后的默认图片
		    	dataMap.put("picture",getImageStr(pictureLostUrl)) ;
		    }
	    }
	    try {
	    	Configuration configuration = new Configuration();
	    	configuration.setDefaultEncoding("utf-8");
	    	configuration.setDirectoryForTemplateLoading(new File(ftlUrl));
	    	
	    	System.out.println(manyDocTempUrl+"/"+se.getRealName()+"_"+se.getNumber()+".doc");
	    	// 输出文档路径及名称
	    	File outFile = new File(manyDocTempUrl+"/"+se.getRealName()+"_"+se.getNumber()+".doc");
	    	
	    	//以utf-8的编码读取ftl文件
	    	
	    	Template t =  configuration.getTemplate("admission.ftl","utf-8");
	    	Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"),10240);
	    	t.process(dataMap, out);
	    	out.close();
		} catch (Exception exception) {
            flag=false;			
		}  
    	return flag;
}
 //删除文件夹new fil（”“）；
 public void deleteFile(File oldPath) {
     if (oldPath.isDirectory()) {
      File[] files = oldPath.listFiles();
      for (File file : files) {
        deleteFile(file);
      }
     }else{
       oldPath.delete();
     }
   }
/* public static void main(String[] args) {
	File file =new File("D:/tomcat-7.0.52/webapps/10kvCableConnector/temp/list/");
	doDownloadChoosedAdmission dddd =new doDownloadChoosedAdmission();
	dddd.deleteFile(file);
	
}*/
 //将文件打包成zip
 public void CompressFiles2Zip(String listDocUrl,String zipUrl) {
     //存放待压缩文件的目录
     File srcFile = new File(listDocUrl);
     String zipPath=zipUrl+"/准考证.zip";
     System.out.println(zipPath);
     //压缩后的zip文件路径
     if(srcFile.exists()) {
         File[] files = srcFile.listFiles();
         ZipFileUtil.compressFiles2Zip(files, zipPath);
     }
 }	
	
	
public String getImageStr(String imgPath) {
 String imgFile = imgPath;
 InputStream in = null;
 byte[] data = null;
 try {
     in = new FileInputStream(imgFile);
     data = new byte[in.available()];
     in.read(data);
     in.close();
 } catch (Exception e) {
     e.printStackTrace();
 }
 BASE64Encoder encoder = new BASE64Encoder();
 return encoder.encode(data);
}

public int getExamInfoId() {
	return examInfoId;
}

public void setExamInfoId(int examInfoId) {
	this.examInfoId = examInfoId;
}
	
	
	

	
}
