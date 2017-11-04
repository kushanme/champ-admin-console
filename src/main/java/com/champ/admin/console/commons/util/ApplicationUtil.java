package com.champ.admin.console.commons.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationUtil {
	
	public static final String CONFIG_PATH_NAME = "configPath";
	public static final String APPLICATION_CONFIG_FILE_NAME = "app-config.xml";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationUtil.class);
	
	public static  final String getConfigPath(){
		
		String path = System.getProperty(CONFIG_PATH_NAME);
		if(path == null){
			LOGGER.warn("Unable to find the system property ["+CONFIG_PATH_NAME+"]");
			path = "/";
		}
		LOGGER.debug("CONFIG_PATH_NAME ["+CONFIG_PATH_NAME+"] :" + path);
		return path;
	}
	
	
	public static  final String getAppConfigFileName(){
		StringBuilder fileName = new StringBuilder(getConfigPath());
		fileName.append("/").append(APPLICATION_CONFIG_FILE_NAME);
				
		return fileName.toString();
	}
	

}
