package com.invest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AdministrationConfig {

    @Value("${admin.name}")
    private String admin;

    @Value("${admin.mail}")
    private String adminMail;

    public String getAdminName() {
        return admin;
    }

    public String getAdminMail() {
        return adminMail;
    }
}
