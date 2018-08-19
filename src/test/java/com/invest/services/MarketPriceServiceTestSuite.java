package com.invest.services;

import com.invest.domain.MarketPrice;
import com.invest.mappers.MarketPriceMapper;
import com.invest.quotations.QuotationConnecting;
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

    @Autowired
    private QuotationConnecting quotationConnecting;

    @Autowired
    private MarketPriceMapper mapper;

    @Test
    public void shouldUpdateAllSharesPrices() {
        //given
        marketPriceService.updatePrices(mapper.mapperToListDomain(quotationConnecting.updateQuotations()));
        List<MarketPrice> currentQuotations = marketPriceService.getAll();
        int size = currentQuotations.size();
        //when
        long id11BIT = currentQuotations.stream()
                .filter(t->t.getIndex().equals("11BIT"))
                .map(MarketPrice::getId)
                .findFirst().orElse(355L);
        long idEssystem= currentQuotations.stream()
                .filter(t->t.getIndex().equals("ESSYSTEM"))
                .map(MarketPrice::getId)
                .findFirst().orElse(355L);
        long idMercor= currentQuotations.stream()
                .filter(t->t.getIndex().equals("MERCOR"))
                .map(MarketPrice::getId)
                .findFirst().orElse(355L);
        long idZue= currentQuotations.stream()
                .filter(t->t.getIndex().equals("ZUE"))
                .map(MarketPrice::getId)
                .findFirst().orElse(355L);
        //then
        assertEquals(355, size);
        assertEquals(0, id11BIT);
        assertEquals(99, idEssystem);
        assertEquals(200, idMercor);
        assertEquals(354, idZue);
    }

}
