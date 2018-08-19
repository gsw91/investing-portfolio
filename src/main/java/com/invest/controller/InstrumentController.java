package com.invest.controller;

import com.invest.dtos.InstrumentDto;
import com.invest.mappers.InstrumentMapper;
import com.invest.mappers.UserMapper;
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
    private InstrumentMapper instrumentMapper;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(method = RequestMethod.POST, value = "add", consumes = APPLICATION_JSON_VALUE)
    public InstrumentDto addInstrument(@RequestBody InstrumentDto instrumentDto) {
        return instrumentMapper.mapperToDto(service.buyInstrument(instrumentMapper.mapperToDomain(instrumentDto)));
    }

//    @RequestMapping(method = RequestMethod.DELETE, value = "sell", params = { "user", "name", "quantity", "price" })
//    public void sellInstrument (@RequestBody UserDto userDto, @RequestParam("name") String name, @RequestParam("quantity") Long quantity, @RequestParam("price")BigDecimal price) {
//
//        List<InstrumentDto> instruments = instrumentMapper.mapperToListDto(service.showAll(userMapper.mapperToDomain(userDto)));
//        instruments.stream()
//                .filter(t -> t. )
//    }

}
