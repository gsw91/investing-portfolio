package com.invest.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MailTestSuite {

    @Test
    public void testMail() {
        //given
        String mailTo = "admin@test.com";
        String mailCC = "test@test.com";
        String subject = "test";
        String message = "message";
        //when
        Mail mail = new Mail("admin@test.com", "test@test.com", "test", "message");
        //then
        Assert.assertEquals(mailTo, mail.getMailTo());
        Assert.assertEquals(mailCC, mail.getMailCC());
        Assert.assertEquals(subject, mail.getSubject());
        Assert.assertEquals(message, mail.getMessage());
    }

}
