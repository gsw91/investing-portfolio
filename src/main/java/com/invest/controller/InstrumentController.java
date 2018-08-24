package com.invest.controller;

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
    private InstrumentService service;

    @Autowired
    private InstrumentMapper instrumentMapper;

    @RequestMapping(method = RequestMethod.POST, value = "add", consumes = APPLICATION_JSON_VALUE)
    public Instrument addInstrument(@RequestBody InstrumentDto instrumentDto) {
        System.out.println(instrumentMapper.mapperToDomain(instrumentDto));
        return service.addInstrument(instrumentMapper.mapperToDomain(instrumentDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "show", params = {"userId"})
    public List<InstrumentDto> showUserInstruments(@RequestParam("userId") Long userId) {
        return instrumentMapper.mapperToListDto(service.allUserInstruments(userId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "sell", params ={"userId", "name", "quantity", "price"})
    public boolean updateQuantity(@RequestParam("userId") Long userId, @RequestParam("name") String name,
                                        @RequestParam("quantity") Long quantity, @RequestParam("price") Double price) throws Exception {

        List<InstrumentDto> instruments = instrumentMapper.mapperToListDto(service.allUserInstruments(userId)).stream()
                .filter(t-> t.getSharesIndex().equals(name))
                .collect(Collectors.toList());

        if(instruments.size()>0) {
            Long shareQuantity = instruments.stream()
                    .mapToLong(InstrumentDto::getQuantity)
                    .sum();
            if(shareQuantity.longValue() == quantity.longValue()) {
                instruments.stream()
                        .map(InstrumentDto::getId)
                        .forEach(service::sellInstrument);
                LOGGER.info("All instruments have just been sold: name - " + name + ", quantity - " + quantity + ", user - "
                        + userId);
                return true;
            } else if (shareQuantity >= quantity) {

                int i = 0;
                while (quantity!=0) {
                    Long currentQty = instruments.get(i).getQuantity();
                    if (currentQty<=quantity) {
                        quantity = quantity-currentQty;
                        Long instrumentId = instruments.get(i).getId();
                        service.sellInstrument(instrumentId);
                    } else {
                        currentQty = currentQty - quantity;

                        InstrumentDto instrumentDto = instruments.get(i);
                        Long id = instrumentDto.getId();
                        instrumentDto.setQuantity(currentQty);
                        service.sellInstrument(id);
                        service.addInstrument(instrumentMapper.mapperToDomain(instrumentDto));
                        quantity = 0L;
                    }
                    i++;
                }
                return true;
            } else {
                return false;
            }
        } else {
            throw new Exception();
        }
    }

}
