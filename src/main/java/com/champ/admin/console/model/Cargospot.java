package com.champ.admin.console.model;

public class Cargospot extends Application {
	
	private String commandPath;
	private String cspPath;
	
	public Cargospot() {
		setApplicationType(ApplicationType.CARGOSPOT);
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
