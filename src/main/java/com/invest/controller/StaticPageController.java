package com.invest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

@ApiIgnore
@Controller
public class StaticPageController {

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("swagger_url", "https://investment-portfolio-app.herokuapp.com/swagger-ui.html#/");
        model.put("service_url", "https://github.com/gsw91/investing-portfolio");
        model.put("client_url", "https://github.com/gsw91/investment-portfolio-gui");
        return "index";
    }


}
