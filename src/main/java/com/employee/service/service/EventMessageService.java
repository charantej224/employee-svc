package com.employee.service.service;

import com.employee.service.config.ApplicationConfiguration;
import com.employee.service.domain.EventMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventMessageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(EventMessage eventMessage){
        rabbitTemplate.convertAndSend(ApplicationConfiguration.EVENT_QUEUE,eventMessage);
    }
}
