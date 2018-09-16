package com.invest.controller;

import com.invest.controller.operations.InstrumentOperations;
import com.invest.controller.operations.StatisticsCreation;
import com.invest.controller.operations.StatisticsOperations;
import com.invest.domain.Instrument;
import com.invest.dtos.InstrumentDto;
import com.invest.dtos.StatisticsDto;
import com.invest.mappers.InstrumentMapper;
import com.invest.services.InstrumentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/instrument/")
public class InstrumentController {

    private static Logger LOGGER = Logger.getLogger(InstrumentController.class);

    @Autowired
    private InstrumentService instrumentService;

    @Autowired
    private InstrumentMapper instrumentMapper;

    @Autowired
    private InstrumentOperations instrumentOperations;

    @Autowired
    private StatisticsCreation statisticsCreation;

    @RequestMapping(method = RequestMethod.POST, value = "add", consumes = APPLICATION_JSON_VALUE)
    public InstrumentDto addInstrument(@RequestBody InstrumentDto instrumentDto) {
        return instrumentMapper.mapperToDto(instrumentService.addInstrument(instrumentMapper.mapperToDomain(instrumentDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "show", params = {"userId"})
    public List<InstrumentDto> showUserInstruments(@RequestParam("userId") Long userId) {
        return instrumentMapper.mapperToListDto(instrumentService.allUserInstruments(userId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "showOne", params = {"userId", "index"})
    public InstrumentDto showUserInstrument(@RequestParam("userId") Long userId, @RequestParam("index") String index) {
        return instrumentMapper.mapperToDto((instrumentService.allUserInstruments(userId)).stream()
                .filter(t->t.getShare().equals(index))
                .findFirst()
                .orElse(new Instrument()));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "sell", params ={"userId", "name", "quantity", "price"})
    public boolean updateQuantity(@RequestParam("userId") Long userId, @RequestParam("name") String name,
                                        @RequestParam("quantity") Long quantity, @RequestParam("price") Double price) {
        try {
            List<StatisticsDto> statisticsDtos = instrumentOperations.sellAndPrepareStatistics(userId, name, quantity, price);
            return statisticsCreation.saveAllStatistics(statisticsDtos);
        } catch (Exception e) {
            LOGGER.error("Incorrect instrument name");
            return false;
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "reset", params = {"userId"})
    public void resetUsersInstruments(@RequestParam("userId") Long userId) {
        instrumentService.deleteAllUsersInstruments(userId);
    }

}
