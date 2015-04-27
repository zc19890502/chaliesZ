package com.csdl.cabexam.dao;

import java.util.List;

import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.UserInfo;

public interface UserInfoDao {
	//根据用户Id得到用户对象
	public UserInfo getUserInfoById(Integer userId);
	//根据用户名得到用户对象
	public UserInfo getUserInfoByName(String account);
	//根据身份证号得到用户对象
	public UserInfo getUserInfoByidnum(String idnum);
	//得到用户所有信息
	public List<UserInfo> getAllUserInfo();
	//得到所有状态是1的userInfo
	public List<UserInfo> getAllUserInfoByState(String userState);
}
