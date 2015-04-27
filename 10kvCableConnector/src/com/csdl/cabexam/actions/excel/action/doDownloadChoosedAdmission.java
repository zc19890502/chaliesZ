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
    	return ServletActionContext.getServletContext().getResourceAsStream("temp/zip/׼��֤.zip");
    }
   
    public String getFileName() {
    	String [] str= fileName.split("/");
    	return str[str.length-1];
	}
	
	
    
    
    
	public String  download() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();	
		//ͨ��cookie��ȡ����ѡ�еĿ���id,����id��������
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
        int[] idArry=sortStrArryToNumArry(cookieStr,"%");   //�õ������examid������
        
        //��cookie���
        String str = "0";
        Cookie cookie = new Cookie("mutiNumberId",str);  
        cookie.setMaxAge(31536000/365);
        response.addCookie(cookie);
        
		//���ɶ���ļ��ݴ��λ�ã��Լ�zipѹ���ļ����ݴ�λ��
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
        
        //ͨ��id���ɶ���ļ����ɵ�ָ����λ��
		System.out.println(idArry.length);
		if(idArry.length>1){
			for(int i=1;i<idArry.length;i++){
			    getMutiDoc(idArry[i],manyDocTempUrl);
			}
			//���ļ��д����zip,�ŵ�zip�ļ��ݴ�λ��
			//CompressFiles2Zip(manyDocTempUrl.replace("\\", "/"),zipTempUrl.replace("\\", "/"));
			CompressFiles2Zip(manyDocTempUrl,zipTempUrl);
			
			//ɾ����Ŷ���ļ����ļ���
			file = new File(manyDocTempUrl);
			doDownloadChoosedAdmission d =new doDownloadChoosedAdmission();
			d.deleteFile(file);
			
			return "success";
		}else{
			return "empty";
		}	
	}
	
//���ַ���ת���������鲢��������
public int[] sortStrArryToNumArry(String str,String regex){
	//�Ƚ��ַ���ת���ַ�������
	String[] strArry = str.split(regex);
	int[] intArry=new int[strArry.length];
	//�Ƚ��ַ���ת���ַ�������
	for(int i=0;i<strArry.length;i++){
		intArry[i]=Integer.parseInt(strArry[i]);
		
	}
	//����ð������
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
//ͨ��examid ��doc�ļ�������ָ�����ļ���
 public boolean getMutiDoc(int examid,String manyDocTempUrl){
	    boolean flag=true;
	    HttpServletRequest  request = ServletActionContext.getRequest();
		//ftl·��
		String ftlUrl = request.getServletContext().getRealPath("images\\ftl\\");
		//ftlUrl=ftlUrl.replace("\\", "/");
		//manyDocTempUrl=manyDocTempUrl.replace("\\", "/");  ����б�ܶ�����

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
	    	dataMap.put("sex", "��");
	    }else{
	    	dataMap.put("sex", "Ů");
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
		    	//TODO ͼƬ��ʧ���Ĭ��ͼƬ
		    	dataMap.put("picture",getImageStr(pictureLostUrl)) ;
		    }
	    }
	    try {
	    	Configuration configuration = new Configuration();
	    	configuration.setDefaultEncoding("utf-8");
	    	configuration.setDirectoryForTemplateLoading(new File(ftlUrl));
	    	
	    	System.out.println(manyDocTempUrl+"/"+se.getRealName()+"_"+se.getNumber()+".doc");
	    	// ����ĵ�·��������
	    	File outFile = new File(manyDocTempUrl+"/"+se.getRealName()+"_"+se.getNumber()+".doc");
	    	
	    	//��utf-8�ı����ȡftl�ļ�
	    	
	    	Template t =  configuration.getTemplate("admission.ftl","utf-8");
	    	Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"),10240);
	    	t.process(dataMap, out);
	    	out.close();
		} catch (Exception exception) {
            flag=false;			
		}  
    	return flag;
}
 //ɾ���ļ���new fil����������
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
 //���ļ������zip
 public void CompressFiles2Zip(String listDocUrl,String zipUrl) {
     //��Ŵ�ѹ���ļ���Ŀ¼
     File srcFile = new File(listDocUrl);
     String zipPath=zipUrl+"/׼��֤.zip";
     System.out.println(zipPath);
     //ѹ�����zip�ļ�·��
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
