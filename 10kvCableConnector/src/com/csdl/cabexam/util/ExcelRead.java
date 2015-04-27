package com.csdl.cabexam.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;

import com.csdl.cabexam.actions.examee.service.ExamineeService;
import com.csdl.cabexam.beans.CertInfoExcel;
import com.csdl.cabexam.beans.CertificateInfo;
import com.csdl.cabexam.beans.ExamInfo;
import com.csdl.cabexam.beans.RecordOfYear;
import com.csdl.cabexam.beans.UserInfo;
import com.csdl.cabexam.dao.CertificateInfoDao;
import com.csdl.cabexam.dao.ExamInfoDao;
import com.csdl.cabexam.dao.RecordOfYearDao;
import com.csdl.cabexam.dao.imp.CertificateInfoDaoImp;
import com.csdl.cabexam.dao.imp.ExamInfoDaoImp;
import com.csdl.cabexam.dao.imp.RecordOfYearDaoImp;

/**
 * ����Excel���Ĺ�����
 */
public class ExcelRead {
    private POIFSFileSystem fs;
    private HSSFWorkbook wb;
    private HSSFSheet sheet;
    private HSSFRow row;

    /**
     * ��ȡExcel����ͷ������
     * @param InputStream
     * @return String ��ͷ���ݵ�����
     */
    public String[] readExcelTitle(InputStream is) {
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        row = sheet.getRow(0);
        // ����������
        int colNum = row.getPhysicalNumberOfCells();
        String[] title = new String[colNum];
        for (int i = 0; i < colNum; i++) {
            //title[i] = getStringCellValue(row.getCell((short) i));
            title[i] = getCellFormatValue(row.getCell((short) i));
        }
        return title;
    }
    
   
    /**
     * ��֤Excel��������
     * @param InputStream
     * @return List ������Ϣ
     */
    public List<String> validateExamXls(InputStream is){
    	List<String> errstrList = new ArrayList<String>();
    	String str = "";
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        // �õ�������
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // ��������Ӧ�ôӵڶ��п�ʼ,��һ��Ϊ��ͷ�ı���
        for (int i = 1; i <= rowNum; i++) {
        	row = sheet.getRow(i);
        	if(null==row){
        		continue;
        	}
        	StringBuilder errstr = new StringBuilder("��"+(i+1)+"��:");
        	String userinfoid_str = getCellFormatValue(row.getCell(0));
        	String examinfoid_str = getCellFormatValue(row.getCell(1));
        	String number_str = getCellFormatValue(row.getCell(3));
        	String theoryExamDate_str = getCellFormatValue(row.getCell(4));
        	String theoryExamRoom_str = getCellFormatValue(row.getCell(5));
        	String theoryExamPlace_str = getCellFormatValue(row.getCell(6));
        	
        	Integer userinfoid =null;
        	Integer examinfoid=null; 
        	if(userinfoid_str!=null&&!userinfoid_str.equals("")){
        		try{
        			userinfoid = (int) Float.parseFloat(userinfoid_str.trim());
        		}catch (Exception e) {
        			errstr.append("�û�ID����Ϊ����; ");
				}
        	}else{
        		errstr.append("�û�IDΪ�գ�");
        	}
        	if (examinfoid_str!=null&&!examinfoid_str.equals("")) {
        		try{
        			examinfoid = (int) Float.parseFloat(examinfoid_str.trim());
        		}catch (Exception e) {
        			errstr.append("����ID����Ϊ����; ");
				}
			}else{
				errstr.append("����IDΪ�գ�");
			}
        	if (number_str==null||"".equals(number_str)) {
				errstr.append("׼��֤��Ϊ�գ�");
			}
        	
        	if (theoryExamDate_str==null||"".equals(theoryExamDate_str)) {
        		errstr.append("����ʱ��Ϊ�գ�");
        	}else{
        		try {
        			new ExamineeService().convertToTimestamp(theoryExamDate_str,"yyyy-MM-dd HH:mm:ss");
        		} catch (Exception e) {
        			errstr.append("����ʱ�䲻�Ϸ���");
        		}
        	}
        	
    		
        	if (theoryExamRoom_str==null||"".equals(theoryExamRoom_str)) {
        		errstr.append("������Ϊ�գ�");
        	}
        	if (theoryExamPlace_str==null||"".equals(theoryExamPlace_str)) {
        		errstr.append("���Եص�Ϊ�գ�");
        	}
        	
        	//һһ��Ӧƥ��
        	ExamInfoDao examinfo = new ExamInfoDaoImp();
        	if(userinfoid!=null&&examinfoid!=null&&number_str!=null&&!examinfo.checkCoordinate(userinfoid, examinfoid, number_str.trim())){
        		errstr.append("�û�ID������ID��׼��֤�Ų�ƥ�䣻");
        	}
        	if(!errstr.toString().equals("��"+(i+1)+"��:")){
        		errstrList.add(errstr.toString());
        	}
        }
    	
        return errstrList;
    }
    
    
    
