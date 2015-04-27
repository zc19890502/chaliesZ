package com.csdl.cabexam.dao;

import com.csdl.cabexam.beans.ExtraHistory;
import com.csdl.cabexam.beans.ExtraInfo;

import java.sql.Timestamp;
import java.util.List;

public interface ExtraInfoDao {
	//�޸ļ������������ʱ������
	public boolean updateExtraInfo(
			Float theoryScoreLimit,Timestamp signLimitDate,Timestamp theoryExamDate,
			String examBatch,String theoryExamPrice,String coldExamPrice,String hotExamPrice);
	//�õ�ExtraInfo
	public ExtraInfo getExtraInfo();
	
	public List<String> getDistinctYear();
	
	public List<String> getDistinctBatch(String year);
	
	public List<String> getDistinctBatch();
	
	public List<ExtraHistory> getExtraHistory();
	
	public ExtraHistory getExtraHistory(String examBatch);
	
	public boolean updateExtraHistory(Float theoryScoreLimit,Timestamp signLimitDate,Timestamp theoryExamDate,String examBatch,String theoryExamPrice,String coldExamPrice,String hotExamPrice,String year,String batch);
}
