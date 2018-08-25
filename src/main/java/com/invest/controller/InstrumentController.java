package com.invest.controller;

import com.invest.controller.operations.InstrumentOperations;
import com.invest.domain.Instrument;
import com.invest.dtos.InstrumentDto;
import com.invest.mappers.InstrumentMapper;
import com.invest.services.InstrumentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @RequestMapping(method = RequestMethod.POST, value = "add", consumes = APPLICATION_JSON_VALUE)
    public Instrument addInstrument(@RequestBody InstrumentDto instrumentDto) {
        System.out.println(instrumentMapper.mapperToDomain(instrumentDto));
        return instrumentService.addInstrument(instrumentMapper.mapperToDomain(instrumentDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "show", params = {"userId"})
    public List<InstrumentDto> showUserInstruments(@RequestParam("userId") Long userId) {
        return instrumentMapper.mapperToListDto(instrumentService.allUserInstruments(userId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "showOnlyOne", params = {"userId", "index"})
    public List<InstrumentDto> showUserInstruments(@RequestParam("userId") Long userId, @RequestParam("index") String index) {
        return instrumentMapper.mapperToListDto((instrumentService.allUserInstruments(userId)).stream()
                .filter(t->t.getShare().equals(index))
                .collect(Collectors.toList()));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "sell", params ={"userId", "name", "quantity", "price"})
    public boolean updateQuantity(@RequestParam("userId") Long userId, @RequestParam("name") String name,
                                        @RequestParam("quantity") Long quantity, @RequestParam("price") Double price) {
        try {
            return instrumentOperations.doIt(userId, name, quantity, price);
        } catch (Exception e) {
            LOGGER.error("Incorrect instrument name");
            return false;
        }
    }

}
