package com.zipe.service.impl;

import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zipe.config.ConfigBean;
import com.zipe.service.IOauthService;
import com.zipe.service.common.CommonService;
import com.zipe.url.constant.Url.URI;

@Service("oauthService")
public class OauthServiceImpl extends CommonService implements IOauthService {
	private static final Logger logger = LoggerFactory.getLogger(OauthServiceImpl.class);

	@Autowired
	public ConfigBean configBean;

	@Override
	public String getOauthUsers() throws Exception {

		LinkedHashMap<String, String> parametersMap = new LinkedHashMap<String, String>();
		String result = this.sendUrl("jdbc-api", parametersMap, URI.OAUTH_GET_USERS.getUri(), false, RequestMethod.GET.toString());
		return result;
	}

}
