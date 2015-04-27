package com.csdl.cabexam.util;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;

import com.csdl.cabexam.beans.CertificateInfo;
import com.csdl.cabexam.beans.ExamInfo;
import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.RecordOfYear;
import com.csdl.cabexam.beans.ScoreExcel;
import com.csdl.cabexam.dao.CertificateInfoDao;
import com.csdl.cabexam.dao.ExamInfoDao;
import com.csdl.cabexam.dao.ExamineeInfoDao;
import com.csdl.cabexam.dao.MutiTableDao;
import com.csdl.cabexam.dao.imp.CertificateInfoDaoImp;
import com.csdl.cabexam.dao.imp.ExamInfoDaoImp;
import com.csdl.cabexam.dao.imp.ExamineeInfoDaoImp;
import com.csdl.cabexam.dao.imp.MutiTableDaoImp;
import com.csdl.cabexam.dao.imp.RecordOfYearDaoImp;


public class GenerateXls {
	//��óɼ�����ļ�
	//*url: ���ص�����·
	/*public GenerateXls(){
		
	}*/
	public void getTheoryScoreXlsTable(String url){
		  // ����Excel�Ĺ������ Workbook,��Ӧ��һ��excel�ĵ�
	    HSSFWorkbook wb = new HSSFWorkbook();

	    // ����Excel�Ĺ���sheet,��Ӧ��һ��excel�ĵ���tab
	    HSSFSheet sheet = wb.createSheet("���۳ɼ�����");

	    // ����excelÿ�п��
	    //sheet.autoSizeColumn(0);   //����Զ�
	    sheet.setColumnWidth(0, 2000);
	    sheet.setColumnWidth(1, 2000);
	    sheet.setColumnWidth(2, 3000);
	    sheet.setColumnWidth(3, 8000);
	    sheet.setColumnWidth(4, 8000);
	    sheet.setColumnWidth(5, 3000);
	    sheet.setColumnWidth(6, 3000);
	    sheet.setColumnWidth(7, 3000);
	    sheet.setColumnWidth(8, 3000);
	    sheet.setColumnWidth(9, 3000);

	    // ����������ʽ
	    HSSFFont font = wb.createFont();
	    font.setFontName("Verdana");
	    font.setBoldweight((short) 90);
	    font.setFontHeight((short) 210);
	    font.setColor(HSSFColor.BLACK.index);
        

	    
	    //TODO   ������Ԫ����ʽ   ���ó��ı���ʽ
	    HSSFCellStyle style = wb.createCellStyle();
	    
	    HSSFDataFormat format = wb.createDataFormat();
	    style.setDataFormat(format.getFormat("@"));                //���ó��ı���ʽ
	    
	    style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //�м����
	    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	    style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

	    // ���ñ߿�
	    style.setBottomBorderColor(HSSFColor.BLACK.index);
	    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style.setBorderTop(HSSFCellStyle.BORDER_THIN);

	    style.setFont(font);
	    style.setWrapText(true);

	    
	    
	    
	    
	    // ����Excel��sheetһ��    ��***********����������
	    HSSFRow row = sheet.createRow(0);
	    row.setHeight((short) 500);// �趨�еĸ߶�
	    // ����һ��Excel�ĵ�Ԫ��
	    HSSFCell cell = row.createCell(0);
	    cell.setCellStyle(style);
	    cell.setCellValue("�û�ID");
	    
	    cell = row.createCell(1);
	    cell.setCellStyle(style);
	    cell.setCellValue("����ID");
	    
	    cell = row.createCell(2);
	    cell.setCellStyle(style);
	    cell.setCellValue("����");
	    
	    cell = row.createCell(3);
	    cell.setCellStyle(style);
	    cell.setCellValue("׼��֤��");
	    
	    cell = row.createCell(4);
	    cell.setCellStyle(style);
	    cell.setCellValue("��˾");
	    
	    cell = row.createCell(5);
	    cell.setCellStyle(style);
	    cell.setCellValue("���۳ɼ�");
  
	    // ����Excel��sheetһ��    ��***********����������

	    //@
	    MutiTableDao md=new MutiTableDaoImp();
	    List<ScoreExcel> list=md.getTheoryScoreXlsInfo();
		for(int i=0;i<list.size();i++){
			ScoreExcel se=list.get(i);
	    	row = sheet.createRow(i+1);
		    row.setHeight((short) 500);// �趨�еĸ߶�
		    // ����һ��Excel�ĵ�Ԫ��
		    cell = row.createCell(0);
		    cell.setCellStyle(style);
		    if(se.getUserInfoId()!=null&&!se.getUserInfoId().equals("")){
		    	cell.setCellValue(se.getUserInfoId()); 	
		    }
		    cell = row.createCell(1);
		    cell.setCellStyle(style);
		    if(se.getExamInfoId()!=null&&!se.getExamInfoId().equals("")){
			    cell.setCellValue(se.getExamInfoId());
		    }
		    
		    cell = row.createCell(2);
		    cell.setCellStyle(style);
		    if(se.getRealName()!=null&&!se.getRealName().equals("")){
			    cell.setCellValue(se.getRealName());
		    }
		    //׼��֤��
		    cell = row.createCell(3);
		    cell.setCellStyle(style);
		    if(se.getNumber()!=null&&!se.getNumber().equals("")){
			    cell.setCellValue(se.getNumber());
		    }
		    
		    cell = row.createCell(4);
		    cell.setCellStyle(style);
		    if(se.getCompany()!=null&&!se.getCompany().equals("")){
			    cell.setCellValue(se.getCompany());
		    }
		    
		    cell = row.createCell(5);
		    cell.setCellStyle(style);
		    if(se.getTheoryScore()!=null&&!se.getTheoryScore().equals("")){
			    cell.setCellValue(se.getTheoryScore());
		    }
	    }
 
	    // �ϲ���Ԫ��(startRow��endRow��startColumn��endColumn)
	    /*    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));*/
	    
	    try {
	    	FileOutputStream os = new FileOutputStream(url);
	 	    wb.write(os);
	 	    os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	public void getAllScoreXlsTable(String url){
		  // ����Excel�Ĺ������ Workbook,��Ӧ��һ��excel�ĵ�
	    HSSFWorkbook wb = new HSSFWorkbook();

	    // ����Excel�Ĺ���sheet,��Ӧ��һ��excel�ĵ���tab
	    HSSFSheet sheet = wb.createSheet("�ɼ�����");

	    
	    
	    // ����excelÿ�п��
	    //sheet.autoSizeColumn(0);   //����Զ�
	    sheet.setColumnWidth(0, 2000);
	    sheet.setColumnWidth(1, 2000);
	    sheet.setColumnWidth(2, 3000);
	    sheet.setColumnWidth(3, 8000);
	    sheet.setColumnWidth(4, 8000);
	    sheet.setColumnWidth(5, 3000);
	    sheet.setColumnWidth(6, 3000);
	    sheet.setColumnWidth(7, 3000);
	    sheet.setColumnWidth(8, 3000);
	    sheet.setColumnWidth(9, 3000);
	    // ����������ʽ
	    HSSFFont font = wb.createFont();
	    font.setFontName("Verdana");
	    font.setBoldweight((short) 90);
	    font.setFontHeight((short) 210);
	    font.setColor(HSSFColor.BLACK.index);
      

	    
	    // ������Ԫ����ʽ
	    HSSFCellStyle style = wb.createCellStyle();
	    HSSFDataFormat format = wb.createDataFormat();
	    style.setDataFormat(format.getFormat("@"));                //���ó��ı���ʽ
	    style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //�м����
	    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	    style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

	    // ���ñ߿�
	    style.setBottomBorderColor(HSSFColor.BLACK.index);
	    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style.setBorderTop(HSSFCellStyle.BORDER_THIN);

	    style.setFont(font);
	    style.setWrapText(true);

	    
	    
	    
	    
	    // ����Excel��sheetһ��    ��***********����������
	    HSSFRow row = sheet.createRow(0);
	    row.setHeight((short) 500);// �趨�еĸ߶�
	    // ����һ��Excel�ĵ�Ԫ��
	    HSSFCell cell = row.createCell(0);
	    cell.setCellStyle(style);
	    cell.setCellValue("�û�ID");
	    
	    cell = row.createCell(1);
	    cell.setCellStyle(style);
	    cell.setCellValue("����ID");
	    
	    cell = row.createCell(2);
	    cell.setCellStyle(style);
	    cell.setCellValue("����");
	    
	    cell = row.createCell(3);
	    cell.setCellStyle(style);
	    cell.setCellValue("׼��֤��");
	    
	    cell = row.createCell(4);
	    cell.setCellStyle(style);
	    cell.setCellValue("��˾");
	    
	    cell = row.createCell(5);
	    cell.setCellStyle(style);
	    cell.setCellValue("����");
	    
	    cell = row.createCell(6);
	    cell.setCellStyle(style);
	    cell.setCellValue("����(��)");
	    
	    cell = row.createCell(7);
	    cell.setCellStyle(style);
	    cell.setCellValue("����(��)");
	    
	    cell = row.createCell(8);
	    cell.setCellStyle(style);
	    cell.setCellValue("����(��)");
	    
	    cell = row.createCell(9);
	    cell.setCellStyle(style);
	    cell.setCellValue("����(��)");
	    
	    
	    // ����Excel��sheetһ��    ��***********����������

	    //@
	    MutiTableDao md=new MutiTableDaoImp();
	    List<ScoreExcel> list=md.getAllScoreXlsInfo();
		for(int i=0;i<list.size();i++){
			ScoreExcel se=list.get(i);
	    	row = sheet.createRow(i+1);
		    row.setHeight((short) 500);// �趨�еĸ߶�
		    // ����һ��Excel�ĵ�Ԫ��
		    cell = row.createCell(0);
		    cell.setCellStyle(style);
		    if(se.getUserInfoId()!=null&&!se.getUserInfoId().equals("")){
		    	cell.setCellValue(se.getUserInfoId()); 	
		    }
		    cell = row.createCell(1);
		    cell.setCellStyle(style);
		    if(se.getExamInfoId()!=null&&!se.getExamInfoId().equals("")){
			    cell.setCellValue(se.getExamInfoId());
		    }
		    
		    cell = row.createCell(2);
		    cell.setCellStyle(style);
		    if(se.getRealName()!=null&&!se.getRealName().equals("")){
			    cell.setCellValue(se.getRealName());
		    }
		    //׼��֤��
		    cell = row.createCell(3);
		    cell.setCellStyle(style);
		    if(se.getNumber()!=null&&!se.getNumber().equals("")){
			    cell.setCellValue(se.getNumber());
		    }
		    
		    
		    cell = row.createCell(4);
		    cell.setCellStyle(style);
		    if(se.getCompany()!=null&&!se.getCompany().equals("")){
			    cell.setCellValue(se.getCompany());
		    }
		    
		    cell = row.createCell(5);
		    cell.setCellStyle(style);
		    if(se.getTheoryScore()!=null&&!se.getTheoryScore().equals("")){
			    cell.setCellValue(se.getTheoryScore());
		    }
		    cell = row.createCell(6);
		    cell.setCellStyle(style);
		    if(se.getColdMidScore()!=null&&!se.getColdMidScore().equals("")){
		    	cell.setCellValue(se.getColdMidScore());
		    }
		    cell = row.createCell(7);
		    cell.setCellStyle(style);
		    if(se.getColdTemScore()!=null&&!se.getColdTemScore().equals("")){
		    	cell.setCellValue(se.getColdTemScore());
		    }
		    cell = row.createCell(8);
		    cell.setCellStyle(style);
		    if(se.getHotMidScore()!=null&&!se.getHotMidScore().equals("")){
		    	cell.setCellValue(se.getHotMidScore());
		    }
		    cell = row.createCell(9);
		    cell.setCellStyle(style);
		    if(se.getHotTemScore()!=null&&!se.getHotTemScore().equals("")){
		    	cell.setCellValue(se.getHotTemScore());
		    }
		    
	    }

	    // �ϲ���Ԫ��(startRow��endRow��startColumn��endColumn)
	    /*    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));*/
	    
	    try {
	    	FileOutputStream os = new FileOutputStream(url);
	 	    wb.write(os);
	 	    os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//������۱�
	public void getRecordXlsTable(String url,String idNumber,String yyyyDate){
		// ����Excel�Ĺ������ Workbook,��Ӧ��һ��excel�ĵ�
		HSSFWorkbook wb = new HSSFWorkbook();
		
		// ����Excel�Ĺ���sheet,��Ӧ��һ��excel�ĵ���tab
		HSSFSheet sheet = wb.createSheet("�������");
		
		sheet.setColumnWidth(0, 2000);  //���
		sheet.setColumnWidth(1, 2000);  //����
		sheet.setColumnWidth(2, 3000);  //�Ա�
		sheet.setColumnWidth(3, 8000);  //���֤����
		sheet.setColumnWidth(4, 8000);  //������λ
		sheet.setColumnWidth(5, 3000);  //��ҵ��Χ
		sheet.setColumnWidth(6, 3000);  //֤����
		sheet.setColumnWidth(7, 3000);  //2014��
		sheet.setColumnWidth(8, 3000);  //��������ͷ������
		sheet.setColumnWidth(9, 3000);  //�������ۼ�����
		sheet.setColumnWidth(10, 3000);  //�ϸ�����
		sheet.setColumnWidth(11, 3000);  //���ϸ�����
		sheet.setColumnWidth(12, 3000);  //�۷�
		sheet.setColumnWidth(13, 3000);  //��ע
		// ����������ʽ
		HSSFFont font = wb.createFont();
		font.setFontName("Verdana");
		font.setBoldweight((short) 90);
		font.setFontHeight((short) 210);
		font.setColor(HSSFColor.BLACK.index);
		
		// ������Ԫ����ʽ
		HSSFCellStyle style = wb.createCellStyle();
		HSSFDataFormat format = wb.createDataFormat();
		style.setDataFormat(format.getFormat("@"));                //���ó��ı���ʽ
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //�м����
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		// ���ñ߿�
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		
		style.setFont(font);
		style.setWrapText(true);
		
		// ����Excel��sheetһ��    ��***********����������
		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) 500);// �趨�еĸ߶�
		// ����һ��Excel�ĵ�Ԫ��
		HSSFCell cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("���");
		
		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("����");
		
		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("�Ա�");
		
		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("���֤����");
		
		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("������λ");
		
		//��ҵ��Χ
		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("��ҵ��Χ");
		
		//֤����
		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("֤����");
		
		//2014��
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("���");
		
		//��������ͷ������
		cell = row.createCell(8);
		cell.setCellStyle(style);
		cell.setCellValue("��������ͷ������");
		
		//�������ۼ�����
		cell = row.createCell(9);
		cell.setCellStyle(style);
		cell.setCellValue("�������ۼ�����");
		
		//�ϸ�����
		cell = row.createCell(10);
		cell.setCellStyle(style);
		cell.setCellValue("�ϸ�����");
		
		//���ϸ�����
		cell = row.createCell(11);
		cell.setCellStyle(style);
		cell.setCellValue("���ϸ�����");
		
		//�۷�
		cell = row.createCell(12);
		cell.setCellStyle(style);
		cell.setCellValue("�۷�");
		
		//��ע
		cell = row.createCell(13);
		cell.setCellStyle(style);
		cell.setCellValue("��ע");
		
		// ����Excel��sheetһ��    ��***********����������
		
		//@
		List<RecordOfYear> list = new RecordOfYearDaoImp().getRecordByIdYear(idNumber, yyyyDate);
		for(int i=0;i<list.size();i++){
			RecordOfYear ry=list.get(i);
			row = sheet.createRow(i+1);
			row.setHeight((short) 500);// �趨�еĸ߶�
			// ����һ��Excel�ĵ�Ԫ��
			
			//���
			cell = row.createCell(0);
			cell.setCellStyle(style);	
			cell.setCellValue(i+1); 	
			//����
			cell = row.createCell(1);
			cell.setCellStyle(style);
			if(ry.getName()!=null&&!ry.getName().equals("")){
				cell.setCellValue(ry.getName());
			}
			//�Ա�
			cell = row.createCell(2);
			cell.setCellStyle(style);
			switch(ry.getSex()){
				case 0: cell.setCellValue("Ů"); break;
				case 1: cell.setCellValue("��"); break;
				case 2:cell.setCellValue("����"); break;
				default:cell.setCellValue(""); 
			}
			//���֤����
			cell = row.createCell(3);
			cell.setCellStyle(style);
			if(ry.getIdNumber()!=null&&!ry.getIdNumber().equals("")){
				cell.setCellValue(ry.getIdNumber());
			}
			//������λ
			cell = row.createCell(4);
			cell.setCellStyle(style);
			if(ry.getCompany()!=null&&!ry.getCompany().equals("")){
				cell.setCellValue(ry.getCompany());
			}
			//��ҵ��Χ
			cell = row.createCell(5);
			cell.setCellStyle(style);
			switch(ry.getSex()){
				case 0: cell.setCellValue("����"); break;
				case 1: cell.setCellValue("����"); break;
				case 2: cell.setCellValue("����+����"); break;
				default:cell.setCellValue(""); 
			}
			//֤����
			cell = row.createCell(6);
			cell.setCellStyle(style);
			if(ry.getCertNumber()!=null&&!ry.getCertNumber().equals("")){
				cell.setCellValue(ry.getCertNumber());
			}
			//2014��
			cell = row.createCell(7);
			cell.setCellStyle(style);
			if(ry.getYyyyDate()!=null&&!ry.getYyyyDate().equals("")){
				cell.setCellValue(ry.getYyyyDate());
			}
			//��������ͷ������
			cell = row.createCell(8);
			cell.setCellStyle(style);
			if(ry.getYearCount()!=null&&!ry.getYearCount().equals("")){
				cell.setCellValue(ry.getYearCount());
			}
			//�������ۼ�����
			cell = row.createCell(9);
			cell.setCellStyle(style);
			if(ry.getThreeYearCount()!=null&&!ry.getThreeYearCount().equals("")){
				cell.setCellValue(ry.getThreeYearCount());
			}
			//�ϸ�����
			cell = row.createCell(10);
			cell.setCellStyle(style);
			if(ry.getPassCount()!=null&&!ry.getPassCount().equals("")){
				cell.setCellValue(ry.getPassCount());
			}
			//���ϸ�����
			cell = row.createCell(11);
			cell.setCellStyle(style);
			if(ry.getFailCount()!=null&&!ry.getFailCount().equals("")){
				cell.setCellValue(ry.getFailCount());
			}
			//�۷�
			cell = row.createCell(12);
			cell.setCellStyle(style);
			if(ry.getDeduction()!=null&&!ry.getDeduction().equals("")){
				cell.setCellValue(ry.getDeduction());
			}
			//��ע
			cell = row.createCell(13);
			cell.setCellStyle(style);
			if(ry.getComments()!=null&&!ry.getComments().equals("")){
				cell.setCellValue(ry.getComments());
			}
		}
		
		// �ϲ���Ԫ��(startRow��endRow��startColumn��endColumn)
		/*    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));*/
		
		try {
			FileOutputStream os = new FileOutputStream(url);
			wb.write(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	

	public void getScoreXlsTable(String url){
		  // ����Excel�Ĺ������ Workbook,��Ӧ��һ��excel�ĵ�
	    HSSFWorkbook wb = new HSSFWorkbook();

	    // ����Excel�Ĺ���sheet,��Ӧ��һ��excel�ĵ���tab
	    HSSFSheet sheet = wb.createSheet("����ɼ�");

	    // ����excelÿ�п��
	    //sheet.autoSizeColumn(0);   //����Զ�
	    sheet.setColumnWidth(0, 2000);
	    sheet.setColumnWidth(1, 2000);
	    sheet.setColumnWidth(2, 3000);
	    sheet.setColumnWidth(3, 8000);
	    sheet.setColumnWidth(4, 8000);
	    sheet.setColumnWidth(5, 3000);
	    sheet.setColumnWidth(6, 3000);
	    sheet.setColumnWidth(7, 3000);
	    sheet.setColumnWidth(8, 3000);
	    sheet.setColumnWidth(9, 3000);
	    // ����������ʽ
	    HSSFFont font = wb.createFont();
	    font.setFontName("Verdana");
	    font.setBoldweight((short) 90);
	    font.setFontHeight((short) 210);
	    font.setColor(HSSFColor.BLACK.index);
      

	    
	    // ������Ԫ����ʽ
	    HSSFCellStyle style = wb.createCellStyle();
	    HSSFDataFormat format = wb.createDataFormat();
	    style.setDataFormat(format.getFormat("@"));                //���ó��ı���ʽ
	    style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //�м����
	    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	    style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

	    // ���ñ߿�
	    style.setBottomBorderColor(HSSFColor.BLACK.index);
	    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style.setBorderTop(HSSFCellStyle.BORDER_THIN);

	    style.setFont(font);
	    style.setWrapText(true);

	    
	    
	    
	    
	    // ����Excel��sheetһ��    ��***********����������
	    HSSFRow row = sheet.createRow(0);
	    row.setHeight((short) 500);// �趨�еĸ߶�
	    // ����һ��Excel�ĵ�Ԫ��
	    HSSFCell cell = row.createCell(0);
	    cell.setCellStyle(style);
	    cell.setCellValue("�û�ID");
	    
	    cell = row.createCell(1);
	    cell.setCellStyle(style);
	    cell.setCellValue("����ID");
	    
	    cell = row.createCell(2);
	    cell.setCellStyle(style);
	    cell.setCellValue("����");
	    
	    cell = row.createCell(3);
	    cell.setCellStyle(style);
	    cell.setCellValue("׼��֤��");
	    
	    cell = row.createCell(4);
	    cell.setCellStyle(style);
	    cell.setCellValue("��˾");
	    
	    cell = row.createCell(5);
	    cell.setCellStyle(style);
	    cell.setCellValue("����");
	    
	    cell = row.createCell(6);
	    cell.setCellStyle(style);
	    cell.setCellValue("����(��)");
	    
	    cell = row.createCell(7);
	    cell.setCellStyle(style);
	    cell.setCellValue("����(��)");
	    
	    cell = row.createCell(8);
	    cell.setCellStyle(style);
	    cell.setCellValue("����(��)");
	    
	    cell = row.createCell(9);
	    cell.setCellStyle(style);
	    cell.setCellValue("����(��)");
	    
	    
	    // ����Excel��sheetһ��    ��***********����������

	    //@
	    MutiTableDao md=new MutiTableDaoImp();
	    List<ScoreExcel> list=md.getScoreXlsInfo();
		for(int i=0;i<list.size();i++){
			ScoreExcel se=list.get(i);
	    	row = sheet.createRow(i+1);
		    row.setHeight((short) 500);// �趨�еĸ߶�
		    // ����һ��Excel�ĵ�Ԫ��
		    cell = row.createCell(0);
		    cell.setCellStyle(style);
		    if(se.getUserInfoId()!=null&&!se.getUserInfoId().equals("")){
		    	cell.setCellValue(se.getUserInfoId()); 	
		    }
		    cell = row.createCell(1);
		    cell.setCellStyle(style);
		    if(se.getExamInfoId()!=null&&!se.getExamInfoId().equals("")){
			    cell.setCellValue(se.getExamInfoId());
		    }
		    
		    cell = row.createCell(2);
		    cell.setCellStyle(style);
		    if(se.getRealName()!=null&&!se.getRealName().equals("")){
			    cell.setCellValue(se.getRealName());
		    }
		    //׼��֤��
		    cell = row.createCell(3);
		    cell.setCellStyle(style);
		    if(se.getNumber()!=null&&!se.getNumber().equals("")){
			    cell.setCellValue(se.getNumber());
		    }
		    
		    
		    cell = row.createCell(4);
		    cell.setCellStyle(style);
		    if(se.getCompany()!=null&&!se.getCompany().equals("")){
			    cell.setCellValue(se.getCompany());
		    }
		    
		    cell = row.createCell(5);
		    cell.setCellStyle(style);
		    if(se.getTheoryScore()!=null&&!se.getTheoryScore().equals("")){
			    cell.setCellValue(se.getTheoryScore());
		    }
		    cell = row.createCell(6);
		    cell.setCellStyle(style);
		    if(se.getColdMidScore()!=null&&!se.getColdMidScore().equals("")){
		    	cell.setCellValue(se.getColdMidScore());
		    }
		    cell = row.createCell(7);
		    cell.setCellStyle(style);
		    if(se.getColdTemScore()!=null&&!se.getColdTemScore().equals("")){
		    	cell.setCellValue(se.getColdTemScore());
		    }
		    cell = row.createCell(8);
		    cell.setCellStyle(style);
		    if(se.getHotMidScore()!=null&&!se.getHotMidScore().equals("")){
		    	cell.setCellValue(se.getHotMidScore());
		    }
		    cell = row.createCell(9);
		    cell.setCellStyle(style);
		    if(se.getHotTemScore()!=null&&!se.getHotTemScore().equals("")){
		    	cell.setCellValue(se.getHotTemScore());
		    }
		    
	    }

	    // �ϲ���Ԫ��(startRow��endRow��startColumn��endColumn)
	    /*    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));*/
	    
	    try {
	    	FileOutputStream os = new FileOutputStream(url);
	 	    wb.write(os);
	 	    os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
	//��óɼ�����ļ�
	//*url: ���ص�����·
	/*public GenerateXls(){
		
	}*/
	public void getNumberXlsTable(String url){
		  // ����Excel�Ĺ������ Workbook,��Ӧ��һ��excel�ĵ�
	    HSSFWorkbook wb = new HSSFWorkbook();

	    // ����Excel�Ĺ���sheet,��Ӧ��һ��excel�ĵ���tab
	    HSSFSheet sheet = wb.createSheet("׼��֤�ŵ���");

	    // ����excelÿ�п��
	    //sheet.autoSizeColumn(0);   //����Զ�
	    sheet.setColumnWidth(0, 2000);
	    sheet.setColumnWidth(1, 2000);
	    sheet.setColumnWidth(2, 3000);
	    sheet.setColumnWidth(3, 8000);
	    sheet.setColumnWidth(4, 10000);
	    sheet.setColumnWidth(5, 10000);
	    sheet.setColumnWidth(6, 10000);

	    // ����������ʽ
	    HSSFFont font = wb.createFont();
	    font.setFontName("Verdana");
	    font.setBoldweight((short) 90);
	    font.setFontHeight((short) 210);
	    font.setColor(HSSFColor.BLACK.index);
        

	    
	    // ������Ԫ����ʽ
	    HSSFCellStyle style = wb.createCellStyle();
	    HSSFDataFormat format = wb.createDataFormat();
	    style.setDataFormat(format.getFormat("@"));                //���ó��ı���ʽ
	    style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //�м����
	    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	    style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

	    // ���ñ߿�
	    style.setBottomBorderColor(HSSFColor.BLACK.index);
	    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style.setBorderTop(HSSFCellStyle.BORDER_THIN);

	    style.setFont(font);
	    style.setWrapText(true);

	    
	    
	    
	    
	    // ����Excel��sheetһ��    ��***********����������
	    HSSFRow row = sheet.createRow(0);
	    row.setHeight((short) 500);// �趨�еĸ߶�
	    // ����һ��Excel�ĵ�Ԫ��
	    HSSFCell cell = row.createCell(0);
	    cell.setCellStyle(style);
	    cell.setCellValue("�û�ID");    //a
	    
	    cell = row.createCell(1);    //b
	    cell.setCellStyle(style);
	    cell.setCellValue("����ID");
	    
	    cell = row.createCell(2);    //b
	    cell.setCellStyle(style);
	    cell.setCellValue("����");
	    
	    cell = row.createCell(3);
	    cell.setCellStyle(style);
	    cell.setCellValue("���֤��");  //c
	    
	    cell = row.createCell(4);
	    cell.setCellStyle(style);
	    cell.setCellValue("����");     //d
	    
	    cell = row.createCell(5);
	    cell.setCellStyle(style);
	    cell.setCellValue("��˾");      //e
	    
	    cell = row.createCell(6);
	    cell.setCellStyle(style);
	    cell.setCellValue("׼��֤��");    //f
	    
	    // ����Excel��sheetһ��    ��***********����������
	    
	    
	    //@
	    MutiTableDao md=new MutiTableDaoImp();
	    List<ScoreExcel> list=md.getNumberXlsInfo();
		for(int i=0;i<list.size();i++){
			ScoreExcel se=list.get(i);
	    	row = sheet.createRow(i+1);
		    row.setHeight((short) 500);// �趨�еĸ߶�
		    // ����һ��Excel�ĵ�Ԫ��
		    cell = row.createCell(0);
		    cell.setCellStyle(style);
		    if(se.getUserInfoId()!=null&&!se.getUserInfoId().equals("")){
		    	cell.setCellValue(se.getUserInfoId());
		    }
		    
		    cell = row.createCell(1);
		    cell.setCellStyle(style);
		    if(se.getExamInfoId()!=null&&!se.getExamInfoId().equals("")){
		    	cell.setCellValue(se.getExamInfoId());
		    }
		    
		    cell = row.createCell(2);
		    cell.setCellStyle(style);
		    if(se.getRealName()!=null&&!se.getRealName().equals("")){
		    	cell.setCellValue(se.getRealName());
		    }
		    
		    cell = row.createCell(3);
		    cell.setCellStyle(style);
		    if(se.getIdnum()!=null&&!se.getIdnum().equals("")){
		    	cell.setCellValue(se.getIdnum());  
		    }
		    
		    cell = row.createCell(4);
		    cell.setCellStyle(style);
		    if(se.getPlace()!=null&&!se.getPlace().equals("")){
		    	cell.setCellValue(se.getPlace());
		    }
		    
		    cell = row.createCell(5);
		    cell.setCellStyle(style);
		    if(se.getCompany()!=null&&!se.getCompany().equals("")){
		    cell.setCellValue(se.getCompany());
		    }
		    //׼��֤��
		    cell = row.createCell(6);
		    cell.setCellStyle(style);
		    if(se.getNumber()!=null&&!se.getNumber().equals("")){
		         cell.setCellValue(se.getNumber());
		    }
		    	 
		    
	    }
 
	    // �ϲ���Ԫ��(startRow��endRow��startColumn��endColumn)
	    /*    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));*/
	    
	    try {
	    	FileOutputStream os = new FileOutputStream(url);
	 	    wb.write(os);
	 	    os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	//���֤�����ļ�
		//*url: ���ص�����·
		/*public GenerateXls(){
			
		}*/
		public void getCertXlsTable(String url){
			  // ����Excel�Ĺ������ Workbook,��Ӧ��һ��excel�ĵ�
		    HSSFWorkbook wb = new HSSFWorkbook();

		    // ����Excel�Ĺ���sheet,��Ӧ��һ��excel�ĵ���tab
		    HSSFSheet sheet = wb.createSheet("����֤��");

		    // ����excelÿ�п��
		    //sheet.autoSizeColumn(0);   //����Զ�
		    sheet.setColumnWidth(0, 5000);
		    sheet.setColumnWidth(1, 5000);
		    sheet.setColumnWidth(2, 8000);
		    sheet.setColumnWidth(3, 10000);
		    sheet.setColumnWidth(4, 10000);
//		    sheet.setColumnWidth(5, 10000);
//		    sheet.setColumnWidth(6, 10000);

		    // ����������ʽ
		    HSSFFont font = wb.createFont();
		    font.setFontName("Verdana");
		    font.setBoldweight((short) 90);
		    font.setFontHeight((short) 210);
		    font.setColor(HSSFColor.BLACK.index);
	        

		    
		    // ������Ԫ����ʽ
		    HSSFCellStyle style = wb.createCellStyle();
		    HSSFDataFormat format = wb.createDataFormat();
		    style.setDataFormat(format.getFormat("@"));                //���ó��ı���ʽ
		    style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //�м����
		    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		    style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		    // ���ñ߿�
		    style.setBottomBorderColor(HSSFColor.BLACK.index);
		    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		    style.setBorderTop(HSSFCellStyle.BORDER_THIN);

		    style.setFont(font);
		    style.setWrapText(true);

		    
		    
		    
		    
		    // ����Excel��sheetһ��    ��***********����������
		    HSSFRow row = sheet.createRow(0);
		    row.setHeight((short) 500);// �趨�еĸ߶�
		    // ����һ��Excel�ĵ�Ԫ��
		    HSSFCell cell = row.createCell(0);
		    cell.setCellStyle(style);
		    cell.setCellValue("֤����");    //a
		    
		    cell = row.createCell(1);    //b
		    cell.setCellStyle(style);
		    cell.setCellValue("��������");
		    
		    cell = row.createCell(2);
		    cell.setCellStyle(style);
		    cell.setCellValue("���֤��");  //c
		    
		    cell = row.createCell(3);
		    cell.setCellStyle(style);
		    cell.setCellValue("��֤����");     //d
		    
		    cell = row.createCell(4);
		    cell.setCellStyle(style);
		    cell.setCellValue("������λ");      //e
		    
//		    cell = row.createCell(5);
//		    cell.setCellStyle(style);
//		    cell.setCellValue("ʣ�����"); 
//		   
//		    
//		    cell = row.createCell(6);
//		    cell.setCellStyle(style);
//		    cell.setCellValue("֤��״̬"); 
		    
		    // ����Excel��sheetһ��    ��***********����������
		    
		    
		    //@
		    CertificateInfoDao certDao = new CertificateInfoDaoImp();
		    List<CertificateInfo> certInfoList = certDao.getCertInfoByState("1");  
			for(int i=0;i<certInfoList.size();i++){
				CertificateInfo certInfo=certInfoList.get(i);
		    	row = sheet.createRow(i+1);
			    row.setHeight((short) 500);// �趨�еĸ߶�
			    // ����һ��Excel�ĵ�Ԫ��
			    cell = row.createCell(0);
			    cell.setCellStyle(style);
			    if(null!=certInfo.getCertificationNum()&&!"".equals(certInfo.getCertificationNum())){
			    	cell.setCellValue(certInfo.getCertificationNum());
			    }
			    
			    cell = row.createCell(1);
			    cell.setCellStyle(style);
			    if(null!=certInfo.getUserInfo().getRealName()&&!"".equals(certInfo.getUserInfo().getRealName())){
			    	cell.setCellValue(certInfo.getUserInfo().getRealName());
			    }
			    
			    cell = row.createCell(2);
			    cell.setCellStyle(style);
			    if(null!=certInfo.getUserInfo().getIdnum()&&!"".equals(certInfo.getUserInfo().getIdnum())){
			    	cell.setCellValue(certInfo.getUserInfo().getIdnum());
			    }
			    
			    cell = row.createCell(3);
			    cell.setCellStyle(style);
			    if(null!=certInfo.getCertificationGrantDate()&&!"".equals(certInfo.getCertificationGrantDate())){
			    	cell.setCellValue(certInfo.getCertificationGrantDate().toString().substring(0,10));
			    }
			    
			    cell = row.createCell(4);
			    cell.setCellStyle(style);
			    Set<ExamineeInfo> examineeSet = certInfo.getUserInfo().getExamineeInfos();
			    for (ExamineeInfo examineeInfo : examineeSet) {
					if(null!=examineeInfo.getCompany()&&!"".equals(examineeInfo.getCompany())){
						cell.setCellValue(examineeInfo.getCompany());
					}
				}
			    
//			    cell = row.createCell(5);
//			    cell.setCellStyle(style);
//			    if(null!=certInfo.getRemainingScore()&&!"".equals(certInfo.getRemainingScore())){
//			    	cell.setCellValue(certInfo.getRemainingScore());
//			    }
//			   
//			    cell = row.createCell(6);
//			    cell.setCellStyle(style);
//			    if(null!=certInfo.getCertificationState()&&!"".equals(certInfo.getCertificationState())){
//			    	if(certInfo.getCertificationState().equals("2"))
//			    		cell.setCellValue("����");
//			    	else if(certInfo.getCertificationState().equals("1"))
//			    		cell.setCellValue("����");
//			    }
			    
		    }
	 
		    // �ϲ���Ԫ��(startRow��endRow��startColumn��endColumn)
		    /*    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));*/
		    
		    try {
		    	FileOutputStream os = new FileOutputStream(url);
		 	    wb.write(os);
		 	    os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		public void getExamManageTable(String url){
		    HSSFWorkbook wb = new HSSFWorkbook();

		    // ����Excel�Ĺ���sheet,��Ӧ��һ��excel�ĵ���tab
		    HSSFSheet sheet = wb.createSheet("�������ŵ���");

		    // ����excelÿ�п��
		    //sheet.autoSizeColumn(0);   //����Զ�
		    sheet.setColumnWidth(0, 2000);    //userInfoId���
		    sheet.setColumnWidth(1, 2000);    //userInfoId���
		    sheet.setColumnWidth(2, 3000);    //����
		    sheet.setColumnWidth(3, 4000);    //׼��֤��
		    sheet.setColumnWidth(4, 7000);   //��������ʱ��
		    sheet.setColumnWidth(5, 7000);   //����������
		    sheet.setColumnWidth(6, 7000);   //�������Եص�
		  
		    // ����������ʽ
		    HSSFFont font = wb.createFont();
		    font.setFontName("Verdana");
		    font.setBoldweight((short) 90);
		    font.setFontHeight((short) 210);
		    font.setColor(HSSFColor.BLACK.index);
	        

		    
		    // ������Ԫ����ʽ
		    HSSFCellStyle style = wb.createCellStyle();
		    HSSFDataFormat format = wb.createDataFormat();
		    style.setDataFormat(format.getFormat("@"));                //���ó��ı���ʽ
		    style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //�м����
		    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		    style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		    // ���ñ߿�
		    style.setBottomBorderColor(HSSFColor.BLACK.index);
		    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		    style.setBorderTop(HSSFCellStyle.BORDER_THIN);

		    style.setFont(font);
		    style.setWrapText(true);

		    
		    
		    
		    
		    // ����Excel��sheetһ��    ��***********����������
		    HSSFRow row = sheet.createRow(0);
		    row.setHeight((short) 500);// �趨�еĸ߶�
		    // ����һ��Excel�ĵ�Ԫ��
		    HSSFCell cell = row.createCell(0);
		    cell.setCellStyle(style);
		    cell.setCellValue("�û�ID");    
		    cell = row.createCell(1);   
		    cell.setCellStyle(style);
		    cell.setCellValue("����ID");  
		    
		    cell = row.createCell(2);   
		    cell.setCellStyle(style);
		    cell.setCellValue("����"); 
		    
		    cell = row.createCell(3);
		    cell.setCellStyle(style);
		    cell.setCellValue("׼��֤��");  
		    
		    cell = row.createCell(4);
		    cell.setCellStyle(style);
		    cell.setCellValue("����ʱ��"); 

		    cell = row.createCell(5);
		    cell.setCellStyle(style);
		    cell.setCellValue("������"); 
		    
		    cell = row.createCell(6);
		    cell.setCellStyle(style);
		    cell.setCellValue("���Եص�"); 
		    
		    
		    
		    // ����Excel��sheetһ��    ��***********����������
		    
		    
		    //@
	/*	    MutiTableDao md=new MutiTableDaoImp();
		    List<ScoreExcel> list0=md.getNumberXlsInfo();*/
		    List<ExamInfo>  list = new ExamInfoDaoImp().getAllExamInfo();
		    System.out.println(list.size());
			for(int i=0;i<list.size();i++){
				ExamInfo ei=list.get(i);
		    	row = sheet.createRow(i+1);
			    row.setHeight((short) 500);// �趨�еĸ߶�
			    // ����һ��Excel�ĵ�Ԫ��
			    cell = row.createCell(0);
			    cell.setCellStyle(style);
			    String temp =null;
	  
			    if(null!=ei.getUserInfo()&&!"".equals(ei.getUserInfo())){
			    	cell.setCellValue(ei.getUserInfo().getUserInfoId().toString());
			    }
			    cell = row.createCell(1);
			    cell.setCellStyle(style);
			    if(null!=ei.getExamInfoId()&&!"".equals(ei.getExamInfoId())){
			    	cell.setCellValue(ei.getExamInfoId().toString());
			    }
			    
			    cell = row.createCell(2);
			    cell.setCellStyle(style);
			    temp=ei.getUserInfo().getRealName();
			    if(temp!=null&&!temp.equals("")){
			    	cell.setCellValue(temp);
			    }
			    
			    cell = row.createCell(3);
			    cell.setCellStyle(style);
			    temp=ei.getNumber();
			    if(temp!=null&&!temp.equals("")){
			    	cell.setCellValue(temp);  
			    }
			    			    
			    cell = row.createCell(4);
			    cell.setCellStyle(style);
			    if(ei.getTheoryExamDate()!=null&&!ei.getTheoryExamDate().equals("")){
			    	temp=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ei.getTheoryExamDate());
			    	cell.setCellValue(temp);  			    	
			    }

			    
			    
			    cell = row.createCell(5);
			    cell.setCellStyle(style);
			    temp=ei.getTheoryExamRoom();
			    if(temp!=null&&!temp.equals("")){
			    	cell.setCellValue(temp);  
			    }
			    
			    cell = row.createCell(6);
			    cell.setCellStyle(style);
			    temp=ei.getTheoryExamPlace();
			    if(temp!=null&&!temp.equals("")){
			    	cell.setCellValue(temp);  
			    }
			    
		    }
	 
		    // �ϲ���Ԫ��(startRow��endRow��startColumn��endColumn)
		    /*    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));*/
		    
		    try {
		    	FileOutputStream os = new FileOutputStream(url);
		 	    wb.write(os);
		 	    os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void checkFirDetailXls(String url){
			  // ����Excel�Ĺ������ Workbook,��Ӧ��һ��excel�ĵ�
		    HSSFWorkbook wb = new HSSFWorkbook();

		    // ����Excel�Ĺ���sheet,��Ӧ��һ��excel�ĵ���tab
		    HSSFSheet sheet = wb.createSheet("�������");

		    // ����excelÿ�п��
		    //sheet.autoSizeColumn(0);   //����Զ�
		    sheet.setColumnWidth(0, 2000);
		    sheet.setColumnWidth(1, 3000);
		    sheet.setColumnWidth(2, 8000);
		    sheet.setColumnWidth(3, 3000);
		    sheet.setColumnWidth(4, 6000);
		    sheet.setColumnWidth(5, 6000);
		    sheet.setColumnWidth(6, 6000);
		    sheet.setColumnWidth(7, 6000);
		    sheet.setColumnWidth(8, 5000);
		    sheet.setColumnWidth(9, 5000);
		    // ����������ʽ
		    HSSFFont font = wb.createFont();
		    font.setFontName("Verdana");
		    font.setBoldweight((short) 90);
		    font.setFontHeight((short) 210);
		    font.setColor(HSSFColor.BLACK.index);
	      

		    
		    // ������Ԫ����ʽ
		    HSSFCellStyle style = wb.createCellStyle();
		    HSSFDataFormat format = wb.createDataFormat();
		    style.setDataFormat(format.getFormat("@"));                //���ó��ı���ʽ
		    style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //�м����
		    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		    style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		    // ���ñ߿�
		    style.setBottomBorderColor(HSSFColor.BLACK.index);
		    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		    style.setBorderTop(HSSFCellStyle.BORDER_THIN);

		    style.setFont(font);
		    style.setWrapText(true);

		    
		    
		    
		    
		    // ����Excel��sheetһ��    ��***********����������
		    HSSFRow row = sheet.createRow(0);
		    row.setHeight((short) 500);// �趨�еĸ߶�
		    // ����һ��Excel�ĵ�Ԫ��
		    HSSFCell cell = row.createCell(0);
		    cell.setCellStyle(style);
		    cell.setCellValue("�û�ID");
		    
		    cell = row.createCell(1);
		    cell.setCellStyle(style);
		    cell.setCellValue("��ʵ����");
		    
		    cell = row.createCell(2);
		    cell.setCellStyle(style);
		    cell.setCellValue("���֤��");
		    
		    cell = row.createCell(3);
		    cell.setCellStyle(style);
		    cell.setCellValue("�Ա�");
		    
		    cell = row.createCell(4);
		    cell.setCellStyle(style);
		    cell.setCellValue("������λ");
		    
		    cell = row.createCell(5);
		    cell.setCellStyle(style);
		    cell.setCellValue("�ʼ�ͨ�ŵ�ַ");
		    
		    cell = row.createCell(6);
		    cell.setCellStyle(style);
		    cell.setCellValue("�绰");
		    
		    cell = row.createCell(7);
		    cell.setCellStyle(style);
		    cell.setCellValue("���µ�����������");
		    
		    cell = row.createCell(8);
		    cell.setCellStyle(style);
		    cell.setCellValue("�������");
		    
		    cell = row.createCell(9);
		    cell.setCellStyle(style);
		    cell.setCellValue("��λ���");
		    
		    
		    // ����Excel��sheetһ��    ��***********����������
		    //����ͨ���Ŀ���
		    ExamineeInfoDao examInfoDao = new ExamineeInfoDaoImp();
			List<ExamineeInfo> list = examInfoDao.getExamineeInfoByCheckState("1");
			for(int i=0;i<list.size();i++){
				ExamineeInfo se=list.get(i);
		    	row = sheet.createRow(i+1);
			    row.setHeight((short) 500);// �趨�еĸ߶�
			    // ����һ��Excel�ĵ�Ԫ��
			    cell = row.createCell(0);
			    cell.setCellStyle(style);
			    if(se.getUserInfo()!=null){
			    	if(se.getUserInfo().getUserInfoId()!=null){
			    		cell.setCellValue(se.getUserInfo().getUserInfoId()); 	
			    	}
			    }
			    cell = row.createCell(1);
			    cell.setCellStyle(style);
			    if(se.getUserInfo()!=null){
			    	if(se.getUserInfo().getRealName()!=null&&!"".equals(se.getUserInfo().getRealName())){
			    		cell.setCellValue(se.getUserInfo().getRealName()); 	
			    	}
			    }
			    
			    cell = row.createCell(2);
			    cell.setCellStyle(style);
			    if(se.getUserInfo()!=null){
			    	if(se.getUserInfo().getIdnum()!=null&&!"".equals(se.getUserInfo().getIdnum())){
			    		cell.setCellValue(se.getUserInfo().getIdnum()); 	
			    	}
			    }
			    //׼��֤��
			    cell = row.createCell(3);
			    cell.setCellStyle(style);
			    if(se.getSex()!=null&&!se.getSex().equals("")){
				    cell.setCellValue(se.getSex().equals("1")?"��":"Ů");
			    }
			    
			    
			    cell = row.createCell(4);
			    cell.setCellStyle(style);
			    if(se.getCompany()!=null&&!se.getCompany().equals("")){
				    cell.setCellValue(se.getCompany());
			    }
			    
			    cell = row.createCell(5);
			    cell.setCellStyle(style);
			    if(se.getHomesite()!=null&&!se.getHomesite().equals("")){
				    cell.setCellValue(se.getHomesite());
			    }
			    cell = row.createCell(6);
			    cell.setCellStyle(style);
			    if(se.getTel()!=null&&!se.getTel().equals("")){
			    	cell.setCellValue(se.getTel());
			    }
			    cell = row.createCell(7);
			    cell.setCellStyle(style);
			    if(se.getWorkLimitTime()!=null&&!se.getWorkLimitTime().equals("")){
			    	String limit = "";
			    	int years = se.getWorkLimitTime();
			    	switch (years) {
						case 0:
							limit="1�꼰����";
							break;
						case 1:
							limit="1-2��";
							break;
						case 2:
							limit="2-3��";
							break;
						case 3:
							limit="3-5��";
							break;
						case 4:
							limit="5������";
							break;
						default:
							break;
					}
			    	cell.setCellValue(limit);
			    }
			    cell = row.createCell(8);
			    cell.setCellStyle(style);
			    if(se.getSkillLevel()!=null&&!se.getSkillLevel().equals("")){
			    	if(se.getSkillLevel().equals("1")){
			    		cell.setCellValue("����");
			    	}else if(se.getSkillLevel().equals("2")){
			    		cell.setCellValue("����");
			    	}else if(se.getSkillLevel().equals("3")){
			    		cell.setCellValue("����+����");
			    	}
			    }
			    cell = row.createCell(9);
			    cell.setCellStyle(style);
			    if(se.getExamineeSource()!=null&&!se.getExamineeSource().equals("")){
			    	if(se.getExamineeSource().equals("fengs")){
			    		cell.setCellValue("�ֵ�����˾");
			    	}else if(se.getExamineeSource().equals("nongdiangs")){
			    		cell.setCellValue("ũ�����˾");
			    	}else if(se.getExamineeSource().equals("shehuidw")){
			    		cell.setCellValue("���ʩ����λ");
			    	}
			    }
			    
		    }

		    // �ϲ���Ԫ��(startRow��endRow��startColumn��endColumn)
		    /*    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));*/
		    
		    try {
		    	FileOutputStream os = new FileOutputStream(url);
		 	    wb.write(os);
		 	    os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

}
