package com.invest.mailing;

import com.invest.config.AdministrationConfig;
import com.invest.domain.Mail;
import com.invest.domain.User;
import com.invest.dtos.UserDto;
import com.invest.services.InstrumentService;
import com.invest.services.StatisticsService;
import com.invest.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailCreatorServiceTestSuite {

    @Autowired
    private EmailCreatorService emailCreatorService;

    @MockBean
    private UserService userService;

    @MockBean
    private AdministrationConfig administrationConfig;

    @MockBean
    private StatisticsService statisticsService;

    @MockBean
    private InstrumentService instrumentService;

    @Test
    public void testCreateMimeMessageToAdmin() {
        //given
        Mail mail = new Mail("test@test.com", "Test", "Test message");
        User user = new User(1L , "test", "test", "test@test.com");

        when(userService.getLastUser()).thenReturn(user);
        when(administrationConfig.getAdminName()).thenReturn("admin");
        //when
        MimeMessagePreparator mimeMessagePreparator = emailCreatorService.createMimeMessageToAdmin(mail);
        //then
        Assert.assertNotNull(mimeMessagePreparator);
    }

    @Test
    public void testCreateMimeStatisticsMessageToAdmin() {
        //given
        Mail mail = new Mail("test@test.com", "Test", "Test message");
        User user = new User(1L , "test", "test", "test@test.com");

        when(userService.countUsers()).thenReturn(0L);
        when(statisticsService.countStatistics()).thenReturn(0L);
        when(instrumentService.countInstruments()).thenReturn(0L);
        when(administrationConfig.getAdminName()).thenReturn("admin");
        //when
        MimeMessagePreparator mimeMessagePreparator = emailCreatorService.createMimeStatisticsMessageToAdmin(mail);
        //then
        Assert.assertNotNull(mimeMessagePreparator);
    }

    @Test
    public void testCreateWelcomeMail() {
        //given
        Mail mail = new Mail("test@test.com", "Test", "Test message");
        User user = new User(1L , "test", "test", "test@test.com");
        //when
        MimeMessagePreparator mimeMessagePreparator = emailCreatorService.createWelcomeMail(mail, user);
        //then
        Assert.assertNotNull(mimeMessagePreparator);
    }

    @Test
    public void testDailySummaryMail() {
        //given
        Mail mail = new Mail("test@test.com", "Test", "Test message");
        User user = new User(1L , "test", "test", "test@test.com");

        when(instrumentService.allUserInstruments(user.getId())).thenReturn(new ArrayList<>());
        //when
        MimeMessagePreparator mimeMessagePreparator = emailCreatorService.createDailySummaryMail(mail, user);
        //then
        Assert.assertNotNull(mimeMessagePreparator);
    }

}
