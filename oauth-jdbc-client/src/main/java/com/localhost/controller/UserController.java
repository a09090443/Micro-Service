package com.localhost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.localhost.model.UserInfo;
import com.localhost.service.IUserService;

@RestController
@RequestMapping("/userApi")
public class UserController {
	
	@Autowired
	private IUserService userService;

	@GetMapping("/GET/user/{loginId}")
	public UserInfo user(@PathVariable String loginId) {
		UserInfo userInfo = userService.findUserByLoginId(loginId);
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
	
	@GetMapping("/DELETE/user/{loginId}")
	public Boolean delete(@PathVariable String loginId) {
		UserInfo userInfo = new UserInfo();
		userInfo.setLoginId(loginId);
		
		return userService.delUser(userInfo);
	}
}
