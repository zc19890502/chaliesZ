package com.csdl.cabexam.dao;

import java.util.List;

import com.csdl.cabexam.beans.Notice;

public interface NoticeDao {
	//�õ����е�֪ͨ��Ϣ
	public List<Notice> getAllNoticeInfo();
	//���������õ�֪ͨ������Ϣ
	public List<Notice> getNoticeByNoticeType(String noticeType,int pageSize);
	//����noticeid��øù���
	public Notice getNoticeByNoticeId(int noticeId);
	//����������ѯnotice
	public List<Notice> getAllNoticeInfoByCondition(String head,String type);
}
