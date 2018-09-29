package com.invest.services;

import com.invest.domain.Instrument;
import com.invest.domain.User;
import com.invest.exceptions.SharesException;
import com.invest.repositories.InstrumentDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InstrumentServiceTestSuite {

    @InjectMocks
    private InstrumentService instrumentService;

    @Mock
    private InstrumentDao instrumentDao;

    @Test
    public void testAllUsersInstruments() {
        //given
        Long userId = 91L;
        List<Instrument> userInsturments = new ArrayList<>();
        userInsturments.add(new Instrument(21L, new User(userId), 2100L, "PKNORLEN", 98.01,
                LocalDate.of(2018, 7, 21)));
        userInsturments.add(new Instrument(26L, new User(userId), 1000L, "CDPROJEKT", 181.01,
                LocalDate.of(2018, 6, 21)));
        userInsturments.add(new Instrument(29L, new User(userId), 4300L, "KGHM", 87.01,
                LocalDate.of(2018, 7, 11)));
        when(instrumentDao.count()).thenReturn(3L);
        when(instrumentDao.findAll()).thenReturn(userInsturments);
        //when
        List<Instrument> list = instrumentService.allUserInstruments(91L);
        //then
        Assert.assertEquals(3, userInsturments.size());
        Assert.assertEquals(21L, list.get(0).getId().longValue());
        Assert.assertEquals(26L, list.get(1).getId().longValue());
        Assert.assertEquals(29L, list.get(2).getId().longValue());
    }

    @Test
    public void testAddInstrument() throws SharesException {
        //given
        Instrument instrument = new Instrument(21L, new User(61L), 2100L, "PKNORLEN", 98.01,
                LocalDate.of(2018, 7, 21));
        when(instrumentDao.save(instrument)).thenReturn(instrument);
        //when
        Instrument savedInstrument = instrumentService.addInstrument(instrument);
        //then
        Assert.assertEquals(21L, savedInstrument.getId().longValue());
        Assert.assertEquals(61L, savedInstrument.getUser().getId().longValue());
        Assert.assertEquals(2100L, savedInstrument.getQuantity().longValue());
        Assert.assertEquals("PKNORLEN", savedInstrument.getShare());
        Assert.assertEquals(98.01, savedInstrument.getBuyingPrice(), 0.01);
        Assert.assertEquals(LocalDate.of(2018, 7, 21), savedInstrument.getBuyingDate());
    }

    @Test
    public void testSellInstrument() {
        //given
        long userId = 11;
        doNothing().when(instrumentDao).deleteById(userId);
        //when &
        instrumentService.sellInstrument(userId);
        //then
        verify(instrumentDao, times(1)).deleteById(userId);
    }

    @Test
    public void testCountInstruments() {
        //given
        when(instrumentDao.count()).thenReturn(17L);
        //when
        long quantityOfInstruments = instrumentService.countInstruments();
        //then
        Assert.assertEquals(17L, quantityOfInstruments);
    }

    @Test
    public void testDeleteAllUsersInstrumentsCaseOne() {
        //given
        List<Instrument> list = new ArrayList<>();

        Instrument instrumentOne = new Instrument(21L, new User(1L), 2100L, "PKNORLEN", 98.01,
                LocalDate.of(2018, 7, 21));
        Instrument instrumentTwo = new Instrument(26L, new User(2L), 1000L, "CDPROJEKT", 181.01,
                LocalDate.of(2018, 6, 21));
        Instrument instrumentThree = new Instrument(29L, new User(1L), 4300L, "KGHM", 87.01,
                LocalDate.of(2018, 7, 11));

        list.add(instrumentOne);
        list.add(instrumentTwo);
        list.add(instrumentThree);

        when(instrumentDao.count()).thenReturn(3L);
        when(instrumentDao.findAll()).thenReturn(list);

        doAnswer((Answer) -> {
            list.remove(instrumentTwo);
            return null;
        }).when(instrumentDao).deleteById(instrumentTwo.getId());

        //when
        boolean result = instrumentService.deleteAllUsersInstruments(2L);
        //then
        Assert.assertTrue(result);
        Assert.assertEquals(2, list.size());
    }

    @Test
    public void testDeleteAllUsersInstrumentsCaseTwo() {
        //given
        List<Instrument> list = new ArrayList<>();

        Instrument instrumentOne = new Instrument(21L, new User(1L), 2100L, "PKNORLEN", 98.01,
                LocalDate.of(2018, 7, 21));
        Instrument instrumentTwo = new Instrument(26L, new User(2L), 1000L, "CDPROJEKT", 181.01,
                LocalDate.of(2018, 6, 21));
        Instrument instrumentThree = new Instrument(29L, new User(1L), 4300L, "KGHM", 87.01,
                LocalDate.of(2018, 7, 11));

        list.add(instrumentOne);
        list.add(instrumentTwo);
        list.add(instrumentThree);

        when(instrumentDao.count()).thenReturn(3L);
        when(instrumentDao.findAll()).thenReturn(list);

        doAnswer((Answer) -> {
            list.remove(instrumentOne);
            return null;
        }).when(instrumentDao).deleteById(instrumentOne.getId());

        doAnswer((Answer) -> {
            list.remove(instrumentThree);
            return null;
        }).when(instrumentDao).deleteById(instrumentThree.getId());
        //when
        boolean result = instrumentService.deleteAllUsersInstruments(1L);
        //then
        Assert.assertTrue(result);
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void testDeleteAllUsersInstrumentsCaseThree() {
        //given
        List<Instrument> list = new ArrayList<>();

        Instrument instrumentOne = new Instrument(21L, new User(1L), 2100L, "PKNORLEN", 98.01,
                LocalDate.of(2018, 7, 21));
        Instrument instrumentTwo = new Instrument(26L, new User(2L), 1000L, "CDPROJEKT", 181.01,
                LocalDate.of(2018, 6, 21));

        list.add(instrumentOne);
        list.add(instrumentTwo);

        when(instrumentDao.count()).thenReturn(2L);
        when(instrumentDao.findAll()).thenReturn(list);
        //when
        boolean result = instrumentService.deleteAllUsersInstruments(1L);
        //then
        Assert.assertFalse(result);
        Assert.assertEquals(2, list.size());
    }

}
