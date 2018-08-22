package com.invest.controller;

import com.invest.exceptions.SharesException;
import com.invest.quotations.Share;
import com.invest.services.SharesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


}
