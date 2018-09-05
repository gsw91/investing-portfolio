package com.invest.mailing;

import com.invest.domain.Mail;
import com.invest.domain.Statistics;
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

    @Test
    public void testSendMailToAdmin() {
        //given
        final Mail mail = new Mail("test@test.com", "Test", "Test message");
        MimeMessagePreparator mimeMessage = emailCreatorService.createMimeMessageToAdmin(mail);
        when(emailCreatorService.createMimeMessageToAdmin(mail)).thenReturn(mimeMessage);
        doNothing().when(javaMailSender).send(mimeMessage);
        //when
        emailPreparationService.sendInfoToAdmin(mail);
        //then
        verify(javaMailSender, times(1)).send(mimeMessage);
    }

    @Test
    public void testSendStatisticsMailToAdmin() {
        //given
        final Mail mail = new Mail("test@test.com", "Test", "Test message");

//        when(statisticsService.countStatistics()).thenReturn(1L);
//        when(userService.countUsers()).thenReturn(1L);
//        when(instrumentService.countInstruments()).thenReturn(1L);

        MimeMessagePreparator mimeMessage = emailCreatorService.createMimeStatisticsMessageToAdmin(mail);

//        when(emailCreatorService.createMimeStatisticsMessageToAdmin(mail)).thenReturn(mimeMessage);
        doNothing().when(javaMailSender).send(mimeMessage);
        //when
        emailPreparationService.sendInfoToAdmin(mail);
        //then
        verify(javaMailSender, times(1)).send(mimeMessage);
    }

}
