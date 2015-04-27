package com.csdl.cabexam.actions.manager.service;


import com.csdl.cabexam.beans.ManagerInfo;
import com.csdl.cabexam.beans.ManagerInfo;
import com.csdl.cabexam.beans.UserInfo;
import com.csdl.cabexam.dao.CommonDao;
import com.csdl.cabexam.dao.ManagerInfoDao;
import com.csdl.cabexam.dao.UserInfoDao;
import com.csdl.cabexam.dao.imp.CommonDaoImp;
import com.csdl.cabexam.dao.imp.ManagerInfoDaoImp;
import com.csdl.cabexam.dao.imp.UserInfoDaoImp;
import com.csdl.cabexam.util.MD5Encoding;

public class ManagerService {
	/*
	 * ��֤�û���0��ʾ�޴��û���1��ʾ�������2��ʾ��֤ͨ��
	 * 
	 */
	public int checkManager(ManagerInfo managerInfo){
		int flag=0;
		ManagerInfoDao md = new ManagerInfoDaoImp();
		ManagerInfo manager_db =md.getManagerInfoByName(managerInfo.getManagerAccount());
		System.out.println(manager_db);
		if(null!=manager_db){
			MD5Encoding md5 = new MD5Encoding();
			if(md5.getMD5ofStr(managerInfo.getPasswd()).equals(manager_db.getPasswd())){
				flag=2;
			}else{
				flag=1;
			}
		}
		System.out.println(flag);
		return flag;
	}
	
	/*
	 * ɾ������Ա
	 * 
	 */
	public boolean deleteManager(int managerId){
        boolean flag =true;
		
		CommonDao cd = new CommonDaoImp();
	
		ManagerInfoDao md = new ManagerInfoDaoImp();
	
		ManagerInfo managerInfo = md.getManagerInfoById(managerId);
		
		if(null!=managerInfo){
				cd.deleteObject(managerInfo);
			}else{
				
				flag=false;
			}
		
		return flag;
	}

	/*
	 * ��ӹ���Ա��0��ʾ�û����ظ���1��ʾע��ɹ�
	 * 
	 */
	public int addManagerInfo(ManagerInfo managerInfo){
		int flag = 1;
		CommonDao cd = new CommonDaoImp();
		ManagerInfoDao um = new ManagerInfoDaoImp();
		ManagerInfo manager_db = um.getManagerInfoByName(managerInfo.getManagerAccount());
		if(null==manager_db){
				MD5Encoding md5 = new MD5Encoding();
				managerInfo.setPasswd(md5.getMD5ofStr(managerInfo.getPasswd()));
				cd.addObject(managerInfo);	
		}
			else{
				flag=0;
			}
		
		return flag;
	}
	
	public int updateManagerInfo(ManagerInfo managerInfo){
		int flag = 1;
		CommonDao cd = new CommonDaoImp();
		ManagerInfoDao um = new ManagerInfoDaoImp();
		MD5Encoding md5 = new MD5Encoding();
		managerInfo.setPasswd(md5.getMD5ofStr(managerInfo.getPasswd()));
		if(null==managerInfo.getPowerGrade()||"".equals(managerInfo.getPowerGrade())){
			managerInfo.setPowerGrade("1");
		}
		cd.updateObject(managerInfo);	
		return flag;
	}
	
	

	/*
	 * �޸Ĺ���Ա����
	 * 
	 * 
	 */
	public boolean updateManagerPassword(int managerId,String password){
		ManagerInfoDao managerDao = new ManagerInfoDaoImp();
		CommonDao cd = new CommonDaoImp();
		ManagerInfo managerInfo = managerDao.getManagerInfoById(managerId);
		if(null!=managerInfo&&!"".equals(managerInfo)){
			MD5Encoding md5 = new MD5Encoding();
			managerInfo.setPasswd(md5.getMD5ofStr(password));
			cd.updateObject(managerInfo);
			return true;
		}else{
			return false;
		}
	}
}
