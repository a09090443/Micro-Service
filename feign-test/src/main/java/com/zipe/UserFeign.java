package com.zipe;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

@FeignClient(name = "microservice-resource",
        configuration = {FeignOAuth2Configuration.class}, fallbackFactory = UserFeign.UserFeignFallbackFactory.class)
public interface UserFeign {
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    Object getUser();

    @Component
    class UserFeignFallbackFactory implements FallbackFactory<UserFeign> {

        private static final Logger LOGGER = LoggerFactory.getLogger(UserFeignFallbackFactory.class);

        @Override
        public UserFeign create(Throwable throwable) {
            return () -> {
                LOGGER.info("fallback reason was: ", throwable);
                HashMap<String, Object> json = new HashMap<>();
                json.put("code", 0);
                json.put("message", "资源服务器宕机");
                return json;
            };
        }
    }
}
