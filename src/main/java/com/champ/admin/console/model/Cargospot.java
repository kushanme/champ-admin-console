package com.champ.admin.console.model;

public class Cargospot implements Application {
	
	private String name;
	private String hostName;
	private String userName;
	private String password;
	private String commandPath;
	private String cspPath;
	
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
	public String getCommandPath() {
		return commandPath;
	}
	public void setCommandPath(String commandPath) {
		this.commandPath = commandPath;
	}
	public String getCspPath() {
		return cspPath;
	}
	public void setCspPath(String cspPath) {
		this.cspPath = cspPath;
	}

}
