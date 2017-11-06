package com.champ.admin.console.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.champ.admin.console.model.Application;
import com.champ.admin.console.model.Cargospot;
import com.champ.admin.console.repository.ApplicationRepository;

@Service
public class ApplicationService implements ChampService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationService.class);
	
	public List<Application> getApplications(){
		
		return ApplicationRepository.getInstance().getApplicationList();
		
	}
	
	public Application getApplicationByName(String name){
		Application app = null;
		app = ApplicationRepository.getInstance().getApplicationByName(name);
		return app;
	}
	

}
