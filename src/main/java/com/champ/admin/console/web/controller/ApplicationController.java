package com.champ.admin.console.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.champ.admin.console.service.ApplicationService;

@Controller
public class ApplicationController {
	
	@Autowired
	ApplicationService   applicationService;
	
	@RequestMapping("/dashborad/{app}" )
	public ModelAndView dashBorad(@PathVariable("id") String appName	){
		ModelAndView modelAndView = null;
		
		
		
		
		return modelAndView;
	}

}
