package com.mq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
@Component
public class Sender {
   @Autowired
   private JmsTemplate jmsTemplate;
   public void send(final String queue, final String msg){
      jmsTemplate.convertAndSend(queue, msg);
   }
}