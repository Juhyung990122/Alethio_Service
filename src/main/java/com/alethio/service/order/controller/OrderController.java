package com.alethio.service.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @RequestMapping(value = "/api/hadoop", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String getHadoopData() {
        return "{\"result\":\"ok\"}";
    }

}
