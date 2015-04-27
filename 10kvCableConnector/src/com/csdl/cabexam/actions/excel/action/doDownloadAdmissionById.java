package com.csdl.cabexam.actions.excel.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import sun.misc.BASE64Encoder;


import com.csdl.cabexam.beans.ExamInfo;
import com.csdl.cabexam.beans.ScoreExcel;
import com.csdl.cabexam.dao.MutiTableDao;
import com.csdl.cabexam.dao.imp.MutiTableDaoImp;
import com.csdl.cabexam.util.ExcelRead;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.mail.iap.Response;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class doDownloadAdmissionById extends ActionSupport {
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
    	MutiTableDao mt=new MutiTableDaoImp();
	    ScoreExcel se = mt.getAdmissionINfoById(userInfoId,examInfoId);
	    System.out.println("temp/"+se.getRealName()+"_"+se.getNumber()+".doc");
    	return ServletActionContext.getServletContext().getResourceAsStream("temp/"+se.getRealName()+"_"+se.getNumber()+".doc");
    }
   
    public String getFileName() {
    	String [] str= fileName.split("/");
    	return str[str.length-1];
	}
	
	
	public String  downloadById() {
		HttpServletRequest request = ServletActionContext.getRequest();
		//ftl路径
		String ftlUrl = request.getServletContext().getRealPath("images\\ftl\\");
		ftlUrl=ftlUrl.replace("\\", "/");
		String docTempUrl=request.getServletContext().getRealPath("temp\\");
		File file = new File(docTempUrl);
		if(!file.exists()){
			file.mkdirs();
		}
		
		docTempUrl=docTempUrl.replace("\\", "/");
		
		
		String examid_s = request.getParameter("examInfoId");
		String userid_s = request.getParameter("userInfoId");
	    Integer examid = null;
	    Integer userid = null;
	    if(examid_s!=null&&!examid_s.equals("")){
	    	examid=Integer.parseInt(examid_s);
	    }
	    if(userid_s!=null&&!userid_s.equals("")){
	    	userid=Integer.parseInt(userid_s);
	    }
	    MutiTableDao mt=new MutiTableDaoImp();
	    ScoreExcel se = mt.getAdmissionINfoById(userid,examid);
	   
	    Map<String,String> dataMap = new HashMap<String,String>();
	    if(se.getTheoryExamDate()!=null&&!se.getTheoryExamDate().equals("")){
	        dataMap.put("year", se.getTheoryExamDate().toString().substring(0, 4));
	    }else{
	    	dataMap.put("year", "2014");
	    }
	    if(se.getNumber()!=null&&!se.getNumber().equals("")){
	    	dataMap.put("number", se.getNumber());
	    }else{
	    	dataMap.put("number", "0000");
	    }
	    if(se.getRealName()!=null&&!se.getRealName().equals("")){
	    	dataMap.put("realName", se.getRealName());
	    }else{
	    	dataMap.put("realName", "张三");
	    }
	    if(se.getSex().trim().equals("1")){	
	    	dataMap.put("sex", "男");
	    }else{
	    	dataMap.put("sex", "女");
	    }
	    if(se.getIdnum()!=null&&!se.getIdnum().equals("")){
	    	dataMap.put("idnum", se.getIdnum());
	    }else{
	    	dataMap.put("idnum", "000000000000000000");
	    }
	    if(se.getCompany()!=null&&!se.getCompany().equals("")){
	    	dataMap.put("company", se.getCompany());
	    }else{
	    	dataMap.put("company", "中南大学软件学院");
	    }
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
	    	
	    	System.out.println(docTempUrl+"/"+se.getRealName()+"_"+se.getNumber()+".doc");
	    	// 输出文档路径及名称
	    	File outFile = new File(docTempUrl+"/"+se.getRealName()+"_"+se.getNumber()+".doc");
	    	
	    	//以utf-8的编码读取ftl文件
	    	
	    	Template t =  configuration.getTemplate("admission.ftl","utf-8");
	    	Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"),10240);
	    	t.process(dataMap, out);
	    	out.close();
		} catch (Exception e) {
			// TODO: handle exception
		}  
    
		    return SUCCESS;
		
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
