package com.localhost.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping(value="/oauth",method=RequestMethod.GET)
	public ModelAndView oauthIndex() {
		ModelAndView modelAndView = new ModelAndView("pages/user/oauth");
	    return modelAndView;
	}
}
