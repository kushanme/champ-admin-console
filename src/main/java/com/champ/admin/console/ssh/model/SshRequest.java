package com.champ.admin.console.ssh.model;

import java.io.OutputStream;
import java.io.Serializable;

import com.champ.admin.console.model.Application;

public class SshRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6945355835069163290L;
	
	private Application application;
	private OutputStream outputStream = null;
	
	private String command = null;
	
	
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	public OutputStream getOutputStream() {
		return outputStream;
	}
	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	

}
