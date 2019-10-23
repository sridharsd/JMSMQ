package com.mq.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

@Component
public class AsynchronousService{
	
	@Autowired 
	private JmsTemplate jmsTemplate;
	
	@Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private ApplicationContext applicationContext;
    
    
    @Async
    public void sendMQ(int count) throws IOException {
    	
    	File file = ResourceUtils.getFile("config/input.txt");
        System.out.println("File Found"+ file.exists());
        String content = new String(Files.readAllBytes(file.toPath()));
        System.out.println(content);

        Scanner infile = new Scanner(new File("config/input.txt"));
        List<String> temp = new ArrayList<String>();
        String token;
        while (infile.hasNext()){
              token = infile.next();
              temp.add(token);
        }

        System.out.println(temp.subList(0, 2));
        for (String line : temp.subList(0, count)){
              //sender.send("msgQueue", line);
              jmsTemplate.convertAndSend("msgQueue", line);
        }
        System.out.println("Message Sent");

        
    }
    
    public void executeAsynchronously() {

        MyThread myThread = applicationContext.getBean(MyThread.class);
        taskExecutor.execute(myThread);
              
    }

}
