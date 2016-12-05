package com.cafe24.pickmetop.recruit.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.pickmetop.recruit.model.Recruit;
import com.cafe24.pickmetop.recruit.service.RecruitService;
import com.cafe24.pickmetop.recruit.service.Commons;

@Controller
public class RecruitController {
	final static Logger logger = LoggerFactory.getLogger(RecruitController.class);
	@Autowired
	@Resource
	RecruitService recruitService;	
	@Autowired
	Commons commons;
	
	
	/* ä�� ���� ó��*/
	@RequestMapping(value="/recruitUpdate")
	public String recruitUpdate(){
		
		return "/recruit/company/companyRecruitUpdate";
	}
	
	
	/* ä�� ���� ȭ��*/
	@RequestMapping(value="/recruitUpdateForm")
	public String recruitUpdateForm(Model model,
			@RequestParam(value="recruitCompanyCd", defaultValue="0") String recruitCompanyCd){
		logger.info("recruitCompanyCd : {}",recruitCompanyCd);
		model.addAttribute("updateList",recruitService.recruitUpdateForm(recruitCompanyCd));
		return "/recruit/company/companyRecruitUpdate";
	}
	
	
	/* ä�� ������*/
	@RequestMapping(value="/recruitDetail")
	public String recruitDetail(Model model,HttpSession session,HttpServletRequest request,
			@RequestParam(value="recruitCompanyCd", defaultValue="0") String recruitCompanyCd,
			@RequestParam(value="checked", defaultValue="") String bookmarkChecked){
		
		String dir = request.getSession().getServletContext().getRealPath("/")+"upload/recruitimg";
		model.addAttribute("dir",dir);
		
		
		logger.info("recruitCd : {}",recruitCompanyCd);
		logger.info("recruitCompanyInfoForDetail :{}",recruitService.selectForRecruitCompanyDetail(recruitCompanyCd));
		model.addAttribute("recruitCompanyInfoForDetail",recruitService.selectForRecruitCompanyDetail(recruitCompanyCd));
		
		//�ϸ�ũ ���,����
		logger.info("bookmarkChecked :{}",bookmarkChecked);
		if(bookmarkChecked!=""){
			recruitService.insertBookmark(recruitCompanyCd,bookmarkChecked,session);
		}
		//�ϸ�ũ üũ����
		String checkBookmark=recruitService.checkBookmarkByLoginId(session,recruitCompanyCd);
		if(checkBookmark.equals("checkBookmark")){
			model.addAttribute("checkBookmark","checkBookmark");
		}
		return "/recruit/company/companyRecruitDetail"; 
	}

	
	/* ä�� ����Ʈ */
	@RequestMapping(value="/diary")
	public String diary(Model model,HttpSession session,
							@RequestParam(value="ddayYear", defaultValue="0") int ddayYear,
							@RequestParam(value="ddayMonth", defaultValue="0") int ddayMonth,
							@RequestParam(value="ddayOption", defaultValue="default") String ddayOption,
							@RequestParam(value="searchCompanyName", defaultValue="") String searchCompanyName,
							@RequestParam(value="bookmark", defaultValue="") String bookmark,
							@RequestParam(value="jobTopIndexCd", defaultValue="") List<String> jobTopIndexCd,
							@RequestParam(value="industryTopindexCd", defaultValue="") List<String> industryTopindexCd,
							@RequestParam(value="recruitJobWorkstatus", defaultValue="") List<String> recruitJobWorkstatus){
		
		logger.info("�˻����Է��� �˻���ư �ϸ� searchCompanyName:{}",searchCompanyName);
		logger.info("bookmark���⸦ ������ true:{}",bookmark);
		logger.info("jobTopIndexCd[]:{}",jobTopIndexCd.size());
		logger.info("industryTopindexCd[]:{}",industryTopindexCd.size());
		logger.info("recruitJobWorkstatus[]:{}",recruitJobWorkstatus.size());
		logger.info("recruitJobWorkstatus[]:{}",recruitJobWorkstatus);
		
	
		
		//��ü ���� ��з�
		model.addAttribute("jobTopIndex", recruitService.getJobTopIndexCd());
		List<String> jobStringList = new ArrayList();
		for(int i=0;i<recruitService.getJobTopIndexCd().size();i++){
			jobStringList.add(recruitService.getJobTopIndexCd().get(i).getJobTopIndexCd());
		}
		logger.info("jobStringList :{}", jobStringList);
		
		//��ü ����� ��з�
		model.addAttribute("topIndustry",recruitService.selectAllTopIndustry());
		List<String> IndustryStringList = new ArrayList();
		for(int j=0;j<recruitService.selectAllTopIndustry().size();j++){
			IndustryStringList.add(recruitService.selectAllTopIndustry().get(j).getIndustryTopindexCd());
		}
		logger.info("IndustryStringList : {}",IndustryStringList);
		
		//ä�����¸���Ʈ
		List<String> workStatueArray = new ArrayList();
		workStatueArray.add("����");
		workStatueArray.add("���");
		workStatueArray.add("����");
		workStatueArray.add("�����");
		
		model.addAttribute("workStatueArray",workStatueArray);
		logger.info("workStatueArray : {}",workStatueArray);
		
		if(recruitJobWorkstatus.size()==0){
			logger.info("�ֵ���!!recruitJobWorkstatus.size() : {}",recruitJobWorkstatus.size());
			recruitJobWorkstatus=workStatueArray;
		}
		
		if(jobTopIndexCd.size()==0){
			jobTopIndexCd=jobStringList;
		}
		
		if(industryTopindexCd.size()==0){
			industryTopindexCd=IndustryStringList;
		}
		logger.info("jobTopIndexCd :{}", jobTopIndexCd);
		
		//jquery�� ���ڿ��� ��ȿ���˻簡 �ȵż� �̷��� ����
/*		if(recruitJobWorkstatus.equals("workStatusNull")){
			recruitJobWorkstatus="workStatusNull";
			logger.info("recruitJobWorkstatus2:{}",recruitJobWorkstatus);
		}*/
		
		//��¥�� ä������ select
		Map<String,Object> map = recruitService.getOneDayList(ddayYear,ddayMonth,ddayOption,
				searchCompanyName,bookmark,jobTopIndexCd,industryTopindexCd,recruitJobWorkstatus,session);

		model.addAttribute("oneDayList",map.get("oneDayList"));
		model.addAttribute("ddayYear",map.get("ddayYear"));
		model.addAttribute("ddayMonth",map.get("ddayMonth"));
		model.addAttribute("today",map.get("today"));
		
		//üũ�׸� ǥ���ϱ����� 
		model.addAttribute("jobTopIndexCd",jobTopIndexCd);
		logger.info("jobTopIndexCd2:{}",jobTopIndexCd);
		model.addAttribute("industryTopindexCd",industryTopindexCd);
		model.addAttribute("recruitJobWorkstatus",recruitJobWorkstatus);


		
		return "/recruit/company/companyRecruitList";
		
	}
	
	
	/* ä�� �Է� ó�� */
	@RequestMapping(value = "/recruitInsert", method = RequestMethod.POST)
	public String recruitInsert(Recruit recruit,HttpSession session,Model model,HttpServletRequest request) {
		//���� Ÿ�� �˻� �޼��� 
		boolean result =commons.checkFileType(recruit);
		
		logger.info("tes t{}",recruit.toString());
		//Recruit�� �ʵ��� ����Ʈ�� 0��° - List<CoverletterCompanyJobVo> cCletterArticle �� 0��° �̷���
		logger.info("tes t{}",recruit.getRecruitList().get(0).getcCletterArticle().get(0).getcCletterArticle());
		
		//���� Ÿ���� �̹����ϰ�� 
		if(result){
			//��������� ����ڵ带 �˻��Ѵ�
			String companyCd = recruitService.getCompanyCd(recruit.getCompanyName());
			recruit.setCompanyCd(companyCd);
			if(companyCd==null){//db���� �Է��ϰ����ϴ� ����� ������ 
				//������ �ڵ尪�� �����
				int defaultNum = recruitService.selectDefaultCd() +1;
				//�ڵ尪�� vo�� �����Ѵ� 
				recruit.setCompanyCd(String.valueOf(defaultNum));
				//��� table�� ������ �ڵ尪�� ȭ�鿡�� �Է¹��� ������� insert�Ѵ�.
				recruitService.insertTemporaryCompany(recruit);
				logger.info("recruit.getCompanyCd(): {}",recruit.getCompanyCd());
			}
				recruitService.insertRecruitCompany(recruit,session);
				recruitService.insertRecruitCompanyJob(recruit,request);	
			return "/admin/adminmain";
		//����Ÿ���� �̹����� �ƴҰ��
		}else{
			model.addAttribute("errorMsg","������Ÿ�� ����ġ");
			
		}
		return "/recruit/company/companyRecruitInsert";
		
	}
	
	
	/* ä�� �Է� ȭ�� */
	@RequestMapping(value = "/recruit", method = RequestMethod.GET)
	public String recruitInsert(Model model,HttpSession session) {
		//��ü ���� ��з�
		model.addAttribute("jobTopIndex", recruitService.getJobTopIndexCd());
		
		//db���� ������ ��ü �����ߺз� �ڵ�� ��������Ʈ�� �𵨿� ��´�
		model.addAttribute("jobMidIndex",recruitService.getJobMidIndexCd());
		//logger.info("recruitService.getJobMidIndexCd() : {}",recruitService.getJobMidIndexCd());
		
		//db���� ������ ��ü �������Ʈ�� �𵨿� ��´�
		List<String> companyList = recruitService.selectCompany();
		//logger.info("companyList : {}",companyList);
		model.addAttribute("companyList", companyList);
		return "/recruit/company/companyRecruitInsert";
	}
}
