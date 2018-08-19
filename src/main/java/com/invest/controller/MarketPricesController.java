package com.invest.controller;

import com.invest.dtos.MarketPriceDto;
import com.invest.mappers.MarketPriceMapper;
import com.invest.repositories.MarketPriceDao;
import com.invest.services.MarketPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/shares/")
public class MarketPricesController {

    @Autowired
    private MarketPriceService service;

    @Autowired
    private MarketPriceMapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "prices")
    public List<MarketPriceDto> getAllCurrentPrices() {
        List<MarketPriceDto> list = new ArrayList<>();

        service.getAll().stream()
                .forEach(t -> list.add(new MarketPriceDto(t.getId(), t.getIndex(), t.getPrice(),
                        t.getServerActualization(), t.getApplicationActualization())));
        return list;
    }

}
