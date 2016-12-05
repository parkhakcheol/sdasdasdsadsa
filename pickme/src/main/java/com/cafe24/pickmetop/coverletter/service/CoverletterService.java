package com.cafe24.pickmetop.coverletter.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.pickmetop.coverletter.model.CoverletterBookmarkListVo;
import com.cafe24.pickmetop.coverletter.model.CoverletterCompanyJobInfoVo;
import com.cafe24.pickmetop.coverletter.model.CoverletterMemberArticleSaveVo;
import com.cafe24.pickmetop.coverletter.model.CoverletterMemberArticleVo;
import com.cafe24.pickmetop.coverletter.model.CoverletterMemberVo;
import com.cafe24.pickmetop.coverletter.model.ResumeCoverletterInfoCountVo;
import com.cafe24.pickmetop.coverletter.repository.CoverletterDao;
import com.cafe24.pickmetop.freeboard.model.FreeboardBookmarkVo;
import com.cafe24.pickmetop.freeboard.model.FreeboardVo;


@Service
public class CoverletterService {
	final static Logger logger = LoggerFactory.getLogger(CoverletterService.class);
	@Autowired
	CoverletterDao coverletterDao;
	
	// 01 ��������(�����ӹ�ä�������Ʈ20)
	public List<CoverletterCompanyJobInfoVo> getCompanyJobCoverletterListForInfo(){
		return coverletterDao.selectCoverletterCompanyJobListForInfo();
	}
	// 02 ��������(����ä�븮��Ʈ20)
	public List<CoverletterBookmarkListVo> getMyRecruitBookmarkListForInfo(String loginId){
		return coverletterDao.selectMyRecruitBookmarkListForInfo(loginId);
	}
	// 03 ��������(�Խñ۸���Ʈ20)
	public List<FreeboardVo> getMyFreeboardWriteListForInfo(String loginId){
		return coverletterDao.selectMyFreeboardWriteListForInfo(loginId);
	}
	// 04 ��������(���ɰԽñ۸���Ʈ20)
	public List<FreeboardBookmarkVo> getMyFreeboardBookmarkListForInfo(String loginId){
		return coverletterDao.selectMyFreeboardBookmarkListForInfo(loginId);
	}
	
	// 05 ��������(�����ڼҼ�Count)
	public ResumeCoverletterInfoCountVo getMyCoverletterListCount(String loginId){
		return coverletterDao.selectMyCoverletterListCount(loginId);
	}
	// 06 ��������(�����̷¼�Count)
	public ResumeCoverletterInfoCountVo getMyResumeListCount(String loginId){
		return coverletterDao.selectMyResumeListCount(loginId);
	}
	// 07 ��������(����ä��ϸ�ũCount)
	public ResumeCoverletterInfoCountVo getMyRecruitListCount(String loginId){
		return coverletterDao.selectMyRecruitListCount(loginId);
	}
	// 08 ���� ����(���ǰԽñ�Count)
	public ResumeCoverletterInfoCountVo getMyFreeboardWriteCount(String loginId){
		return coverletterDao.selectMyFreeboardWriteCount(loginId);
	}
	// 08 ��������(���ɱ�Count)
	public ResumeCoverletterInfoCountVo getMyFreeboardBookmarkCount(String loginId){
		return coverletterDao.selectMyFreeboardBookmarkCount(loginId);
	}
	
	// 09 �ڱ�Ұ��� ����Ʈ(ȸ���� ���� �ۼ��� �ڱ�Ұ��� ����Ʈ)
	public List<CoverletterMemberVo> getMemberCoverletterList(String loginId){
		return coverletterDao.selectCoverletterMemberList(loginId);
	}
	
	// 10 ���ä������� �ڱ�Ұ��� ����Ʈ(�ڱ�Ұ����� �˻��̳� üũ����Ʈ üũ�� ���� �Է�ȭ������ �̵�)
	public List<CoverletterCompanyJobInfoVo> getCompanyJobCoverletterList(){
		return coverletterDao.selectCoverletterCompanyJobList();
	}
	
	// 11_01 �ڱ�Ұ��� �Է�ȭ��(�ڱ�Ұ����̸�(ä������+ä���+������), �ڱ�Ұ��� ���� ��������, ä�������� �ڱ�Ұ����׸񸮽�Ʈ)
	public Map<String, Object> getCompanyOneJobCletter(String recruitJobCd){
		Map<String, Object> companyOneJobMap = new HashMap<String, Object>();
		companyOneJobMap.put("companyOneJobCletterInfo", coverletterDao.selectOneCletterCompanyJobInfo(recruitJobCd));
		logger.info("companyOneJobCletterInfo {}", companyOneJobMap.toString());
		companyOneJobMap.put("companyOneJobArticleList", coverletterDao.selectListCletterArticleByJobCd(recruitJobCd));
		logger.info("companyOneJobArticleList {}", companyOneJobMap.toString());
		return companyOneJobMap;
	}
	
	// 12_02 �ڱ�Ұ��� �Է�ó��(�ڱ�Ұ��� �̸�/�����ð�/����/����)
	public void addCoverletter(CoverletterMemberVo coverletterMember, 
							CoverletterMemberArticleVo memberArticle,
							CoverletterMemberArticleSaveVo saveRecord,
							HttpSession session
							){
		coverletterMember.setLoginId((String) session.getAttribute("generalId"));
		coverletterDao.insertCoverletter(coverletterMember);
		coverletterDao.insertCoverletterArticle(memberArticle);
		coverletterDao.insertCoverletterSaveRecord(saveRecord);
		logger.info("coverletterMember {}", coverletterMember.toString());
		logger.info("memberArticle {}", memberArticle.toString());
		logger.info("saveRecord {}", saveRecord.toString());
	}
	
	// 13_02 �ڱ�Ұ��� �󼼺���
	public Map<String, Object> getMemberCoverletter(String mCletterCd){
		Map<String, Object> memberCoverletterMap = new HashMap<String, Object>();
		memberCoverletterMap.put("memeberCoverletterInfo", coverletterDao.selectOneMemeberCoverletterInfo(mCletterCd));
		logger.info("memeberCoverletterInfo {}", memberCoverletterMap.toString());
		memberCoverletterMap.put("memeberCoverletterArticleList", coverletterDao.selectMemeberCoverletterArticleList(mCletterCd));
		logger.info("memeberCoverletterArticleList {}", memberCoverletterMap.toString());
		memberCoverletterMap.put("memeberCoverletterArticleSaveRecord", coverletterDao.selectMemeberCoverletterArticleSaveRecord(mCletterCd));
		logger.info("memeberCoverletterArticleSaveRecord {}", memberCoverletterMap.toString());
		return memberCoverletterMap;
	}
	
	// 14_01 ���� �ڱ�Ұ��� & �̷¼� ����
	public Map<String, Object> getMemberInfo(String loginId){
		return null;
	}
	
	// 05_01_01 �հ��ڱ�Ұ��� ����Ʈ(�˾���:�����)
	// 05_01_02 �հ��ڱ�Ұ��� ����Ʈ(��������������: ������)
	// 05_02 �հ��ڱ�Ұ��� �Է���
	// 05_03 �հ��ڱ�Ұ��� �Է�ó��
	// 05_04 �հ��ڱ�Ұ��� �󼼺���
	// 05_05 �հ��ڱ�Ұ��� ����
	// 05_06 �հ��ڱ�Ұ��� ����
	
	
}
