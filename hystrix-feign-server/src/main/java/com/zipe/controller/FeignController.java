package com.zipe.controller;

import com.zipe.provider.Loadbalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

    @Autowired
    private Loadbalance loadbalance;

    @RequestMapping(value = "/load/{applicationName}", method = RequestMethod.GET)
    public String load(@PathVariable(value = "applicationName") String applicationName) {
        return loadbalance.load(applicationName);
    }

    @RequestMapping(value = "/jwt1", method = RequestMethod.GET)
    public String jwt1() {
        return loadbalance.jwt1();
    }

    @RequestMapping(value = "/jwt2", method = RequestMethod.GET)
    public String jwt2() {
        return loadbalance.jwt2();
    }

    @RequestMapping(value = "/jwt3", method = RequestMethod.GET)
    public String jwt3() {
        return loadbalance.jwt3();
    }
}
