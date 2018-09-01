package com.invest.controller.operations;

import com.invest.domain.Instrument;
import com.invest.domain.User;
import com.invest.dtos.InstrumentDto;
import com.invest.dtos.StatisticsDto;
import com.invest.mappers.InstrumentMapper;
import com.invest.services.InstrumentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InstrumentOperationsTestSuite {

    @Autowired
    private InstrumentOperations instrumentOperations;

    @MockBean
    private InstrumentMapper instrumentMapper;

    @MockBean
    private InstrumentService instrumentService;

    @Test
    public void testSellAndPrepareStatisticsReturnEmptyList() {
        //given
        List<InstrumentDto> listDto = new ArrayList<>();
        List<Instrument> list = new ArrayList<>();
        long userId = 12;
        when(instrumentMapper.mapperToListDto(list)).thenReturn(listDto);
        when(instrumentService.allUserInstruments(userId)).thenReturn(list);
        //when
        List<StatisticsDto> statisticsDtos = instrumentOperations.sellAndPrepareStatistics(userId, "COGNOR", 1291L, 99.99);
        //then
        Assert.assertEquals(0, statisticsDtos.size());
    }

    @Test
    public void testSellAndPrepareStatisticsToManyToSell() {
        //given
        long userId = 12;
        List<Instrument> list = new ArrayList<>();
        List<InstrumentDto> listDto = new ArrayList<>();
        listDto.add(new InstrumentDto(userId, 1000L, "COGNOR", 90.01, LocalDate.of(2013,9,1)));
        listDto.add(new InstrumentDto(userId, 800L, "COGNOR", 90.92, LocalDate.of(2013,9,2)));

        long quantityToSell = 2000;

        when(instrumentMapper.mapperToListDto(list)).thenReturn(listDto);
        when(instrumentService.allUserInstruments(userId)).thenReturn(list);
        //when
        List<StatisticsDto> statisticsDtos = instrumentOperations.sellAndPrepareStatistics(userId, "COGNOR", quantityToSell, 99.99);
        //then
        Assert.assertEquals(0, statisticsDtos.size());
    }

    @Test
    public void testSellAndPrepareStatisticsSellAllShares() {
        //given
        Long userId = 12L;
        List<Instrument> list = new ArrayList<>();
        List<InstrumentDto> listDto = new ArrayList<>();
        listDto.add(new InstrumentDto(1L, userId, 1000L, "COGNOR", 90.01, LocalDate.of(2013,9,1)));
        listDto.add(new InstrumentDto(2L, userId, 800L, "COGNOR", 90.92, LocalDate.of(2013,9,2)));

        Long quantityToSell = 1800L;

        when(instrumentMapper.mapperToListDto(list)).thenReturn(listDto);
        when(instrumentService.allUserInstruments(userId)).thenReturn(list);
        doNothing().when(instrumentService).sellInstrument(1L);
        doNothing().when(instrumentService).sellInstrument(2L);
        //when
        List<StatisticsDto> statisticsDtos = instrumentOperations.sellAndPrepareStatistics(userId, "COGNOR", quantityToSell, 99.99);
        //then
        Assert.assertEquals(2, statisticsDtos.size());
        Assert.assertEquals(1000L, statisticsDtos.get(0).getQuantity().longValue());
        Assert.assertEquals(800L, statisticsDtos.get(1).getQuantity().longValue());
    }

    @Test
    public void testSellAndPrepareStatisticsSellLessShares() {
        //given
        long userId = 12;
        List<Instrument> list = new ArrayList<>();
        List<InstrumentDto> listDto = new ArrayList<>();
        listDto.add(new InstrumentDto(1L, userId, 1000L, "COGNOR", 90.01, LocalDate.of(2013,9,1)));
        listDto.add(new InstrumentDto(2L, userId, 1000L, "COGNOR", 90.92, LocalDate.of(2013,9,2)));

        long quantityToSell = 1800;

        Instrument instrument = new Instrument(new User(userId), 200L, "COGNOR", 90.92, LocalDate.of(2013,9,2));
        InstrumentDto instrumentDto = new InstrumentDto(userId, 200L, "COGNOR", 90.92, LocalDate.of(2013,9,2));

        when(instrumentMapper.mapperToListDto(list)).thenReturn(listDto);
        when(instrumentService.allUserInstruments(userId)).thenReturn(list);
        when(instrumentMapper.mapperToDomain(instrumentDto)).thenReturn(instrument);
        doNothing().when(instrumentService).sellInstrument(1L);
        doNothing().when(instrumentService).sellInstrument(2L);
        when(instrumentService.addInstrument(instrument)).thenReturn(instrument);
        //when
        List<StatisticsDto> statisticsDtos = instrumentOperations.sellAndPrepareStatistics(userId, "COGNOR", quantityToSell, 99.99);
        //then
        Assert.assertEquals(2, statisticsDtos.size());
        Assert.assertEquals(1000L, statisticsDtos.get(0).getQuantity().longValue());
        Assert.assertEquals(800L, statisticsDtos.get(1).getQuantity().longValue());
    }

}
