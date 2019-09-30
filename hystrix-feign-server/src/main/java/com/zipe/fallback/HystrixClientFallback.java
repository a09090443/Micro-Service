package com.zipe.fallback;

import com.zipe.controller.TestFeignClient;
import com.zipe.provider.Loadbalance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HystrixClientFallback implements Loadbalance {
    private static final Logger LOGGER = LoggerFactory.getLogger(HystrixClientFallback.class);

    @Override
    public String load(String applicationName) {
        LOGGER.info("异常发生，进入fallback方法，接收的参数： {},{}",applicationName);
        return "feign-hystrix";
    }
}
