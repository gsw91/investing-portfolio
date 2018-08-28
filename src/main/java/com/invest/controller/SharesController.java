package com.invest.controller;

import com.invest.exceptions.SharesException;
import com.invest.quotations.Share;
import com.invest.services.SharesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/share/")
public class SharesController {

    @Autowired
    private SharesService sharesService;

    @RequestMapping(method = RequestMethod.GET, params = {"name"}, value = "name")
    public Share getCurrentSharePrice(@RequestParam("name") String name) {
        try {
            return sharesService.getShare(name);
        } catch (SharesException e) {
            return new Share();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "all")
    public Map<String, Share> getCurrentSharesPrices() {
        try {
            return sharesService.getAllShares();
        } catch (SharesException e) {
            return new HashMap<String, Share>();
        }
    }


}
