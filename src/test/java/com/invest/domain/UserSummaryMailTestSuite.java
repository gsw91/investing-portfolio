package com.invest.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class UserSummaryMailTestSuite {

    @Test
    public void testUserSummaryMail() {
        //given & when
        UserSummaryMail userSummaryMail = new UserSummaryMail("COGNOR", 1000L, 2.11, 1.81);
        //then
        Assert.assertEquals("COGNOR", userSummaryMail.getInstrument());
        Assert.assertEquals(1000L, userSummaryMail.getQuantity());
        Assert.assertEquals(2.11, userSummaryMail.getPurchasePrice(), 0.01);
        Assert.assertEquals(1.81, userSummaryMail.getCurrentPrice(), 0.01);
        Assert.assertEquals(2110.00, userSummaryMail.getInvestedCapital().doubleValue(), 0.01);
        Assert.assertEquals(-299.99, userSummaryMail.getResult().doubleValue(), 0.01);
        Assert.assertEquals(1810.00, userSummaryMail.getValuation().doubleValue(), 0.01);
    }

}
