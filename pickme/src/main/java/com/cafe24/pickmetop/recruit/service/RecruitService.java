package com.cafe24.pickmetop.recruit.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.DefaultNamingPolicy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.cafe24.pickmetop.admin.model.IndustryTopIndexVo;
import com.cafe24.pickmetop.admin.model.JobMidIndexVo;
import com.cafe24.pickmetop.admin.model.JobTopIndexVo;
import com.cafe24.pickmetop.coverletter.model.CoverletterCompanyJobVo;
import com.cafe24.pickmetop.recruit.model.OneDay;
import com.cafe24.pickmetop.recruit.model.Recruit;
import com.cafe24.pickmetop.recruit.model.RecruitCompany;
import com.cafe24.pickmetop.recruit.model.RecruitCompanyBookmarkVo;
import com.cafe24.pickmetop.recruit.model.RecruitCompanyJobVo;
import com.cafe24.pickmetop.recruit.repository.RecruitDao;

@Service
public class RecruitService {
	final static Logger logger = LoggerFactory.getLogger(RecruitService.class);
	@Autowired
	RecruitDao recruitDao;
	int count = 0;
	/*=========================local��=======================*/
	/*final String imgDir = "C:\\Users\\202-10\\git\\topteam_pickme\\pickme\\src\\main\\webapp\\upload\\recruitimg";*/
	
