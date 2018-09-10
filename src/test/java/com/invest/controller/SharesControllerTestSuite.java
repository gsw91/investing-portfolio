package com.invest.controller;

import com.invest.exceptions.SharesException;
import com.invest.quotations.Share;
import com.invest.services.SharesService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import sun.security.provider.SHA;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SharesController.class)
public class SharesControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SharesService service;

    @Test
    public void shouldFetchOneShare() throws Exception {
        //given

        Share share = new Share("COGNOR", 2.20, LocalDateTime.now(), LocalDateTime.now());

        when(service.getShare("COGNOR")).thenReturn(share);
        //when & then
        mockMvc.perform(get("/v1/share/name").contentType(MediaType.APPLICATION_JSON).param("name", "COGNOR"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.index", is("COGNOR")))
                .andExpect(jsonPath("$.price", is(2.2)));
    }

    @Test
    public void shouldFetchAllShares() throws Exception {
        //given
        Map<String, Share> shareMap = new HashMap<>();
        shareMap.put("COGNOR", new Share("COGNOR", 2.20, LocalDateTime.now(), LocalDateTime.now()));
        shareMap.put("KREZUS", new Share("KREZUS", 2.55, LocalDateTime.now(), LocalDateTime.now()));
        shareMap.put("POLIMEXMS", new Share("POLIMEXMS", 3.76, LocalDateTime.now(), LocalDateTime.now()));

        when(service.getAllShares()).thenReturn(shareMap);
        //when & then
        mockMvc.perform(get("/v1/share/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test(expected = SharesException.class)
    public void shouldThrownAnExceptionWhenFetchAllShares() throws SharesException {
        //given
        when(service.getAllShares()).thenThrow(new SharesException(SharesException.UPDATING_QUOTATIONS_FAILED));
        //then & then
        service.getAllShares();
    }

    @Test(expected = SharesException.class)
    public void shouldThrownAnExceptionWhenFetchOneShare() throws SharesException {
        //given
        when(service.getShare("KREZUS")).thenThrow(new SharesException(SharesException.NO_SHARE_EXCEPTION));
        //then & then
        Share share = service.getShare("KREZUS");
        //
        Assert.assertNull(share);
    }

}
