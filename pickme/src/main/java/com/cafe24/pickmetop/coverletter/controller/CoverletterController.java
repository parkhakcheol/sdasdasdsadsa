package com.cafe24.pickmetop.coverletter.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.pickmetop.coverletter.model.CoverletterMemberArticleSaveVo;
import com.cafe24.pickmetop.coverletter.model.CoverletterMemberArticleVo;
import com.cafe24.pickmetop.coverletter.model.CoverletterMemberVo;
import com.cafe24.pickmetop.coverletter.model.CoverletterPassVo;
import com.cafe24.pickmetop.coverletter.service.CoverletterService;

@Controller
public class CoverletterController {
	final static Logger Logger = LoggerFactory.getLogger(CoverletterController.class);
	@Autowired
	private CoverletterService coverletterService;
	
	
	// 00 �̷¼� �� �ڱ�Ұ��� ���� ����������(�����ӹ�ä�����10/�����ڼҼ�Count/���� �̷¼�Count/����ä��ϸ�ũCount)
	@RequestMapping(value="/resumeCoverletterInfo", method = RequestMethod.GET)
	public String resumeCoverletterInfo(Model model, HttpSession session){
		String loginId = (String) session.getAttribute("generalId");
		Logger.info("Session : loginId {} " , loginId);
		
		model.addAttribute("companyJobCoverletterListForInfo", coverletterService.getCompanyJobCoverletterListForInfo());
		model.addAttribute("myRecruitBookmarkListForInfo", coverletterService.getMyRecruitBookmarkListForInfo(loginId));
		model.addAttribute("myFreeboardWriteListForInfo", coverletterService.getMyFreeboardWriteListForInfo(loginId));
		model.addAttribute("myFreeboardBookmarkListForInfo", coverletterService.getMyFreeboardBookmarkListForInfo(loginId));
		Logger.info("ä�븶���ӹڸ���Ʈ : {} " , coverletterService.getCompanyJobCoverletterListForInfo());
		Logger.info("����ä�븮��Ʈ  : {} " , coverletterService.getMyRecruitBookmarkListForInfo(loginId));
		Logger.info("���ǰԽñ۸���Ʈ : {} " , coverletterService.getMyFreeboardWriteListForInfo(loginId));
		Logger.info("���ǰ��ɱ۸���Ʈ : {} " , coverletterService.getMyFreeboardBookmarkListForInfo(loginId));
		
		model.addAttribute("myCoverletterListCount", coverletterService.getMyCoverletterListCount(loginId));
		model.addAttribute("myResumeListCount", coverletterService.getMyResumeListCount(loginId));
		model.addAttribute("myRecruitListCount", coverletterService.getMyRecruitListCount(loginId));
		model.addAttribute("myFreeboardWriteCount", coverletterService.getMyFreeboardWriteCount(loginId));
		model.addAttribute("myFreeboardBookmarkCount", coverletterService.getMyFreeboardBookmarkCount(loginId));
		Logger.info("�����ڼҼ�ī��Ʈ : {} " , coverletterService.getMyCoverletterListCount(loginId));
		Logger.info("�����̷¼�ī��Ʈ : {} " , coverletterService.getMyResumeListCount(loginId));
		Logger.info("���ǰ���ä��ī��Ʈ : {} " , coverletterService.getMyRecruitListCount(loginId));
		Logger.info("���ǰԽñ�ī��Ʈ : {} " , coverletterService.getMyFreeboardWriteCount(loginId));
		Logger.info("���ǰ��ɱ�ī��Ʈ : {} " , coverletterService.getMyFreeboardBookmarkCount(loginId));
		
		if(loginId == null) {
			return "/common/etc/loginCheck";
		}
		return "/resumecoverletter/resumeCoverletterInfo";
	}                          
	
	// 01_01 �ڱ�Ұ��� ����Ʈ(ȸ���� ���� �ۼ��� �ڱ�Ұ��� ����Ʈ) : ����Ʈ������ ����
	@RequestMapping(value="/memberCoverletterList", method = RequestMethod.GET)
	public String memberCoverletterList(Model model, HttpSession session){
		String loginId = (String) session.getAttribute("generalId");
		model.addAttribute("memberCoverletterList", coverletterService.getMemberCoverletterList(loginId));
		Logger.info("Session : loginId {} " , loginId);
		Logger.info("ȸ���ڱ�Ұ��� ����Ʈ : {}", model.toString());
		if(loginId == null){
			return "/common/etc/loginCheck";
		}
		return "/resumecoverletter/coverletter/member/memberCoverletterList";
	}
	
