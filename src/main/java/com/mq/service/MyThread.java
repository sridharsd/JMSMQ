package com.mq.service;


import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.mq.bean.JmsBean;

@Component
public class MyThread implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyThread.class);
    
    @Autowired
    private AsynchronousService anAsynchronousService;
    
    @Autowired
    private JmsBean jmsBean;

    @Override
    public void run() {

        LOGGER.info("Called from thread");
        
        System.out.println("before : "+Thread.currentThread().getName()); 
        
        try {
			anAsynchronousService.sendMQ(jmsBean.getCount());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {Thread.sleep(3000);} catch (InterruptedException e) {
			e.printStackTrace();
		}
        System.out.println("after : "+Thread.currentThread().getName()); 
    }
}

