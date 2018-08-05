package com.zipe.service.impl;

import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zipe.config.ConfigBean;
import com.zipe.service.ILoginUserService;
import com.zipe.service.common.CommonService;
import com.zipe.url.constant.Url.URI;

@Service("loginUserService")
public class LoginUserServiceImpl extends CommonService implements ILoginUserService {
	private static final Logger logger = LoggerFactory.getLogger(LoginUserServiceImpl.class);

	@Autowired
	public ConfigBean configBean;

	public String getUsers() throws Exception {
		
		LinkedHashMap<String, String> parametersMap = new LinkedHashMap<String, String>();
		String result =this.sendUrl("jdbc-api", parametersMap, URI.GET_USERS.getUri(), false);
		return result;
	}

}
