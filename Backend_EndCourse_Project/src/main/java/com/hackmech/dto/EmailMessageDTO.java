// EmailMessageDTO.java
package com.hackmech.dto;

import java.io.Serializable;

public class EmailMessageDTO implements Serializable {
    private String to;
    private String subject;
    private String body;

    // Constructors, Getters, Setters
    public EmailMessageDTO() {}

    public EmailMessageDTO(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
}
