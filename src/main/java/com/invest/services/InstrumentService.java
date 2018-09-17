package com.invest.services;

import com.invest.domain.*;
import com.invest.dtos.InstrumentDto;
import com.invest.mappers.InstrumentMapper;
import com.invest.repositories.InstrumentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstrumentService {

    @Autowired
    private InstrumentDao instrumentDao;

    public List<Instrument> allUserInstruments(Long userId) {
        if (instrumentDao.count()!=0) {
            return instrumentDao.findAll().stream()
                    .filter(t -> t.getUser().getId().equals(userId))
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    public Instrument addInstrument(Instrument instrument) {
        return instrumentDao.save(instrument);
    }

    public void sellInstrument(long id) {
        instrumentDao.deleteById(id);
    }

    public long countInstruments() {
        return instrumentDao.count();
    }

    public boolean deleteAllUsersInstruments(Long userId) {
        List<Instrument> userInstruments = allUserInstruments(userId);

        for (Instrument instrument: userInstruments) {
            instrumentDao.deleteById(instrument.getId());
        }
        return instrumentDao.findAll().stream().noneMatch(t->t.getUser().getId().equals(userId));
    }

}
