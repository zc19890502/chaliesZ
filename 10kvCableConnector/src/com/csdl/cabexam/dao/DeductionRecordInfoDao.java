package com.csdl.cabexam.dao;

import java.util.List;

import com.csdl.cabexam.beans.DeductionRecordInfo;

public interface DeductionRecordInfoDao {
	//����certId��ȡDeduction����
	public List<DeductionRecordInfo> getDeducRecordBycertId(int certInfoId);
	//����certId������п۷���
//	public int getAllDeductionBycertId(int certInfoId);
	//����id��ÿ۷ּ�¼
	public DeductionRecordInfo getDeducRecordBydeducId(int deducId);
}
