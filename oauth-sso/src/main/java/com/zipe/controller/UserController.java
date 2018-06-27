package com.zipe.controller;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zipe.model.OauthClientDetails;
import com.zipe.service.IOauthService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IOauthService oauthService;

	@RequestMapping(value = "/oauth", method = RequestMethod.GET)
	public ModelAndView oauthIndex() {
		ModelAndView modelAndView = new ModelAndView("pages/user/oauth");
		return modelAndView;
	}

	@RequestMapping(value = "/oauth/users", method = RequestMethod.GET)
	public @ResponseBody List<OauthClientDetails> getOauthUsers() {
		String result = null;
		try {
			result = oauthService.getOauthUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();
		List<OauthClientDetails> oauthClientDetailsList = null;
		try {
			oauthClientDetailsList = (List<OauthClientDetails>) mapper.readValue(result,
					new TypeReference<List<OauthClientDetails>>() {
					});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return oauthClientDetailsList;
	}

}