    /**
     * ��֤Excel���۳ɼ�
     * @param InputStream
     * @return List ������Ϣ
     */
    //TODO 2 ��֤   �û�id������id��׼��֤�ŵ�һһ��Ӧ
    
    public List<String> validateTheoryScoreXls(InputStream is){
    	List<String> errstrList = new ArrayList<String>();
    	String str = "";
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        // �õ�������
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // ��������Ӧ�ôӵڶ��п�ʼ,��һ��Ϊ��ͷ�ı���
        for (int i = 1; i <= rowNum; i++) {
        	row = sheet.getRow(i);
        	if(null==row){
        		continue;
        	}
        	StringBuilder errstr = new StringBuilder("��"+(i+1)+"��:");
        	String userinfoid_str = getCellFormatValue(row.getCell(0));
        	String examinfoid_str = getCellFormatValue(row.getCell(1));
        	String number_str = getCellFormatValue(row.getCell(3));
        	String theoryScore_str = getCellFormatValue(row.getCell(5));
        	
        	Integer userinfoid =null;
        	Integer examinfoid=null; 
        	if(userinfoid_str!=null&&!userinfoid_str.equals("")){
        		try{
        			userinfoid = (int) Float.parseFloat(userinfoid_str.trim());
        		}catch (Exception e) {
        			errstr.append("�û�ID����Ϊ����; ");
				}
        	}else{
        		errstr.append("�û�IDΪ�գ�");
        	}
        	if (examinfoid_str!=null&&!examinfoid_str.equals("")) {
        		try{
        			examinfoid = (int) Float.parseFloat(examinfoid_str.trim());
        		}catch (Exception e) {
        			errstr.append("����ID����Ϊ����; ");
				}
			}else{
				errstr.append("����IDΪ�գ�");
			}
        	if (number_str==null||"".equals(number_str)) {
				errstr.append("׼��֤��Ϊ�գ�");
			}   	
        	if (theoryScore_str==null||"".equals(theoryScore_str)) {
        		errstr.append("���۳ɼ�Ϊ�գ�");
        	}else{
        		try {
        		  	Float.parseFloat(theoryScore_str);
        		} catch (Exception e) {
        			errstr.append("���۳ɼ��������֣�");
        		}
        	}
        	
        	//һһ��Ӧƥ��
        	ExamInfoDao examinfo = new ExamInfoDaoImp();
        	if(userinfoid!=null&&examinfoid!=null&&number_str!=null&&!examinfo.checkCoordinate(userinfoid, examinfoid, number_str.trim())){
        		errstr.append("�û�ID������ID��׼��֤�Ų�ƥ�䣻");
        	}
        	if(!errstr.toString().equals("��"+(i+1)+"��:")){
        		errstrList.add(errstr.toString());
        	}
        }
        return errstrList;
    }
    
    
    public List<String> validateCertXls(InputStream is){
    	List<String> errstrList = new ArrayList<String>();
    	CertificateInfoDao certDao = new CertificateInfoDaoImp();
    	String str = "";
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        // �õ�������
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        String CertNumber,name,IdNumber,dateTime,company,certScore,certState;
        for (int i = 1; i <= rowNum; i++) {
        	row = sheet.getRow(i);
        	if(null==row){
        		continue;
        	}
        	StringBuilder errstr = new StringBuilder("��"+(i+1)+"��:");
        	
        	CertNumber = getCellFormatValue(row.getCell(0));
        	name = getCellFormatValue(row.getCell(1));
        	IdNumber = getCellFormatValue(row.getCell(2));
        	dateTime = getCellFormatValue(row.getCell(3));
        	company = getCellFormatValue(row.getCell(4));
//        	certScore = getCellFormatValue(row.getCell(5)).trim();
//        	certState = getCellFormatValue(row.getCell(6)).trim();
        	
        	boolean flag = StrNotNull(CertNumber)&&StrNotNull(name)&&StrNotNull(IdNumber)&&StrNotNull(dateTime)&&StrNotNull(company);//&&StrNotNull(certScore)&&StrNotNull(certState);
        	if(flag){
        		try {
					new SimpleDateFormat("yyyy-MM-dd").parse(dateTime);
				} catch (ParseException e) {
					errstr.append("��֤���ڱ�����[2010-01-01]��ʽ��");
				}
        		List<CertificateInfo> list = certDao.getCertInfoByCertNum(CertNumber);
        		if(list.size()>0){
        			errstr.append("֤�����ظ�");
        		}
        	}else{
        		errstr.append("�е�����Ϊ�գ�");
        	}
        	
        	
        	if(!errstr.toString().equals("��"+(i+1)+"��:")){
        		errstrList.add(errstr.toString());
        	}
        }
        return errstrList;
    }
    /**
     * ��֤Excel�����ɼ�
     * @param InputStream
     * @return List ������Ϣ
     */
    //TODO 2 ��֤   �û�id������id��׼��֤�ŵ�һһ��Ӧ
    
