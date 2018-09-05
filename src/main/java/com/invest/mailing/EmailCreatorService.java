package com.invest.mailing;

import com.invest.config.AdministrationConfig;
import com.invest.domain.Instrument;
import com.invest.domain.Mail;
import com.invest.domain.User;
import com.invest.services.InstrumentService;
import com.invest.services.StatisticsService;
import com.invest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;

@Service
public class EmailCreatorService {

    @Autowired
    private AdministrationConfig administrationConfig;

    @Autowired
    private UserService userService;

    @Autowired
    private InstrumentService instrumentService;

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;


    public MimeMessagePreparator createMimeMessageToAdmin(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(buildMailToAdmin(), true);
        };
    }

    public MimeMessagePreparator createMimeStatisticsMessageToAdmin(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(buildStatisticsMailtoAdmin(), true);
        };
    }

    private String buildMailToAdmin() {

        User user = userService.getLastUser();

        Context context = new Context();
        context.setVariable("first_message", "New user has registered!");
        context.setVariable("admin_config", administrationConfig);
        context.setVariable("user_name", user.getLogin());
        context.setVariable("user_password", user.getPassword());
        context.setVariable("user_mail", user.getEmail());

        return templateEngine.process("mail/admin_new_registration.html", context);
    }

    private String buildStatisticsMailtoAdmin() {

        long users = userService.countUsers();
        long instruments = instrumentService.countInstruments();
        long statistics = statisticsService.countStatistics();

        Context context = new Context();
        context.setVariable("first_message", "Daily summary !");
        context.setVariable("admin_config", administrationConfig);
        context.setVariable("users_quantity", users);
        context.setVariable("instruments_quantity", instruments);
        context.setVariable("statistics_quantity", statistics);
        context.setVariable("the_day", LocalDate.now());

        return templateEngine.process("mail/admin_daily_summary.html", context);
    }

}