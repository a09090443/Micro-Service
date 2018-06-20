package com.localhost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;

import com.localhost.config.ConfigBean;

@SpringBootApplication()
@EnableConfigurationProperties({ ConfigBean.class })
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = Application.class)
public class Application {

	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}