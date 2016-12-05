package com.cafe24.pickmetop.recruit.repository;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.pickmetop.admin.model.IndustryTopIndexVo;
import com.cafe24.pickmetop.admin.model.JobMidIndexVo;
import com.cafe24.pickmetop.admin.model.JobTopIndexVo;
import com.cafe24.pickmetop.company.model.CompanyInfoVo;
import com.cafe24.pickmetop.coverletter.model.CoverletterCompanyJobVo;
import com.cafe24.pickmetop.recruit.model.Recruit;
import com.cafe24.pickmetop.recruit.model.RecruitCompany;
import com.cafe24.pickmetop.recruit.model.RecruitCompanyBookmarkVo;
import com.cafe24.pickmetop.recruit.model.RecruitCompanyJobVo;
import com.cafe24.pickmetop.recruit.service.RecruitService;

@Repository
public class RecruitDao {
	final static Logger logger = LoggerFactory.getLogger(RecruitDao.class);
	private final String NS = "com.cafe24.pickmetop.recruit.repository.RecruitMapper";
	@Autowired
	@Resource(name = "sqlSessionRecruit")
	private SqlSessionTemplate sqlSessionFactoryRecruit;	
	
	//�ڱ�Ұ��� ���� 
	public int countCletter(String recruitJobCd){
		return sqlSessionFactoryRecruit.selectOne(NS+".countCletter",recruitJobCd);
	}
	//����ȭ��
	public List<Recruit> recruitUpdateForm(String recruitCompanyCd){
		return sqlSessionFactoryRecruit.selectList(NS+".recruitUpdateForm",recruitCompanyCd);
	}
	
	
	//�޷� ��ü ������ ����Ʈ& �˻�
	public List<Recruit> selectBeginListOnSearchKeyword(Map companySearchMap){
		return sqlSessionFactoryRecruit.selectList(NS+".selectBeginListOnSearchKeyword",companySearchMap);
	}
	
	//�޷� ��ü ������ ����Ʈ& �˻�
	public List<Recruit> selectEndListOnSearchKeyword(Map companySearchMap){
		logger.info("cd : {}" ,companySearchMap.get("jobTopIndexCd"));
		return sqlSessionFactoryRecruit.selectList(NS+".selectEndListOnSearchKeyword",companySearchMap);
	}
	
	//loginId�� �ϸ�ũ Ȯ�� 
	public String checkBookmarkByLoginId(RecruitCompanyBookmarkVo recruitCompanyBookmarkVo){
		String aa = sqlSessionFactoryRecruit.selectOne(NS+".checkBookmarkByLoginId",recruitCompanyBookmarkVo);
		logger.info("aa : {} ",aa);
		
		return sqlSessionFactoryRecruit.selectOne(NS+".checkBookmarkByLoginId",recruitCompanyBookmarkVo);
	}
	//loginId �ϸ�ũ ���� 
	public int deleteBookmark(RecruitCompanyBookmarkVo recruitCompanyBookmarkVo){
		logger.info("recruitCompanyBookmarkVo : {}",recruitCompanyBookmarkVo);
		return sqlSessionFactoryRecruit.delete(NS+".deleteBookmark",recruitCompanyBookmarkVo);
	}
	//loginId �ϸ�ũ ���
	public int insertBookmark(RecruitCompanyBookmarkVo recruitCompanyBookmarkVo){
		logger.info("recruitCompanyBookmarkVo : {}",recruitCompanyBookmarkVo);
		return sqlSessionFactoryRecruit.insert(NS+".insertBookmark",recruitCompanyBookmarkVo);
	}
	
	//ä�� ������ 
	public List<Recruit> selectForRecruitCompanyDetail(String recruitCompanyCd){
		logger.info("recruitCompanyCd : {} ", recruitCompanyCd);
		return sqlSessionFactoryRecruit.selectList(NS+".selectForRecruitCompanyDetail",recruitCompanyCd);
	}
	
	//�����topindex ��ü ����Ʈ
	public List<IndustryTopIndexVo> selectAllTopIndustry(){
		return sqlSessionFactoryRecruit.selectList(NS+".selectAllTopIndustry");
	}

	//CoverletterJob�� �������ڵ� 
	public int getCountOfCoverletterJob(){
		return sqlSessionFactoryRecruit.selectOne(NS+".getCountOfCoverletterJob");
	}
	//�ڼҼ� �׸��Է�
	public int insertCoverletterArticle(CoverletterCompanyJobVo cletterArticle){
		return sqlSessionFactoryRecruit.insert(NS+".insertCoverletterArticle",cletterArticle);
	}
	//�ӽñ���Է�
	public int insertTemporaryCompany(Recruit recruit){
		return sqlSessionFactoryRecruit.insert(NS+".insertTemporaryCompany",recruit);
	}
	
	//�ӽñ���ڵ� ������ 
	public int selectDefaultCd(){
		return sqlSessionFactoryRecruit.selectOne(NS+".selectDefaultCd");
	}
	
	//��ü ���� ��з�
	public List<JobTopIndexVo> getJopTopIndexCd(){
		return sqlSessionFactoryRecruit.selectList(NS+".getJobTopIndexCd");
	}
	
	//��ü ���� �ߺз� 
	public List<JobMidIndexVo> getJobMidIndexCd(){
		return sqlSessionFactoryRecruit.selectList(NS+".getJobMidIndexCd");
	}
	
	//RecruitCompanyJob�� ������ Code
	public int getCountOfRecruitJob(){
		return sqlSessionFactoryRecruit.selectOne(NS+".getCountOfRecruitJob");
	}
	
	//RecruitCompany �� ������ Code
	public int getCountOfRecruit(){
		return sqlSessionFactoryRecruit.selectOne(NS+".getCountOfRecruit");
	}
	
	//��������� ����ڵ带 �˻�
	public String getCompanyCd(String companyName){
		return sqlSessionFactoryRecruit.selectOne(NS+".getCompanyCd",companyName);
	}
	
	//��ü �����
	public List<String> selectCompany(){
		return sqlSessionFactoryRecruit.selectList(NS+".selectCompany");
	}
	
	//RecruitCompanyJob �Է� 
	public int insertRecruitJob(RecruitCompanyJobVo recruitCompanyJobVo){
		return sqlSessionFactoryRecruit.insert(NS+".insertRecruitJob",recruitCompanyJobVo);
	}
	
	
	//RecruitCompany �Է�
	public int insertRecruitCompany(RecruitCompany recruitCompany){
		return sqlSessionFactoryRecruit.insert(NS+".insertRecruit",recruitCompany);
	}
}
