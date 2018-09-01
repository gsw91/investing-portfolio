package com.invest.quotations;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class InternetConnectionTestSuite {

    @Test
    public void testIsAvailable() {
        //given
        boolean isAvailable = InternetConnection.isAvailable();
        //when&then
        Assert.assertTrue(isAvailable);
    }


}
