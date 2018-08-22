package com.invest.controller;

import com.invest.domain.Instrument;
import com.invest.dtos.InstrumentDto;
import com.invest.mappers.InstrumentMapper;
import com.invest.services.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/instrument/")
public class InstrumentController {

    @Autowired
    private InstrumentService service;

    @Autowired
    private InstrumentMapper instrumentMapper;

    @RequestMapping(method = RequestMethod.POST, value = "add", consumes = APPLICATION_JSON_VALUE)
    public Instrument addInstrument(@RequestBody InstrumentDto instrumentDto) {
        System.out.println(instrumentMapper.mapperToDomain(instrumentDto));
        return service.buyInstrument(instrumentMapper.mapperToDomain(instrumentDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "show", params = {"userId"})
    public List<InstrumentDto> showUserInstruments(@RequestParam("userId") Long userId) {
        return instrumentMapper.mapperToListDto(service.allUserInstruments(userId));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "sell", params = {"id"})
    public void sellInstrument(@RequestParam("id") Long id) {
        service.sellInstrument(id);
    }

}
