package com.attijariLeasing.appBackend.email;

import lombok.Data;

@Data
public class Email {

    private String from;
    private String to;
    private String subject;

    private String body;

    public Email() {
    }

    public Email(String from, String to, String subject, String body) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
    }
}
