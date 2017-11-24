package com.champ.admin.console.ssh;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.champ.admin.console.ssh.model.SshRequest;
import com.champ.admin.console.ssh.model.SshResponse;
import com.champ.admin.console.ssh.util.SshLogger;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.Session;

@Component
public class SshClient {

	@Autowired
	SshManager sshManager;

	private static final Logger LOGGER = LoggerFactory.getLogger(SshClient.class);

	public SshResponse executeCommand(SshRequest sshRequest) throws Exception {
		SshResponse response = null;
		ChannelExec channel = null;
		InputStream inputStream = null;
		try {
			Session session = sshManager.getSession(sshRequest.getApplication(), sshRequest.getOutputStream());
			if (session != null) {
				channel = (ChannelExec) session.openChannel("exec");
				channel.setCommand(sshRequest.getCommand());
				channel.setInputStream(null);
				channel.setErrStream(System.err);

				inputStream = channel.getInputStream();
				channel.connect();
				byte[] stream = new byte[1024];
				while (true) {
					while (inputStream.available() > 0) {
						int i = inputStream.read(stream, 0, 1024);
						if (i < 0)
							break;
						SshLogger.writeLog(new String(stream, 0, i), LOGGER, sshRequest.getOutputStream(),
								sshRequest.getApplication());
					}
					if (channel.isClosed()) {
						SshLogger.writeLog("Exit-status: " + channel.getExitStatus(), LOGGER,
								sshRequest.getOutputStream(), sshRequest.getApplication());
						break;
					}
				}
			}
		} catch (Exception ex) {
			SshLogger.writeLog("Error Occured: " + ex.getMessage(), LOGGER, sshRequest.getOutputStream(),
					sshRequest.getApplication());
			throw ex;
		} finally {
			inputStream.close();
			disconnectChannel(channel);
		}

		return response;
	}

	private void disconnectChannel(Channel channel) {
		try {
			if (channel != null) {
				LOGGER.debug("disconnecting the channel");
				channel.disconnect();
				LOGGER.debug("Channel disconnected");
			}
		} catch (Exception e) {
			LOGGER.error("Error occured while disconnection the Channel", e);
		}
	}

}
