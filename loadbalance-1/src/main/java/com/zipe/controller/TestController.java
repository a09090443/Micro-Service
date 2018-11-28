package com.zipe.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test")
    public String test() {
        return "loadbalance1";
    }

    @RequestMapping(value = "/test1/{applicationName}", method = RequestMethod.GET)
    public String test1(@PathVariable String applicationName) {
        return "loadbalance1:" + applicationName;
    }
}
