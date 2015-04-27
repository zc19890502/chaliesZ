package com.csdl.cabexam.dao;


public interface CommonDao {
	//添加一个对象
	public void addObject(Object obj);
	//删除一个对象
	public void deleteObject(Object obj);
	//修改一个对象
	public void updateObject(Object obj);
	
	
	//添加一个对象，返回其主键
	public Integer saveObjectAndGetObjectId(Object obj);
	
	//获得某一个表的记录条数
	public long getCountNum(String type);
	
}