	// 01_02 �ڱ�Ұ��� ����Ʈ(ȸ���� ���� �ۼ��� �ڱ�Ұ��� ����Ʈ) : ��������� ����
	@RequestMapping(value="/memberCoverletterListForModal", method = RequestMethod.GET)
	public String memberCoverletterListForMordal(Model model, HttpSession session){
		String loginId = (String) session.getAttribute("generalId");
		model.addAttribute("memberCoverletterListForModal", coverletterService.getMemberCoverletterList(loginId));
		Logger.info("Session : loginId {} " , loginId);
		Logger.info("ȸ���ڱ�Ұ��� ����Ʈ for Mordal : {}", model.toString());
		if(loginId == null){
			return "/common/etc/loginCheck";
		}
		return "/resumecoverletter/coverletter/member/memberCoverletterListForModal";
	}
	
	// 02 ���ä������� �ڱ�Ұ��� ����Ʈ(�ڱ�Ұ����� �˻��̳� üũ����Ʈ üũ�� ���� �Է�ȭ������ �̵�)
	@RequestMapping(value="/companyJobCoverletterList", method = RequestMethod.GET)
	public String companyJobCoverletterList(Model model){
		model.addAttribute("companyJobCoverletterList", coverletterService.getCompanyJobCoverletterList());
		Logger.info("���ä������� �ڱ�Ұ��� ����Ʈ {}", model.toString());
		return "/resumecoverletter/coverletter/admin/companyJobCoverletterList"; 
	}
	                                                     
	// 03_01 �ڱ�Ұ��� �Է�ȭ��(ä�������ڵ�/ä������/ä���/�����ߺз���/��������/ä�븶������/����ڱ�Ұ����׸񸮽�Ʈ)
	@RequestMapping(value="/memberCoverletterInsert", method = RequestMethod.GET)
	public String memberCoverletterInsertForm(Model model, @RequestParam(value="recruitJobCd") String recruitJobCd, HttpSession session){
		Map<String, Object> companyOneJobCletter = coverletterService.getCompanyOneJobCletter(recruitJobCd);
		Logger.info("�ڱ�Ұ��� �Է�ȭ�� {}", model.toString());
		model.addAttribute("companyOneJobCletterInfo", companyOneJobCletter.get("companyOneJobCletterInfo"));
		Logger.info("companyOneJobCletterInfo : {}", companyOneJobCletter.get("companyOneJobCletterInfo").toString());
		model.addAttribute("companyOneJobArticleList", companyOneJobCletter.get("companyOneJobArticleList"));
		Logger.info("companyOneJobArticleList : {}", companyOneJobCletter.get("companyOneJobArticleList").toString());
		return "/resumecoverletter/coverletter/member/memberCoverletterInsert";
	}
	
	// 03_02 �ڱ�Ұ��� �Է�ó��(�ڱ�Ұ��� �̸�/�����ð�/����/����)
	@RequestMapping(value="/memberCoverletterInsert", method = RequestMethod.POST)
	public String memberCoverletterInsert(CoverletterMemberVo coverletterMember, 
										CoverletterMemberArticleVo memberArticle, 
										CoverletterMemberArticleSaveVo saveRecord, 
										HttpSession session){
		coverletterService.addCoverletter(coverletterMember, memberArticle, saveRecord, session);
		String loginId = (String) session.getAttribute("generalId");
		Logger.info("coverletterMember {}", coverletterMember.toString());
		Logger.info("memberArticle {}", memberArticle.toString());
		Logger.info("saveRecord {}", saveRecord.toString());
		if(loginId == null){
			return "/common/etc/loginCheck";
		}
		return "redirect:/memberCoverletterList";
	}
	
	// 04_01 ȸ���� �ۼ��� �ڱ�Ұ��� �󼼺��� (�ٽ� ����ó���ؾ���, ȸ���� �ۼ��� �ڱ�Ұ���(�ϳ��� �ڼҼ����� ������ �� ���� ���߿� �ۼ��� �ڼҼ��� ȭ�鿡 ���)
	@RequestMapping(value="/memberCoverletterDetail", method = RequestMethod.GET)
	public String memberCoverletterDetail(Model model, @RequestParam(value="mCletterCd") String mCletterCd, HttpSession session){
		Map<String, Object> memberCoverletter = coverletterService.getMemberCoverletter(mCletterCd);
		String loginId = (String) session.getAttribute("generalId");
		model.addAttribute("memeberCoverletterInfo", memberCoverletter.get("memeberCoverletterInfo"));
		Logger.info("memeberCoverletterInfo {}", model.toString());
		model.addAttribute("memeberCoverletterArticleList", memberCoverletter.get("memeberCoverletterArticleList"));
		Logger.info("memeberCoverletterArticleList{}", model.toString());
		model.addAttribute("memeberCoverletterArticleSaveRecord", memberCoverletter.get("memeberCoverletterArticleSaveRecord"));
		Logger.info("memeberCoverletterArticleSaveRecord{}", model.toString());
		if(loginId == null){
			return "/common/etc/loginCheck";
		}
		return "/resumecoverletter/coverletter/member/memberCoverletterDetail";
	}
	
