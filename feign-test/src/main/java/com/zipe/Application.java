package com.zipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringCloudApplication
@EnableFeignClients
@RestController
public class Application {
    @Autowired
    private GreetingClient greetingClient;

    @Autowired
    private JdbcClient jdbcClient;

    @RequestMapping("/test")
    public String greeting() {
        return greetingClient.test();
    }

    @RequestMapping(value = "/test1/{applicationName}", method = RequestMethod.GET)
    public String test1(@PathVariable(value = "applicationName") String applicationName) {
        return greetingClient.test1(applicationName);
    }

    @RequestMapping(value = "/userApi/GET/users", method = RequestMethod.GET)
    public String getUsers() {
        return jdbcClient.getUsers();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}