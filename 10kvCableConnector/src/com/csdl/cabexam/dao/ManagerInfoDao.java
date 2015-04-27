package com.csdl.cabexam.dao;

import java.util.List;

import com.csdl.cabexam.beans.ManagerInfo;
import com.csdl.cabexam.beans.UserInfo;

public interface ManagerInfoDao {
	public ManagerInfo getManagerInfoById(int managerId);
	public ManagerInfo getManagerInfoByName(String managerAccount);
	public List<ManagerInfo> getAllManagerInfo();
}
