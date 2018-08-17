package com.invest.controller;

import com.invest.dtos.InstrumentDto;
import com.invest.mappers.InstrumentMapper;
import com.invest.services.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/instrument/")
public class InstrumentController {

    @Autowired
    private InstrumentService service;

    @Autowired
    private InstrumentMapper mapper;

    @RequestMapping(method = RequestMethod.POST, value = "add", consumes = APPLICATION_JSON_VALUE)
    public InstrumentDto addInstrument(@RequestBody InstrumentDto instrumentDto) {
        return mapper.mapperToDto(service.addNewInstrument(mapper.mapperToDomain(instrumentDto)));
    }

}
