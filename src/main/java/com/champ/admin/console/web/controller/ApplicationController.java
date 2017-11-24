package com.champ.admin.console.web.controller;

import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.champ.admin.console.exception.InvalidActionException;
import com.champ.admin.console.exception.ServiceException;
import com.champ.admin.console.model.Application;
import com.champ.admin.console.service.ApplicationService;
import com.champ.admin.console.service.SshService;
import com.champ.admin.console.ssh.util.SshLogger;

@Controller
public class ApplicationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);
	
	@Autowired
	private SshService sshService;
	
	@Autowired
	private ApplicationService applicationService;
	
	
	@RequestMapping("/application/view/{appName}" )
	public ModelAndView dashBorad(@PathVariable("appName") String appName	){
		ModelAndView modelAndView = new ModelAndView();
		Application application = null ;
    	try {
    		application = applicationService.getApplicationByName(appName);
    		
		} catch (ServiceException e) {
		}
		
		modelAndView.addObject("app", application);
		modelAndView.setViewName("/application/application-view.html");
		return modelAndView;
	}
	
	
	@RequestMapping("/application/{appName}/{action}")
	public StreamingResponseBody handleRequest (@PathVariable("appName") String appName,@PathVariable("action") String action) {
		
        return new StreamingResponseBody() {
            @Override
            public void writeTo (OutputStream outputStream) throws IOException {
            	Application application = null ;
            	try {
            		application = applicationService.getApplicationByName(appName);
					sshService.actionApplicaton(application, outputStream, action);
				} catch (ServiceException | InvalidActionException e) {
					SshLogger.writeLog(e.getMessage(), LOGGER, outputStream, application);
				}
            }
       
        
        };
    }

}
