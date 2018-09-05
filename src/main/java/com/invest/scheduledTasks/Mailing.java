package com.invest.scheduledTasks;

import com.invest.mailing.EmailPreparationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Mailing {

    @Autowired
    private EmailPreparationService emailPreparationService;

    @Scheduled(cron = "0 0 8 * * *")
    public void sendStatisticsEmailToAdmin () {
        emailPreparationService.createStatisticsMessageToAdmin();
    }

}
