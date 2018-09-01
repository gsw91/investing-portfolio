package com.invest.scheduledTasks;

import com.invest.quotations.QuotationConnecting;
import com.invest.quotations.Share;
import com.invest.quotations.SharesMap;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class QuotationsUpdatingTestSuite {

    private static final Logger LOGGER = Logger.getLogger(QuotationsUpdatingTestSuite.class);

    @InjectMocks
    private QuotationsUpdating quotationsUpdating;

    @Mock
    private QuotationConnecting quotationConnecting;

    @Test
    public void testUpdateQuotations() {
        //given
        Share share1 = new Share("TEST", 0.01, LocalDateTime.of(2018, 9, 1, 10, 30),
                LocalDateTime.of(2018, 9, 1, 10, 30));
        Map<String, Share> shareMap = new HashMap<>();
        shareMap.put("TEST", share1);

        when(quotationConnecting.updateQuotations(SharesMap.marketPriceMap)).thenReturn(shareMap);
        //when
        LOGGER.info("--->>> START QUOTATIONS TEST <<<---");
        quotationsUpdating.updateQuotations();
        quotationsUpdating.updateQuotations();
        quotationsUpdating.updateQuotations();
        quotationsUpdating.updateQuotations();
        quotationsUpdating.updateQuotations();
        //then
        verify(quotationConnecting, times(5)).updateQuotations(SharesMap.marketPriceMap);
        LOGGER.info("--->>> END QUOTATIONS TEST <<<---");
    }


}
