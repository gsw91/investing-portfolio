package com.invest.controller;

import com.invest.dtos.StatisticsDto;
import com.invest.mappers.StatisticsMapper;
import com.invest.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/stats/")
public class StatisticsController {

    @Autowired
    private StatisticsService service;

    @Autowired
    private StatisticsMapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "show", params = {"userId"})
    public List<StatisticsDto> showStats (@RequestParam("userId") Long userId) {
        return mapper.mapperToListDto(service.showAllOfUser(userId));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "reset", params = {"userId"})
    public boolean resetUsersInstruments(@RequestParam("userId") Long userId) {
        return service.deleteAllUsersStatistics(userId);
    }

}
