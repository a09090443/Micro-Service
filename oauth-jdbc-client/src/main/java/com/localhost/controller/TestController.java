package com.localhost.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {

	@GetMapping("/service1")
    public String service1() {
        return "This is service1";
    }

	@GetMapping("/service2")
    public String service2() {
        return "This is service2";
    }

	@GetMapping("/service3")
    public String service3() {
        return "This is service3";
    }

	@GetMapping("/service4")
    public String service4() {
        return "This is service4";
    }

}
