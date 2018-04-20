package com.localhost.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {

	@GetMapping("/jwt1")
    public String test1() {
        return "jwt1";
    }

	@GetMapping("/jwt2")
    public String test2() {
        return "jwt2";
    }

	@GetMapping("/jwt3")
    public String test3() {
        return "jwt3";
    }

	@GetMapping("/jwt4")
    public String test4() {
        return "jwt4";
    }

}
