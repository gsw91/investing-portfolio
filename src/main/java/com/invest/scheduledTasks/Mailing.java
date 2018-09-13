package com.invest.scheduledTasks;

import com.invest.domain.User;
import com.invest.mailing.EmailPreparationService;
import com.invest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mailing {

    @Autowired
    private EmailPreparationService emailPreparationService;

    @Autowired
    private UserService userService;

    @Scheduled(cron = "0 0 8 * * *")
    public void sendStatisticsEmailToAdmin () {
        emailPreparationService.sendStatisticsMessageToAdmin();
    }

    @Scheduled(cron = "0 30 17 * * MON-FRI")
    public void sendSummaryEmailToUsers () {
        List<User> users = userService.getAllUsers();

        users.forEach(emailPreparationService::sendSummaryMail);

    }

}
