package com.mq.controller;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
@Component
public class Receiver {
   @JmsListener(destination = "msgQueue", containerFactory = "jmsListenerContainerFactory")
   public void receiveMessage(String msg) {
      System.out.println("Message Received\n\n");
      System.out.println("Message received from Queue: "+msg);
   }
}