    public List<String> validateAllScoreXls(InputStream is){
    	
    	List<String> errstrList = new ArrayList<String>();
    	String str = "";
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        // �õ�������
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // ��������Ӧ�ôӵڶ��п�ʼ,��һ��Ϊ��ͷ�ı���
        for (int i = 1; i <= rowNum; i++) {
        	row = sheet.getRow(i);
        	if(null==row){
        		continue;
        	}
        	StringBuilder errstr = new StringBuilder("��"+(i+1)+"��:");
        	String userinfoid_str = getCellFormatValue(row.getCell(0));
        	String examinfoid_str = getCellFormatValue(row.getCell(1));
        	String number_str = getCellFormatValue(row.getCell(3));
        	String theoryScore_str = getCellFormatValue(row.getCell(5));
        	String coldMidScore_str = getCellFormatValue(row.getCell(6));
        	String coldTermScore_str = getCellFormatValue(row.getCell(7));
        	String hotMidScore_str = getCellFormatValue(row.getCell(8));
        	String hotTermScore_str = getCellFormatValue(row.getCell(9));
        	
        	Integer userinfoid =null;
        	Integer examinfoid=null; 
        	if(userinfoid_str!=null&&!userinfoid_str.equals("")){
        		try{
        			userinfoid = (int) Float.parseFloat(userinfoid_str.trim());
        		}catch (Exception e) {
        			errstr.append("�û�ID����Ϊ����; ");
				}
        	}else{
        		errstr.append("�û�IDΪ�գ�");
        	}
        	if (examinfoid_str!=null&&!examinfoid_str.equals("")) {
        		try{
        			examinfoid = (int) Float.parseFloat(examinfoid_str.trim());
        		}catch (Exception e) {
        			errstr.append("����ID����Ϊ����; ");
				}
			}else{
				errstr.append("����IDΪ�գ�");
			}
        	if (number_str==null||"".equals(number_str)) {
				errstr.append("׼��֤��Ϊ�գ�");
			}       
        	if(theoryScore_str!=null&&"".equals(theoryScore_str)){
        		errstr.append("���۳ɼ�Ϊ�գ�");
        	}else{
        		try{
        			Float.parseFloat(theoryScore_str);
        		}catch (Exception e) {
					errstr.append("���۳ɼ��������֣�");
				}
        	}
        	
        	//�ĸ��ɼ�Ϊ�ղ�������
        	String allScore_str=(coldMidScore_str+coldTermScore_str+hotMidScore_str+hotTermScore_str).trim();
        	if(allScore_str==null||allScore_str.equals("")){
        		errstr.append("û�в����ɼ�;");
        	}
        	
        	if (coldMidScore_str!=null&&!coldMidScore_str.equals("")) {
        		try {
        		  	Float.parseFloat(coldMidScore_str);
        		} catch (Exception e) {
        			errstr.append("����(��)�ɼ��������֣�");
        		}
        	}
        	if (coldTermScore_str!=null&&!coldTermScore_str.equals("")) {
        		try {
        		  	Float.parseFloat(coldTermScore_str);
        		} catch (Exception e) {
        			errstr.append("����(��)�ɼ��������֣�");
        		}
        	}
        	if (hotMidScore_str!=null&&!hotMidScore_str.equals("")) {
        		try {
        		  	Float.parseFloat(hotMidScore_str);
        		} catch (Exception e) {
        			errstr.append("����(��)�ɼ��������֣�");
        		}
        	}
        	if (hotTermScore_str!=null&&!hotTermScore_str.equals("")) {
        		try {
        		  	Float.parseFloat(hotTermScore_str);
        		} catch (Exception e) {
        			errstr.append("����(��)�ɼ��������֣�");
        		}
        	}
        	//һһ��Ӧƥ��
        	ExamInfoDao examinfo = new ExamInfoDaoImp();
        	if(userinfoid!=null&&examinfoid!=null&&number_str!=null&&!examinfo.checkCoordinate(userinfoid, examinfoid, number_str.trim())){
        		errstr.append("�û�ID������ID��׼��֤�Ų�ƥ�䣻");
        	}
        	if(!errstr.toString().equals("��"+(i+1)+"��:")){
        		errstrList.add(errstr.toString());
        	}
        }
        
        return errstrList;
    }
    
    
    public List<String> validateRecord(InputStream is){

    	List<String> errstrList = new ArrayList<String>();
    	String str = "";
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        // �õ�������
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        
        String name,sex,IdNumber,company,kind,certNumber,yyyyDate,yearCount,threeYearCount,passCount,failCount,deduction,comments;
        RecordOfYearDao ry = new RecordOfYearDaoImp();
        for (int i = 1; i <= rowNum; i++) {
        	row = sheet.getRow(i);
        	if(null==row){
        		continue;
        	}
        	StringBuilder errstr = new StringBuilder("��"+(i+1)+"��:");
        	
        	name = getCellFormatValue( row.getCell(1));
        	sex = getCellFormatValue( row.getCell(2));
        	IdNumber = getCellFormatValue( row.getCell(3));
        	company = getCellFormatValue( row.getCell(4));
        	kind = getCellFormatValue( row.getCell(5));
        	certNumber = getCellFormatValue( row.getCell(6));
        	yyyyDate = getCellFormatValue( row.getCell(7));
        	yearCount = getCellFormatValue( row.getCell(8));
        	threeYearCount = getCellFormatValue( row.getCell(9));
        	passCount = getCellFormatValue( row.getCell(10));
        	failCount = getCellFormatValue( row.getCell(11));
        	deduction = getCellFormatValue( row.getCell(12));
        	comments = getCellFormatValue( row.getCell(13));
        	
        	boolean flag=StrNotNull(name)&&StrNotNull(sex)&&StrNotNull(IdNumber)&&StrNotNull(company)&&StrNotNull(kind)&&StrNotNull(certNumber)&&StrNotNull(yyyyDate)&&StrNotNull(yearCount)&&StrNotNull(threeYearCount)&&StrNotNull(passCount)&&StrNotNull(failCount)&&StrNotNull(deduction)&&StrNotNull(comments);
        	if(flag){
        		if(!(sex.trim().equals("Ů")||sex.trim().equals("��"))){
        			errstr.append("�Ա��������/Ů��");
        		}
        		if(!(kind.trim().equals("����")||kind.trim().equals("����")||kind.trim().equals("����+����"))){
        			errstr.append("��ҵ��Χ����������/����/����+������");
        		}
        		if(yyyyDate.trim().length()!=4){
        			errstr.append("��ݱ�������λ��");
        		}
        		try{
        			Float.parseFloat(yyyyDate.trim());
        			Float.parseFloat(yearCount.trim());
        			Float.parseFloat(threeYearCount.trim());
        			Float.parseFloat(passCount.trim());
        			Float.parseFloat(failCount.trim());
        			Float.parseFloat(deduction.trim());
        		}catch(Exception e){
        			errstr.append("����д������ĸ");
        		}
        		
        	}else{
        		errstr.append("�е�����Ϊ��");
        	}   	
        	
        	if(!errstr.toString().equals("��"+(i+1)+"��:")){
        		errstrList.add(errstr.toString());
        	}
        }
        
        return errstrList;
    	
    }
    /**
     * ��ȡExcel��������
     * @param InputStream
     * @return List ������Ԫ���������ݵ�List����
     */
    public List<ExamInfo> readExcelScore(InputStream is) {
    	List<ExamInfo> content = new ArrayList<ExamInfo>();
    	String str = "";
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        // �õ�������
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // ��������Ӧ�ôӵڶ��п�ʼ,��һ��Ϊ��ͷ�ı���
        for (int i = 1; i <= rowNum; i++) {
        	row = sheet.getRow(i);
        	ExamInfo e = new ExamInfo();
        	UserInfo u = new UserInfo();
        	String userId_str = getCellFormatValue(row.getCell(0)).trim();
        	String examId_str = getCellFormatValue(row.getCell(1)).trim();
        	String theoryScore_str = getCellFormatValue(row.getCell(5)).trim();
        	String coldMidScore_str = getCellFormatValue(row.getCell(6)).trim();
        	String coldTemScore_str = getCellFormatValue(row.getCell(7)).trim();
        	String hotMidScore_str = getCellFormatValue(row.getCell(8)).trim();
        	String hotTemScore_str = getCellFormatValue(row.getCell(9)).trim();
        	
        	
        	Integer userInfoid=null; 
        	Integer examinfoid=null; 
        	Float theoryScore = null;
        	Float coldMidScore = null;
        	Float coldTemScore = null;
        	Float hotMidScore = null;
        	Float hotTemScore = null;
        	if (!userId_str.equals("")&&userId_str!=null) {
        		userInfoid = (int) Float.parseFloat(userId_str);
        		u.setUserInfoId(userInfoid);
        		e.setUserInfo(u);
			}
        	if (!examId_str.equals("")&&examId_str!=null) {
        		examinfoid = (int) Float.parseFloat(examId_str);
        		e.setExamInfoId(examinfoid);
			}
        	if (!theoryScore_str.equals("")&&theoryScore_str!=null) {
        		theoryScore =  Float.parseFloat(theoryScore_str);
        		e.setTheoryScore(theoryScore);
        	}else{
        		e.setTheoryScore(0f);
        	}
        	if (!coldMidScore_str.equals("")&&coldMidScore_str!=null) {
        		coldMidScore =  Float.parseFloat(coldMidScore_str);
        		e.setColdMidScore(coldMidScore);
        	}else{
        		e.setColdMidScore(0f);
        	}
        	if (!coldTemScore_str.equals("")&&coldTemScore_str!=null) {
        		coldTemScore =  Float.parseFloat(coldTemScore_str);
        		e.setColdTemScore(coldTemScore);
        	}else{
        		e.setColdTemScore(0f);
        	}
        	if (!hotMidScore_str.equals("")&&hotMidScore_str!=null) {
        		hotMidScore =  Float.parseFloat(hotMidScore_str);
        		e.setHotMidScore(hotMidScore);
        	}else{
        		e.setHotMidScore(0f);
        	}
        	if (!hotTemScore_str.equals("")&&hotTemScore_str!=null) {
        		hotTemScore =  Float.parseFloat(hotTemScore_str);
        		e.setHotTemScore(hotTemScore);
        	}else{
        		e.setHotTemScore(0f);
        	}
        	
        	content.add(e);
        }
    	
    	return content;
    }
    
