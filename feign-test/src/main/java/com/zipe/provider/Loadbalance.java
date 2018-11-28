package com.zipe.provider;

import com.zipe.FeignConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(name = "loadbalance", configuration = FeignConfig.class)
public interface Loadbalance {
    @RequestMapping("/test")
    String test();

    @RequestMapping(value = "/test1/{applicationName}", method = RequestMethod.GET)
    String test1(@PathVariable(value = "applicationName") String applicationName);
}