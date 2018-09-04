package com.invest.quotations;

import com.invest.config.QuotationsConfig;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.*;
import java.util.*;

@Service
public class QuotationConnecting {

    @Autowired
    private QuotationsConfig quotationsConfig;

    private final static Logger LOGGER = Logger.getLogger(QuotationConnecting.class);

    public Map<String, Share> updateQuotations(Map<String, Share> updatedQuotations) {

        try {
            Document doc = Jsoup.connect(quotationsConfig.getQuotationsPage()).userAgent(quotationsConfig.getUserAgent()).get();
            Elements sharesNames = doc.select(quotationsConfig.getSharesNames());
            Elements sharesPrices = doc.select(quotationsConfig.getSharesPrices());
            Elements actualization = doc.select(quotationsConfig.getActualization());

            LOGGER.info("Connected to website");

            if (sharesNames.size() == sharesPrices.size() && sharesNames.size()>0) {

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
