package com.csdl.cabexam.dao;


public interface CommonDao {
	//���һ������
	public void addObject(Object obj);
	//ɾ��һ������
	public void deleteObject(Object obj);
	//�޸�һ������
	public void updateObject(Object obj);
	
	
	//���һ�����󣬷���������
	public Integer saveObjectAndGetObjectId(Object obj);
	
	//���ĳһ����ļ�¼����
	public long getCountNum(String type);
	
}
