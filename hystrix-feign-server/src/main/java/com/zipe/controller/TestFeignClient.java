package com.zipe.controller;

import com.zipe.fallback.HystrixClientFallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "service-B",fallback = HystrixClientFallback.class)
public interface TestFeignClient {

    @RequestMapping("/add")
    String add(@RequestParam("a") Integer a, @RequestParam("b") Integer b);

}