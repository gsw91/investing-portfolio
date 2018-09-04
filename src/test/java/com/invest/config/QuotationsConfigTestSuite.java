package com.invest.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuotationsConfigTestSuite {

    @Autowired
    private QuotationsConfig quotationsConfig;

    @Test
    public void shouldReturnUrl(){
        //Given
        //When
        String url = quotationsConfig.getQuotationsPage();
        //Then
        assertEquals("http://notowania.pb.pl/stocktable/WIG", url);
    }

    @Test
    public void shouldReturnUserAgent(){
        //Given
        //When
        String userAgent = quotationsConfig.getUserAgent();
        //Then
        assertEquals("Chrome/68.0.3440.106", userAgent);
    }

    @Test
    public void shouldReturnSharesElement(){
        //Given
        //When
        String element = quotationsConfig.getSharesNames();
        //Then
        assertEquals("td.colWalor", element);
    }

    @Test
    public void shouldReturnPriceElement(){
        //Given
        //When
        String element = quotationsConfig.getSharesPrices();
        //Then
        assertEquals("td.colKurs", element);
    }

    @Test
    public void shouldReturnActualizationElement(){
        //Given
        //When
        String element = quotationsConfig.getActualization();
        //Then
        assertEquals("td.colAktualizacja", element);
    }

}
