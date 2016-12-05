package com.cafe24.pickmetop.company.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.pickmetop.admin.model.JobTopIndexVo;
import com.cafe24.pickmetop.commons.PageHelper;
import com.cafe24.pickmetop.company.model.*;
import com.cafe24.pickmetop.company.repository.CompanyDao;


@Service
public class CompanyService {
	private static final Logger logger = LoggerFactory.getLogger(CompanyService.class);
	private final int MAX_LINE_COUNT = 5; //������ ���� ��
    private final int MAX_PAGE_COUNT = 5; //������ �ִ� ������ ��
    
	@Autowired
	CompanyDao companyDao;
	/*---------------------------------------------------------------------------------- 
	 * 									������� ����
	 * ---------------------------------------------------------------------------------*/
	//��� ���� Top10
	public List<CompanyInfoVo> getCompanyTotalRateTop(){
		return companyDao.selectCompanyByTotalRate();
	}
	/*---------------------------------------------------------------------------------- 
	 * 									������� ������
	 * ---------------------------------------------------------------------------------*/ 
	//������� ��� ����Ʈ �� (��������� �˻�����)
	public Map<String, Object> getCompantInfoList(int page, String searchCompanyName){
		PageHelper pageHelper = new PageHelper(page,MAX_LINE_COUNT);
		pageHelper.setLastPage(companyDao.selectCompanyInfoListCount(searchCompanyName), MAX_LINE_COUNT);
		Map<String, Object> companyInfoMap = new HashMap<String, Object>();
		Map<String, Object> companyoInfoSearchMap = new HashMap<String, Object>();
		
		companyoInfoSearchMap.put("pageHelp", pageHelper);
		companyoInfoSearchMap.put("searchCompanyName", searchCompanyName);
		
		companyInfoMap.put("startPage", pageHelper.startPage(page, MAX_PAGE_COUNT));
		companyInfoMap.put("endPage", pageHelper.endPage());
		companyInfoMap.put("companyInfoList", companyDao.selectCompanyInfoList(companyoInfoSearchMap));
		logger.info("listsize : {}", companyDao.selectCompanyInfoList(companyoInfoSearchMap).size());
		return companyInfoMap;
	}
	//������� ������
	public CompanyInfoVo getCompnayInfoDetail(String companyName){
		return companyDao.selectCompanyInfoDetailByCompanyName(companyName);
	}
	
