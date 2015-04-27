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
	//获得成绩表格文件
	//*url: 下载的请求路
	/*public GenerateXls(){
		
	}*/
	public void getTheoryScoreXlsTable(String url){
		  // 创建Excel的工作书册 Workbook,对应到一个excel文档
	    HSSFWorkbook wb = new HSSFWorkbook();

	    // 创建Excel的工作sheet,对应到一个excel文档的tab
	    HSSFSheet sheet = wb.createSheet("理论成绩导入");

	    // 设置excel每列宽度
	    //sheet.autoSizeColumn(0);   //宽度自动
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

	    // 创建字体样式
	    HSSFFont font = wb.createFont();
	    font.setFontName("Verdana");
	    font.setBoldweight((short) 90);
	    font.setFontHeight((short) 210);
	    font.setColor(HSSFColor.BLACK.index);
        

	    
	    //TODO   创建单元格样式   设置成文本格式
	    HSSFCellStyle style = wb.createCellStyle();
	    
	    HSSFDataFormat format = wb.createDataFormat();
	    style.setDataFormat(format.getFormat("@"));                //设置成文本格式
	    
	    style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //中间对齐
	    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	    style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

	    // 设置边框
	    style.setBottomBorderColor(HSSFColor.BLACK.index);
	    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style.setBorderTop(HSSFCellStyle.BORDER_THIN);

	    style.setFont(font);
	    style.setWrapText(true);

	    
	    
	    
	    
	    // 创建Excel的sheet一行    ，***********创建标题行
	    HSSFRow row = sheet.createRow(0);
	    row.setHeight((short) 500);// 设定行的高度
	    // 创建一个Excel的单元格
	    HSSFCell cell = row.createCell(0);
	    cell.setCellStyle(style);
	    cell.setCellValue("用户ID");
	    
	    cell = row.createCell(1);
	    cell.setCellStyle(style);
	    cell.setCellValue("考试ID");
	    
	    cell = row.createCell(2);
	    cell.setCellStyle(style);
	    cell.setCellValue("姓名");
	    
	    cell = row.createCell(3);
	    cell.setCellStyle(style);
	    cell.setCellValue("准考证号");
	    
	    cell = row.createCell(4);
	    cell.setCellStyle(style);
	    cell.setCellValue("公司");
	    
	    cell = row.createCell(5);
	    cell.setCellStyle(style);
	    cell.setCellValue("理论成绩");
  
	    // 创建Excel的sheet一行    ，***********创建标题行

	    //@
	    MutiTableDao md=new MutiTableDaoImp();
	    List<ScoreExcel> list=md.getTheoryScoreXlsInfo();
		for(int i=0;i<list.size();i++){
			ScoreExcel se=list.get(i);
	    	row = sheet.createRow(i+1);
		    row.setHeight((short) 500);// 设定行的高度
		    // 创建一个Excel的单元格
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
		    //准考证号
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
 
	    // 合并单元格(startRow，endRow，startColumn，endColumn)
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
		  // 创建Excel的工作书册 Workbook,对应到一个excel文档
	    HSSFWorkbook wb = new HSSFWorkbook();

	    // 创建Excel的工作sheet,对应到一个excel文档的tab
	    HSSFSheet sheet = wb.createSheet("成绩导入");

	    
	    
	    // 设置excel每列宽度
	    //sheet.autoSizeColumn(0);   //宽度自动
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
	    // 创建字体样式
	    HSSFFont font = wb.createFont();
	    font.setFontName("Verdana");
	    font.setBoldweight((short) 90);
	    font.setFontHeight((short) 210);
	    font.setColor(HSSFColor.BLACK.index);
      

	    
	    // 创建单元格样式
	    HSSFCellStyle style = wb.createCellStyle();
	    HSSFDataFormat format = wb.createDataFormat();
	    style.setDataFormat(format.getFormat("@"));                //设置成文本格式
	    style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //中间对齐
	    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	    style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

	    // 设置边框
	    style.setBottomBorderColor(HSSFColor.BLACK.index);
	    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style.setBorderTop(HSSFCellStyle.BORDER_THIN);

	    style.setFont(font);
	    style.setWrapText(true);

	    
	    
	    
	    
	    // 创建Excel的sheet一行    ，***********创建标题行
	    HSSFRow row = sheet.createRow(0);
	    row.setHeight((short) 500);// 设定行的高度
	    // 创建一个Excel的单元格
	    HSSFCell cell = row.createCell(0);
	    cell.setCellStyle(style);
	    cell.setCellValue("用户ID");
	    
	    cell = row.createCell(1);
	    cell.setCellStyle(style);
	    cell.setCellValue("考试ID");
	    
	    cell = row.createCell(2);
	    cell.setCellStyle(style);
	    cell.setCellValue("姓名");
	    
	    cell = row.createCell(3);
	    cell.setCellStyle(style);
	    cell.setCellValue("准考证号");
	    
	    cell = row.createCell(4);
	    cell.setCellStyle(style);
	    cell.setCellValue("公司");
	    
	    cell = row.createCell(5);
	    cell.setCellStyle(style);
	    cell.setCellValue("理论");
	    
	    cell = row.createCell(6);
	    cell.setCellStyle(style);
	    cell.setCellValue("冷缩(中)");
	    
	    cell = row.createCell(7);
	    cell.setCellStyle(style);
	    cell.setCellValue("冷缩(终)");
	    
	    cell = row.createCell(8);
	    cell.setCellStyle(style);
	    cell.setCellValue("热缩(中)");
	    
	    cell = row.createCell(9);
	    cell.setCellStyle(style);
	    cell.setCellValue("热缩(终)");
	    
	    
	    // 创建Excel的sheet一行    ，***********创建标题行

	    //@
	    MutiTableDao md=new MutiTableDaoImp();
	    List<ScoreExcel> list=md.getAllScoreXlsInfo();
		for(int i=0;i<list.size();i++){
			ScoreExcel se=list.get(i);
	    	row = sheet.createRow(i+1);
		    row.setHeight((short) 500);// 设定行的高度
		    // 创建一个Excel的单元格
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
		    //准考证号
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

	    // 合并单元格(startRow，endRow，startColumn，endColumn)
	    /*    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));*/
	    
	    try {
	    	FileOutputStream os = new FileOutputStream(url);
	 	    wb.write(os);
	 	    os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//年度评价表
	public void getRecordXlsTable(String url,String idNumber,String yyyyDate){
		// 创建Excel的工作书册 Workbook,对应到一个excel文档
		HSSFWorkbook wb = new HSSFWorkbook();
		
		// 创建Excel的工作sheet,对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet("年度评价");
		
		sheet.setColumnWidth(0, 2000);  //序号
		sheet.setColumnWidth(1, 2000);  //姓名
		sheet.setColumnWidth(2, 3000);  //性别
		sheet.setColumnWidth(3, 8000);  //身份证号码
		sheet.setColumnWidth(4, 8000);  //工作单位
		sheet.setColumnWidth(5, 3000);  //作业范围
		sheet.setColumnWidth(6, 3000);  //证书编号
		sheet.setColumnWidth(7, 3000);  //2014年
		sheet.setColumnWidth(8, 3000);  //制作电缆头总数量
		sheet.setColumnWidth(9, 3000);  //近三年累计数量
		sheet.setColumnWidth(10, 3000);  //合格数量
		sheet.setColumnWidth(11, 3000);  //不合格数量
		sheet.setColumnWidth(12, 3000);  //扣分
		sheet.setColumnWidth(13, 3000);  //备注
		// 创建字体样式
		HSSFFont font = wb.createFont();
		font.setFontName("Verdana");
		font.setBoldweight((short) 90);
		font.setFontHeight((short) 210);
		font.setColor(HSSFColor.BLACK.index);
		
		// 创建单元格样式
		HSSFCellStyle style = wb.createCellStyle();
		HSSFDataFormat format = wb.createDataFormat();
		style.setDataFormat(format.getFormat("@"));                //设置成文本格式
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //中间对齐
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		// 设置边框
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		
		style.setFont(font);
		style.setWrapText(true);
		
		// 创建Excel的sheet一行    ，***********创建标题行
		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) 500);// 设定行的高度
		// 创建一个Excel的单元格
		HSSFCell cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("序号");
		
		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("姓名");
		
		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("性别");
		
		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("身份证号码");
		
		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("工作单位");
		
		//作业范围
		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("作业范围");
		
		//证书编号
		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("证书编号");
		
		//2014年
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("年份");
		
		//制作电缆头总数量
		cell = row.createCell(8);
		cell.setCellStyle(style);
		cell.setCellValue("制作电缆头总数量");
		
		//近三年累计数量
		cell = row.createCell(9);
		cell.setCellStyle(style);
		cell.setCellValue("近三年累计数量");
		
		//合格数量
		cell = row.createCell(10);
		cell.setCellStyle(style);
		cell.setCellValue("合格数量");
		
		//不合格数量
		cell = row.createCell(11);
		cell.setCellStyle(style);
		cell.setCellValue("不合格数量");
		
		//扣分
		cell = row.createCell(12);
		cell.setCellStyle(style);
		cell.setCellValue("扣分");
		
		//备注
		cell = row.createCell(13);
		cell.setCellStyle(style);
		cell.setCellValue("备注");
		
		// 创建Excel的sheet一行    ，***********创建标题行
		
		//@
		List<RecordOfYear> list = new RecordOfYearDaoImp().getRecordByIdYear(idNumber, yyyyDate);
		for(int i=0;i<list.size();i++){
			RecordOfYear ry=list.get(i);
			row = sheet.createRow(i+1);
			row.setHeight((short) 500);// 设定行的高度
			// 创建一个Excel的单元格
			
			//序号
			cell = row.createCell(0);
			cell.setCellStyle(style);	
			cell.setCellValue(i+1); 	
			//姓名
			cell = row.createCell(1);
			cell.setCellStyle(style);
			if(ry.getName()!=null&&!ry.getName().equals("")){
				cell.setCellValue(ry.getName());
			}
			//性别
			cell = row.createCell(2);
			cell.setCellStyle(style);
			switch(ry.getSex()){
				case 0: cell.setCellValue("女"); break;
				case 1: cell.setCellValue("男"); break;
				case 2:cell.setCellValue("其它"); break;
				default:cell.setCellValue(""); 
			}
			//身份证号码
			cell = row.createCell(3);
			cell.setCellStyle(style);
			if(ry.getIdNumber()!=null&&!ry.getIdNumber().equals("")){
				cell.setCellValue(ry.getIdNumber());
			}
			//工作单位
			cell = row.createCell(4);
			cell.setCellStyle(style);
			if(ry.getCompany()!=null&&!ry.getCompany().equals("")){
				cell.setCellValue(ry.getCompany());
			}
			//作业范围
			cell = row.createCell(5);
			cell.setCellStyle(style);
			switch(ry.getSex()){
				case 0: cell.setCellValue("冷缩"); break;
				case 1: cell.setCellValue("热缩"); break;
				case 2: cell.setCellValue("冷缩+热缩"); break;
				default:cell.setCellValue(""); 
			}
			//证书编号
			cell = row.createCell(6);
			cell.setCellStyle(style);
			if(ry.getCertNumber()!=null&&!ry.getCertNumber().equals("")){
				cell.setCellValue(ry.getCertNumber());
			}
			//2014年
			cell = row.createCell(7);
			cell.setCellStyle(style);
			if(ry.getYyyyDate()!=null&&!ry.getYyyyDate().equals("")){
				cell.setCellValue(ry.getYyyyDate());
			}
			//制作电缆头总数量
			cell = row.createCell(8);
			cell.setCellStyle(style);
			if(ry.getYearCount()!=null&&!ry.getYearCount().equals("")){
				cell.setCellValue(ry.getYearCount());
			}
			//近三年累计数量
			cell = row.createCell(9);
			cell.setCellStyle(style);
			if(ry.getThreeYearCount()!=null&&!ry.getThreeYearCount().equals("")){
				cell.setCellValue(ry.getThreeYearCount());
			}
			//合格数量
			cell = row.createCell(10);
			cell.setCellStyle(style);
			if(ry.getPassCount()!=null&&!ry.getPassCount().equals("")){
				cell.setCellValue(ry.getPassCount());
			}
			//不合格数量
			cell = row.createCell(11);
			cell.setCellStyle(style);
			if(ry.getFailCount()!=null&&!ry.getFailCount().equals("")){
				cell.setCellValue(ry.getFailCount());
			}
			//扣分
			cell = row.createCell(12);
			cell.setCellStyle(style);
			if(ry.getDeduction()!=null&&!ry.getDeduction().equals("")){
				cell.setCellValue(ry.getDeduction());
			}
			//备注
			cell = row.createCell(13);
			cell.setCellStyle(style);
			if(ry.getComments()!=null&&!ry.getComments().equals("")){
				cell.setCellValue(ry.getComments());
			}
		}
		
		// 合并单元格(startRow，endRow，startColumn，endColumn)
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
		  // 创建Excel的工作书册 Workbook,对应到一个excel文档
	    HSSFWorkbook wb = new HSSFWorkbook();

	    // 创建Excel的工作sheet,对应到一个excel文档的tab
	    HSSFSheet sheet = wb.createSheet("历年成绩");

	    // 设置excel每列宽度
	    //sheet.autoSizeColumn(0);   //宽度自动
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
	    // 创建字体样式
	    HSSFFont font = wb.createFont();
	    font.setFontName("Verdana");
	    font.setBoldweight((short) 90);
	    font.setFontHeight((short) 210);
	    font.setColor(HSSFColor.BLACK.index);
      

	    
	    // 创建单元格样式
	    HSSFCellStyle style = wb.createCellStyle();
	    HSSFDataFormat format = wb.createDataFormat();
	    style.setDataFormat(format.getFormat("@"));                //设置成文本格式
	    style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //中间对齐
	    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	    style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

	    // 设置边框
	    style.setBottomBorderColor(HSSFColor.BLACK.index);
	    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style.setBorderTop(HSSFCellStyle.BORDER_THIN);

	    style.setFont(font);
	    style.setWrapText(true);

	    
	    
	    
	    
	    // 创建Excel的sheet一行    ，***********创建标题行
	    HSSFRow row = sheet.createRow(0);
	    row.setHeight((short) 500);// 设定行的高度
	    // 创建一个Excel的单元格
	    HSSFCell cell = row.createCell(0);
	    cell.setCellStyle(style);
	    cell.setCellValue("用户ID");
	    
	    cell = row.createCell(1);
	    cell.setCellStyle(style);
	    cell.setCellValue("考试ID");
	    
	    cell = row.createCell(2);
	    cell.setCellStyle(style);
	    cell.setCellValue("姓名");
	    
	    cell = row.createCell(3);
	    cell.setCellStyle(style);
	    cell.setCellValue("准考证号");
	    
	    cell = row.createCell(4);
	    cell.setCellStyle(style);
	    cell.setCellValue("公司");
	    
	    cell = row.createCell(5);
	    cell.setCellStyle(style);
	    cell.setCellValue("理论");
	    
	    cell = row.createCell(6);
	    cell.setCellStyle(style);
	    cell.setCellValue("冷缩(中)");
	    
	    cell = row.createCell(7);
	    cell.setCellStyle(style);
	    cell.setCellValue("冷缩(终)");
	    
	    cell = row.createCell(8);
	    cell.setCellStyle(style);
	    cell.setCellValue("热缩(中)");
	    
	    cell = row.createCell(9);
	    cell.setCellStyle(style);
	    cell.setCellValue("热缩(终)");
	    
	    
	    // 创建Excel的sheet一行    ，***********创建标题行

	    //@
	    MutiTableDao md=new MutiTableDaoImp();
	    List<ScoreExcel> list=md.getScoreXlsInfo();
		for(int i=0;i<list.size();i++){
			ScoreExcel se=list.get(i);
	    	row = sheet.createRow(i+1);
		    row.setHeight((short) 500);// 设定行的高度
		    // 创建一个Excel的单元格
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
		    //准考证号
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

	    // 合并单元格(startRow，endRow，startColumn，endColumn)
	    /*    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));*/
	    
	    try {
	    	FileOutputStream os = new FileOutputStream(url);
	 	    wb.write(os);
	 	    os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
	//获得成绩表格文件
	//*url: 下载的请求路
	/*public GenerateXls(){
		
	}*/
	public void getNumberXlsTable(String url){
		  // 创建Excel的工作书册 Workbook,对应到一个excel文档
	    HSSFWorkbook wb = new HSSFWorkbook();

	    // 创建Excel的工作sheet,对应到一个excel文档的tab
	    HSSFSheet sheet = wb.createSheet("准考证号导入");

	    // 设置excel每列宽度
	    //sheet.autoSizeColumn(0);   //宽度自动
	    sheet.setColumnWidth(0, 2000);
	    sheet.setColumnWidth(1, 2000);
	    sheet.setColumnWidth(2, 3000);
	    sheet.setColumnWidth(3, 8000);
	    sheet.setColumnWidth(4, 10000);
	    sheet.setColumnWidth(5, 10000);
	    sheet.setColumnWidth(6, 10000);

	    // 创建字体样式
	    HSSFFont font = wb.createFont();
	    font.setFontName("Verdana");
	    font.setBoldweight((short) 90);
	    font.setFontHeight((short) 210);
	    font.setColor(HSSFColor.BLACK.index);
        

	    
	    // 创建单元格样式
	    HSSFCellStyle style = wb.createCellStyle();
	    HSSFDataFormat format = wb.createDataFormat();
	    style.setDataFormat(format.getFormat("@"));                //设置成文本格式
	    style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //中间对齐
	    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	    style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

	    // 设置边框
	    style.setBottomBorderColor(HSSFColor.BLACK.index);
	    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style.setBorderTop(HSSFCellStyle.BORDER_THIN);

	    style.setFont(font);
	    style.setWrapText(true);

	    
	    
	    
	    
	    // 创建Excel的sheet一行    ，***********创建标题行
	    HSSFRow row = sheet.createRow(0);
	    row.setHeight((short) 500);// 设定行的高度
	    // 创建一个Excel的单元格
	    HSSFCell cell = row.createCell(0);
	    cell.setCellStyle(style);
	    cell.setCellValue("用户ID");    //a
	    
	    cell = row.createCell(1);    //b
	    cell.setCellStyle(style);
	    cell.setCellValue("考试ID");
	    
	    cell = row.createCell(2);    //b
	    cell.setCellStyle(style);
	    cell.setCellValue("姓名");
	    
	    cell = row.createCell(3);
	    cell.setCellStyle(style);
	    cell.setCellValue("身份证号");  //c
	    
	    cell = row.createCell(4);
	    cell.setCellStyle(style);
	    cell.setCellValue("籍贯");     //d
	    
	    cell = row.createCell(5);
	    cell.setCellStyle(style);
	    cell.setCellValue("公司");      //e
	    
	    cell = row.createCell(6);
	    cell.setCellStyle(style);
	    cell.setCellValue("准考证号");    //f
	    
	    // 创建Excel的sheet一行    ，***********创建标题行
	    
	    
	    //@
	    MutiTableDao md=new MutiTableDaoImp();
	    List<ScoreExcel> list=md.getNumberXlsInfo();
		for(int i=0;i<list.size();i++){
			ScoreExcel se=list.get(i);
	    	row = sheet.createRow(i+1);
		    row.setHeight((short) 500);// 设定行的高度
		    // 创建一个Excel的单元格
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
		    //准考证号
		    cell = row.createCell(6);
		    cell.setCellStyle(style);
		    if(se.getNumber()!=null&&!se.getNumber().equals("")){
		         cell.setCellValue(se.getNumber());
		    }
		    	 
		    
	    }
 
	    // 合并单元格(startRow，endRow，startColumn，endColumn)
	    /*    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));*/
	    
	    try {
	    	FileOutputStream os = new FileOutputStream(url);
	 	    wb.write(os);
	 	    os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	//获得证书表格文件
		//*url: 下载的请求路
		/*public GenerateXls(){
			
		}*/
		public void getCertXlsTable(String url){
			  // 创建Excel的工作书册 Workbook,对应到一个excel文档
		    HSSFWorkbook wb = new HSSFWorkbook();

		    // 创建Excel的工作sheet,对应到一个excel文档的tab
		    HSSFSheet sheet = wb.createSheet("考试证书");

		    // 设置excel每列宽度
		    //sheet.autoSizeColumn(0);   //宽度自动
		    sheet.setColumnWidth(0, 5000);
		    sheet.setColumnWidth(1, 5000);
		    sheet.setColumnWidth(2, 8000);
		    sheet.setColumnWidth(3, 10000);
		    sheet.setColumnWidth(4, 10000);
//		    sheet.setColumnWidth(5, 10000);
//		    sheet.setColumnWidth(6, 10000);

		    // 创建字体样式
		    HSSFFont font = wb.createFont();
		    font.setFontName("Verdana");
		    font.setBoldweight((short) 90);
		    font.setFontHeight((short) 210);
		    font.setColor(HSSFColor.BLACK.index);
	        

		    
		    // 创建单元格样式
		    HSSFCellStyle style = wb.createCellStyle();
		    HSSFDataFormat format = wb.createDataFormat();
		    style.setDataFormat(format.getFormat("@"));                //设置成文本格式
		    style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //中间对齐
		    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		    style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		    // 设置边框
		    style.setBottomBorderColor(HSSFColor.BLACK.index);
		    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		    style.setBorderTop(HSSFCellStyle.BORDER_THIN);

		    style.setFont(font);
		    style.setWrapText(true);

		    
		    
		    
		    
		    // 创建Excel的sheet一行    ，***********创建标题行
		    HSSFRow row = sheet.createRow(0);
		    row.setHeight((short) 500);// 设定行的高度
		    // 创建一个Excel的单元格
		    HSSFCell cell = row.createCell(0);
		    cell.setCellStyle(style);
		    cell.setCellValue("证书编号");    //a
		    
		    cell = row.createCell(1);    //b
		    cell.setCellStyle(style);
		    cell.setCellValue("考生姓名");
		    
		    cell = row.createCell(2);
		    cell.setCellStyle(style);
		    cell.setCellValue("身份证号");  //c
		    
		    cell = row.createCell(3);
		    cell.setCellStyle(style);
		    cell.setCellValue("发证日期");     //d
		    
		    cell = row.createCell(4);
		    cell.setCellStyle(style);
		    cell.setCellValue("工作单位");      //e
		    
//		    cell = row.createCell(5);
//		    cell.setCellStyle(style);
//		    cell.setCellValue("剩余分数"); 
//		   
//		    
//		    cell = row.createCell(6);
//		    cell.setCellStyle(style);
//		    cell.setCellValue("证书状态"); 
		    
		    // 创建Excel的sheet一行    ，***********创建标题行
		    
		    
		    //@
		    CertificateInfoDao certDao = new CertificateInfoDaoImp();
		    List<CertificateInfo> certInfoList = certDao.getCertInfoByState("1");  
			for(int i=0;i<certInfoList.size();i++){
				CertificateInfo certInfo=certInfoList.get(i);
		    	row = sheet.createRow(i+1);
			    row.setHeight((short) 500);// 设定行的高度
			    // 创建一个Excel的单元格
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
//			    		cell.setCellValue("吊销");
//			    	else if(certInfo.getCertificationState().equals("1"))
//			    		cell.setCellValue("正常");
//			    }
			    
		    }
	 
		    // 合并单元格(startRow，endRow，startColumn，endColumn)
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

		    // 创建Excel的工作sheet,对应到一个excel文档的tab
		    HSSFSheet sheet = wb.createSheet("考场安排导入");

		    // 设置excel每列宽度
		    //sheet.autoSizeColumn(0);   //宽度自动
		    sheet.setColumnWidth(0, 2000);    //userInfoId编号
		    sheet.setColumnWidth(1, 2000);    //userInfoId编号
		    sheet.setColumnWidth(2, 3000);    //姓名
		    sheet.setColumnWidth(3, 4000);    //准考证号
		    sheet.setColumnWidth(4, 7000);   //操作考试时间
		    sheet.setColumnWidth(5, 7000);   //操作考场号
		    sheet.setColumnWidth(6, 7000);   //操作考试地点
		  
		    // 创建字体样式
		    HSSFFont font = wb.createFont();
		    font.setFontName("Verdana");
		    font.setBoldweight((short) 90);
		    font.setFontHeight((short) 210);
		    font.setColor(HSSFColor.BLACK.index);
	        

		    
		    // 创建单元格样式
		    HSSFCellStyle style = wb.createCellStyle();
		    HSSFDataFormat format = wb.createDataFormat();
		    style.setDataFormat(format.getFormat("@"));                //设置成文本格式
		    style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //中间对齐
		    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		    style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		    // 设置边框
		    style.setBottomBorderColor(HSSFColor.BLACK.index);
		    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		    style.setBorderTop(HSSFCellStyle.BORDER_THIN);

		    style.setFont(font);
		    style.setWrapText(true);

		    
		    
		    
		    
		    // 创建Excel的sheet一行    ，***********创建标题行
		    HSSFRow row = sheet.createRow(0);
		    row.setHeight((short) 500);// 设定行的高度
		    // 创建一个Excel的单元格
		    HSSFCell cell = row.createCell(0);
		    cell.setCellStyle(style);
		    cell.setCellValue("用户ID");    
		    cell = row.createCell(1);   
		    cell.setCellStyle(style);
		    cell.setCellValue("考试ID");  
		    
		    cell = row.createCell(2);   
		    cell.setCellStyle(style);
		    cell.setCellValue("姓名"); 
		    
		    cell = row.createCell(3);
		    cell.setCellStyle(style);
		    cell.setCellValue("准考证号");  
		    
		    cell = row.createCell(4);
		    cell.setCellStyle(style);
		    cell.setCellValue("考试时间"); 

		    cell = row.createCell(5);
		    cell.setCellStyle(style);
		    cell.setCellValue("考场号"); 
		    
		    cell = row.createCell(6);
		    cell.setCellStyle(style);
		    cell.setCellValue("考试地点"); 
		    
		    
		    
		    // 创建Excel的sheet一行    ，***********创建标题行
		    
		    
		    //@
	/*	    MutiTableDao md=new MutiTableDaoImp();
		    List<ScoreExcel> list0=md.getNumberXlsInfo();*/
		    List<ExamInfo>  list = new ExamInfoDaoImp().getAllExamInfo();
		    System.out.println(list.size());
			for(int i=0;i<list.size();i++){
				ExamInfo ei=list.get(i);
		    	row = sheet.createRow(i+1);
			    row.setHeight((short) 500);// 设定行的高度
			    // 创建一个Excel的单元格
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
	 
		    // 合并单元格(startRow，endRow，startColumn，endColumn)
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
			  // 创建Excel的工作书册 Workbook,对应到一个excel文档
		    HSSFWorkbook wb = new HSSFWorkbook();

		    // 创建Excel的工作sheet,对应到一个excel文档的tab
		    HSSFSheet sheet = wb.createSheet("审核详情");

		    // 设置excel每列宽度
		    //sheet.autoSizeColumn(0);   //宽度自动
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
		    // 创建字体样式
		    HSSFFont font = wb.createFont();
		    font.setFontName("Verdana");
		    font.setBoldweight((short) 90);
		    font.setFontHeight((short) 210);
		    font.setColor(HSSFColor.BLACK.index);
	      

		    
		    // 创建单元格样式
		    HSSFCellStyle style = wb.createCellStyle();
		    HSSFDataFormat format = wb.createDataFormat();
		    style.setDataFormat(format.getFormat("@"));                //设置成文本格式
		    style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //中间对齐
		    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		    style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		    // 设置边框
		    style.setBottomBorderColor(HSSFColor.BLACK.index);
		    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		    style.setBorderTop(HSSFCellStyle.BORDER_THIN);

		    style.setFont(font);
		    style.setWrapText(true);

		    
		    
		    
		    
		    // 创建Excel的sheet一行    ，***********创建标题行
		    HSSFRow row = sheet.createRow(0);
		    row.setHeight((short) 500);// 设定行的高度
		    // 创建一个Excel的单元格
		    HSSFCell cell = row.createCell(0);
		    cell.setCellStyle(style);
		    cell.setCellValue("用户ID");
		    
		    cell = row.createCell(1);
		    cell.setCellStyle(style);
		    cell.setCellValue("真实姓名");
		    
		    cell = row.createCell(2);
		    cell.setCellStyle(style);
		    cell.setCellValue("身份证号");
		    
		    cell = row.createCell(3);
		    cell.setCellStyle(style);
		    cell.setCellValue("性别");
		    
		    cell = row.createCell(4);
		    cell.setCellStyle(style);
		    cell.setCellValue("工作单位");
		    
		    cell = row.createCell(5);
		    cell.setCellStyle(style);
		    cell.setCellValue("邮寄通信地址");
		    
		    cell = row.createCell(6);
		    cell.setCellStyle(style);
		    cell.setCellValue("电话");
		    
		    cell = row.createCell(7);
		    cell.setCellStyle(style);
		    cell.setCellValue("从事电缆制作年限");
		    
		    cell = row.createCell(8);
		    cell.setCellStyle(style);
		    cell.setCellValue("报考类别");
		    
		    cell = row.createCell(9);
		    cell.setCellStyle(style);
		    cell.setCellValue("单位类别");
		    
		    
		    // 创建Excel的sheet一行    ，***********创建标题行
		    //复审通过的考生
		    ExamineeInfoDao examInfoDao = new ExamineeInfoDaoImp();
			List<ExamineeInfo> list = examInfoDao.getExamineeInfoByCheckState("1");
			for(int i=0;i<list.size();i++){
				ExamineeInfo se=list.get(i);
		    	row = sheet.createRow(i+1);
			    row.setHeight((short) 500);// 设定行的高度
			    // 创建一个Excel的单元格
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
			    //准考证号
			    cell = row.createCell(3);
			    cell.setCellStyle(style);
			    if(se.getSex()!=null&&!se.getSex().equals("")){
				    cell.setCellValue(se.getSex().equals("1")?"男":"女");
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
							limit="1年及以下";
							break;
						case 1:
							limit="1-2年";
							break;
						case 2:
							limit="2-3年";
							break;
						case 3:
							limit="3-5年";
							break;
						case 4:
							limit="5年以上";
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
			    		cell.setCellValue("冷缩");
			    	}else if(se.getSkillLevel().equals("2")){
			    		cell.setCellValue("热缩");
			    	}else if(se.getSkillLevel().equals("3")){
			    		cell.setCellValue("冷缩+热缩");
			    	}
			    }
			    cell = row.createCell(9);
			    cell.setCellStyle(style);
			    if(se.getExamineeSource()!=null&&!se.getExamineeSource().equals("")){
			    	if(se.getExamineeSource().equals("fengs")){
			    		cell.setCellValue("分电力公司");
			    	}else if(se.getExamineeSource().equals("nongdiangs")){
			    		cell.setCellValue("农电服务公司");
			    	}else if(se.getExamineeSource().equals("shehuidw")){
			    		cell.setCellValue("社会施工单位");
			    	}
			    }
			    
		    }

		    // 合并单元格(startRow，endRow，startColumn，endColumn)
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
