package com.cafe24.pickmetop.coverletter.repository;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.pickmetop.coverletter.model.CoverletterBookmarkListVo;
import com.cafe24.pickmetop.coverletter.model.CoverletterCompanyJobInfoVo;
import com.cafe24.pickmetop.coverletter.model.CoverletterCompanyJobVo;
import com.cafe24.pickmetop.coverletter.model.CoverletterMemberArticleSaveVo;
import com.cafe24.pickmetop.coverletter.model.CoverletterMemberArticleVo;
import com.cafe24.pickmetop.coverletter.model.CoverletterMemberVo;
import com.cafe24.pickmetop.coverletter.model.ResumeCoverletterInfoCountVo;
import com.cafe24.pickmetop.freeboard.model.FreeboardBookmarkVo;
import com.cafe24.pickmetop.freeboard.model.FreeboardVo;

@Repository
public class CoverletterDao {
	final static Logger logger = LoggerFactory.getLogger(CoverletterDao.class);
	private final String nameSpace = "com.cafe24.pickmetop.coverletter.repository.CoverletterMapper";
	@Autowired
	@Resource(name="sqlSessionCoverletter")
	private SqlSessionTemplate sqlSessionFactoryCoverletter;
	
	// 01 ȸ���� �ۼ��� �ڱ�Ұ��� ����Ʈ
	public List<CoverletterMemberVo> selectCoverletterMemberList(String loginId){
		return sqlSessionFactoryCoverletter.selectList(nameSpace + ".selectCoverletterMemberList", loginId);
	}
	
	// 02 ���ä������� ����������Ʈ(�ڱ�Ұ����� �˻��̳� üũ����Ʈ üũ�� ���� �Է�ȭ������ �̵�)
	public List<CoverletterCompanyJobInfoVo> selectCoverletterCompanyJobList(){
		return sqlSessionFactoryCoverletter.selectList(nameSpace+".selectCoverletterCompanyJobList");
	}
	
	// 03_01 �ڱ�Ұ��� �Է�ȭ��(ä������/ä���/ä������/ä�������/ä�븶������)
	public CoverletterCompanyJobInfoVo selectOneCletterCompanyJobInfo(String recruitJobCd){
		logger.info("�ڼҼ��Է�ȭ�� : ������ ä������ {} ", recruitJobCd.toString());
		return sqlSessionFactoryCoverletter.selectOne(nameSpace+".selectOneCletterCompanyJobInfo", recruitJobCd);
	}
	
	// 03_02 �ڱ�Ұ��� �Է�ȭ��(�ڱ�Ұ��� �׸񸮽�Ʈ)
	public List<CoverletterCompanyJobVo> selectListCletterArticleByJobCd(String recruitJobCd){
		logger.info("�ڼҼ��Է�ȭ��: �ڼҼ��׸񸮽�Ʈ {}", recruitJobCd.toString());
		return sqlSessionFactoryCoverletter.selectList(nameSpace+".selectListCletterArticleByJobCd", recruitJobCd);
	}
	
	// 03_03 �ڱ�Ұ��� �Է�ó��
	public int insertCoverletter(CoverletterMemberVo coverletterMember){
		return sqlSessionFactoryCoverletter.insert(nameSpace + ".insertCoverletter", coverletterMember);
	}
	// 03_04 �ڱ�Ұ��� �׸� �Է�ó��
	public int insertCoverletterArticle(CoverletterMemberArticleVo memberArticle){
		return sqlSessionFactoryCoverletter.insert(nameSpace + ".insertCoverletterArticle", memberArticle);
	}
	// 03_05 �ڱ�Ұ��� ������ �Է�ó��
	public int insertCoverletterSaveRecord(CoverletterMemberArticleSaveVo saveRecord){
		return sqlSessionFactoryCoverletter.insert(nameSpace + ".insertCoverletterSaveRecord", saveRecord);
	}
	
	// 04_01 ȸ���� �ڱ�Ұ��� ����
	public CoverletterMemberVo selectOneMemeberCoverletterInfo(String mCletterCd){
		logger.info("�ڱ�Ұ��� ���� : {}", mCletterCd.toString());
		return sqlSessionFactoryCoverletter.selectOne(nameSpace+".selectOneMemeberCoverletterInfo", mCletterCd);
	}
	
