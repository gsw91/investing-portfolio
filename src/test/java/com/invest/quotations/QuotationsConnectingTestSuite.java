package com.invest.quotations;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashMap;
import java.util.Map;

@RunWith(JUnit4.class)
public class QuotationsConnectingTestSuite {

    private static final Logger LOGGER = Logger.getLogger(QuotationsConnectingTestSuite.class);

    @Test
    public void testUpdateQuotations() {
        LOGGER.info("--->>> START TEST QUOTATIONS CONNECTING <<<---");
        //given
        QuotationConnecting connecting = new QuotationConnecting();
        Map<String, Share> shareMap = new HashMap<>();
        //when
        shareMap = connecting.updateQuotations(shareMap);
        //then
        if(InternetConnection.isAvailable()) {
            Assert.assertTrue(InternetConnection.isAvailable());
            Assert.assertEquals(355, shareMap.size());
        } else {
            Assert.assertFalse(InternetConnection.isAvailable());
            Assert.assertEquals(0, shareMap.size());
        }
        LOGGER.info("--->>> END TEST QUOTATIONS CONNECTING <<<---");
    }

}
