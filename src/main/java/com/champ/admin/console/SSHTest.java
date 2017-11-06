package com.champ.admin.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SSHTest {

	public static void main(String[] args) throws Exception {
		
		JSch jSch =  new JSch();
		
		System.out.println("Openning Session");
		Session session=jSch.getSession("jakarta", "csiesg.champ.aero", 22);

		System.out.println("Session Created");
		
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		config.put("PreferredAuthentications", "publickey,keyboard-interactive,password");
		session.setConfig(config);
		System.out.println("Session Set Config");
		session.setPassword("hand777");
		System.out.println("Session Set Pasword");
		session.connect();
		System.out.println("Connected");
		
		Channel channel = null;
		
		channel = (ChannelExec) session.openChannel("exec");			
		
		System.out.println("Execute channel open");
		
		
		((ChannelExec) channel).setCommand("sh /tmp/test.sh");
		channel.setInputStream(null);
		
		((ChannelExec)channel).setErrStream(System.err);
		
		InputStream in=channel.getInputStream();
        channel.connect();
        byte[] tmp=new byte[1024];
        while(true){
          while(in.available()>0){
            int i=in.read(tmp, 0, 1024);
            if(i<0)break;
            System.out.print(new String(tmp, 0, i));
          }
          if(channel.isClosed()){
            System.out.println("exit-status: "+channel.getExitStatus());
            break;
          }
          try{Thread.sleep(1000);}catch(Exception ee){}
        }
        channel.disconnect();
        session.disconnect();
        System.out.println("DONE");

	}

}
