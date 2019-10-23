package com.mq.controller;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mq.bean.JmsBean;
import com.mq.service.AsynchronousService;
 
@RestController
@RequestMapping("/jms") 
public class Controller {
	/*
	 * @Autowired private JmsTemplate jmsTemplate;
	 */
	
	@Autowired
    private AsynchronousService anAsynchronousService;
	
	@Autowired
    private JmsBean jmsBean;

    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);


    @RequestMapping("/runTask/{count}")
    public String executeAsync(@PathVariable("count") int count) throws IOException {
    	jmsBean.setCount(count);
        anAsynchronousService.executeAsynchronously();
        //anAsynchronousService.sendMQ(count);

        return "OK";
    }
	
	@RequestMapping("/send/{count}") 
	public String send(@PathVariable("count") int count) throws IOException 
	{ 
		System.out.println("Sending a transaction.");
		anAsynchronousService.sendMQ(count);
		/*
											 * // Post message * to the message queue named "OrderTransactionQueue"
											 * //jmsTemplate.convertAndSend("OrderTransactionQueue", transaction);
											 * 
											 * File file = ResourceUtils.getFile("config/input.txt");
											 * System.out.println("File Found"+ file.exists()); String content = new
											 * String(Files.readAllBytes(file.toPath())); System.out.println(content);
											 * 
											 * Scanner infile = new Scanner(new File("config/input.txt")); List<String>
											 * temp = new ArrayList<String>(); String token; while (infile.hasNext()){
											 * token = infile.next(); temp.add(token); }
											 * 
											 * System.out.println(temp.subList(0, 2)); for (String line :
											 * temp.subList(0, count)){ //sender.send("msgQueue", line);
											 * jmsTemplate.convertAndSend("msgQueue", line); }
											 * System.out.println("Message Sent");
											 */
		
		
		return "ooooooooo"+count;
	} 
	
	
}