package com.champ.admin.console.ssh.util;

import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.champ.admin.console.model.Cargospot;

public class SshLogger {

	private static final Logger LOGGER = LoggerFactory.getLogger(SshLogger.class);

	public static void writeLog(String log,Logger logger, OutputStream out, Cargospot cargospot) {
		StringBuilder logMsg = new StringBuilder();
		logMsg.append("[").append(cargospot.getName()).append("]").append(log);
		if (logger != null) {
			logger.info(logMsg.toString());
		}
		if (out != null) {
			try {
				out.write(logMsg.toString().getBytes());
				out.flush();
			} catch (IOException e) {
				LOGGER.error("Unable to wrtie OutputStream.", e);
			}
		}
	}

}
