package com.localhost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.localhost.model.UserInfo;
import com.localhost.service.IUserService;

@RestController
@RequestMapping("/userApi")
public class UserController {
	
	@Autowired
	private IUserService userService;

	@GetMapping("/GET/user/{login}")
	public UserInfo user(String login) {
		UserInfo userInfo = userService.findUserByLoginId(login);
		return userInfo;
	}

	@GetMapping("/GET/test")
	public Authentication test(Authentication user) {
		return user;
	}
	
	@GetMapping("/POST/users")
	public Authentication update(Authentication user) {
		return user;
	}
	
	@GetMapping("/DELETE/user/{}")
	public Authentication delete(Authentication user) {
		return user;
	}
}
