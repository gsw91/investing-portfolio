package com.invest.mappers;

import com.invest.domain.*;
import com.invest.dtos.InstrumentDto;
import com.invest.exceptions.*;
import com.invest.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InstrumentMapper implements BasicMapper<Instrument, InstrumentDto> {

    @Autowired
    private UserService userService;

    @Autowired
    private MarketPriceService marketPriceService;

    @Override
    public Instrument mapperToDomain(InstrumentDto instrumentDto) {

        try {
            User user = userService.findUserById(instrumentDto.getUserId());
            MarketPrice marketPrice = marketPriceService.findMarketPrice(instrumentDto.getSharesIndex());

            return new Instrument(
                    new User(user.getId(), user.getLogin(), user.getPassword(), user.getEmail()),
                    instrumentDto.getQuantity(),
                    new MarketPrice(marketPrice.getId(), marketPrice.getIndex(), marketPrice.getPrice(),
                            marketPrice.getServerActualization(), marketPrice.getApplicationActualization()),
                    instrumentDto.getBuyingPrice(),
                    instrumentDto.getBuyingDate()
            );
        } catch (MarketPriceException mpe) {
            return null;
        } catch (UserExistsException e) {
            return null;
        }
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
                instrument.getUser().getId(),
                instrument.getQuantity(),
                instrument.getMarketPrice().getIndex(),
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
