package com.invest.mailing;

import com.invest.config.AdministrationConfig;
import com.invest.domain.Mail;
import com.invest.domain.User;
import com.invest.dtos.UserDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailPreparationService {

    private static final Logger LOGGER = Logger.getLogger(EmailPreparationService.class);
    private static final String PREPARATION = "Starting email preparation...";
    private static final String MAIL_SEND = "Email has been sent";
    private static final String MAIL_SENDING_ERROR = "Failed to process email sending: ";

    @Autowired
    private AdministrationConfig administrationConfig;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailCreatorService emailCreatorService;

    public void sendReminderDataAccess(final User user) {
        LOGGER.info(PREPARATION + " reminder for user: " + user.getLogin());
        Mail mail = new Mail(user.getEmail(), "Reminder of your data of access", "");
        try {
            javaMailSender.send(emailCreatorService.createMessageDataReminder(mail, user));
            LOGGER.info(MAIL_SEND);
        } catch (MailException e) {
            LOGGER.error(MAIL_SENDING_ERROR + e.getMessage());
        }
    }

    public void sendInfoAccountDeleted(final User user) {
        LOGGER.info(PREPARATION + " account deleted");
        Mail mail = new Mail(user.getEmail(), "Account deleted", "");
        try {
            javaMailSender.send(emailCreatorService.createMessageAccountDeleted(mail, user));
            LOGGER.info(MAIL_SEND);
        } catch (MailException e) {
            LOGGER.error(MAIL_SENDING_ERROR + e.getMessage());
        }
    }

    public void sendInfoToAdmin() {
        LOGGER.info(PREPARATION + " to admin");
        Mail mail = new Mail(administrationConfig.getAdminMail(), "New user", "");
        try {
            javaMailSender.send(emailCreatorService.createMimeMessageToAdmin(mail));
            LOGGER.info(MAIL_SEND);
        } catch (MailException e) {
            LOGGER.error(MAIL_SENDING_ERROR + e.getMessage());
        }
    }

    public void sendStatisticsMessageToAdmin() {
        LOGGER.info(PREPARATION + " statistics message to admin");
        Mail mail = new Mail(administrationConfig.getAdminMail(), "Daily Statistics", "");
        try {
            javaMailSender.send(emailCreatorService.createMimeStatisticsMessageToAdmin(mail));
            LOGGER.info(MAIL_SEND);
        } catch (MailException e) {
            LOGGER.error(MAIL_SENDING_ERROR + e.getMessage());
        }
    }

    public void sendWelcomeMail(final User user) {
        LOGGER.info(PREPARATION + " welcome mail");
        Mail mail = new Mail(user.getEmail(), "Welcome in Investment Portfolio", "");
        try {
            javaMailSender.send(emailCreatorService.createWelcomeMail(mail, user));
            LOGGER.info(MAIL_SEND);
        } catch (MailException e) {
            LOGGER.error(MAIL_SENDING_ERROR + e.getMessage());
        }
    }

    public void sendSummaryMail(final User user) {
        LOGGER.info(PREPARATION + " summary mail");
        Mail mail = new Mail(user.getEmail(), "Daily summary", "");
        try {
            javaMailSender.send(emailCreatorService.createDailySummaryMail(mail, user));
            LOGGER.info(MAIL_SEND);
        } catch (MailException e) {
            LOGGER.error(MAIL_SENDING_ERROR + e.getMessage());
        }
    }

}
