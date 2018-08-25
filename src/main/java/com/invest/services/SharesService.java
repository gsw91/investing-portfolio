package com.invest.services;

import com.invest.exceptions.SharesException;
import com.invest.quotations.Share;
import com.invest.quotations.SharesMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SharesService {

    @Autowired
    private SharesMap sharesMap;

    public Share getShare(String name) throws SharesException {
        try {
            return sharesMap.getMarketPriceMap().get(name);
        } catch (IllegalArgumentException e){
            throw new SharesException(SharesException.NO_SHARE_EXCEPTION);
        }
    }

    public Map<String, Share> getAllShares() throws SharesException {
        try {
            return sharesMap.getMarketPriceMap();
        } catch (IllegalArgumentException e){
            throw new SharesException(SharesException.UPDATING_QUOTATIONS_FAILED);
        }
    }

}
