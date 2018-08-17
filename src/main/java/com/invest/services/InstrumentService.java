package com.invest.services;

import com.invest.domain.Instrument;
import com.invest.repositories.InstrumentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstrumentService {

    @Autowired
    private InstrumentDao instrumentDao;

    public Instrument addNewInstrument(Instrument instrument) {
        return instrumentDao.save(instrument);
    }

    public void removeInstrument(long id) {
        instrumentDao.deleteById(id);
    }

}
