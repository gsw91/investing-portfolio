package com.invest.domain;

public class Mail {

    private String mailTo;
    private String mailCC;
    private String subject;
    private String message;

    public Mail(String mailTo, String subject, String message) {
        this.mailTo = mailTo;
        this.subject = subject;
        this.message = message;
    }

    public Mail(String mailTo, String mailCC, String subject, String message) {
        this.mailTo = mailTo;
        this.mailCC = mailCC;
        this.subject = subject;
        this.message = message;
    }

    public String getMailTo() {
        return mailTo;
    }

    public String getMailCC() {
        return mailCC;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }


}
