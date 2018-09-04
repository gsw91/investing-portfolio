package com.invest.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdministrationConfigTestSuite {

    @Autowired
    private AdministrationConfig administrationConfig;

    @Test
    public void testGetAdminName() {
        //given
        String name = "Grzegorz";
        //when
        String givenName = administrationConfig.getAdmin();
        //then
        Assert.assertEquals(name, givenName);
    }

    @Test
    public void testGetAdminMail() {
        //given
        String mail = "investment.portfolio.app@gmail.com";
        //when
        String givenMail = administrationConfig.getAdminMail();
        //then
        Assert.assertEquals(mail, givenMail);
    }

}
