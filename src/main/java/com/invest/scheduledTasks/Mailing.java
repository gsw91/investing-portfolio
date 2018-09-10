package com.invest.scheduledTasks;

import com.invest.config.AdministrationConfig;
import com.invest.domain.Mail;
import com.invest.dtos.UserDto;
import com.invest.mailing.EmailPreparationService;
import com.invest.mappers.UserMapper;
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

    @Autowired
    private UserMapper userMapper;



    @Scheduled(cron = "0 0 8 * * *")
    public void sendStatisticsEmailToAdmin () {
        emailPreparationService.sendStatisticsMessageToAdmin();
    }

    @Scheduled(cron = "0 30 17 * * MON-FRI")
    public void sendSummaryEmailToUsers () {
        List<UserDto> users = userMapper.mapperToListDto(userService.getAllUsers());

        users.forEach(emailPreparationService::sendSummaryMail);

    }

}
