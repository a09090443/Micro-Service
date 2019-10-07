package com.zipe.provider;

import com.zipe.config.FeignConfig;
import com.zipe.controller.TestFeignClient;
import com.zipe.fallback.HystrixClientFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(name = "loadbalance", configuration = FeignConfig.class, fallback = HystrixClientFallback.class)
public interface Loadbalance {

    @RequestMapping(value = "/load/{applicationName}", method = RequestMethod.GET)
    String load(@PathVariable(value = "applicationName") String applicationName);

    @RequestMapping(value = "/jwt1", method = RequestMethod.GET)
    String jwt1();

    @RequestMapping(value = "/jwt2", method = RequestMethod.GET)
    String jwt2();

    @RequestMapping(value = "/jwt3", method = RequestMethod.GET)
    String jwt3();
}