	//companyRecruitJob�Է�
		public void insertRecruitCompanyJob(Recruit recruit,HttpServletRequest request){
			/*=========================������=========================*/
			String imgDir = request.getSession().getServletContext().getRealPath("/")+"upload/recruitimg";
			
			
			logger.info("recruit.getRecruitJobEducation() : {} " ,recruit.getRecruitJobEducation());
			for(int i=0; i<recruit.getRecruitList().size(); i++){
				
				//recruitJobCd�� ���� ū�� select
				int countJop = recruitDao.getCountOfRecruitJob()+1;
				
				logger.info("recruit {}",recruit.toString());
				RecruitCompanyJobVo recruitCompanyJobVo = new RecruitCompanyJobVo();
				recruitCompanyJobVo.setRecruitJobCd(String.valueOf(countJop));
				recruitCompanyJobVo.setCompanyCd(recruit.getCompanyCd());
				
				recruitCompanyJobVo.setRecruitCompanyCd(String.valueOf(count));
				for(int j=0;j<recruit.getRecruitList().get(i).getJobMidIndexCd().size();j++){
				recruitCompanyJobVo.setJobMidindexCd(recruit.getRecruitList().get(i).getJobMidIndexCd().get(j));
				recruitCompanyJobVo.setRecruitJobWorkstatus(recruit.getRecruitList().get(i).getRecruitJobWorkstatus().get(j));
				recruitCompanyJobVo.setRecruitJobJobdetail(recruit.getRecruitList().get(i).getRecruitJobJobdetail().get(j));
				recruitCompanyJobVo.setRecruitJobEducation(recruit.getRecruitList().get(i).getRecruitJobEducation().get(j));
				}
				
				//file�̸� = originalFilename + currentTimeMillis
				MultipartFile recruitImgs = recruit.getRecruitJobFile();
				String saveFileName = recruit.getRecruitJobFile().getOriginalFilename().substring(0,recruit.getRecruitJobFile().getOriginalFilename().length()-4);

				String ext = recruitImgs.getOriginalFilename().substring(recruitImgs.getOriginalFilename().lastIndexOf(".") + 1);
				ext = ext.toLowerCase();
				saveFileName=saveFileName+ "_" + System.currentTimeMillis()+"."+ext;
				String type = recruitImgs.getContentType();
				logger.info("������ �����̸� : {}", saveFileName);
				
				recruitCompanyJobVo.setRecruitJobFile(saveFileName );
				
				 
				 logger.info("imgDir : {}",imgDir);
				// file����
				 
				/*=========================local��=======================*/
				 
				/*String fullFileName = imgDir + "\\" + saveFileName ;*/
				 
				/*=========================������=========================*/
				String fullFileName = imgDir + "/" + saveFileName ;
				
				logger.info("fullFileName : {}",fullFileName);
				File saveFile = new File(fullFileName);
				try {
				// img�� saveFile�� �̵�
					recruitImgs.transferTo(saveFile);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				//�ڱ�Ұ��� �׸� ���
				for(int k=0;k<recruit.getRecruitList().get(i).getcCletterArticle().size();k++){   
					CoverletterCompanyJobVo cletterArticle = new CoverletterCompanyJobVo();
					logger.info("recruit.getRecruitList().get(i).getcCletterArticle().get(k).getCletterArticle() : {}",i +" : "+ k);
					cletterArticle.setRecruitJobCd(String.valueOf(countJop));
					cletterArticle.setcCletterArticle(recruit.getRecruitList().get(i).getcCletterArticle().get(k).getcCletterArticle());
					logger.info("cletterArticle.toString() :{}", cletterArticle.toString());
					recruitDao.insertCoverletterArticle(cletterArticle);
				}
				logger.info("recruitCompanyJobVo : {}",recruitCompanyJobVo.toString());	
				recruitDao.insertRecruitJob(recruitCompanyJobVo);
			}
		}
	
	//�ڱ�Ұ��� ���� 
/*	public int countCletter(String recruitJobCd){
		return recruitDao.countCletter(recruitJobCd);
	}*/
	
	//����ȭ��
	public Recruit recruitUpdateForm(String recruitCompanyCd){
		Recruit recruit = new Recruit();
		List<RecruitCompanyJobVo> jobVoList = new ArrayList<RecruitCompanyJobVo>();
		recruit.setRecruitList(recruitDao.recruitUpdateForm(recruitCompanyCd));		
		logger.info("���Ƥ����������� : {}",recruit.getRecruitList().get(0).getWorkStatus());
		for(int i =0;i<recruit.getRecruitList().size()-1;i++){
			logger.info(" 0 : {}",recruit.getRecruitList().get(i).getRecruitJobCd());
			if(!recruit.getRecruitList().get(i).getRecruitJobCd().equals(recruit.getRecruitList().get(i+1).getRecruitJobCd())){
				logger.info(" �ֵ���..1 : {}",recruit.getRecruitList().get(i).getRecruitJobCd());
				logger.info(" �ֵ���..2 : {}",recruit.getRecruitList().get(i+1).getRecruitJobCd());
				RecruitCompanyJobVo jobVo = new RecruitCompanyJobVo();
				jobVo.setRecruitJobCd(recruit.getRecruitList().get(i).getRecruitJobCd());
				jobVo.setJobMidindexCd(recruit.getRecruitList().get(i).getJobMidIndexCds());
				jobVo.setRecruitJobWorkstatus(recruit.getRecruitList().get(i).getWorkStatus());
				jobVo.setRecruitJobJobdetail(recruit.getRecruitList().get(i).getJobDetail());
				logger.info("jobVoList.size() : {}",jobVoList.size());
				jobVoList.add(jobVo);
			}
		}
		int a =recruit.getRecruitList().size()-1;
		if(recruit.getRecruitList().get(a) != null){
			RecruitCompanyJobVo jobVo = new RecruitCompanyJobVo();
			logger.info("recruit.getRecruitList().size() : {}",recruit.getRecruitList().size());
			
			jobVo.setRecruitJobCd(recruit.getRecruitList().get(a).getRecruitJobCd());
			jobVo.setJobMidindexCd(recruit.getRecruitList().get(a).getJobMidIndexCds());
			jobVo.setRecruitJobWorkstatus(recruit.getRecruitList().get(a).getWorkStatus());
			jobVo.setRecruitJobJobdetail(recruit.getRecruitList().get(a).getJobDetail());
			
			jobVoList.add(jobVo);
		}
		recruit.setJobVo(jobVoList);
		
		return recruit;
	}
	
	
	//�ϸ�ũ üũ���� Ȯ�� 
	public String checkBookmarkByLoginId(HttpSession session,String recruitCompanyCd){
		RecruitCompanyBookmarkVo recruitCompanyBookmarkVo = new RecruitCompanyBookmarkVo();
		recruitCompanyBookmarkVo.setLoginId((String) session.getAttribute("generalId"));
		
		recruitCompanyBookmarkVo.setRecruitCompanyCd(recruitCompanyCd);
		String checkBookmark=recruitDao.checkBookmarkByLoginId(recruitCompanyBookmarkVo);
		if(checkBookmark!=null){
			return "checkBookmark";
		}
		return "";
	}
	//�ϸ�ũ ���, ����
	public void insertBookmark(String recruitCompanyCd,String bookmarkChecked, HttpSession session){
		RecruitCompanyBookmarkVo recruitCompanyBookmarkVo = new RecruitCompanyBookmarkVo();
		recruitCompanyBookmarkVo.setLoginId((String) session.getAttribute("generalId"));
		
		recruitCompanyBookmarkVo.setRecruitCompanyCd(recruitCompanyCd);
		logger.info("recruitCompanyBookmarkVo : {}",recruitCompanyBookmarkVo.toString());
		logger.info("bookmarkChecked : {}",bookmarkChecked);
		if(bookmarkChecked.equals("checked")){
			//�ϸ�ũ ��� 
			logger.info("bookmarkChecked : {}",bookmarkChecked);
			recruitDao.insertBookmark(recruitCompanyBookmarkVo);
		}else if(bookmarkChecked.equals("unchecked")){
			//�ϸ�ũ ����
			recruitDao.deleteBookmark(recruitCompanyBookmarkVo);
		}
		
	}
	//ä��󼼺��� 
	public Recruit selectForRecruitCompanyDetail(String recruitCompanyCd){
			Recruit recruit = new Recruit();
			recruit.setRecruitList(recruitDao.selectForRecruitCompanyDetail(recruitCompanyCd));
			logger.info("�̰Ŵ�{}",recruitDao.selectForRecruitCompanyDetail(recruitCompanyCd));
		return  recruit;
	}
	
	//����� ��ü����Ʈ
	public List<IndustryTopIndexVo> selectAllTopIndustry(){
		return recruitDao.selectAllTopIndustry();
	}
	
	
	//�޷�ȭ��
	public Map<String , Object> getOneDayList(int ddayYear,int ddayMonth,String ddayOption,String searchCompanyName
			,String bookmark,List<String> jobTopIndexCd,List<String> industryTopindexCd,List<String> recruitJobWorkstatus,HttpSession session){
		logger.info("ddayYear : {}",ddayYear);
		Map map = new HashMap<String , Object>();
		//dday : ?�� + ?�� + 1��
		Calendar dday= Calendar.getInstance();	//���ó�¥
		dday.set(Calendar.DATE,1);//1�Ϸ� ����
		if(ddayOption.equals("prev")){
			dday.set(ddayYear, ddayMonth,1);
			dday.add(Calendar.MONTH, -1);//1������ -1�ϸ� 12���� �ɼ��ֵ��� �޼��带 ���
		}else if(ddayOption.equals("next")){
			dday.set(ddayYear, ddayMonth,1);
			dday.add(Calendar.MONTH, 1);//12������ +1�ϸ� 1���� �ɼ��ֵ��� �޼��带 ���
		}else if(ddayOption.equals("search")){
			dday.set(ddayYear, ddayMonth,1);
			logger.info("dday:{}",dday);
		}
		
		//1���� ���� : �պκ� ���鱸�ϱ�
		int firstWeek = dday.get(Calendar.DAY_OF_WEEK);
		List<OneDay> oneDayList = new ArrayList<OneDay>();
		//��������¥
		int endDay= dday.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		//List size(=��¥�� �� <td>�ǰ���)
		int listSize = (firstWeek-1)+endDay;
		if(listSize%7!=0){
			listSize=listSize+(7-(listSize%7));
		}
		//�������� �������� 
		Calendar preMonth = Calendar.getInstance();	
		preMonth.set(Calendar.MONTH, dday.MONTH-1);
		int preLastDay= preMonth.getActualMaximum(Calendar.DATE);
		int beginSpace= preLastDay -(firstWeek-2);
		int endSpace=1;
		for(int i =0; i<listSize;i++){
			//��¥�� ä�������� ���� ��ü
			OneDay oneDay;
			//���ǰ���
			if(i<(firstWeek-1)){
				oneDay = new OneDay();
				oneDay.setDay(beginSpace);
				beginSpace++;
			
			 
			}else if(i<endDay+(firstWeek-1)){
				oneDay = new OneDay();
				oneDay.setDay((i+1)-(firstWeek-1));
				oneDay.setYear(dday.get(Calendar.YEAR));
				oneDay.setMonth(dday.get(Calendar.MONTH)+1);
				String scheduleDate = oneDay.getYear()+"-"+oneDay.getMonth()+"-"+oneDay.getDay();
				
				Map<String, Object> companySearchMap = new HashMap<String, Object>();
				companySearchMap.put("scheduleDate", scheduleDate);
				
				logger.info("jobTopIndexCd : {} ",jobTopIndexCd.size());
				logger.info("industryTopindexCd :{}",industryTopindexCd.size());
				logger.info("recruitJobWorkstatus :{}",recruitJobWorkstatus);
				logger.info("scheduleDate : {}",scheduleDate);
				
					
	
				if(!bookmark.equals("")){
				
					companySearchMap.put("bookmark", bookmark);
					companySearchMap.put("sessionLoginId",(String)session.getAttribute("generalId"));
				}
				if(jobTopIndexCd.size()!=0){
					
					//�� �̰Ŵ� ����Ʈ�ϱ� ���..
					companySearchMap.put("jobTopIndexCd", jobTopIndexCd.toArray());
				}
				if(industryTopindexCd.size()!=0){
					
					companySearchMap.put("industryTopindexCd", industryTopindexCd.toArray());
				}
				if(recruitJobWorkstatus.size()!=0){
					
					companySearchMap.put("recruitJobWorkstatus", recruitJobWorkstatus.toArray());
				}
				if(!searchCompanyName.equals("") && bookmark.equals("")){
					companySearchMap.put("searchCompanyName", searchCompanyName);
				}
				//ä������� & ������ ����Ʈ
				List<Recruit> beginScheduleList = recruitDao.selectBeginListOnSearchKeyword(companySearchMap);
				List<Recruit> endScheduleList = recruitDao.selectEndListOnSearchKeyword(companySearchMap);
				
				beginScheduleList.addAll(endScheduleList);
				//�� ��¥�� ä�����, ���� 
				oneDay.setScheduleList(beginScheduleList);	
			
			//���ǰ���
			}else {
				oneDay = new OneDay();
				oneDay.setDay(endSpace);
				endSpace++;
			}
			oneDayList.add(oneDay);
		}
		OneDay today = new OneDay();
		Calendar getToDay= Calendar.getInstance();	
		today.setDay(getToDay.get(Calendar.DATE));
		//month�� 0��°���� �����ϱ⶧���� 1�� ������
		today.setMonth(getToDay.get(Calendar.MONTH)+1);
		today.setYear(getToDay.get(Calendar.YEAR));
		map.put("oneDayList", oneDayList);
		map.put("ddayYear", dday.get(Calendar.YEAR));
		map.put("ddayMonth", dday.get(Calendar.MONTH));
		map.put("today", today);
		
		return map;		
	}
	//�ӽ� ����� �Է�
	public void insertTemporaryCompany(Recruit recruit){
		recruitDao.insertTemporaryCompany(recruit);
	}
	
	//�ӽñ���� ���� 
	public int selectDefaultCd(){
		return recruitDao.selectDefaultCd();
	}
	//���� ��з� ��ü
	public List<JobTopIndexVo> getJobTopIndexCd(){
		return recruitDao.getJopTopIndexCd();
	}
	//���� �ߺз� ��ü����Ʈ 
	public List<JobMidIndexVo> getJobMidIndexCd(){
		return recruitDao.getJobMidIndexCd();
	}
	//��ü �������Ʈ
	public List<String> selectCompany(){
		return recruitDao.selectCompany();
	}
	//�Է¹��� ��������� ����ڵ� �˻�
	public String getCompanyCd(String companyName){
		return recruitDao.getCompanyCd(companyName);
	}
	
	//companyRecruit�Է�
	public void insertRecruitCompany(Recruit recruit,HttpSession session){
		RecruitCompany recruitCompany = new RecruitCompany();
		
		//RecruitCompanyCd���� �ڵ��� ���� ū���ڸ� select
		count = recruitDao.getCountOfRecruit() +1;
		
		//��ü���� ��setting
		recruitCompany.setRecruitCompanyCd(String.valueOf(count));
		recruitCompany.setCompanyCd(recruit.getCompanyCd());
		recruitCompany.setRecruitName(recruit.getRecruitName());
		recruitCompany.setRecruitBegindate(recruit.getRecruitBegindate());
		recruitCompany.setRecruitEnddate(recruit.getRecruitEnddate());
		recruitCompany.setRecruitRegister((String) session.getAttribute("generalId"));
		recruitCompany.setRecruitModify((String) session.getAttribute("generalId"));
			
		logger.info("recruitCompany : {}",recruitCompany.toString());
			
		recruitDao.insertRecruitCompany(recruitCompany);
	}

	
	
}

