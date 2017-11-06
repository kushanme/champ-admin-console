package com.champ.admin.console.web.controller;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Controller
public class ConsoleController {
	
	
	@RequestMapping("/console/restart")
	public StreamingResponseBody handleRequest () {

        return new StreamingResponseBody() {
            @Override
            public void writeTo (OutputStream out) throws IOException {
                for (int i = 0; i < 1000; i++) {
                    out.write((Integer.toString(i) + " - ")
                                        .getBytes());
                    out.flush();
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
       
        
        };
    }
	
	@RequestMapping("/console")
    public String view(){
    	return "/console";
    }

}
