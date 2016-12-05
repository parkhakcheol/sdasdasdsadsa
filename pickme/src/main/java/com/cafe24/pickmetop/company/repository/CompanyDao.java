package com.cafe24.pickmetop.company.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.pickmetop.admin.model.JobTopIndexVo;
import com.cafe24.pickmetop.commons.PageHelper;
import com.cafe24.pickmetop.company.model.*;
import com.cafe24.pickmetop.company.service.CompanyService;

@Repository 
public class CompanyDao {
	private static final Logger logger = LoggerFactory.getLogger(CompanyDao.class);
	final String NS = "com.cafe24.pickmetop.company.repository.CompanyMapper";
	@Autowired
	@Resource(name = "sqlSessionCompany")
	private SqlSessionTemplate sqlSessionFactoryCompany;
	/*
	 * ----------------------------------------------------------------------------------------
	 * 
	 * 									   ������������� ����
	 * 
	 * ----------------------------------------------------------------------------------------
	 * */
	public List<CompanyInfoVo> selectCompanyByTotalRate(){
		return sqlSessionFactoryCompany.selectList(NS + ".selectCompanyByTotalRate");
	}
	/*
	 * ----------------------------------------------------------------------------------------
	 * 
	 * 									   ������������� ����
	 * 
	 * ----------------------------------------------------------------------------------------
	 * */
	public List<CompanyInfoVo> selectCompanyInfoList(Map<String, Object> companyInfoMap){
		return sqlSessionFactoryCompany.selectList(NS + ".selectCompanyInfoList", companyInfoMap);
	}
	
	public int selectCompanyInfoListCount(String searchCompanyName){
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put("searchCompanyName", searchCompanyName);
		return sqlSessionFactoryCompany.selectOne(NS + ".selectCompanyInfoListCount", sqlMap);
	}
	
	public CompanyInfoVo selectCompanyInfoDetailByCompanyName(String companyName){
		return sqlSessionFactoryCompany.selectOne(NS + ".selectCompanyInfoDetailByCompanyName", companyName);
	}
	/*
	 * ----------------------------------------------------------------------------------------
	 * 
	 * 									   ������� ����
	 * 
	 * ----------------------------------------------------------------------------------------
	 * */	
	//������� ���(����)
	public List<CompanyReviewVo> selectCompanyReviewListByReviewAllow(Map<String, Object> reviewSearchMap){
		return sqlSessionFactoryCompany.selectList(NS +".selectCompanyReviewListByReviewAllow", reviewSearchMap);
	}
	//���������(�����) �޼���
	public int insertCompanyReview(CompanyReviewVo companyReviewVo){
		return sqlSessionFactoryCompany.insert(NS + ".insertCompanyReview", companyReviewVo);
	}
	//����ڵ�˻�
	public String selectCompanyInfoByCompanyCd(String companyName){
		return sqlSessionFactoryCompany.selectOne(NS + ".selectCompanyInfoByCompanyCd", companyName);
	}
	
