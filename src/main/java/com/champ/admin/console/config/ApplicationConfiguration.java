package com.champ.admin.console.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.castor.CastorMarshaller;

@Configuration
public class ApplicationConfiguration {
	
	@Bean
	public CastorMarshaller castorMarshaller(){
		CastorMarshaller castorMarshaller = new CastorMarshaller();
		return castorMarshaller;
	}
	

}
