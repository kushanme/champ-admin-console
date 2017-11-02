package com.champ.admin.console.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.champ.admin.console.model.Application;
import com.champ.admin.console.model.Cargospot;

@Service
public class ApplicationService implements ChampService {
	
	
	
	public List<Application> getApplications(){
		
		List<Application> applications = new ArrayList<Application>(3);
		
		Cargospot a5 = new Cargospot();
		a5.setName("SGIE");
		a5.setHostName("sgie.champ.aero");
		a5.setUserName("jakarta");
		a5.setPassword("hand777");
		
		Cargospot sqie = new Cargospot();
		sqie.setName("SQIE");
		sqie.setHostName("sqie.champ.aero");
		sqie.setUserName("jakarta");
		sqie.setPassword("hand777");
		
		applications.add(a5);
		applications.add(sqie);
		
		return applications;
		
	}
	

}
