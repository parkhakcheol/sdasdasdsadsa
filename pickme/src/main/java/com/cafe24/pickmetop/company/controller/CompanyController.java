package com.cafe24.pickmetop.company.controller;

import java.util.Locale;
import java.util.Map;

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

import com.cafe24.pickmetop.company.model.*;
import com.cafe24.pickmetop.company.service.CompanyService;

@Controller
public class CompanyController {
	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);
	
	@Autowired
	CompanyService companyService;
	/*---------------------------------------------------------------------------------- 
	 * 
	 * 									������� ���� ����
	 * 
	 * ---------------------------------------------------------------------------------*/	
	
	//������� ����
	@RequestMapping(value = "/companyInfo", method = RequestMethod.GET)
	public String companyMain(Locale locale, Model model) {
		model.addAttribute("companyTotalRateList", companyService.getCompanyTotalRateTop());
		return "/companyinfo/companymain";
	}
	@RequestMapping(value = "/company/companyInfoDetail", method = RequestMethod.GET)
	public String companyInfoDetail(Locale locale, Model model,
			@RequestParam(value="companyName") String companyName) {
		model.addAttribute("companyInfoDetail", companyService.getCompnayInfoDetail(companyName));
		return "/companyinfo/companyInfoDetail";
	}
	
	//������������� ����
	@RequestMapping(value = "/company/companyInfoList", method = RequestMethod.GET)
	public String companyInfoList(Locale locale, Model model,
			@RequestParam(value="page", defaultValue="1") int page,
			@RequestParam(value="searchCompanyName", defaultValue="") String searchCompanyName) {
		if(page < 1){
			page = 1;
		}
		Map<String, Object> companyInfoMap = companyService.getCompantInfoList(page, searchCompanyName);
		logger.info("startPage: {}", companyInfoMap.get("startPage"));
		logger.info("endPage: {}", companyInfoMap.get("endPage"));
		logger.info("companyInfoList: {}", companyInfoMap.get("companyInfoList").toString());
		
		model.addAttribute("page", page);
		model.addAttribute("searchCompanyName", searchCompanyName);
		model.addAttribute("startPage",companyInfoMap.get("startPage"));
		model.addAttribute("endPage",companyInfoMap.get("endPage"));
		model.addAttribute("companyInfoList", companyInfoMap.get("companyInfoList"));
		return "/companyinfo/companyInfoList";
	}
	/*---------------------------------------------------------------------------------- 
	 * 
	 * 									�������� ���� ����
	 * 
	 * ---------------------------------------------------------------------------------*/	
	//�������� ���ȭ��
	@RequestMapping(value = "/salary/companySalaryInsertForm", method = RequestMethod.GET)
	public String companySalaryInsertForm(Model model, HttpSession session) {
		if(session.getAttribute("generalId") == null){
			return "/common/etc/loginCheck";
		}else{
			model.addAttribute("companyInfoList", companyService.getCompanyNameList());
			model.addAttribute("jobTopIndexList", companyService.getJobTopIndexList());
			return "/companyinfo/salary/companySalaryInsert";
		}
		
	}
	//�������� ���ó��
	@RequestMapping(value = "/salary/companySalaryInsert", method = RequestMethod.POST)
	public String companySalaryInsert(CompanySalaryVo companySalaryVo){
		logger.info("command param companyReview:{}", companySalaryVo.toString());
		companyService.addCompanySalary(companySalaryVo);
		return "redirect:/companyInfo";
	}
	//�������� ����� ����Ʈ(������) ����
	@RequestMapping(value = "/salary/companySalarywUnreceivedList", method = RequestMethod.GET)
	public String companySalaryUnreceivedList(Model model, @RequestParam(value="page", defaultValue="1") int page) {
		if(page < 1){
			page = 1;
		}
		model.addAttribute("name", "�������� �����");
		model.addAttribute("page", page);
		model.addAttribute("salaryUnreceivedMap", companyService.getCompanySalaryUnreceivedList(page));
		return "/admin/companyinfo/salary/companySalaryUnreceivedList";
	}
	//�������� ������ ������ ����
	@RequestMapping(value = "/salary/companySalaryUnreceivedDetail", method = RequestMethod.GET)
	public String companySalaryUnreceivedDetail(Model model, @RequestParam(value="salaryCd") int salaryCd) {
		model.addAttribute("companySalaryDetail", companyService.getCompnaySalaryDetail(salaryCd));
		return "/admin/companyinfo/salary/companySalaryUnreceivedDetail";
	}
	//�������� ������ ����ó��
	@RequestMapping(value = "/salary/companySalaryAllow", method = RequestMethod.GET)
	public String companyInterviewAllow(
				@RequestParam(value="salaryCd") int salaryCd,
				@RequestParam(value="companyName") String companyName,
				@RequestParam(value="salaryWorklevel") String salaryWorklevel) {
		
		companyService.updateCompanySalaryAllow(salaryCd, salaryWorklevel, companyName);
		return "redirect:/salary/companySalarywUnreceivedList";
	}
	//�������� ����Ʈ
	@RequestMapping(value = "/salary/companySalaryListAllow", method = RequestMethod.GET)
	public String companySalaryListAllow(Locale locale, Model model,
			@RequestParam(value="page", defaultValue="1") int page,
			@RequestParam(value="searchCompanyName", defaultValue="") String searchCompanyName) {
		if(page < 1){
			page = 1;
		}
		Map<String, Object> companyInfoMap = companyService.getCompantInfoList(page, searchCompanyName);
		logger.info("startPage: {}", companyInfoMap.get("startPage"));
		logger.info("endPage: {}", companyInfoMap.get("endPage"));
		logger.info("companyInfoList: {}", companyInfoMap.get("companyInfoList").toString());
		
		model.addAttribute("page", page);
		model.addAttribute("searchCompanyName", searchCompanyName);
		model.addAttribute("startPage",companyInfoMap.get("startPage"));
		model.addAttribute("endPage",companyInfoMap.get("endPage"));
		model.addAttribute("companyInfoList", companyInfoMap.get("companyInfoList"));
		return "/companyinfo/salary/companySalaryList";
	}
	//�������� ���
	@RequestMapping(value = "/salary/companySalaryDetail", method = RequestMethod.GET)
	public String companySalaryInsertDetail(Model model, @RequestParam(value="companyName") String companyName
											,HttpSession session) {
		if(session.getAttribute("generalId") == null){
			return "/common/etc/loginCheck";
		}else{
			model.addAttribute("companySalaryDetail", companyService.getCompanyStatisticsSalary(companyName));
			return "/companyinfo/salary/companySalaryDetail";
		}	
	}
	
	/*---------------------------------------------------------------------------------- 
	 * 
	 * 									�����ı� ���� ����
	 * 
	 * ---------------------------------------------------------------------------------*/	
	//�����ı� ���ȭ�� ����
	@RequestMapping(value = "/interview/companyInterviewInsertForm", method = RequestMethod.GET)
	public String companyInterviewInsertForm(Model model, HttpServletRequest request, HttpSession session) {
		model.addAttribute("companyInfoList", companyService.getCompanyNameList());
		model.addAttribute("jobTopIndexList", companyService.getJobTopIndexList());
		
		if(session.getAttribute("generalId") == null){
			return "/common/etc/loginCheck";
		}else{
			return "/companyinfo/interview/companyInterviewInsert";
		}
	}
	//�����ı� ���ó�� ����
	@RequestMapping(value = "/interview/companyInterviewInsert", method = RequestMethod.POST)
	public String companyInterviewInsert(Model model, CompanyInterviewVo companyInterviewVo, HttpSession session) {
		String loginId = (String) session.getAttribute("generalId");
		logger.info("loginId : {}",loginId);
		companyInterviewVo.setLoginId(loginId);
		companyService.addCompnayInterview(companyInterviewVo);
		return "redirect:/interview/companyInterviewListAllow";
	}
	//�����ı� ������ ������ ȭ�� ����
	@RequestMapping(value = "/interview/companyInterviewUnreceivedDetail", method = RequestMethod.GET)
	public String companyInterviewUnreceivedDetail(Model model, @RequestParam(value="interviewCd") int interviewCd) {
		logger.info("companyInterviewDetail : {}",companyService.getCompanyInterviewDetail(interviewCd).toString());
		model.addAttribute("companyInterviewDetail",companyService.getCompanyInterviewDetail(interviewCd));
		return "/admin/companyinfo/interview/companyInterviewUnreceivedDetail";
	}
	//�����ı� ����� ������ ȭ�� ����
	@RequestMapping(value = "/interview/companyInterviewDetail", method = RequestMethod.GET)
	public String companyInterviewDetail(Model model, @RequestParam(value="interviewCd") int interviewCd
										,HttpSession session) {
		logger.info("companyInterviewDetail : {}",companyService.getCompanyInterviewDetail(interviewCd).toString());
		if(session.getAttribute("generalId") == null){
			return "/common/etc/loginCheck";
		}else{
			model.addAttribute("companyInterviewDetail",companyService.getCompanyInterviewDetail(interviewCd));
			return "/companyinfo/interview/companyInterviewDetail";
		}			
	}
	//�����ı� ����ó�� ����
	@RequestMapping(value = "/interview/companyInterviewDelete", method = RequestMethod.GET)
	public String companyInterviewDelete(@RequestParam(value="interviewCd") int interviewCd) {
		companyService.delectCompanyInterview(interviewCd);
		return "redirect:/interview/companyInterviewUnreceivedList";
	}	
	//�����ı� ����ó�� ����
	@RequestMapping(value = "/interview/companyInterviewAllow", method = RequestMethod.GET)
	public String companyInterviewAllow(@RequestParam(value="interviewCd") int interviewCd) {
		companyService.updateCompanyInterviewAllow(interviewCd);
		return "redirect:/interview/companyInterviewUnreceivedList";
	}
	//�����ı� ���θ���Ʈ ����
	@RequestMapping(value = "/interview/companyInterviewListAllow", method = RequestMethod.GET)
	public String companyInterviewListAllow(Model model, 
				@RequestParam(value="page", defaultValue="1") int page, 
				@RequestParam(value="jobTopIndexCd", defaultValue="") String jobTopIndexCd,
				@RequestParam(value="searchCompanyName", defaultValue="") String searchCompanyName) {
		
		if(page < 1){
			page = 1;
		}
		logger.info("searchCompanyName : {}",searchCompanyName);
		Map<String, Object> companyInterviewMap = companyService.getCompanyInterviewAllowList(page, jobTopIndexCd, searchCompanyName);
		model.addAttribute("page", page);
		logger.info("jobTopIndexCd : {}",jobTopIndexCd);
		model.addAttribute("searchCompnayName", searchCompanyName);
		model.addAttribute("jobTopIndexCd", jobTopIndexCd);	
		model.addAttribute("jobTopIndexList",companyService.getJobTopIndexList());
		model.addAttribute("interviewListAllow", companyInterviewMap.get("interviewListAllow"));
		logger.info("interviewListAllow : {}",companyInterviewMap.get("interviewListAllow").toString());
		model.addAttribute("startPage", companyInterviewMap.get("startPage"));
		model.addAttribute("endPage", companyInterviewMap.get("endPage"));
		return "/companyinfo/interview/companyInterviewList";
	}
	//�����ı� ����ȭ�� ����
	
	
	
	//�����ı� ����� ����Ʈ(������) ����
	@RequestMapping(value = "/interview/companyInterviewUnreceivedList", method = RequestMethod.GET)
	public String companyInterviewUnreceivedList(Model model, @RequestParam(value="page", defaultValue="1") int page) {
		if(page < 1){
			page = 1;
		}
		model.addAttribute("page", page);
		model.addAttribute("name", "�����ı� �����");
		model.addAttribute("interviewUnreceivedMap", companyService.getCompanyInterviewUnreceivedList(page));
		return "/admin/companyinfo/interview/companyInterviewUnreceivedList";
	}
	
	/*---------------------------------------------------------------------------------- 
	 * 
	 * 									������� ���� ����
	 * 
	 * ---------------------------------------------------------------------------------*/	
	
	//������� ����ڸ���Ʈ ����
	@RequestMapping(value = "/review/companyReviewListAllow", method = RequestMethod.GET)
	public String companyReviewListAllow(Model model, 
				@RequestParam(value="page", defaultValue="1") int page, 
				@RequestParam(value="jobTopIndexCd", defaultValue="") String jobTopIndexCd,
				@RequestParam(value="searchCompanyName", defaultValue="") String searchCompanyName) {
		
		if(page < 1){
			page = 1;
		}
		logger.info("searchCompanyName : {}",searchCompanyName);
		Map<String, Object> companyReviewMap = companyService.getCompanyReviewAllowList(page, jobTopIndexCd, searchCompanyName);
		model.addAttribute("page", page);
		logger.info("jobTopIndexCd : {}",jobTopIndexCd);
		model.addAttribute("searchCompnayName", searchCompanyName);
		model.addAttribute("jobTopIndexCd", jobTopIndexCd);	
		model.addAttribute("jobTopIndexList",companyService.getJobTopIndexList());
		model.addAttribute("reviewListAllow", companyReviewMap.get("reviewListAllow"));
		logger.info("reviewListAllow : {}",companyReviewMap.get("reviewListAllow").toString());
		model.addAttribute("startPage", companyReviewMap.get("startPage"));
		model.addAttribute("endPage", companyReviewMap.get("endPage"));
		return "/companyinfo/review/companyReviewList";
	}
	//������� ����ó�� ����
	@RequestMapping(value = "/review/companyReviewDelete", method = RequestMethod.GET)
	public String companyReviewDelete(@RequestParam(value="companyReviewCd") int companyReviewCd) {
		companyService.deleteCompanyReview(companyReviewCd);
		return "redirect:/review/companyReviewUnreceivedList";
	}
	
	//������� ����ó�� ����
	@RequestMapping(value = "/review/companyReviewAllow", method = RequestMethod.GET)
	public String companyReviewAllow(@RequestParam(value="companyReviewCd") int companyReviewCd) {
		companyService.updateCompanyReviewAllow(companyReviewCd);
		return "redirect:/review/companyReviewUnreceivedList";
	}
	
	//������� �󼼺���(������)
	@RequestMapping(value = "/review/companyReviewUnreceivedDetail", method = RequestMethod.GET)
	public String companyReviewUnreceivedDetail(Model model, @RequestParam(value="companyReviewCd") int companyReviewCd) {
		model.addAttribute("reviewDetail", companyService.getCompanyReviewDetail(companyReviewCd));
		return "/admin/companyinfo/review/companyReviewUnreceivedDetail";
	}
	
	//������� �󼼺���(�����)
	@RequestMapping(value = "/review/companyReviewDetail", method = RequestMethod.GET)
	public String companyReviewDetail(Model model, @RequestParam(value="companyReviewCd") int companyReviewCd
										,HttpSession session) {
		if(session.getAttribute("generalId") == null){
			return "/common/etc/loginCheck";
		}else{
			model.addAttribute("reviewDetail", companyService.getCompanyReviewDetail(companyReviewCd));
			return "/companyinfo/review/companyReviewDetail";
		}			
	}
	
	//������� ����θ���Ʈ(������)
	@RequestMapping(value = "/review/companyReviewUnreceivedList", method = RequestMethod.GET)
	public String companyReviewUnreceivedList(Model model, @RequestParam(value="page", defaultValue="1") int page) {
		if(page < 1){
			page = 1;
		}
		model.addAttribute("name", "������� �����");
		model.addAttribute("page", page);
		model.addAttribute("reviewUnreceivedMap", companyService.getCompanyReviewUnreceivedList(page));
		return "/admin/companyinfo/review/companyReviewUnreceivedList";
	}
	
	//������� ���ó�� ����
	@RequestMapping(value = "/review/companyReviewInsert", method = RequestMethod.POST)
	public String companyReviewInsert(CompanyReviewVo companyReviewVo, HttpSession session){
		String loginId = (String) session.getAttribute("generalId");
		companyReviewVo.setLoginId(loginId);
		companyService.addCompanyReview(companyReviewVo);
		return "redirect:/review/companyReviewListAllow";
	}
	
	//������� ���ȭ�� ����
	@RequestMapping(value = "/review/companyReviewInsertForm", method = RequestMethod.GET)
	public String companyReviewInsertForm(Model model, HttpSession session) {
		if(session.getAttribute("generalId") == null){
			return "/common/etc/loginCheck";
		}else{
			model.addAttribute("companyInfoList", companyService.getCompanyNameList());
			model.addAttribute("jobTopIndexList", companyService.getJobTopIndexList());
			return "/companyinfo/review/companyReviewInsert";
		}	
	}
	//������� ��õ ����
	@RequestMapping(value = "/review/companyReviewLike", method = RequestMethod.GET)
	public String companyReviewLike(Model model, @RequestParam(value="companyReviewCd") int companyReviewCd
			,@RequestParam(value="userChoice") int userChoice) {
		companyService.updateCompanyReviewLike(companyReviewCd, userChoice);
		return "redirect:/review/companyReviewListAllow";
	}
}
