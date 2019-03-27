package com.employee.service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ApplicationConfiguration {

    public static final String EVENT_QUEUE = "event-queue";

    @Bean
    public HttpHeaders getHttpHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Queue getEventQueue(){
        return QueueBuilder.durable(EVENT_QUEUE).build();
    }
}
