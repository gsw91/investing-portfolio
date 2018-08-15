package com.invest.quotations;

import com.invest.dtos.MarketPriceDto;
import com.invest.services.MarketPriceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuotationConnectingTestSuite {

    @Autowired
    private QuotationConnecting quotationConnecting;

    @Autowired
    private MarketPriceService marketPriceService;

    @Test
    public void shouldReturnCurrentStocksListFromServerWithTheSameIDs() {
        //given
        List<MarketPriceDto> pricesBefore = marketPriceService.findMarketPrices();
        List<MarketPriceDto> currentQuotations = quotationConnecting.updateQuotations();

        //when
        List<LocalDateTime> timesBefore = pricesBefore.stream()
                .map(MarketPriceDto::getServerActualization)
                .collect(Collectors.toList());
        List<LocalDateTime> timesAfter = currentQuotations.stream()
                .map(MarketPriceDto::getServerActualization)
                .collect(Collectors.toList());

        int size = timesBefore.size();
        int score = 0;
        for(int i=0; i<size; i++) {
            if(!timesBefore.get(i).equals(timesAfter.get(i))) {
                score++;
            }
        }
        long idBeforeZue = pricesBefore.stream()
                .filter(t-> t.getIndex().equals("ZUE"))
                .map(MarketPriceDto::getId)
                .findFirst().get();

        long idBefore11BIT = pricesBefore.stream()
                .filter(t-> t.getIndex().equals("11BIT"))
                .map(MarketPriceDto::getId)
                .findFirst().get();

        long idCurrentZue = currentQuotations.stream()
                .filter(t-> t.getIndex().equals("ZUE"))
                .map(MarketPriceDto::getId)
                .findFirst().get();

        long idCurrent11BIT = currentQuotations.stream()
                .filter(t-> t.getIndex().equals("11BIT"))
                .map(MarketPriceDto::getId)
                .findFirst().get();

        //then
        assertNotEquals(0, score);
        assertEquals(idBefore11BIT, idCurrent11BIT);
        assertEquals(idBeforeZue, idCurrentZue);
    }

}