	//������� ���̺��� ����̸� ����Ʈ ��ü
	public List<CompanyInfoVo> selectCompanyNameList(){
		return sqlSessionFactoryCompany.selectList(NS + ".selectCompanyNameList");
	}
	//�����ߺз� ���̺��� ����Ʈ ��ü
	public List<JobTopIndexVo> selectJobTopIndexAllList(){
		return sqlSessionFactoryCompany.selectList(NS + ".selectJobTopIndexAllList");
	}
	//���������(�����)
	public List<CompanyReviewVo> selectCompanyReviewListByReviewUnreceived(PageHelper pageHelper){
		return sqlSessionFactoryCompany.selectList(NS + ".selectCompanyReviewListByReviewUnreceived", pageHelper);
	}
	//�������󼼺���
	public CompanyReviewVo selectCompanyListByReviewCd(int companyReviewCd){
		return sqlSessionFactoryCompany.selectOne(NS + ".selectCompanyListByReviewCd", companyReviewCd);
	}
	//���������� ������Ʈ
	public int updateCompanyReviewAllow(Map<String, Object> allow){
		return sqlSessionFactoryCompany.update(NS + ".updateCompanyReviewAllow", allow);
	}
	//���������ν� ��������
	public int updateCompanyInfoTotalRate(Map<String, Object> allow){
		return sqlSessionFactoryCompany.update(NS + ".updateCompanyInfoTotalRate", allow);
	}
	//����������
	public int deleteCompanyReview(int companyReviewCd){
		return sqlSessionFactoryCompany.delete(NS + ".deleteCompanyReview", companyReviewCd);
	}
	//������� ��� ī��Ʈ
	public int selectAllowTotalCount(String tbName, String tbColumn, int allow){
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put("tbName", tbName);
		sqlMap.put("tbColumn", tbColumn);
		sqlMap.put("allow", allow);
		return sqlSessionFactoryCompany.selectOne(NS + ".selectAllowTotalCount", sqlMap);
	}
	public int selectAllowSearchCount(String tbName, String tbColumn, String jobTopIndexCd, String searchCompanyName){
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put("tbName", tbName);
		sqlMap.put("tbColumn", tbColumn);
		sqlMap.put("jobTopIndexCd", jobTopIndexCd);
		sqlMap.put("searchCompanyName", searchCompanyName);
		return sqlSessionFactoryCompany.selectOne(NS + ".selectAllowSearchCount", sqlMap);
	}
	public int updateCompnayReviewLikeByuserChoice(Map<String, Object> sqlMap){
		return sqlSessionFactoryCompany.update(NS + ".updateCompnayReviewLikeByuserChoice", sqlMap);
	}
	/*
	 * ----------------------------------------------------------------------------------------
	 * 
	 * 									   �����ı� ����
	 * 
	 * ----------------------------------------------------------------------------------------
	 * */
	//�����ı� ���
	public int insertCompanyInterview(CompanyInterviewVo companyInterviewVo){
		return sqlSessionFactoryCompany.insert(NS + ".insertCompanyInterview", companyInterviewVo);
	}
	//�����ı� ����� ����Ʈ(������)
	public List<CompanyInterviewVo> selectCompanyInterviewListByInterviewUnreceived(PageHelper pageHelper){
		return sqlSessionFactoryCompany.selectList(NS + ".selectCompanyInterviewListByInterviewUnreceived", pageHelper);
	}
	//�����ı� ���� ����Ʈ
	public List<CompanyInterviewVo> selectCompanyInterviewListByInterviewAllow(Map<String, Object> interviewSearchMap){
		return sqlSessionFactoryCompany.selectList(NS + ".selectCompanyInterviewListByInterviewAllow", interviewSearchMap);
	}
	//�����ı� ������
	public CompanyInterviewVo selectCompanyInterviewDetailByCompanyInterviewCd(int interviewCd){
		return sqlSessionFactoryCompany.selectOne(NS + ".selectCompanyInterviewDetailByCompanyInterviewCd", interviewCd);
	}
	//�����ı� ���� ������Ʈ
	public int updateCompanyInterviewAllow(Map<String, Object> allow){
		return sqlSessionFactoryCompany.update(NS + ".updateCompanyInterviewAllow", allow);
	}
	//�����ı� ����ó��
	public int deleteCompanyInterview(int interviewCd){
		return sqlSessionFactoryCompany.delete(NS + ".deleteCompanyInterview", interviewCd);
	}
	/*
	 * ----------------------------------------------------------------------------------------
	 * 
	 * 									  �������� ����
	 * 
	 * ----------------------------------------------------------------------------------------
	 * */
	//�������� ���
	public int insertCompanySalary(CompanySalaryVo companySalaryVo){
		return sqlSessionFactoryCompany.insert(NS + ".insertCompanySalary", companySalaryVo);
	}
	//�������� �̽��� ����Ʈ
	public List<CompanySalaryVo> selectCompanySalaryListBySalaryUnreceived(PageHelper pageHelper){
		return sqlSessionFactoryCompany.selectList(NS + ".selectCompanySalaryListBySalaryUnreceived", pageHelper);
	}
	//�������� ������(������)
	public CompanySalaryVo selectCompanySalaryDetailBySalaryCd(int salaryCd){
		return sqlSessionFactoryCompany.selectOne(NS + ".selectCompanySalaryDetailBySalaryCd", salaryCd);
	}
	//�������� ����ó��
	public int updateCompanySalaryAllow(Map<String, Object> sqlMap){
		return sqlSessionFactoryCompany.update(NS + ".updateCompanySalaryAllow", sqlMap);
	}
	//�������� �����ϸ� �������� ��� ���̺� ������Ʈ
	public int updateCompanyStatisticsSalary(Map<String, Object> sqlMap){
		return sqlSessionFactoryCompany.update(NS + ".updateCompanyStatisticsSalary", sqlMap);
	}
	//�������� ����ó��
	public int deleteCompanySalary(int salaryCd){
		return sqlSessionFactoryCompany.update(NS + ".deleteCompanySalary", salaryCd);
	}
	//�������� ���
	public CompanyStatisticsVo selectCompanyStatisticsSalaryByCompanyName(String companyName){
		return sqlSessionFactoryCompany.selectOne(NS + ".selectCompanyStatisticsSalaryByCompanyName", companyName);
	}
}
