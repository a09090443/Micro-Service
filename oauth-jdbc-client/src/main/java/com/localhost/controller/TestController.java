package com.localhost.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {

	@GetMapping("/service2")
    public String test1() {
        return "resource2 service";
    }

	@GetMapping("/service22")
    public String test2() {
        return "This is test22222";
    }

	@GetMapping("/service3")
    public String test3() {
        return "This is test3333333";
    }

	@GetMapping("/service4")
    public String test4() {
        return "This is test44";
    }

}
