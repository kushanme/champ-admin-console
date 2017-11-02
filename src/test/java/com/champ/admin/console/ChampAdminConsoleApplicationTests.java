package com.champ.admin.console;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.test.context.junit4.SpringRunner;

import com.champ.admin.console.io.XmlConverter;
import com.champ.admin.console.model.Application;
import com.champ.admin.console.model.Cargospot;
import com.champ.admin.console.model.ChampAdminConsole;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChampAdminConsoleApplicationTests {

	@Autowired
	CastorMarshaller castorMarshaller;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void writeXmlFIle() throws Exception{
		XmlConverter converter = new XmlConverter();
		converter.setUnmarshaller(castorMarshaller);
		converter.setMarshaller(castorMarshaller);
		
		Cargospot a5 = new Cargospot();
		a5.setName("SGIE");
		a5.setHostName("sgie.champ.aero");
		a5.setUserName("jakarta");
		a5.setPassword("hand777");
		
		List<Application> applications = new ArrayList<>(1);
		applications.add(a5);
		
		ChampAdminConsole adminConsole = new ChampAdminConsole();
		adminConsole.setApplications(applications);
		
		
		converter.convertFromObjectToXML(adminConsole, "/home/kushan/app-config.xml");
		
	}

}
