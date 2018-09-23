package com.zipe.service.impl;

import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zipe.config.ConfigBean;
import com.zipe.service.ILoginUserService;
import com.zipe.service.common.CommonService;
import com.zipe.url.constant.Url.URI;

@Service("loginUserService")
public class LoginUserServiceImpl extends CommonService implements ILoginUserService {
	private static final Logger logger = LoggerFactory.getLogger(LoginUserServiceImpl.class);

	@Autowired
	public ConfigBean configBean;
	
	LinkedHashMap<String, String> parametersMap = new LinkedHashMap<String, String>();
	
	public String getUsers() throws Exception {
		return this.sendUrl("jdbc-api", parametersMap, URI.GET_USERS.getUri(), false, RequestMethod.GET.toString());
	}

	@Override
	public String getAuthorities() throws Exception {
		return this.sendUrl("jdbc-api", parametersMap, URI.GET_AUTHORITIES.getUri(), false, RequestMethod.GET.toString());
	}

	@Override
	public String getPersonalTitles() throws Exception {
		return this.sendUrl("jdbc-api", parametersMap, URI.GET_PERSONALTITLES.getUri(), false, RequestMethod.GET.toString());
	}

	@Override
	public String saveUser(String content) throws Exception {
		parametersMap.put("userForm", content);
		logger.info("sent user form!");
		return this.sendUrl("jdbc-api", parametersMap, URI.SAVE_USER.getUri(), false, RequestMethod.POST.toString());
	}

}
