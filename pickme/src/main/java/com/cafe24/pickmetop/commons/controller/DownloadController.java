package com.cafe24.pickmetop.commons.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DownloadController {
 
	@RequestMapping("/resumeDetail/download")
    public ModelAndView download( @RequestParam("etcFileName")String etcFileName, HttpServletRequest request){
        /****�ٿ�ε� ���****/ 
        // KSMART LOCAL �۾���
		//String pdfDir = "C:\\Users\\202-09\\Desktop\\PickMe_Workspace\\TeamGit\\topteam_pickme\\pickme\\src\\main\\webapp\\upload\\resumefile\\portfolio";
		// HOME LOCAL �۾���
		String pdfDir = "C:\\Users\\DoubleS\\Desktop\\TeamProject\\Workspace\\Git\\topteam_pickme\\pickme\\src\\main\\webapp\\upload\\resumefile\\portfolio";
		//CAFE24 ������
        //String pdfDir = request.getSession().getServletContext().getRealPath("/")+"upload/resumefile/portfolio";
        System.out.println("download <------------------------------");
        System.out.println("etcFileName : " + etcFileName);
    	
        ModelAndView modelAndView = new ModelAndView();
        
        modelAndView.addObject("etcFileName", new File(pdfDir+"/"+etcFileName));
        modelAndView.setViewName("viewName");
        return modelAndView;
    }
     
}
