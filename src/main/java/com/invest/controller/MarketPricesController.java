package com.invest.controller;

import com.invest.dtos.MarketPriceDto;
import com.invest.exceptions.MarketPriceException;
import com.invest.mappers.MarketPriceMapper;
import com.invest.services.MarketPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/shares/")
public class MarketPricesController {

    @Autowired
    private MarketPriceService service;

    @Autowired
    private MarketPriceMapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "price", params = {"name"})
    public MarketPriceDto getAllCurrentPrices(@RequestParam("name") String index) throws MarketPriceException {
        try {
            return mapper.mapperToDto(service.findMarketPrice(index));
        } catch (MarketPriceException e) {
            return new MarketPriceDto();
        }
    }

}
