package com.champ.admin.console.service;

import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.champ.admin.console.exception.InvalidActionException;
import com.champ.admin.console.exception.ServiceException;
import com.champ.admin.console.model.Action;
import com.champ.admin.console.model.Application;
import com.champ.admin.console.model.Cargospot;
import com.champ.admin.console.ssh.SshClient;
import com.champ.admin.console.ssh.model.SshRequest;

@Service
public class SshService implements ChampService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SshService.class);

	@Value("${script.cargospot.stop}")
	private String cargospotStopScript;

	@Value("${script.cargospot.start}")
	private String cargospotStartScript;

	@Value("${script.cargospot.install}")
	private String cargospotInstallScript;

	@Autowired
	SshClient sshClient;

	

	public void actionApplicaton(Application application, OutputStream outputStream, String actionName)
			throws ServiceException, InvalidActionException {

		try {

			SshRequest sshRequest = new SshRequest();
			sshRequest.setApplication(application);
			sshRequest.setOutputStream(outputStream);
			Action action = Action.getAction(actionName);

			sshRequest.setCommand(getScriptCommand(application, action));
			sshClient.executeCommand(sshRequest);

		} catch (InvalidActionException iAE) {
			throw iAE;
		}catch(Exception ex){
			LOGGER.error(ex.getMessage(),ex);
		}
		

	}

	private String getScriptCommand(Application application, Action action) {
		StringBuilder command = new StringBuilder();
		command.append("sh ");

		switch (application.getApplicationType()) {
		case CARGOSPOT:
			command.append(((Cargospot) application).getCommandPath());
			command.append(getCargospotCommand(action));
			break;

		default:
			break;
		}

		return command.toString();
	}

	private String getCargospotCommand(Action action) {
		String cargospotActionCommand = null;
		switch (action) {
		case START:
			cargospotActionCommand = cargospotStartScript;
			break;
		case STOP:
			cargospotActionCommand = cargospotStopScript;
			break;
		case INSTALL:
			cargospotActionCommand = cargospotInstallScript;
			break;
		default:
			break;
		}
		return cargospotActionCommand;
	}

}