	/*---------------------------------------------------------------------------------- 
	* 									�������� ����
	* ---------------------------------------------------------------------------------*/ 
	//�������� ���
	public int addCompanySalary(CompanySalaryVo companySalaryVo){
		companySalaryVo.setCompanyCd(companyDao.selectCompanyInfoByCompanyCd(companySalaryVo.getCompanyName()));
		companySalaryVo.setLoginId("kji212@naver.com");
		return companyDao.insertCompanySalary(companySalaryVo);
	}
	//�������� ����� ���
	public Map<String, Object> getCompanySalaryUnreceivedList(int page){
		//����θ�� ������ ó��
		PageHelper pageHelper = new PageHelper(page,MAX_LINE_COUNT);
		//���λ��°� 0�� ����λ����ΰ͸� ������ ���ͼ� ������������ SET
		String tbName = "tb_company_salary";
		String tbColumn = "salary_allow";
		pageHelper.setLastPage(companyDao.selectAllowTotalCount(tbName, tbColumn, 0),MAX_LINE_COUNT);
		Map<String, Object> salaryUnreceivedMap = new HashMap<String, Object>();
		salaryUnreceivedMap.put("startPage", pageHelper.startPage(page, MAX_PAGE_COUNT));
		salaryUnreceivedMap.put("endPage", pageHelper.endPage());
		salaryUnreceivedMap.put("interviewListUnreceived", companyDao.selectCompanySalaryListBySalaryUnreceived(pageHelper));
		return salaryUnreceivedMap;
	}
	//�������� ������
	public CompanySalaryVo getCompnaySalaryDetail(int salaryCd){
		return companyDao.selectCompanySalaryDetailBySalaryCd(salaryCd);
	}
	//�������� ó��
	public int updateCompanySalaryAllow(int salaryCd, String salaryWorklevel, String companyName){
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		String tbColumn = "";
		sqlMap.put("loginId", "admin");
		sqlMap.put("salaryCd", salaryCd);
		companyDao.updateCompanySalaryAllow(sqlMap);
		
		if(salaryWorklevel.equals("����")){
			tbColumn = "company_statistics_salary_bj";
		}else if(salaryWorklevel.equals("����")){
			tbColumn = "company_statistics_salary_cj";
		}else if(salaryWorklevel.equals("����")){
			tbColumn = "company_statistics_salary_gj";
		}else if(salaryWorklevel.equals("�븮")){
			tbColumn = "company_statistics_salary_dr";
		}else if(salaryWorklevel.equals("���")){
			tbColumn = "company_statistics_salary_sw";
		}
		sqlMap.put("tbColumn", tbColumn);
		sqlMap.put("companyName", companyName);
		
		return companyDao.updateCompanyStatisticsSalary(sqlMap);
	}
	//�������� ���� ó��
	public int deleteCompnaySalary(int salaryCd){
		return companyDao.deleteCompanySalary(salaryCd);
	}
	//�������� ���
	public CompanyStatisticsVo getCompanyStatisticsSalary(String companyName){
		return companyDao.selectCompanyStatisticsSalaryByCompanyName(companyName);
	}
	/*---------------------------------------------------------------------------------- 
	 * 									�����ı� ����
	 * ---------------------------------------------------------------------------------*/ 
	//�����ı� ���
	public int addCompnayInterview(CompanyInterviewVo companyInterviewVo){
		companyInterviewVo.setCompanyCd(companyDao.selectCompanyInfoByCompanyCd(companyInterviewVo.getCompanyName()));
		companyInterviewVo.setLoginId("kji212@naver.com");
		return companyDao.insertCompanyInterview(companyInterviewVo);
	}
	//�����ı� ����� ���
	public Map<String, Object> getCompanyInterviewUnreceivedList(int page){
		//����θ�� ������ ó��
		PageHelper pageHelper = new PageHelper(page,MAX_LINE_COUNT);
		//���λ��°� 0�� ����λ����ΰ͸� ������ ���ͼ� ������������ SET
		String tbName = "tb_company_interview";
		String tbColumn = "interview_allow";
		pageHelper.setLastPage(companyDao.selectAllowTotalCount(tbName, tbColumn, 0),MAX_LINE_COUNT);
		Map<String, Object> interviewUnreceivedMap = new HashMap<String, Object>();
		interviewUnreceivedMap.put("startPage", pageHelper.startPage(page, MAX_PAGE_COUNT));
		interviewUnreceivedMap.put("endPage", pageHelper.endPage());
		interviewUnreceivedMap.put("interviewListUnreceived", companyDao.selectCompanyInterviewListByInterviewUnreceived(pageHelper));
		return interviewUnreceivedMap;
	}
	//�����ı� ���θ���Ʈ
	public Map<String, Object> getCompanyInterviewAllowList(int page, String jobTopIndexCd, String searchCompanyName){
		//���θ�� ������ ó��
		PageHelper pageHelper = new PageHelper(page,MAX_LINE_COUNT);
		String tbName = "tb_company_interview";
		String tbColumn = "interview_allow";
		//���λ��°� 1�� ���λ����ΰ͸� ������ ���ͼ� ������������ SET
		if(jobTopIndexCd.equals("") && searchCompanyName.equals("")){
			logger.info("null");
			pageHelper.setLastPage(companyDao.selectAllowTotalCount(tbName, tbColumn, 1),MAX_LINE_COUNT);
		}else{
			logger.info("not null : {}", companyDao.selectAllowSearchCount(tbName, tbColumn, jobTopIndexCd, searchCompanyName));
			pageHelper.setLastPage(companyDao.selectAllowSearchCount(tbName, tbColumn, jobTopIndexCd, searchCompanyName),MAX_LINE_COUNT);
		}
		//����¡ ó������ ������������ ��������
		Map<String, Object> interviewAllowMap = new HashMap<String, Object>();
		interviewAllowMap.put("startPage", pageHelper.startPage(page, MAX_PAGE_COUNT));
		interviewAllowMap.put("endPage", pageHelper.endPage());
		
		//�˻��� ������ ���� �˻����ǵ� ��
		Map<String, Object> interviewSearchMap = new HashMap<String, Object>();
		interviewSearchMap.put("pageHelp", pageHelper);
		interviewSearchMap.put("jobTopIndexCd", jobTopIndexCd);
		interviewSearchMap.put("searchCompanyName", searchCompanyName);
		logger.info("pageHelper {}", pageHelper.toString());
		logger.info("jobTopIndexCd {}", jobTopIndexCd.toString());
		logger.info("searchCompanyName {}", searchCompanyName.toString());
		interviewAllowMap.put("interviewListAllow", companyDao.selectCompanyInterviewListByInterviewAllow(interviewSearchMap));
		return interviewAllowMap;	
	}
	//�����ı� ������
	public CompanyInterviewVo getCompanyInterviewDetail(int interviewCd){
		return companyDao.selectCompanyInterviewDetailByCompanyInterviewCd(interviewCd);
	}
	//�����ı� ����ó��
	public int updateCompanyInterviewAllow(int interviewCd){
		Map<String, Object> allow = new HashMap<String, Object>();
		allow.put("loginId", "admin");
		allow.put("interviewCd", interviewCd);
		return companyDao.updateCompanyInterviewAllow(allow);
	}
	//�����ı� ����ó��
	public int delectCompanyInterview(int interviewCd){
		return companyDao.deleteCompanyInterview(interviewCd);
	}
	
	/*---------------------------------------------------------------------------------- 
	* 									������� ����
	* ---------------------------------------------------------------------------------*/ 
	 
