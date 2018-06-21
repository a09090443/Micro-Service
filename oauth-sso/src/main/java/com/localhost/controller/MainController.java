package com.localhost.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/main")
public class MainController {

	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView index(Authentication user) {
		ModelAndView modelAndView = new ModelAndView("pages/main");
	    return modelAndView;
	}
}
