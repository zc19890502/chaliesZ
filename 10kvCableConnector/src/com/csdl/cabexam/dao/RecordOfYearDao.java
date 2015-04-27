package com.csdl.cabexam.dao;

import java.util.List;

import com.csdl.cabexam.beans.RecordOfYear;

public interface RecordOfYearDao {
	//根据用户身份证取得年度评价   参数为null时表示查询所有
	public List<RecordOfYear> getRecordByIdYear(String IdNumber,String yyyyDate);
	//增删改 用common的方法
	public RecordOfYear getRecordById(Integer recordId);
}
