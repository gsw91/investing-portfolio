package com.invest.services;

import com.invest.quotations.Share;
import com.invest.quotations.SharesMap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SharesServiceTestSuite {

    @InjectMocks
    private SharesService sharesService;

    @Mock
    private SharesMap sharesMap;

    @Test
    public void testGetShare() throws Exception{
        //given
        Share share1 = new Share("TEST", 0.01, LocalDateTime.of(2018, 9, 1, 10, 30),
                LocalDateTime.of(2018, 9, 1, 10, 30));
        Share share2 = new Share("TEST2", 0.01, LocalDateTime.of(2018, 9, 1, 10, 30),
                LocalDateTime.of(2018, 9, 1, 10, 30));
        Share share3 = new Share("TEST3", 0.01, LocalDateTime.of(2018, 9, 1, 10, 30),
                LocalDateTime.of(2018, 9, 1, 10, 30));

        Map<String, Share> shareMap = new HashMap<>();
        shareMap.put("TEST", share1);
        shareMap.put("TEST2", share2);
        shareMap.put("TEST3", share3);

        when(sharesMap.getMarketPriceMap()).thenReturn(shareMap);
        //when
        Share receivedShare = sharesService.getShare("TEST");
        //then
        Assert.assertEquals(receivedShare, share1);
    }

    @Test
    public void testGetAllShares() throws Exception{
        //given
        Share share1 = new Share("TEST", 0.01, LocalDateTime.of(2018, 9, 1, 10, 30),
                LocalDateTime.of(2018, 9, 1, 10, 30));
        Share share2 = new Share("TEST2", 0.01, LocalDateTime.of(2018, 9, 1, 10, 30),
                LocalDateTime.of(2018, 9, 1, 10, 30));
        Share share3 = new Share("TEST3", 0.01, LocalDateTime.of(2018, 9, 1, 10, 30),
                LocalDateTime.of(2018, 9, 1, 10, 30));

        Map<String, Share> shareMap = new HashMap<>();
        shareMap.put("TEST", share1);
        shareMap.put("TEST2", share2);
        shareMap.put("TEST3", share3);

        when(sharesMap.getMarketPriceMap()).thenReturn(shareMap);
        //when
        Map<String, Share> receivedSharesMap = sharesService.getAllShares();
        //then
        Assert.assertEquals(3, receivedSharesMap.size());
        Assert.assertEquals(share1, receivedSharesMap.get("TEST"));
        Assert.assertEquals(share2, receivedSharesMap.get("TEST2"));
        Assert.assertEquals(share3, receivedSharesMap.get("TEST3"));
    }

}
