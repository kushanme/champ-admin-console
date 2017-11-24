package com.champ.admin.console.ssh.util;

import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.champ.admin.console.model.Application;
import com.champ.admin.console.model.Cargospot;

public class SshLogger {

	private static final Logger LOGGER = LoggerFactory.getLogger(SshLogger.class);
	private static final String UNKNOWN  = "UNKNOW" ;
	private static final String LINE_BREAK  = "\n" ;
	
	public static void writeLog(String log,Logger logger, OutputStream out, Application application) {
		StringBuilder logMsg = new StringBuilder();
		String applicationName = application!=null ? application.getName() : UNKNOWN;
		
		logMsg.append("[").append(applicationName).append("]").append(log);
		if (logger != null) {
			logger.info(logMsg.toString());
		}
		if (out != null) {
			try {
				logMsg.append(LINE_BREAK);
				out.write(logMsg.toString().getBytes());
				out.flush();
			} catch (IOException e) {
				LOGGER.error("Unable to wrtie OutputStream.", e);
			}
		}
	}

}
