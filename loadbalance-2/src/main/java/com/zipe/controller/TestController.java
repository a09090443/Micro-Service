package com.zipe.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(value = "/load/{applicationName}", method = RequestMethod.GET)
    public String load(@PathVariable String applicationName) {
        return "loadbalance2:" + applicationName;
    }

    @RequestMapping(value = "/jwt1", method = RequestMethod.GET)
    public String jwt1() {
        return "loadbalance2:jwt1";
    }
    @RequestMapping(value = "/jwt2", method = RequestMethod.GET)
    public String jwt2() {
        return "loadbalance2:jwt2";
    }
    @RequestMapping(value = "/jwt3", method = RequestMethod.GET)
    public String jwt3() {
        return "loadbalance2:jwt3";
    }
}
