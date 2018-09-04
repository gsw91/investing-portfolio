package com.invest.quotations;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuotationsConnectingTestSuite {

    private static final Logger LOGGER = Logger.getLogger(QuotationsConnectingTestSuite.class);

    @Autowired
    private QuotationConnecting connecting;

    @Test
    public void testUpdateQuotations() {
        LOGGER.info("--->>> START TEST QUOTATIONS CONNECTING <<<---");
        //given
        Map<String, Share> shareMap = new HashMap<>();
        //when
        shareMap = connecting.updateQuotations(shareMap);
        //then
        if(InternetConnection.isAvailable()) {
            Assert.assertTrue(InternetConnection.isAvailable());
            Assert.assertEquals(356, shareMap.size());
        } else {
            Assert.assertFalse(InternetConnection.isAvailable());
            Assert.assertEquals(0, shareMap.size());
        }
        LOGGER.info("--->>> END TEST QUOTATIONS CONNECTING <<<---");
    }

}
