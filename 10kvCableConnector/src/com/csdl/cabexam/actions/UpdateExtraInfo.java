package com.csdl.cabexam.actions;

import com.csdl.cabexam.beans.ExtraInfo;
import com.csdl.cabexam.dao.ExtraInfoDao;
import com.csdl.cabexam.dao.imp.ExtraInfoDaoImp;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class UpdateExtraInfo extends ActionSupport {
    private ExtraInfo extraInfo;
  
	public ExtraInfo getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(ExtraInfo extraInfo) {
		this.extraInfo = extraInfo;
	}

	public String update() throws Exception{
		Timestamp limit3=extraInfo.getSignLimitDate();
		if(limit3!=null){	
			ExtraInfoDao eid = new ExtraInfoDaoImp();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateStr = sdf.format(limit3);
			String year = dateStr.substring(0, 4);
			String batch = extraInfo.getExamBatch().substring(8, 10);
			boolean flag=eid.updateExtraInfo(
					80f, extraInfo.getSignLimitDate(),extraInfo.getTheoryExamDate(),
					extraInfo.getExamBatch(),extraInfo.getTheoryExamPrice(),
					extraInfo.getColdExamPrice(),extraInfo.getHotExamPrice());
			flag=eid.updateExtraHistory(
					80f, extraInfo.getSignLimitDate(),extraInfo.getTheoryExamDate(),
					extraInfo.getExamBatch(),extraInfo.getTheoryExamPrice(),
					extraInfo.getColdExamPrice(),extraInfo.getHotExamPrice(),year,batch);
		    if(flag){
		    	return "success";
		    }else{
		    	return "error";
		    }
		}else {
			return "error";
		}
		
	}
	
	
}

