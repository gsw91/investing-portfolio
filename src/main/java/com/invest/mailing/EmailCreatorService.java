package com.invest.mailing;

import com.invest.config.AdministrationConfig;
import com.invest.domain.User;
import com.invest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailCreatorService {

    @Autowired
    private AdministrationConfig administrationConfig;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildMailToAdmin() {

        User user = userService.getLastUser();

        Context context = new Context();
        context.setVariable("first_message", "New user has registered!");
        context.setVariable("admin_config", administrationConfig);
        context.setVariable("user_name", user.getLogin());
        context.setVariable("user_password", user.getPassword());
        context.setVariable("user_mail", user.getEmail());

        return templateEngine.process("mail/admin_new_registration", context);
    }

}
