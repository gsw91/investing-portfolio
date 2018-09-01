package com.invest.quotations;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShareMapTestSuite {

    @Autowired
    private SharesMap sharesMap;

    @Test
    public void testGetMarketPriceMap() {
        if (InternetConnection.isAvailable()) {
            //given
            Map<String, Share> shares = sharesMap.getMarketPriceMap();

            //shares.forEach((t, k) -> System.out.println(k));

            boolean doesContain11BIT = shares.containsKey("11BIT");
            boolean doesContainECHO = shares.containsKey("ECHO");
            boolean doesContainMBANK = shares.containsKey("MBANK");

            //when & then
            assertEquals(355, shares.size());
            assertTrue(doesContain11BIT);
            assertTrue(doesContainECHO);
            assertTrue(doesContainMBANK);
        }

    }


}
