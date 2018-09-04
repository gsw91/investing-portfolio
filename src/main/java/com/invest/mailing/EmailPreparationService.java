package com.invest.mailing;

import com.invest.config.AdministrationConfig;
import com.invest.domain.Mail;
import com.invest.dtos.UserDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailPreparationService {

    private static final Logger LOGGER = Logger.getLogger(EmailPreparationService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailCreatorService emailCreatorService;

    @Autowired
    private AdministrationConfig administrationConfig;

    public void sendInfoToAdmin() {
        LOGGER.info("Starting email preparation...");
        Mail mail = new Mail(administrationConfig.getAdminMail(), "New user", "");
        try {
            javaMailSender.send(createMimeMessageToAdmin(mail));
            LOGGER.info("Email has been sent.");
        } catch (MailException e) {
            LOGGER.error("Failed to process email sending: " + e.getMessage());
        }
    }

    private MimeMessagePreparator createMimeMessageToAdmin(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(emailCreatorService.buildMailToAdmin(), true);
        };
    }

}
