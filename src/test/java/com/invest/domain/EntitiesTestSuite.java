package com.invest.domain;

import com.invest.controller.UserController;
import com.invest.repositories.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.time.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntitiesTestSuite {

    @SpyBean
    private UserController controller;

    @Autowired
    private UserDao userDao;

    @Autowired
    private InstrumentDao instrumentDao;

    @Autowired
    private StatisticsDao statisticsDao;

    private long user1Id = 0;
    private long user2Id = 0;
    private long user3Id = 0;

    @Before
    public void shouldGetIds() {
        //create data testing
        User testingUser1 = new User("test1", "test1", "test1@test.com");
        User testingUser2 = new User("test2", "test2", "test2@test.com");
        User testingUser3 = new User("test3", "test3", "test3@test.com");
        //saving data testing into db
        userDao.save(testingUser1);
        userDao.save(testingUser2);
        userDao.save(testingUser3);
        //getting the ids
        user1Id = testingUser1.getId();
        user2Id = testingUser2.getId();
        user3Id = testingUser3.getId();
    }

    @Test
    public void shouldCheckCreatedUsers() {
        //given
        User readT1User = userDao.findById(user1Id).orElse(new User());
        User readT2User = userDao.findById(user2Id).orElse(new User());
        User readT3User = userDao.findById(user3Id).orElse(new User());
        //when & then
        Assert.assertEquals("test1", readT1User.getLogin());
        Assert.assertEquals("test2", readT2User.getPassword());
        Assert.assertEquals("test3@test.com", readT3User.getEmail());
    }

    @Test
    public void shouldAddAndRemoveStats() {
        //given
        User readT1User = userDao.findById(user1Id).orElse(new User());
        User readT2User = userDao.findById(user2Id).orElse(new User());
        User readT3User = userDao.findById(user3Id).orElse(new User());

        Statistics statistics1 = new Statistics(readT1User, "Cognor", BigDecimal.valueOf(1.80), LocalDate.parse("2018-08-10"), 2400L, BigDecimal.valueOf(1.94), LocalDate.now());
        Statistics statistics2 = new Statistics(readT2User, "Cognor", BigDecimal.valueOf(1.55), LocalDate.parse("2018-07-02"), 100L, BigDecimal.valueOf(1.94), LocalDate.now());
        Statistics statistics3 = new Statistics(readT3User, "Cognor", BigDecimal.valueOf(2.00), LocalDate.parse("2018-06-10"), 20000L, BigDecimal.valueOf(1.94), LocalDate.now());

        //when
        statisticsDao.save(statistics1);
        statisticsDao.save(statistics2);
        statisticsDao.save(statistics3);

        long statistics1Id = statistics1.getId();
        long statistics2Id = statistics2.getId();
        long statistics3Id = statistics3.getId();

        Statistics readStatistics1 = statisticsDao.findById(statistics1Id).orElse(new Statistics());
        Statistics readStatistics2 = statisticsDao.findById(statistics2Id).orElse(new Statistics());
        Statistics readStatistics3 = statisticsDao.findById(statistics3Id).orElse(new Statistics());
        //then
        Assert.assertEquals("Cognor", readStatistics1.getInstrumentName());
        Assert.assertEquals(1.55, readStatistics2.getBuyingPrice().doubleValue(), 0);
        Assert.assertEquals(LocalDate.parse("2018-06-10"), readStatistics3.getBuyingDate());
        //clean-up
        statisticsDao.deleteById(statistics1Id);
        statisticsDao.deleteById(statistics2Id);
        statisticsDao.deleteById(statistics3Id);
    }

    @Test
    public void shouldAddInstrumentsWithRelationsToUser() {
        //given
        User userOne = userDao.findById(user1Id).orElse(new User());
        User userTwo = userDao.findById(user2Id).orElse(new User());
        User userThree = userDao.findById(user3Id).orElse(new User());

        Instrument userOneInstrument = new Instrument(userOne, 1000L, "COGNOR", 1.80, LocalDate.parse("2018-05-05"));
        Instrument userTwoInstrument = new Instrument(userTwo, 1500L, "KREZUS", 1.76, LocalDate.parse("2018-05-05"));
        Instrument userThreeInstrument = new Instrument(userThree, 2000L, "POLIMEXMS", 1.55, LocalDate.parse("2018-05-05"));
        Instrument userOneInstrument2 = new Instrument(userOne, 1800L, "POLIMEXMS", 1.94, LocalDate.now());
        //when
        instrumentDao.save(userOneInstrument);
        instrumentDao.save(userTwoInstrument);
        instrumentDao.save(userThreeInstrument);
        instrumentDao.save(userOneInstrument2);

        long idOne = userOneInstrument.getId();
        long idTwo = userTwoInstrument.getId();
        long idThree = userThreeInstrument.getId();
        long idFour = userOneInstrument2.getId();

        Instrument readInstrumentOne = instrumentDao.findById(idOne).orElse(new Instrument());
        Instrument readInstrumentTwo = instrumentDao.findById(idTwo).orElse(new Instrument());
        Instrument readInstrumentThree = instrumentDao.findById(idThree).orElse(new Instrument());
        Instrument readInstrumentFour = instrumentDao.findById(idFour).orElse(new Instrument());
        //then
        Assert.assertEquals(user1Id, readInstrumentOne.getUser().getId().longValue());
        Assert.assertEquals(user2Id, readInstrumentTwo.getUser().getId().longValue());
        Assert.assertEquals("POLIMEXMS", readInstrumentThree.getShare());
        Assert.assertEquals("POLIMEXMS", readInstrumentFour.getShare());
        //clean-up
        instrumentDao.deleteById(idOne);
        instrumentDao.deleteById(idTwo);
        instrumentDao.deleteById(idThree);
        instrumentDao.deleteById(idFour);
    }

    @After
    public void shouldCleanUpAfterTests() {
        //clean-up
        userDao.deleteById(user1Id);
        userDao.deleteById(user2Id);
        userDao.deleteById(user3Id);
    }

}
