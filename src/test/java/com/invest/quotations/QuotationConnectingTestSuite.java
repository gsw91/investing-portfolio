package com.invest.quotations;

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
public class QuotationConnectingTestSuite {

    @Autowired
    private QuotationConnecting quotationConnecting;

    @Test
    public void shouldReturnCurrentStocksListFromServer() {
        //given
        List<MarketPrice> currentQuotations = quotationConnecting.updateQuotations();
        //when & then
        assertEquals(355, currentQuotations.size());
        assertEquals("11BIT", currentQuotations.get(0).getIndex());
        assertEquals("ZUE", currentQuotations.get(354).getIndex());
    }

}
