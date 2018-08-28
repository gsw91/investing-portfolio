package com.invest.services;

import com.invest.domain.*;
import com.invest.dtos.InstrumentDto;
import com.invest.mappers.InstrumentMapper;
import com.invest.repositories.InstrumentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstrumentService {

    @Autowired
    private InstrumentDao instrumentDao;

    @Autowired
    private InstrumentMapper mapper;

    public List<Instrument> allUserInstruments(Long userId) {
        return instrumentDao.findAll().stream()
                .filter(t -> t.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }

    public Instrument addInstrument(Instrument instrument) {
        return instrumentDao.save(instrument);
    }

    public void sellInstrument(long id) {
        instrumentDao.deleteById(id);
    }

    public Instrument findById(Long id) {
        if (instrumentDao.findById(id).isPresent()) {
            return instrumentDao.findById(id).get();
        } else {
            return new Instrument();
        }
    }

}