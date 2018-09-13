package com.invest.controller;

import com.invest.exceptions.SharesException;
import com.invest.quotations.Share;
import com.invest.services.SharesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.TreeMap;

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
    public TreeMap<String, Share> getCurrentSharesPrices() {
        try {
            Map<String, Share> map = sharesService.getAllShares();
            TreeMap<String, Share> treeMap = new TreeMap<>();
            map.forEach(treeMap::put);
            return treeMap;
        } catch (SharesException e) {
            return new TreeMap<>();
        }
    }


}
