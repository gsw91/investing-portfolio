package com.invest.mappers;

import com.invest.domain.Instrument;
import com.invest.domain.User;
import com.invest.dtos.InstrumentDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InstrumentMapper implements BasicMapper<Instrument, InstrumentDto> {

    @Override
    public void accept(Object o) {
    }

    @Override
    public Instrument mapperToDomain(InstrumentDto instrumentDto) {
        return new Instrument(
                instrumentDto.getId(),
                new User(instrumentDto.getUserId()),
                instrumentDto.getQuantity(),
                instrumentDto.getMarketPrice(),
                instrumentDto.getBuyingPrice(),
                instrumentDto.getBuyingDate()
        );
    }

    @Override
    public List<Instrument> mapperToListDomain(List<InstrumentDto> listDto) {
        return listDto.stream()
                .map(this::mapperToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public InstrumentDto mapperToDto(Instrument instrument) {
        return new InstrumentDto(
                instrument.getId(),
                instrument.getUser().getId(),
                instrument.getQuantity(),
                instrument.getMarketPrice(),
                instrument.getBuyingPrice(),
                instrument.getBuyingDate()
        );
    }

    @Override
    public List<InstrumentDto> mapperToListDto(List<Instrument> listDomain) {
        return listDomain.stream()
                .map(this::mapperToDto)
                .collect(Collectors.toList());
    }

}
