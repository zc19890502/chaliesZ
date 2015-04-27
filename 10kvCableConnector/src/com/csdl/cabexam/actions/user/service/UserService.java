package com.csdl.cabexam.actions.user.service;

import com.csdl.cabexam.beans.UserInfo;
import com.csdl.cabexam.dao.CommonDao;
import com.csdl.cabexam.dao.UserInfoDao;
import com.csdl.cabexam.dao.imp.CommonDaoImp;
import com.csdl.cabexam.dao.imp.UserInfoDaoImp;
import com.csdl.cabexam.util.MD5Encoding;


public class UserService {
	/*
	 * ��֤�û���0��ʾ�޴��û���1��ʾ�������2��ʾ��֤ͨ��
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
	 * ����û���0��ʾ�û����ظ���1��ʾ���֤�ظ���2��ʾע��ɹ�
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
