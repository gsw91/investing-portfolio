package com.invest.services;

import com.invest.tables.MarketPrice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MarketPriceServiceTestSuite {

    @Autowired
    private MarketPriceService marketPriceService;

    @Test
    public void shouldUpdateAllSharesPrices() {
        //given
        int size = 355;
        String name0 = "11BIT";
        String name99 = "ESSYSTEM";
        String name200 = "MERCOR";
        String name354 = "ZUE";
        //when
        List<MarketPrice> currentQuotations = marketPriceService.updatePrices();
        //then
        assertEquals(size, currentQuotations.size());
        assertEquals(name0, currentQuotations.get(0).getIndex());
        assertEquals(name99, currentQuotations.get(99).getIndex());
        assertEquals(name200, currentQuotations.get(200).getIndex());
        assertEquals(name354, currentQuotations.get(354).getIndex());
    }

    @Test
    public void shouldReturnAllStocksFromDb() {
        //given
        List<MarketPrice> receivedList = marketPriceService.findMarketPrices();
        //when & then
        assertEquals(355, receivedList.size());

    }

}
