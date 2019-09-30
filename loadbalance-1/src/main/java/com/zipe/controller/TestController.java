package com.zipe.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(value = "/load/{applicationName}", method = RequestMethod.GET)
    public String load(@PathVariable String applicationName) {
        return "loadbalance1:" + applicationName;
    }
}