	//���������(����)
	public Map<String, Object> getCompanyReviewAllowList(int page, String jobTopIndexCd, String searchCompanyName){
		//���θ�� ������ ó��
		PageHelper pageHelper = new PageHelper(page,MAX_LINE_COUNT);
		String tbName = "tb_company_review";
		String tbColumn = "review_allow";
		//���λ��°� 1�� ���λ����ΰ͸� ������ ���ͼ� ������������ SET
		if(jobTopIndexCd.equals("") && searchCompanyName.equals("")){
			logger.info("null");
			pageHelper.setLastPage(companyDao.selectAllowTotalCount(tbName, tbColumn, 1),MAX_LINE_COUNT);
		}else{
			logger.info("not null : {}", companyDao.selectAllowSearchCount(tbName, tbColumn, jobTopIndexCd, searchCompanyName));
			pageHelper.setLastPage(companyDao.selectAllowSearchCount(tbName, tbColumn, jobTopIndexCd, searchCompanyName),MAX_LINE_COUNT);
		}
		//����¡ ó������ ������������ ��������
		Map<String, Object> reviewAllowMap = new HashMap<String, Object>();
		reviewAllowMap.put("startPage", pageHelper.startPage(page, MAX_PAGE_COUNT));
		reviewAllowMap.put("endPage", pageHelper.endPage());
		
		//�˻��� ������ ���� �˻����ǵ� ��
		Map<String, Object> reviewSearchMap = new HashMap<String, Object>();
		reviewSearchMap.put("pageHelp", pageHelper);
		reviewSearchMap.put("jobTopIndexCd", jobTopIndexCd);
		reviewSearchMap.put("searchCompanyName", searchCompanyName);
		logger.info("pageHelper {}", pageHelper.toString());
		logger.info("jobTopIndexCd {}", jobTopIndexCd.toString());
		logger.info("searchCompanyName {}", searchCompanyName.toString());
		reviewAllowMap.put("reviewListAllow", companyDao.selectCompanyReviewListByReviewAllow(reviewSearchMap));
		return reviewAllowMap;
	}
	
	//������� ����ó��
	public int deleteCompanyReview(int companyReviewCd){
		return companyDao.deleteCompanyReview(companyReviewCd);
	}
	
	//������� ����ó��
	public int updateCompanyReviewAllow(int companyReviewCd){
		Map<String, Object> allow = new HashMap<String, Object>();
		allow.put("loginId", "admin");
		allow.put("companyReviewCd", companyReviewCd);
		companyDao.updateCompanyInfoTotalRate(allow);
		return companyDao.updateCompanyReviewAllow(allow);
	}
	//������� �󼼺���
	public CompanyReviewVo getCompanyReviewDetail(int companyReviewCd){
		return companyDao.selectCompanyListByReviewCd(companyReviewCd);
	}
	
	//���������(�����)
	public Map<String, Object> getCompanyReviewUnreceivedList(int page){
		//����θ�� ������ ó��
		PageHelper pageHelper = new PageHelper(page,MAX_LINE_COUNT);
		String tbName = "tb_company_review";
		String tbColumn = "review_allow";
		//���λ��°� 0�� ����λ����ΰ͸� ������ ���ͼ� ������������ SET
		pageHelper.setLastPage(companyDao.selectAllowTotalCount(tbName, tbColumn, 0),MAX_LINE_COUNT);
		Map<String, Object> reviewUnreceivedMap = new HashMap<String, Object>();
		reviewUnreceivedMap.put("startPage", pageHelper.startPage(page, MAX_PAGE_COUNT));
		reviewUnreceivedMap.put("endPage", pageHelper.endPage());
		reviewUnreceivedMap.put("reviewListUnreceived", companyDao.selectCompanyReviewListByReviewUnreceived(pageHelper));
		return reviewUnreceivedMap;
	}
	
	//���������(�����) �޼���
	public int addCompanyReview(CompanyReviewVo companyReviewVo){
		companyReviewVo.setCompanyCd(companyDao.selectCompanyInfoByCompanyCd(companyReviewVo.getCompanyName()));
		logger.info("CompanyService -> companyCd2 {}", companyReviewVo.getCompanyCd());
		return companyDao.insertCompanyReview(companyReviewVo);
	}
	//������� ��õ ����õ
	public int updateCompanyReviewLike(int companyReviewCd, int userChoice){
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		if(userChoice == 1){
			sqlMap.put("tbColumn", "review_like");
		}else{
			sqlMap.put("tbColumn", "review_dislike");
		}
		sqlMap.put("companyReviewCd", companyReviewCd);
		return companyDao.updateCompnayReviewLikeByuserChoice(sqlMap);
	}
	
	//����̸� ����Ʈ��� �޼���
	public List<CompanyInfoVo> getCompanyNameList(){
		return companyDao.selectCompanyNameList();
	}
	
	//�����ߺз� ����Ʈ ��� �޼���
	public List<JobTopIndexVo> getJobTopIndexList(){
		return companyDao.selectJobTopIndexAllList();
	}
	
}