	// 04_02 ȸ���� �ۼ��� �ڱ�Ұ��� �׸� �� ���� ����Ʈ
	public List<CoverletterMemberArticleVo> selectMemeberCoverletterArticleList(String mCletterCd){
		logger.info("�ڱ�Ұ��� �׸� �� ���� ����Ʈ : {}", mCletterCd.toString());
		return sqlSessionFactoryCoverletter.selectList(nameSpace+".selectMemeberCoverletterArticleList", mCletterCd);
	}
	
	// 04_03 ȸ���� �ۼ��� �ڱ�Ұ��� ������
	public CoverletterMemberArticleSaveVo selectMemeberCoverletterArticleSaveRecord(String mCletterCd){
		logger.info("�ڱ�Ұ��� ������ : {}", mCletterCd.toString());
		return sqlSessionFactoryCoverletter.selectOne(nameSpace+".selectMemeberCoverletterArticleSaveRecord", mCletterCd);
	}
	
	// 05_01 ���� ����������(�����ӹ�ä�������Ʈ20)
	public List<CoverletterCompanyJobInfoVo> selectCoverletterCompanyJobListForInfo(){
		return sqlSessionFactoryCoverletter.selectList(nameSpace+".selectCoverletterCompanyJobListForInfo");
	}
	// 05_02 ���� ����������(����ä�븮��Ʈ20)
	public List<CoverletterBookmarkListVo> selectMyRecruitBookmarkListForInfo(String loginId){
		return sqlSessionFactoryCoverletter.selectList(nameSpace+".selectMyRecruitBookmarkListForInfo", loginId);
	}
	// 05_03 ���� ����������(�Խñ۸���Ʈ20)
	public List<FreeboardVo> selectMyFreeboardWriteListForInfo(String loginId){
		return sqlSessionFactoryCoverletter.selectList(nameSpace+".selectMyFreeboardWriteListForInfo", loginId);
	}
	// 05_04 ���� ����������(���ɰԽñ۸���Ʈ20)
	public List<FreeboardBookmarkVo> selectMyFreeboardBookmarkListForInfo(String loginId){
		return sqlSessionFactoryCoverletter.selectList(nameSpace+".selectMyFreeboardBookmarkListForInfo", loginId);
	}
	
	// 05_05 ���� ����������(�����ڼҼ�Count)
	public ResumeCoverletterInfoCountVo selectMyCoverletterListCount(String loginId){
		logger.info("�����ڼҼ�Count : {}", loginId.toString());
		return sqlSessionFactoryCoverletter.selectOne(nameSpace + ".selectMyCoverletterListCount", loginId);
	}
	// 05_06 ���� ����������(�����̷¼�Count)
	public ResumeCoverletterInfoCountVo selectMyResumeListCount(String loginId){
		logger.info("�����̷¼�Count : {}", loginId.toString());
		return sqlSessionFactoryCoverletter.selectOne(nameSpace + ".selectMyResumeListCount", loginId);
	}
	// 05_07 ���� ����������(����ä��ϸ�ũCount)
	public ResumeCoverletterInfoCountVo selectMyRecruitListCount(String loginId){
		logger.info("����ä��ϸ�ũCount : {}", loginId.toString());
		return sqlSessionFactoryCoverletter.selectOne(nameSpace + ".selectMyRecruitListCount", loginId);
	}
	// 05_08 ���� ����������(���ǰԽñ�Count)
	public ResumeCoverletterInfoCountVo selectMyFreeboardWriteCount(String loginId){
		logger.info("���� �̷¼�Count : {}", loginId.toString());
		return sqlSessionFactoryCoverletter.selectOne(nameSpace + ".selectMyFreeboardWriteCount", loginId);
	}
	// 05_08 ���� ����������(���ɱ�Count)
	public ResumeCoverletterInfoCountVo selectMyFreeboardBookmarkCount(String loginId){
		logger.info("���ɱ�Count : {}", loginId.toString());
		return sqlSessionFactoryCoverletter.selectOne(nameSpace + ".selectMyFreeboardBookmarkCount", loginId);
		}	
	
}
