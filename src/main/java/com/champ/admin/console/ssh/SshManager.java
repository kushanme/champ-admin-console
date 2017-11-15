package com.champ.admin.console.ssh;

import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.champ.admin.console.model.Cargospot;
import com.champ.admin.console.ssh.util.SshLogger;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

@Component
public class SshManager {

	private static Map<String, Session> sshSessionMap = null;
	private static final Object LOCK = new Object();
	private Properties configProperties = null;
	private static final Logger LOGGER = LoggerFactory.getLogger(SshLogger.class);

	public SshManager() {
		getSshSessionMap();
		configProperties = new Properties();
		configProperties.put("StrictHostKeyChecking", "no");
		configProperties.put("PreferredAuthentications", "publickey,keyboard-interactive,password");

	}

	public Session getSession(Cargospot cargospot, OutputStream out) {
		Session session = null;
		if (getSshSessionMap().containsKey(cargospot.getName())) {
			synchronized (LOCK) {
				session = getSshSessionMap().get(cargospot.getName());
				if (session != null && session.isConnected()) {
					return session;
				}
			}

		}
		return session;
	}

	public Session createSession(Cargospot cargospot, OutputStream out) {
		Session session = null;
		try {
			JSch jSch = new JSch();

			SshLogger.writeLog("Connecting to the server..." + cargospot.getHostName(), LOGGER, out, cargospot);
			session = jSch.getSession(cargospot.getUserName(), cargospot.getHostName(), cargospot.getPort());

			SshLogger.writeLog("Connected", LOGGER, out, cargospot);

			session.setConfig(getConfigProperties());
			session.setPassword(cargospot.getPassword());
			
			SshLogger.writeLog("Creating the session", LOGGER, out, cargospot);
			session.connect();
			SshLogger.writeLog("Session created", LOGGER, out, cargospot);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		return session;
	}

	public Properties getConfigProperties() {
		return configProperties;
	}

	public void setConfigProperties(Properties configProperties) {
		this.configProperties = configProperties;
	}

	public static Map<String, Session> getSshSessionMap() {
		if(sshSessionMap == null){
			synchronized (LOCK) {
				if (sshSessionMap == null){
					sshSessionMap = new ConcurrentHashMap<>();
				}
			}
		}
		return sshSessionMap;
	}

}