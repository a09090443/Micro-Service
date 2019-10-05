package com.zipe.base.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class BaseController {

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	protected Locale currentLocale;

	@ModelAttribute
	public void myModel(HttpServletRequest request, HttpServletResponse response, Model model) {
			this.request = request;
			this.response = response;
			this.currentLocale = LocaleContextHolder.getLocale();
	}

	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	public String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

}
