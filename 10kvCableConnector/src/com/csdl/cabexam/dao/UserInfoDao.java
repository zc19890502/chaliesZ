package com.csdl.cabexam.dao;

import java.util.List;

import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.UserInfo;

public interface UserInfoDao {
	//�����û�Id�õ��û�����
	public UserInfo getUserInfoById(Integer userId);
	//�����û����õ��û�����
	public UserInfo getUserInfoByName(String account);
	//�������֤�ŵõ��û�����
	public UserInfo getUserInfoByidnum(String idnum);
	//�õ��û�������Ϣ
	public List<UserInfo> getAllUserInfo();
	//�õ�����״̬��1��userInfo
	public List<UserInfo> getAllUserInfoByState(String userState);
}
