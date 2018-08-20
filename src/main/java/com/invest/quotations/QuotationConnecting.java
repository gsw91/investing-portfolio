package com.invest.quotations;

import com.invest.dtos.MarketPriceDto;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.*;
import java.util.*;


@Service
public class QuotationConnecting {

    private final static Logger LOGGER = Logger.getLogger(QuotationConnecting.class);

    public List<MarketPriceDto> updateQuotations() {
        List<MarketPriceDto> updatedQuotations = new ArrayList<>();

        try {
            Document doc = Jsoup.connect("http://notowania.pb.pl/stocktable/WIG").userAgent("Chrome/68.0.3440.106").get();
            Elements sharesNames = doc.select("td.colWalor");
            Elements sharesPrices = doc.select("td.colKurs");
            Elements actualization = doc.select("td.colAktualizacja");

            if (sharesNames.size() == sharesPrices.size()) {

                for(int x=0; x<sharesNames.size(); x++) {
                    String correctedPrice = correctPrice(sharesPrices.get(x).text());
                    String correctedDate = correctDate(actualization.get(x).text());
                    MarketPriceDto currentShares = new MarketPriceDto(
                            Integer.valueOf(x).longValue(),
                            sharesNames.get(x).text(),
                            Double.valueOf(correctedPrice),
                            LocalDateTime.parse(correctedDate),
                            LocalDateTime.now()
                    );
                    updatedQuotations.add(currentShares);
                }

            }
            LOGGER.info("Connected to website");
        } catch (IOException e) {
            LOGGER.error("Connection to website failed");
            return new ArrayList<>();
        }
        return updatedQuotations;
    }


    private String correctDate (String date) {
        return Year.now().toString() + "-" + date.substring(3,5) + "-" + date.substring(0, 2) + "T" + date.substring(6);
    }


    private String correctPrice(String price) {
        price = price.replace(" ", "");
        return price.replace(',' , '.');
    }


}