	// 04_02 ȸ���� �ۼ��� �ڱ�Ұ��� ������Ʈ(������ ������ƮX, ������̺� �׿�����) (�ٽü���ó���ؾ���, �ڱ�Ұ��������� �Է�X, �׸�, �����ϸ� �Էµǰ� ��)
	@RequestMapping(value="/memberCoverletterUpdateForm", method = RequestMethod.GET)
	public String memberCoverletterUpdateForm(Model model, @RequestParam(value="mCletterCd") String mCletterCd, HttpSession session){
		Map<String, Object> memberCoverletter = coverletterService.getMemberCoverletter(mCletterCd);
		String loginId = (String) session.getAttribute("generalId");
		model.addAttribute("memeberCoverletterInfo", memberCoverletter.get("memeberCoverletterInfo"));
		Logger.info("memeberCoverletterInfo {}", model.toString());
		model.addAttribute("memeberCoverletterArticleList", memberCoverletter.get("memeberCoverletterArticleList"));
		Logger.info("memeberCoverletterArticleList{}", model.toString());
		model.addAttribute("memeberCoverletterArticleSaveRecord", memberCoverletter.get("memeberCoverletterArticleSaveRecord"));
		Logger.info("memeberCoverletterArticleSaveRecord{}", model.toString());
		if(loginId == null){
			return "/common/etc/loginCheck";
		}
		return "/resumecoverletter/coverletter/member/memberCoverletterUpdate";
	}
	// 04_03 ȸ���� �ۼ��� �ڱ�Ұ��� �����ϸ���Ʈ
	// 04_03 ȸ���� �ۼ��� �ڱ�Ұ��� ����
	
	
	
	
	
	
	// 05_01_01 �հ��ڱ�Ұ��� ����Ʈ(�˾���:�����)
	@RequestMapping(value="/passCoverletterList", method = RequestMethod.GET)
	public String passCoverletterList(Model model, HttpSession session){
		return "/resumecoverletter/coverletter/admin/passCoverletterList"; 
	}
	// 05_01_02 �հ��ڱ�Ұ��� ����Ʈ(��������������: ������)
	@RequestMapping(value="/passCoverletterListForAdmin", method = RequestMethod.GET)
	public String passCoverletterListForAdmin(Model model, HttpSession session){
		Logger.info(model.toString());
		return "/resumecoverletter/coverletter/admin/passCoverletterListForAdmin"; 
	}
	// 05_02 �հ��ڱ�Ұ��� �Է���
	@RequestMapping(value="/passCoverletterInsert", method = RequestMethod.GET)
	public String passCoverletterInsertForm(Model model, HttpSession session){
		Logger.info(model.toString());
		return "/resumecoverletter/coverletter/admin/passCoverletterInsert"; 
	}
	// 05_03 �հ��ڱ�Ұ��� �Է�ó��
	@RequestMapping(value="/passCoverletterInsert", method = RequestMethod.POST)
	public String passCoverletterInsert(Model model, CoverletterPassVo passCoverletter ,HttpSession session){
		
		Logger.info(model.toString());
		return "/resumecoverletter/coverletter/admin/passCoverletterList"; 
	}
	// 05_04 �հ��ڱ�Ұ��� �󼼺���
	@RequestMapping(value="/passCoverletterDetail", method = RequestMethod.GET)
	public String passCoverletterDetail(Model model, HttpSession session){
		Logger.info(model.toString());
		return "/resumecoverletter/coverletter/admin/passCoverletterDetail"; 
	}
	// 05_05 �հ��ڱ�Ұ��� ����
	@RequestMapping(value="/passCoverletterUpdate", method = RequestMethod.POST)
	public String passCoverletterUpdate(Model model, HttpSession session){
		Logger.info(model.toString());
		return "/resumecoverletter/coverletter/admin/passCoverletterUpdate"; 
	}
	// 05_06 �հ��ڱ�Ұ��� ����
	@RequestMapping(value="/passCoverletterDelete", method = RequestMethod.GET)
	public String passCoverletterDelete(Model model, HttpSession session){
		Logger.info(model.toString());
		return "/resumecoverletter/coverletter/admin/passCoverletterDelete"; 
	}
}
