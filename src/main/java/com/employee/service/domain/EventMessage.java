package com.employee.service.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class EventMessage implements Serializable {

    @JsonProperty("emailId")
    private String emailId;

    @JsonProperty("eventName")
    private String eventName;

    public EventMessage(String emailId,String eventName){
        this.emailId = emailId;
        this.eventName = eventName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

}
