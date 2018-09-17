package com.invest.mailing;

import com.invest.config.AdministrationConfig;
import com.invest.domain.Mail;
import com.invest.domain.User;
import com.invest.dtos.UserDto;
import com.invest.services.InstrumentService;
import com.invest.services.StatisticsService;
import com.invest.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;


import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmailPreparationServiceTestSuite {

    @InjectMocks
    private EmailPreparationService emailPreparationService;

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private EmailCreatorService emailCreatorService;

    @Mock
    private UserService userService;

    @Mock
    private InstrumentService instrumentService;

    @Mock
    private StatisticsService statisticsService;

    @Mock
    private AdministrationConfig administrationConfig;

    @Test
    public void testSendInfoAccountDeleted() {
        //given
        User user = new User(1L, "test", "test", "test@test.com");
        Mail mail = new Mail("test@test.com", "Test", "Test message");
        MimeMessagePreparator mimeMessage = emailCreatorService.createMessageAccountDeleted(mail, user);
        doNothing().when(javaMailSender).send(mimeMessage);
        //when
        emailPreparationService.sendInfoAccountDeleted(user);
        //then
        verify(javaMailSender, times(1)).send(mimeMessage);

    }

    @Test
    public void testSendMailToAdmin() {
        //given
        Mail mail = new Mail("test@test.com", "Test", "Test message");
        MimeMessagePreparator mimeMessage = emailCreatorService.createMimeMessageToAdmin(mail);
        doNothing().when(javaMailSender).send(mimeMessage);
        //when
        emailPreparationService.sendInfoToAdmin();
        //then
        verify(javaMailSender, times(1)).send(mimeMessage);
    }

    @Test
    public void testSendStatisticsMailToAdmin() {
        //given
        Mail mail = new Mail("test@test.com", "Test", "Test message");
        MimeMessagePreparator mimeMessage = emailCreatorService.createMimeStatisticsMessageToAdmin(mail);
        when(administrationConfig.getAdminMail()).thenReturn(mail.getMailTo());
        doNothing().when(javaMailSender).send(mimeMessage);
        //when
        emailPreparationService.sendStatisticsMessageToAdmin();
        //then
        verify(javaMailSender, times(1)).send(mimeMessage);
    }

    @Test
    public void testSendWelcomeMail() {
        //given
        User user = new User(33L, "test", "test", "test@test.com");
        Mail mail = new Mail("test@test.com", "Test", "Test message");
        MimeMessagePreparator mimeMessage = emailCreatorService.createWelcomeMail(mail, user);
        doNothing().when(javaMailSender).send(mimeMessage);
        //when
        emailPreparationService.sendWelcomeMail(user);
        //then
        verify(javaMailSender, times(1)).send(mimeMessage);
    }

    @Test
    public void testSendSummaryMail() {
        //given
        User user = new User(33L, "test", "test", "test@test.com");
        Mail mail = new Mail("test@test.com", "Test", "Test message");
        MimeMessagePreparator mimeMessage = emailCreatorService.createDailySummaryMail(mail, user);
        doNothing().when(javaMailSender).send(mimeMessage);
        //when
        emailPreparationService.sendSummaryMail(user);
        //then
        verify(javaMailSender, times(1)).send(mimeMessage);
    }

}
