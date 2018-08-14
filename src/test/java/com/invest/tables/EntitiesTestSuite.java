package com.invest.tables;

import com.invest.repositories.InstrumentDao;
import com.invest.repositories.MarketPriceDao;
import com.invest.repositories.StatisticsDao;
import com.invest.repositories.UserDao;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntitiesTestSuite {

    @Autowired
    private UserDao userDao;

    @Autowired
    private InstrumentDao instrumentDao;

    @Autowired
    private StatisticsDao statisticsDao;

    @Autowired
    private MarketPriceDao marketPriceDao;

    private long user1Id = 0;
    private long user2Id = 0;
    private long user3Id = 0;
    private long marketId = 0;

    @Before
    public void shouldGetIds() {
        //create data testing
        User testingUser1 = new User("test1", "test1", "test1@test.com");
        User testingUser2 = new User("test2", "test2", "test2@test.com");
        User testingUser3 = new User("test3", "test3", "test3@test.com");
        MarketPrice testingInstrument = new MarketPrice(999999999L, "Cognor", 1.94, LocalDateTime.now());
        //saving data testing into db
        userDao.save(testingUser1);
        userDao.save(testingUser2);
        userDao.save(testingUser3);
        marketPriceDao.save(testingInstrument);
        //getting the ids
        user1Id = testingUser1.getId();
        user2Id = testingUser2.getId();
        user3Id = testingUser3.getId();
        marketId = testingInstrument.getId();
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

        Statistics statistics1 = new Statistics(readT1User, "Cognor", BigDecimal.valueOf(1.80), LocalDate.parse("2018-08-10"), BigDecimal.valueOf(1.94), LocalDate.now(), 2400L);
        Statistics statistics2 = new Statistics(readT2User, "Cognor", BigDecimal.valueOf(1.55), LocalDate.parse("2018-07-02"), BigDecimal.valueOf(1.94), LocalDate.now(), 100L);
        Statistics statistics3 = new Statistics(readT3User, "Cognor", BigDecimal.valueOf(2.00), LocalDate.parse("2018-06-10"), BigDecimal.valueOf(1.94), LocalDate.now(), 20000L);

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
    public void shouldAddMarketPrice() {
        //given
        MarketPrice readCognor = marketPriceDao.findById(marketId).orElse(new MarketPrice());
        //when & then
        Assert.assertEquals(marketId, readCognor.getId().longValue());
        Assert.assertEquals(1.94, readCognor.getPrice(),0);
        Assert.assertEquals("Cognor", readCognor.getIndex());
    }

    @Test
    public void shouldCreateAndRemoveRelationMTMBetweenMarketPriceAndUsers() {
        //given
        MarketPrice readTesting = marketPriceDao.findById(marketId).orElse(new MarketPrice());
        User readUser1 = userDao.findById(user1Id).orElse(new User());
        User readUser2 = userDao.findById(user2Id).orElse(new User());
        User readUser3 = userDao.findById(user3Id).orElse(new User());
        //when
        readTesting.getUsers().add(readUser1);
        readTesting.getUsers().add(readUser2);
        readTesting.getUsers().add(readUser3);
        readTesting.setId(marketId);

        marketPriceDao.save(readTesting);

        readTesting = marketPriceDao.findById(marketId).orElse(new MarketPrice());

        Long userOneId = readTesting.getUsers().stream()
                .filter(t -> t.getLogin().equals(readUser1.getLogin()))
                .findFirst()
                .orElse(new User())
                .getId();

        Long userTwoId = readTesting.getUsers().stream()
                .filter(t -> t.getLogin().equals(readUser2.getLogin()))
                .findFirst()
                .orElse(new User())
                .getId();

        Long userThreeId = readTesting.getUsers().stream()
                .filter(t -> t.getLogin().equals(readUser3.getLogin()))
                .findFirst()
                .orElse(new User())
                .getId();
        //then
        Assert.assertEquals(readUser1.getId(), userOneId);
        Assert.assertEquals(readUser2.getId(), userTwoId);
        Assert.assertEquals(readUser3.getId(), userThreeId);

        //clean-up
        List<User> updateList = readTesting.getUsers().stream()
                .filter(name -> !name.getLogin().equals(readUser1.getLogin()))
                .filter(name -> !name.getLogin().equals(readUser2.getLogin()))
                .filter(name -> !name.getLogin().equals(readUser3.getLogin()))
                .collect(Collectors.toList());

        readTesting.setUsers(updateList);
        marketPriceDao.save(readTesting);
    }

    @Test
    public void shouldAddInstrumentsWithRelationsToUserAndMarketPrice() {
        //given
        MarketPrice readMarketPrice = marketPriceDao.findById(marketId).orElse(new MarketPrice());
        User userOne = userDao.findById(user1Id).orElse(new User());
        User userTwo = userDao.findById(user2Id).orElse(new User());
        User userThree = userDao.findById(user3Id).orElse(new User());

        Instrument userOneInstrument = new Instrument(userOne, readMarketPrice, 1.80, LocalDate.parse("2018-05-05"));
        Instrument userTwoInstrument = new Instrument(userTwo, readMarketPrice, 1.76, LocalDate.parse("2018-05-05"));
        Instrument userThreeInstrument = new Instrument(userThree, readMarketPrice, 1.55, LocalDate.parse("2018-05-05"));
        Instrument userOneInstrument2 = new Instrument(userOne, readMarketPrice, 1.94, LocalDate.now());
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
        Assert.assertEquals(marketId, readInstrumentThree.getMarketPrice().getId().longValue());
        Assert.assertEquals(marketId, readInstrumentFour.getMarketPrice().getId().longValue());
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
        marketPriceDao.deleteById(marketId);
    }

}