    /**
     * ��ȡExcel��������
     * @param InputStream
     * @return List ������Ԫ���������ݵ�List����
     */
    public List<RecordOfYear> readExcelRecord(InputStream is) {
    	List<RecordOfYear> content = new ArrayList<RecordOfYear>();
    	String str = "";
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        // �õ�������
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // ��������Ӧ�ôӵڶ��п�ʼ,��һ��Ϊ��ͷ�ı���
        String sex_str;
        String kind_str;
        for (int i = 1; i <= rowNum; i++){
        	row = sheet.getRow(i);
        	RecordOfYear r = new RecordOfYear();
        	r.setName(getCellFormatValue(row.getCell(1)).trim());
        	sex_str=getCellFormatValue(row.getCell(2)).trim();
        	if(sex_str.equals("Ů")){
        		r.setSex(0);
        	}else if(sex_str.equals("��")){
        		r.setSex(1);
        	}else if(sex_str.length()>1){
        		r.setSex(2);
        	}
        	r.setIdNumber(getCellFormatValue(row.getCell(3)).trim());
        	r.setCompany(getCellFormatValue(row.getCell(4)).trim());
        	kind_str=getCellFormatValue(row.getCell(5)).trim();
        	if(sex_str.equals("����")){
        		r.setKind(0);
        	}else if(sex_str.equals("����")){
        		r.setKind(1);
        	}else if(sex_str.length()>2){
        		r.setKind(2);
        	}
        	r.setCertNumber(getCellFormatValue(row.getCell(6)).trim());
        	r.setYyyyDate(getCellFormatValue(row.getCell(7)).trim());
        	r.setYearCount((int)Float.parseFloat(getCellFormatValue(row.getCell(8)).trim()));
        	r.setThreeYearCount((int)Float.parseFloat(getCellFormatValue(row.getCell(9)).trim()));
        	r.setPassCount((int)Float.parseFloat(getCellFormatValue(row.getCell(10)).trim()));
        	r.setFailCount((int)Float.parseFloat(getCellFormatValue(row.getCell(11)).trim()));
        	r.setDeduction((int)Float.parseFloat(getCellFormatValue(row.getCell(12)).trim()));
        	r.setComments(getCellFormatValue(row.getCell(13)).trim());
        	
        	content.add(r);
        }
    	
    	return content;
    }
    
