package com.invest.mailing;

import com.invest.config.AdministrationConfig;
import com.invest.domain.Mail;
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
    private static final String PREPARATION = "Starting email preparation...";
    private static final String MAIL_SEND = "Email has been sent";
    private static final String MAIL_SENDING_ERROR = "Failed to process email sending: ";

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailCreatorService emailCreatorService;

    @Autowired
    private AdministrationConfig administrationConfig;

    public void sendInfoToAdmin(final Mail mail) {
        LOGGER.info(PREPARATION);
        try {
            javaMailSender.send(emailCreatorService.createMimeMessageToAdmin(mail));
            LOGGER.info(MAIL_SEND);
        } catch (MailException e) {
            LOGGER.error(MAIL_SENDING_ERROR + e.getMessage());
        }
    }

    public void createStatisticsMessageToAdmin() {
        LOGGER.info(PREPARATION);
        Mail mail = new Mail(administrationConfig.getAdminMail(), "Daily Statistics", "");
        try {
            javaMailSender.send(emailCreatorService.createMimeStatisticsMessageToAdmin(mail));
            LOGGER.info(MAIL_SEND);
        } catch (MailException e) {
            LOGGER.error(MAIL_SENDING_ERROR + e.getMessage());
        }
    }





}
