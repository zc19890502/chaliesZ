package com.csdl.cabexam.dao;

import java.util.List;

import com.csdl.cabexam.beans.DeductionRecordInfo;

public interface DeductionRecordInfoDao {
	//根据certId获取Deduction对象
	public List<DeductionRecordInfo> getDeducRecordBycertId(int certInfoId);
	//根据certId获得所有扣分数
//	public int getAllDeductionBycertId(int certInfoId);
	//根据id获得扣分记录
	public DeductionRecordInfo getDeducRecordBydeducId(int deducId);
}