    public List<ExamInfo> readExcelScore_bak(InputStream is) {
    	List<ExamInfo> content = new ArrayList<ExamInfo>();
    	String str = "";
    	try {
    		fs = new POIFSFileSystem(is);
    		wb = new HSSFWorkbook(fs);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	sheet = wb.getSheetAt(0);
    	// �õ�������
    	int rowNum = sheet.getLastRowNum();
    	row = sheet.getRow(0);
    	int colNum = row.getPhysicalNumberOfCells();
    	// ��������Ӧ�ôӵڶ��п�ʼ,��һ��Ϊ��ͷ�ı���
    	for (int i = 1; i <= rowNum; i++) {
    		row = sheet.getRow(i);
    		ExamInfo e = new ExamInfo();
    		UserInfo u = new UserInfo();
    		String userId_str = getCellFormatValue(row.getCell(0));
    		String examId_str = getCellFormatValue(row.getCell(1));
//        	String theoryScore_str = getCellFormatValue(row.getCell(6)).trim();
    		String theoryScore_str = getCellFormatValue(row.getCell(7));
    		
    		
    		Integer userInfoid=null; 
    		Integer examinfoid=null; 
    		Float theoryScore = null;
    		if (!userId_str.equals("")&&userId_str!=null) {
    			userInfoid = (int) Float.parseFloat(userId_str.trim());
    		}
    		if (!examId_str.equals("")&&examId_str!=null) {
    			examinfoid = (int) Float.parseFloat(examId_str.trim());
    		}
//        	if (!theoryScore_str.equals("")&&theoryScore_str!=null) {
//        		theoryScore =  Float.parseFloat(theoryScore_str);
//			}
    		if (!theoryScore_str.equals("")&&theoryScore_str!=null) {
    			theoryScore =  Float.parseFloat(theoryScore_str.trim());
    		}
    		u.setUserInfoId(userInfoid);
    		e.setUserInfo(u);
    		e.setExamInfoId(examinfoid);
    		e.setTheoryScore(theoryScore);
    		content.add(e);
    	}
    	
    	return content;
    }
    
    //��ȡExam.xls���е�����
    public List<ExamInfo> readExcelExam(InputStream is) throws ParseException  {
    	List<ExamInfo> content = new ArrayList<ExamInfo>();
    	String str = "";
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        // �õ�������
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // ��������Ӧ�ôӵڶ��п�ʼ,��һ��Ϊ��ͷ�ı���
        for (int i = 1; i <= rowNum; i++) {
        	row = sheet.getRow(i);
        	ExamInfo e = new ExamInfo();
        	String examid_str = getCellFormatValue(row.getCell(1)).trim();
        	String theoryExamDate = getCellFormatValue(row.getCell(4));
        	String theoryExamRoom = getCellFormatValue(row.getCell(5));
        	String theoryExamPlace = getCellFormatValue(row.getCell(6));

        	
        	
        	Integer examinfoid=null; 
        	if (!examid_str.equals("")&&examid_str!=null) {
        		examinfoid= (int) Float.parseFloat(examid_str);
			}

        	e.setExamInfoId(examinfoid);
        	e.setTheoryExamDate(new ExamineeService().convertToTimestamp(theoryExamDate,"yyyy-MM-dd HH:mm:ss"));
        	e.setTheoryExamRoom(theoryExamRoom);
        	
        	e.setTheoryExamPlace(theoryExamPlace);
        	content.add(e);
        }
    	return content;
    }
    
    

    
    /**
     * ��ȡExcel��������
     * @param InputStream
     * @return List ������Ԫ���������ݵ�List����
     */
    public List<ExamInfo> readExcelNumber(InputStream is) {
    	List<ExamInfo> content = new ArrayList<ExamInfo>();
    	String str = "";
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        // �õ�������
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // ��������Ӧ�ôӵڶ��п�ʼ,��һ��Ϊ��ͷ�ı���
        for (int i = 1; i <= rowNum; i++) {
        	row = sheet.getRow(i);
        	ExamInfo e = new ExamInfo();
        	String examinfoid_str = getCellFormatValue(row.getCell(1)).trim();
        	String number_str = getCellFormatValue(row.getCell(6)).trim();
        	
        	
        	Integer examinfoid=null; 
        	String  number = null;
        	if (!examinfoid_str.equals("")&&examinfoid_str!=null) {
        		examinfoid = (int) Float.parseFloat(examinfoid_str);
			}
        	if (!number_str.equals("")&&number_str!=null) {
        		number = number_str;
			}
        	e.setExamInfoId(examinfoid);
        	e.setNumber(number);
        	content.add(e);
        }
    	
    	return content;
    }
    
    
    /**
     * ��ȡExcel��������
     * @param InputStream
     * @return List ������Ԫ���������ݵ�List����
     * @throws ParseException 
     */
    public List<CertInfoExcel> readCertExcelContent(InputStream is) throws ParseException {
    	List<CertInfoExcel> content = new ArrayList<CertInfoExcel>();
    	ExamineeService examerService = new ExamineeService();
    	String str = "";
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        // �õ�������
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // ��������Ӧ�ôӵڶ��п�ʼ,��һ��Ϊ��ͷ�ı���
        String certificationNum = "";
        String realName = "";
        String idnum = "";
        Timestamp certificationGrantDate = null;
        String company = "";
        Integer remainingScore=0;
        for (int i = 1; i <= rowNum; i++) {
        	row = sheet.getRow(i);
        	if(null!=getCellFormatValue(row.getCell(0)).trim()&&!"".equals(getCellFormatValue(row.getCell(0)).trim())){
        		certificationNum = getCellFormatValue(row.getCell(0)).trim();
        	}
        	if(null!=getCellFormatValue(row.getCell(1)).trim()&&!"".equals(getCellFormatValue(row.getCell(1)).trim())){
        		realName = getCellFormatValue(row.getCell(1)).trim();
        	}
        	if(null!=getCellFormatValue(row.getCell(2)).trim()&&!"".equals(getCellFormatValue(row.getCell(2)).trim())){
        		idnum = getCellFormatValue(row.getCell(2)).trim();
        	}
        	if(null!=getCellFormatValue(row.getCell(3)).trim()&&!"".equals(getCellFormatValue(row.getCell(3)).trim())){
        		String date = getCellFormatValue(row.getCell(3)).trim();
        		certificationGrantDate = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime());
        	}else{
        		Date date = new Date();
        		certificationGrantDate = new Timestamp(date.getTime());
        	}
        	if(null!=getCellFormatValue(row.getCell(4)).trim()&&!"".equals(getCellFormatValue(row.getCell(4)).trim())){
        		company = getCellFormatValue(row.getCell(4)).trim();
        	}
        	
//        	if(null!=getCellFormatValue(row.getCell(5)).trim()&&!"".equals(getCellFormatValue(row.getCell(5)).trim())){
//        		String scoreTemp = getCellFormatValue(row.getCell(5)).trim();
//        		remainingScore =Integer.parseInt((scoreTemp.indexOf(".")>=0)?scoreTemp.substring(0,scoreTemp.indexOf(".")):scoreTemp);
//        	}else{
//        		remainingScore = 12;
//        	}
        	
        	CertInfoExcel certExcel = new CertInfoExcel();
        	certExcel.setCertificationGrantDate(certificationGrantDate);
        	certExcel.setCertificationNum(certificationNum);
        	certExcel.setCompany(company);
        	certExcel.setIdnum(idnum);
        	certExcel.setRealName(realName);
//        	certExcel.setRemainingScore(remainingScore);
        	content.add(certExcel);
        }
    	
    	return content;
    }
    
    
    /**
     * ��ȡExcel��������
     * @param InputStream
     * @return Map ������Ԫ���������ݵ�Map����
     */
    public Map<Integer, String> readExcelContent(InputStream is) {
        Map<Integer, String> content = new HashMap<Integer, String>();
        String str = "";
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        // �õ�������
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // ��������Ӧ�ôӵڶ��п�ʼ,��һ��Ϊ��ͷ�ı���
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            while (j < colNum) {
                // ÿ����Ԫ�������������"-"�ָ���Ժ���Ҫʱ��String���replace()������ԭ����
                // Ҳ���Խ�ÿ����Ԫ����������õ�һ��javabean�������У���ʱ��Ҫ�½�һ��javabean
                // str += getStringCellValue(row.getCell((short) j)).trim() +
                // "-";
                str += getCellFormatValue(row.getCell((short) j)).trim() + "    ";
                j++;
            }
            content.put(i, str);
            str = "";
        }
        return content;
    }

    /**
     * ��ȡ��Ԫ����������Ϊ�ַ������͵�����
     * 
     * @param cell Excel��Ԫ��
     * @return String ��Ԫ����������
     */
    private String getStringCellValue(HSSFCell cell) {
        String strCell = "";
        switch (cell.getCellType()) {
        case HSSFCell.CELL_TYPE_STRING:
            strCell = cell.getStringCellValue();
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            strCell = String.valueOf(cell.getNumericCellValue());
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            strCell = String.valueOf(cell.getBooleanCellValue());
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            strCell = "";
            break;
        default:
            strCell = "";
            break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        if (cell == null) {
            return "";
        }
        return strCell;
    }

    /**
     * ��ȡ��Ԫ����������Ϊ�������͵�����
     * 
     * @param cell
     *            Excel��Ԫ��
     * @return String ��Ԫ����������
     */
    private String getDateCellValue(HSSFCell cell) {
        String result = "";
        try {
            int cellType = cell.getCellType();
            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
                Date date = cell.getDateCellValue();
                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
                        + "-" + date.getDate();
            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
                String date = getStringCellValue(cell);
                result = date.replaceAll("[����]", "-").replace("��", "").trim();
            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
                result = "";
            }
        } catch (Exception e) {
            System.out.println("���ڸ�ʽ����ȷ!");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * ����HSSFCell������������
     * @param cell
     * @return
     */
    private String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // �жϵ�ǰCell��Type
            switch (cell.getCellType()) {
            // �����ǰCell��TypeΪNUMERIC
            case HSSFCell.CELL_TYPE_NUMERIC:
            case HSSFCell.CELL_TYPE_FORMULA: {
                // �жϵ�ǰ��cell�Ƿ�ΪDate
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // �����Date������ת��ΪData��ʽ
                    
                    //����1�������ӵ�data��ʽ�Ǵ�ʱ����ģ�2011-10-12 0:00:00
                    //cellvalue = cell.getDateCellValue().toLocaleString();
                    
                    //����2�������ӵ�data��ʽ�ǲ�����ʱ����ģ�2011-10-12
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellvalue = sdf.format(date);
                    
                }
                // ����Ǵ�����
                else {
                    // ȡ�õ�ǰCell����ֵ
                    cellvalue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            }
            // �����ǰCell��TypeΪSTRIN
            case HSSFCell.CELL_TYPE_STRING:
                // ȡ�õ�ǰ��Cell�ַ���
                cellvalue = cell.getRichStringCellValue().getString();
                break;
            // Ĭ�ϵ�Cellֵ
            default:
                cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }

    public static boolean StrNotNull(String str){
    	if(str!=null&&!"".equals(str)){
    		return true;
    	}else{
    		return false;
    	}
    }
    public static void main(String[] args) {
    	Date date1 = null;
    	try {
			 date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2010-10-1;");
		} catch (ParseException e) {
			
			System.out.println("����");
		}
       /* try {
            // �Զ�ȡExcel���������
            InputStream is = new FileInputStream("d:\\test2.xls");
            ExcelRead excelReader = new ExcelRead();
            String[] title = excelReader.readExcelTitle(is);
            System.out.println("���Excel���ı���:");
            for (String s : title) {
                System.out.print(s + " ");
            }

            // �Զ�ȡExcel������ݲ���
            InputStream is2 = new FileInputStream("d:\\test2.xls");
            Map<Integer, String> map = excelReader.readExcelContent(is2);
            System.out.println("���Excel��������:");
            for (int i = 1; i <= map.size(); i++) {
                System.out.println(map.get(i));
            }

        } catch (FileNotFoundException e) {
            System.out.println("δ�ҵ�ָ��·�����ļ�!");
            e.printStackTrace();
        }*/
    }
    
 
}