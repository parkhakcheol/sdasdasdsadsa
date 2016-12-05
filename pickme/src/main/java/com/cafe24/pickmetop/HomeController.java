package com.cafe24.pickmetop;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "index";
	}
	@RequestMapping(value = "/projectcover", method = RequestMethod.GET)
	public String projectCover(Locale locale, Model model) {		
		return "projectcover";
	}
	
	@RequestMapping(value="/personalPolicy")
	public String policy(){
		return "/common/etc/personalPolicy";
	}
	@RequestMapping(value="/terms")
	public String terms(){
		return "/common/etc/terms";
	}

	@RequestMapping(value = "/error/error500")
	public String error500(Locale locale, Model model) {
		model.addAttribute("msg", "������ ������ �߻��Ͽ����ϴ�.");
		return "/common/error/error500";
	}
	@RequestMapping(value = "/error/error404")
	public String error404(Locale locale, Model model) {
		model.addAttribute("msg", "������ ������ �߻��Ͽ����ϴ�.");
		return "/common/error/error404";
	}
	
}
