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
        List<InstrumentDto> list = mapper.mapperToListDto(instrumentDao.findAll());
        return list.stream()
                .filter(t -> t.getUserId().equals(userId))
                .map(mapper::mapperToDomain)
                .collect(Collectors.toList());
    }

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
