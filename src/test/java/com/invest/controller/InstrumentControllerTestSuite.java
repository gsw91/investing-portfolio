package com.invest.controller;

import com.invest.controller.operations.InstrumentOperations;
import com.invest.controller.operations.StatisticsCreation;
import com.invest.domain.Instrument;
import com.invest.domain.User;
import com.invest.dtos.InstrumentDto;
import com.invest.dtos.StatisticsDto;
import com.invest.mappers.InstrumentMapper;
import com.invest.services.InstrumentService;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InstrumentController.class)
public class InstrumentControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InstrumentService service;

    @MockBean
    private InstrumentMapper mapper;

    @MockBean
    private InstrumentOperations operations;

    @MockBean
    private StatisticsCreation statisticsCreation;

    @Test
    public void shouldSellInstrument() throws Exception {
        //given
        List<StatisticsDto> statisticsDtos = new ArrayList<>();
        when(operations.sellAndPrepareStatistics(1L, "COGNOR", 16000L, 2.3)).thenReturn(statisticsDtos);
        when(statisticsCreation.saveAllStatistics(statisticsDtos)).thenReturn(true);
        //when & then
        mockMvc.perform(put("/v1/instrument/sell/")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("userId", "1")
                .param("name", "COGNOR")
                .param("quantity", "16000")
                .param("price", "2.3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(true)));
    }

    @Test
    public void shouldAddNewInstrument() throws Exception {
        //given
        Instrument instrument = new Instrument(new User(1L), 1000L, "COGNOR", 2.10,
                LocalDate.of(2018, 8, 28));
        InstrumentDto instrumentDto = new InstrumentDto(1L, 1000L, "COGNOR", 2.10,
                LocalDate.of(2018,8,28));

        when(service.addInstrument(instrument)).thenReturn(instrument);
        when(mapper.mapperToDomain(instrumentDto)).thenReturn(instrument);
        when(mapper.mapperToDto(instrument)).thenReturn(instrumentDto);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", "1");
        jsonObject.put("quantity", "1000");
        jsonObject.put("sharesIndex", "COGNOR");
        jsonObject.put("buyingPrice", "2.1");
        jsonObject.put("buyingDate", "2018-08-28");
        String jsonContent = jsonObject.toString();

        //when & then
        mockMvc.perform(post("/v1/instrument/add")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldFetchOneInstrumentOfUser() throws Exception {
        //given
        List<Instrument> instrumentList = new ArrayList<>();
        instrumentList.add(new Instrument(1L, new User(1L), 1000L, "COGNOR", 2.2,
                LocalDate.of(2018, 8, 10)));
        instrumentList.add(new Instrument(2L, new User(1L), 1000L, "KREZUS", 2.02,
                LocalDate.of(2018, 8, 10)));
        instrumentList.add(new Instrument(3L, new User(1L), 1000L, "POLIMEXMS", 3.55,
                LocalDate.of(2018, 8, 10)));

        List<InstrumentDto> instrumentDtoList = new ArrayList<>();
        instrumentDtoList.add(new InstrumentDto(1L, 1L, 1000L, "COGNOR", 2.2,
                LocalDate.of(2018, 8, 10)));
        instrumentDtoList.add(new InstrumentDto(2L, 1L, 1000L, "KREZUS", 2.02,
                LocalDate.of(2018, 8, 10)));
        instrumentDtoList.add(new InstrumentDto(3L, 1L, 1000L, "POLIMEXMS", 3.55,
                LocalDate.of(2018, 8, 10)));

        when(service.allUserInstruments(1L)).thenReturn(instrumentList);
        when(mapper.mapperToDto(instrumentList.get(0))).thenReturn(instrumentDtoList.get(0));
        //when & then
        mockMvc.perform(get("/v1/instrument/showOne")
                .contentType(MediaType.APPLICATION_JSON)
                .param("userId", "1")
                .param("index", "COGNOR"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.quantity", is(1000)))
                .andExpect(jsonPath("$.sharesIndex", is("COGNOR")));
    }

    @Test
    public void shouldFetchAllInstrumentsOfUser() throws Exception {
        //given
        List<Instrument> instrumentList = new ArrayList<>();
        instrumentList.add(new Instrument(1L, new User(1L), 1000L, "COGNOR", 2.2,
                LocalDate.of(2018, 8, 10)));
        instrumentList.add(new Instrument(2L, new User(1L), 1000L, "KREZUS", 2.02,
                LocalDate.of(2018, 8, 10)));
        instrumentList.add(new Instrument(3L, new User(1L), 1000L, "POLIMEXMS", 3.55,
                LocalDate.of(2018, 8, 10)));

        List<InstrumentDto> instrumentDtoList = new ArrayList<>();
        instrumentDtoList.add(new InstrumentDto(1L, 1L, 1000L, "COGNOR", 2.2,
                LocalDate.of(2018, 8, 10)));
        instrumentDtoList.add(new InstrumentDto(2L, 1L, 1000L, "KREZUS", 2.02,
                LocalDate.of(2018, 8, 10)));
        instrumentDtoList.add(new InstrumentDto(3L, 1L, 1000L, "POLIMEXMS", 3.55,
                LocalDate.of(2018, 8, 10)));

        when(service.allUserInstruments(1L)).thenReturn(instrumentList);
        when(mapper.mapperToListDto(instrumentList)).thenReturn(instrumentDtoList);
        //when & then
        mockMvc.perform(get("/v1/instrument/show").contentType(MediaType.APPLICATION_JSON).param("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[0].sharesIndex", is("COGNOR")))
                .andExpect(jsonPath("$[1].userId", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[2].userId", is(1)))
                .andExpect(jsonPath("$[2].buyingPrice", is(3.55)));
    }

    @Test
    public void shouldResetUsersInstruments() throws Exception {
        //given & when & then
        mockMvc.perform(delete("/v1/instrument/reset").param("userId", "1"))
                .andExpect(status().isOk());
    }

}
