package com.csdl.cabexam.dao;

import java.util.List;

import com.csdl.cabexam.beans.Notice;

public interface NoticeDao {
	//得到所有的通知信息
	public List<Notice> getAllNoticeInfo();
	//根据条件得到通知公告信息
	public List<Notice> getNoticeByNoticeType(String noticeType,int pageSize);
	//根据noticeid获得该公告
	public Notice getNoticeByNoticeId(int noticeId);
	//根据条件查询notice
	public List<Notice> getAllNoticeInfoByCondition(String head,String type);
}
