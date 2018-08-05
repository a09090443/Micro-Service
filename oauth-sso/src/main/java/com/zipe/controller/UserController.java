package com.zipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zipe.service.ILoginUserService;
import com.zipe.service.IOauthService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IOauthService oauthService;
	
	@Autowired
	private ILoginUserService loginUserService;

	@RequestMapping(value = "/oauth", method = RequestMethod.GET)
	public ModelAndView oauthIndex() {
		ModelAndView modelAndView = new ModelAndView("pages/user/oauth");
		return modelAndView;
	}

	@RequestMapping(value = "/oauth/users", method = RequestMethod.GET)
	public @ResponseBody String getOauthUsers() {
		String result = null;
		try {
			result = oauthService.getOauthUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView userListIndex() {
		ModelAndView modelAndView = new ModelAndView("pages/user/userList");
		return modelAndView;
	}
	@RequestMapping(value = "/login/users", method = RequestMethod.GET)
	public @ResponseBody String getLoginUsers() {
		String result = null;
		try {
			result = loginUserService.getUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
