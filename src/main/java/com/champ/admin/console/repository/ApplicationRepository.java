package com.champ.admin.console.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.champ.admin.console.model.Application;
import com.champ.admin.console.model.Cargospot;
import com.champ.admin.console.model.ChampAdminConsoleConfig;

public class ApplicationRepository {
	
	private static ApplicationRepository applicationRepository = null;
	
	private static ChampAdminConsoleConfig adminConsoleConfig = null;
	
	private static final Object LOCK = new Object();
	
	private static final Logger  LOGGER = LoggerFactory.getLogger(ApplicationRepository.class);
	
	private ApplicationRepository(){
		loadChampAdminConsoleConfig();
	}
	
	public static ApplicationRepository   getInstance(){
		if(applicationRepository == null){
			synchronized (LOCK) {
				if(applicationRepository == null){
					applicationRepository = new ApplicationRepository();
					
					LOGGER.debug("Create an instance of ApplicationRepository");
					
				}
			}
		}
		return applicationRepository;
	}
	
	public List<Application> getApplicationList(){
		return adminConsoleConfig !=null ? adminConsoleConfig.getApplications() : null;
	}
	
	public Application getApplicationByName(String name){
		Application application = null;
		if(adminConsoleConfig!=null){
			for(Application app : adminConsoleConfig.getApplications()){
				if(app.getName().equalsIgnoreCase(name)){
					application = app;
					break;
				}
			}
		}
		return application;
	}
	
	public ChampAdminConsoleConfig getChampAdminConsoleConfig(){
		return adminConsoleConfig;
	}
	
	private void loadChampAdminConsoleConfig(){
		
		if(adminConsoleConfig == null){
			synchronized (LOCK) {
				if(adminConsoleConfig == null){
					adminConsoleConfig = new ChampAdminConsoleConfig();
					List<Application> applications = new ArrayList<Application>(3);
					
					Cargospot a5 = new Cargospot();
					a5.setName("SGIE");
					a5.setHostName("sgie.champ.aero");
					a5.setUserName("jakarta");
					a5.setPassword("hand777");
					a5.setCommandPath("/tmp/admin-console");
					a5.setCspPath("/cargospot/sgie");
					
					Cargospot sqie = new Cargospot();
					sqie.setName("SQIE");
					sqie.setHostName("sqie.champ.aero");
					sqie.setUserName("jakarta");
					sqie.setPassword("hand777");
					
					applications.add(a5);
					applications.add(sqie);
					
					adminConsoleConfig.setApplications(applications);
					LOGGER.info("Load the ChampAdminConsoleConfig");
				}
			}
		}
	}
	
	private void clearConfig(){
		synchronized (LOCK) {
			adminConsoleConfig = null;
			LOGGER.debug("Clear the ChampAdminConsoleConfig");
		}
	}
	
	public void refreshChampAdminConsoleConfig(){
		clearConfig();
		loadChampAdminConsoleConfig();
	}

}
