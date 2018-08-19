package com.invest.services;

import com.invest.domain.Instrument;
import com.invest.domain.User;
import com.invest.repositories.InstrumentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstrumentService {

    @Autowired
    private InstrumentDao instrumentDao;

    public Instrument buyInstrument(Instrument instrument) {
        return instrumentDao.save(instrument);
    }

    public void sellInstrument(long id) {
        instrumentDao.deleteById(id);
    }

    public List<Instrument> showAll(User user) {
        return instrumentDao.findAll().stream()
                .filter(t -> t.getUser().equals(user))
                .collect(Collectors.toList());
    }

}
