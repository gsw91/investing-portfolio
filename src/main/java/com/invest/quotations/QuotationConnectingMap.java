package com.invest.quotations;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.*;
import java.util.*;

@Service
public class QuotationConnectingMap {

    private final static Logger LOGGER = Logger.getLogger(QuotationConnectingMap.class);

    public Map<String, Share> updateQuotations(Map<String, Share> updatedQuotations) {

        try {
            Document doc = Jsoup.connect("http://notowania.pb.pl/stocktable/WIG").userAgent("Chrome/68.0.3440.106").get();
            Elements sharesNames = doc.select("td.colWalor");
            Elements sharesPrices = doc.select("td.colKurs");
            Elements actualization = doc.select("td.colAktualizacja");

            if (sharesNames.size() == sharesPrices.size()) {

                for(int x=0; x<sharesNames.size(); x++) {
                    String correctedPrice = correctPrice(sharesPrices.get(x).text());
                    String correctedDate = correctDate(actualization.get(x).text());
                    String key = sharesNames.get(x).text();
                    Share currentShares = new Share(
                            sharesNames.get(x).text(),
                            Double.valueOf(correctedPrice),
                            LocalDateTime.parse(correctedDate),
                            LocalDateTime.now()
                    );
                    updatedQuotations.put(key, currentShares);
                }

            }
            LOGGER.info("Connected to website");
        } catch (IOException e) {
            LOGGER.error("Connection to website failed");
            return new HashMap<>();
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
