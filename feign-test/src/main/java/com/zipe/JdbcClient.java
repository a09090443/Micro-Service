package com.zipe;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(name = "OAUTH-JDBC-CLIENT", configuration = FeignOAuth2Configuration.class)
public interface JdbcClient {

    @RequestMapping("/userApi/GET/users")
    String getUsers();
}