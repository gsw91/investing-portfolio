package com.invest.controller.operations;

import com.invest.dtos.InstrumentDto;
import com.invest.mappers.InstrumentMapper;
import com.invest.services.InstrumentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstrumentOperations {

    private static Logger LOGGER = Logger.getLogger(InstrumentOperations.class);

    @Autowired
    private InstrumentMapper instrumentMapper;

    @Autowired
    private InstrumentService instrumentService;

    @Autowired
    private StatisticsOperations statisticsOperations;

    public boolean doIt(Long userId, String name, Long quantity, double price) throws Exception {
        List<InstrumentDto> instruments = instrumentMapper.mapperToListDto(instrumentService.allUserInstruments(userId)).stream()
                .filter(t-> t.getSharesIndex().equals(name))
                .collect(Collectors.toList());

        if(instruments.size()>0) {
            Long shareQuantity = instruments.stream()
                    .mapToLong(InstrumentDto::getQuantity)
                    .sum();

            if(shareQuantity.longValue() == quantity.longValue()) {

                LOGGER.info("Adding new statistics for user " + userId);

                statisticsOperations.createStatisticsWhenAllSold(instruments, userId, name, price);

                instruments.stream()
                        .map(InstrumentDto::getId)
                        .forEach(instrumentService::sellInstrument);
                LOGGER.info("All instruments have just been sold: name - " + name + ", quantity - " + quantity + ", user - "
                        + userId);

                return true;
            } else if (shareQuantity > quantity) {

                int i = 0;
                int statisticsVariable = 0;
                while (quantity!=0) {
                    Long currentQty = instruments.get(i).getQuantity();
                    if (currentQty<=quantity) {
                        quantity = quantity-currentQty;
                        Long instrumentId = instruments.get(i).getId();
                        instrumentService.sellInstrument(instrumentId);

                        statisticsOperations.createStatisticsWhenMoreSold(userId, name, statisticsVariable, price, instruments);

                        statisticsVariable++;

                    } else {
                        currentQty = currentQty - quantity;

                        statisticsOperations.createStatisticsWhenLessSold(userId, name, statisticsVariable, quantity, price, instruments);

                        InstrumentDto instrumentDto = instruments.get(i);
                        Long id = instrumentDto.getId();
                        instrumentDto.setQuantity(currentQty);
                        instrumentService.sellInstrument(id);
                        instrumentService.addInstrument(instrumentMapper.mapperToDomain(instrumentDto));
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
