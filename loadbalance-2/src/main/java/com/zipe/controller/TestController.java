package com.zipe.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test")
    public String test() {
        return "loadbalance2";
    }

    @RequestMapping("/test1/{applicationName}")
    public String test1(@PathVariable String applicationName) {
        return "loadbalance2:" + applicationName;
    }
}
