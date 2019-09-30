package com.zipe;

import com.zipe.provider.Loadbalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringCloudApplication
@EnableFeignClients
@RestController
public class Application {
    @Autowired
    private Loadbalance loadbalance;

    @RequestMapping("/hello")
    public String greeting() {
        return loadbalance.test();
    }

    @RequestMapping(value = "/load/{applicationName}", method = RequestMethod.GET)
    public String load(@PathVariable(value = "applicationName") String applicationName) {
        return loadbalance.load(applicationName);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}