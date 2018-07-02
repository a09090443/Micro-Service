package com.zipe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zipe.model.OauthClientDetails;
import com.zipe.service.IOauthService;

@RestController
@RequestMapping("/oauthApi")
public class OauthController {

	@Autowired
	private IOauthService oauthService;

	@RequestMapping("/GET/users")
	public List<OauthClientDetails> getOauthUsers() throws Exception {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return oauthService.findAllOauthClientDetails();
	}

	// @RequestMapping("/user/me")
	// public Principal user(Principal principal) {
	// System.out.println(principal);
	// return principal;
	// }
}