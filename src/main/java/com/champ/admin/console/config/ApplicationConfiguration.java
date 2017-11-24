package com.champ.admin.console.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.champ.admin.console.io.XmlConverter;
import com.champ.admin.console.model.ChampAdminConsoleConfig;
import com.champ.admin.console.repository.ApplicationRepository;

@Configuration
public class ApplicationConfiguration {

	@Autowired
	CastorMarshaller castorMarshaller;

	@Autowired
	XmlConverter xmlConverter;
	

	@Bean
	public CastorMarshaller castorMarshaller() {
		CastorMarshaller castorMarshaller = new CastorMarshaller();
		return castorMarshaller;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.US);
		return slr;
	}

	@Bean
	public XmlConverter xmlConverter() {
		XmlConverter converter = new XmlConverter();
		converter.setUnmarshaller(castorMarshaller);
		converter.setMarshaller(castorMarshaller);
		return converter;
	}

	
	public ChampAdminConsoleConfig champAdminConsoleConfig() throws Exception {

		ChampAdminConsoleConfig champAdminConsoleConfig = null;
		
//		 champAdminConsoleConfig = (ChampAdminConsoleConfig) xmlConverter
//					.convertFromXMLToObject(ApplicationUtil.getAppConfigFileName());
//			return champAdminConsoleConfig;
		
		champAdminConsoleConfig = ApplicationRepository.getInstance().getChampAdminConsoleConfig();
		
		return champAdminConsoleConfig;
	}

}
