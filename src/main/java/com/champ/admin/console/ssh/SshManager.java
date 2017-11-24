package com.champ.admin.console.ssh;

import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.champ.admin.console.model.Application;
import com.champ.admin.console.model.Cargospot;
import com.champ.admin.console.ssh.util.SshLogger;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

@Component
public class SshManager {

	private static Map<String, Session> sshSessionMap = null;
	private static final Object LOCK = new Object();
	private Properties configProperties = null;
	private static final Logger LOGGER = LoggerFactory.getLogger(SshManager.class);

	public SshManager() {
		getSshSessionMap();
		configProperties = new Properties();
		configProperties.put("StrictHostKeyChecking", "no");
		configProperties.put("PreferredAuthentications", "publickey,keyboard-interactive,password");

	}

	public Session getSession(Application application, OutputStream out) throws Exception {
		Session session = null;
		if (getSshSessionMap().containsKey(application.getName())) {
			synchronized (LOCK) {
				session = getSshSessionMap().get(application.getName());
				if (session != null && session.isConnected()) {
					return session;
				}
				else{
					session = createSession(application, out);
				}
			}

		}else{
			session = createSession(application, out);
		}
		return session;
	}

	private Session createSession(Application application, OutputStream out) throws Exception {
		Session session = null;
		try {
			synchronized (LOCK) {
				JSch jSch = new JSch();

				SshLogger.writeLog("Connecting to the server..." + application.getHostName(), LOGGER, out, application);
				session = jSch.getSession(application.getUserName(), application.getHostName(), application.getPort());

				SshLogger.writeLog("Connected", LOGGER, out, application);

				session.setConfig(getConfigProperties());
				session.setPassword(application.getPassword());

				SshLogger.writeLog("Creating the session", LOGGER, out, application);
				session.connect();
				SshLogger.writeLog("Session created", LOGGER, out, application);

				getSshSessionMap().put(application.getName(), session);
			}

		} catch (Exception e) {
			throw e;
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
		if (sshSessionMap == null) {
			synchronized (LOCK) {
				if (sshSessionMap == null) {
					sshSessionMap = new ConcurrentHashMap<>();
				}
			}
		}
		return sshSessionMap;
	}

}
