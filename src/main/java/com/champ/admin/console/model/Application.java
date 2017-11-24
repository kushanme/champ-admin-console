package com.champ.admin.console.model;

public abstract class Application {
	
	
	protected String name;
	protected String hostName;
	protected String userName;
	protected String password;
	protected int port = 22;
	protected ApplicationType applicationType;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	public ApplicationType getApplicationType() {
		return applicationType;
	}
	protected void setApplicationType(ApplicationType applicationType) {
		this.applicationType = applicationType;
	}
		
}
