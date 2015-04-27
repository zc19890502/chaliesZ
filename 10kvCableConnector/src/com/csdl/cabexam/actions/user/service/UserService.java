package com.csdl.cabexam.actions.user.service;

import com.csdl.cabexam.beans.UserInfo;
import com.csdl.cabexam.dao.CommonDao;
import com.csdl.cabexam.dao.UserInfoDao;
import com.csdl.cabexam.dao.imp.CommonDaoImp;
import com.csdl.cabexam.dao.imp.UserInfoDaoImp;
import com.csdl.cabexam.util.MD5Encoding;


public class UserService {
	/*
	 * 验证用户，0表示无此用户，1表示密码错误，2表示验证通过
	 * 
	 */
	public int checkUser(UserInfo userInfo){
		int flag=0;
		UserInfoDao ud = new UserInfoDaoImp();
		UserInfo user_db = ud.getUserInfoByName(userInfo.getAccount());
		if(null!=user_db){
			MD5Encoding md5 = new MD5Encoding();
			if(md5.getMD5ofStr(userInfo.getPasswd()).equals(user_db.getPasswd())){
				flag=2;
			}else{
				flag=1;
			}
		}
		return flag;
	}
	/*
	 * 添加用户，0表示用户名重复，1表示身份证重复，2表示注册成功
	 * 
	 */
	public int addUserInfo(UserInfo userInfo){
		int flag = 0;
		CommonDao cd = new CommonDaoImp();
		UserInfoDao ud = new UserInfoDaoImp();
		UserInfo user_db = ud.getUserInfoByName(userInfo.getAccount());
		UserInfo user_dbBycerId = ud.getUserInfoByidnum(userInfo.getIdnum());
		if(null==user_db){
			if(null==user_dbBycerId){
				MD5Encoding md5 = new MD5Encoding();
				userInfo.setPasswd(md5.getMD5ofStr(userInfo.getPasswd()));
				cd.addObject(userInfo);
				flag=2;
			}else{
				flag=1;
			}
		}
		return flag;
	}
	
}
