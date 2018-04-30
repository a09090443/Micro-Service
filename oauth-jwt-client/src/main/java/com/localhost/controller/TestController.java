package com.localhost.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {

	@GetMapping("/jwt1")
    public String jwt1() {
        return "jwt1";
    }

	@GetMapping("/jwt2")
    public String jwt2() {
        return "jwt2";
    }

	@GetMapping("/jwt3")
    public String jwt3() {
        return "jwt3";
    }

	@GetMapping("/jwt4")
    public String jwt4() {
        return "jwt4";
    }

}
