package com.invest.mappers;

import com.invest.domain.Instrument;
import com.invest.domain.User;
import com.invest.dtos.InstrumentDto;
import com.invest.exceptions.UserExistsException;
import com.invest.services.UserService;
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

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InstrumentMapperTestSuite {

    @MockBean
    private UserService userService;

    @Autowired
    private InstrumentMapper mapper;

    @Test
    public void shouldReturnInstrument() throws UserExistsException {
        //given
        InstrumentDto instrumentDto = new InstrumentDto(22L, 36L, 1800L, "COGNOR",
                2.02, LocalDate.of(2018, 8, 29));
        Instrument result = new Instrument(22L, new User(36L), 1800L, "COGNOR",
                2.02, LocalDate.of(2018, 8, 29));

        when(userService.findUserById(36L)).thenReturn(new User(36L));
        //when
        Instrument instrument = mapper.mapperToDomain(instrumentDto);
        //then
        Assert.assertEquals(result.getId(), instrument.getId());
        Assert.assertEquals(result.getUser().getId(), instrument.getUser().getId());
        Assert.assertEquals(result.getShare(), instrument.getShare());
        Assert.assertEquals(result.getBuyingDate(), instrument.getBuyingDate());
        Assert.assertEquals(result.getBuyingPrice(), instrument.getBuyingPrice());
        Assert.assertEquals(result.getQuantity(), instrument.getQuantity());
    }

    @Test
    public void shouldReturnInstrumentDto() {
        //given
        Instrument instrument = new Instrument(22L, new User(36L), 1800L, "COGNOR",
                2.02, LocalDate.of(2018, 8, 29));

        InstrumentDto result = new InstrumentDto(22L, 36L, 1800L, "COGNOR",
                2.02, LocalDate.of(2018, 8, 29));
        //when
        InstrumentDto instrumentDto = mapper.mapperToDto(instrument);
        //given
        Assert.assertEquals(result.getId(), instrumentDto.getId());
        Assert.assertEquals(result.getUserId(), instrumentDto.getUserId());
        Assert.assertEquals(result.getSharesIndex(), instrumentDto.getSharesIndex());
        Assert.assertEquals(result.getBuyingDate(), instrumentDto.getBuyingDate());
        Assert.assertEquals(result.getBuyingPrice(), instrumentDto.getBuyingPrice());
        Assert.assertEquals(result.getQuantity(), instrumentDto.getQuantity());
    }

    @Test
    public void shouldReturnInstrumentsList() throws UserExistsException{
        //give
        InstrumentDto instrumentDto1 = new InstrumentDto(22L, 36L, 1800L, "COGNOR",
                2.02, LocalDate.of(2018, 8, 29));
        InstrumentDto instrumentDto2 = new InstrumentDto(26L, 36L, 1800L, "KREZUS",
                2.30, LocalDate.of(2018, 8, 29));
        InstrumentDto instrumentDto3 = new InstrumentDto(88L, 36L, 1800L, "POLIMEXMS",
                3.70, LocalDate.of(2018, 8, 29));

        List<InstrumentDto> instrumentDtos = new ArrayList<>();
        instrumentDtos.add(instrumentDto1);
        instrumentDtos.add(instrumentDto2);
        instrumentDtos.add(instrumentDto3);

        when(userService.findUserById(36L)).thenReturn(new User(36L));
        //when
        List<Instrument> instrumentList = mapper.mapperToListDomain(instrumentDtos);
        //then
        Assert.assertEquals(3, instrumentList.size());
        Assert.assertEquals("COGNOR", instrumentList.get(0).getShare());
        Assert.assertEquals("KREZUS", instrumentList.get(1).getShare());
        Assert.assertEquals("POLIMEXMS", instrumentList.get(2).getShare());
    }

    @Test
    public void shouldReturnInstrumentDtoList() {
        //given
        Instrument instrument1 = new Instrument(22L, new User(36L), 1800L, "COGNOR",
                2.02, LocalDate.of(2018, 8, 29));
        Instrument instrument2 = new Instrument(26L, new User(36L), 1800L, "KREZUS",
                2.30, LocalDate.of(2018, 8, 29));
        Instrument instrument3 = new Instrument(88L, new User(36L), 1800L, "POLIMEXMS",
                3.70, LocalDate.of(2018, 8, 29));

        List<Instrument> instrumentsList = new ArrayList<>();
        instrumentsList.add(instrument1);
        instrumentsList.add(instrument2);
        instrumentsList.add(instrument3);

        //when
        List<InstrumentDto> instrumentDtos = mapper.mapperToListDto(instrumentsList);
        //then
        Assert.assertEquals(3, instrumentDtos.size());
        Assert.assertEquals("COGNOR", instrumentDtos.get(0).getSharesIndex());
        Assert.assertEquals("KREZUS", instrumentDtos.get(1).getSharesIndex());
        Assert.assertEquals(36L, instrumentDtos.get(2).getUserId().longValue());
    }

}
