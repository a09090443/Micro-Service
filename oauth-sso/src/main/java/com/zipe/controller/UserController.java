package com.zipe.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zipe.base.controller.BaseController;
import com.zipe.service.ILoginUserService;
import com.zipe.service.IOauthService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private IOauthService oauthService;

	@Autowired
	private ILoginUserService loginUserService;

	@GetMapping(value = "/oauth")
	public ModelAndView oauthIndex() {
		ModelAndView modelAndView = new ModelAndView("pages/user/oauth");
		return modelAndView;
	}

	@GetMapping(value = "/oauth/users")
	@ResponseBody
	public String getOauthUsers() {
		String result = null;
		try {
			result = oauthService.getOauthUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@GetMapping(value = "/login")
	public ModelAndView userListIndex() {
		ModelAndView modelAndView = new ModelAndView("pages/user/userList");
		return modelAndView;
	}

	@GetMapping(value = "/login/users")
	public @ResponseBody String getLoginUsers() {
		String result = null;
		try {
			result = loginUserService.getUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@GetMapping(value = "/login/authorities")
	public @ResponseBody String getAuthorities() {
		String result = null;
		try {
			result = loginUserService.getAuthorities();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@GetMapping(value = "/login/personalTitles")
	public @ResponseBody String getPersonalTitles() {
		String result = null;
		try {
			result = loginUserService.getPersonalTitles();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@GetMapping(value = "/login/save")
	public String save() {
		Map<String, String> formContentMap = new HashMap<String, String>();
		Map<String, String[]> readOnlyMap = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : readOnlyMap.entrySet()) {
			List<String> valueList = new ArrayList<String>();
			valueList = Arrays.asList(entry.getValue());
			formContentMap.put(entry.getKey(), StringUtils.strip(valueList.toString(), "[]"));
		}

		JSONObject json = new JSONObject(formContentMap);
		try {
			loginUserService.saveUser(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/user/login";
	}

}
