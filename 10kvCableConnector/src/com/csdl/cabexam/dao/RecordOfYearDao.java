package com.csdl.cabexam.dao;

import java.util.List;

import com.csdl.cabexam.beans.RecordOfYear;

public interface RecordOfYearDao {
	//�����û����֤ȡ���������   ����Ϊnullʱ��ʾ��ѯ����
	public List<RecordOfYear> getRecordByIdYear(String IdNumber,String yyyyDate);
	//��ɾ�� ��common�ķ���
	public RecordOfYear getRecordById(Integer recordId);
}
