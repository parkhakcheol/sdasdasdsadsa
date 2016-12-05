package com.cafe24.pickmetop.admin.service;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.pickmetop.admin.model.*;
import com.cafe24.pickmetop.admin.repository.AdminDao;

@Service
public class AdminService {
	private static final Logger logger = LoggerFactory.getLogger(AdminService.class);
	
	@Autowired
	AdminDao adminDao;
	
	//������ ������ �� �̽��� �Խñ� �� ��������
	public CountVo getUnreceivedListCount(){
		return adminDao.selectUnreceivedListCount();
	}
	
	//�湮�ڼ� ���ϱ�
	public VisitantCountVo getVisitantCount(){
		Calendar today = Calendar.getInstance();
		String visitantRegdate = today.get(Calendar.YEAR) + "-" + (today.get(Calendar.MONTH)+1) + "-" + today.get(Calendar.DATE);
		return adminDao.selectVisitantCount(visitantRegdate);
	}
	
	//�Ϻ� �湮�� ���
	public List<VisitantCountVo> getRegDateVisitantCount(){
		return adminDao.selectRegDateVisitantCount();
	}
}
