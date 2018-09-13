package com.invest.scheduledTasks;

import com.invest.domain.User;
import com.invest.mailing.EmailPreparationService;
import com.invest.mappers.UserMapper;
import com.invest.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MailingTestSuite {

    @Mock
    private EmailPreparationService emailPreparationService;

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private Mailing mailing;

    @Test
    public void testSendStatisticsEmailToAdmin() {
        //given
        doNothing().when(emailPreparationService).sendStatisticsMessageToAdmin();
        //when
        mailing.sendStatisticsEmailToAdmin();
        mailing.sendStatisticsEmailToAdmin();
        mailing.sendStatisticsEmailToAdmin();
        //then
        verify(emailPreparationService, times(3)).sendStatisticsMessageToAdmin();
    }

    @Test
    public void testSendSummaryEmailToUsers() {
        //given
        List<User> usersList = new ArrayList<>();
        usersList.add(new User(1L, "Test1", "test1", "test1@test.com"));
        usersList.add(new User(2L, "Test2", "test2", "test2@test.com"));
        usersList.add(new User(3L, "Test3", "test3", "test3@test.com"));

        when(userService.getAllUsers()).thenReturn(usersList);
        doNothing().when(emailPreparationService).sendSummaryMail(usersList.get(0));
        doNothing().when(emailPreparationService).sendSummaryMail(usersList.get(1));
        doNothing().when(emailPreparationService).sendSummaryMail(usersList.get(2));
        //when
        mailing.sendSummaryEmailToUsers();
        mailing.sendSummaryEmailToUsers();
        mailing.sendSummaryEmailToUsers();
        mailing.sendSummaryEmailToUsers();
        //then
        verify(emailPreparationService, times(4)).sendSummaryMail(usersList.get(0));
        verify(emailPreparationService, times(4)).sendSummaryMail(usersList.get(1));
        verify(emailPreparationService, times(4)).sendSummaryMail(usersList.get(2));
    }

}
