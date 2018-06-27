package com.zipe.service.impl;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

import com.localhost.config.ConfigBean;
import com.zipe.service.IOauthService;
import com.zipe.url.constant.Url;
import com.zipe.utils.HttpUtility;

@Service("oauthService")
public class OauthServiceImpl implements IOauthService {
	private static final Logger logger = LoggerFactory.getLogger(OauthServiceImpl.class);

	@Autowired
	public ConfigBean configBean;

	@Override
	public String getOauthUsers() throws Exception {
		OAuth2AuthenticationDetails authentication = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext()
				.getAuthentication().getDetails();

		String tokenId = authentication.getTokenValue();
		if (StringUtils.isBlank(tokenId)) {
			throw new Exception("The token id is blank or empty!");
		}

		String result = null;
		Url url = new Url();
		String fullRequestUrl = null;
		try {
			String apiServer = configBean.getServer().get("api");
			fullRequestUrl = url.getUrlPath(apiServer.split(":")[0], apiServer.split(":")[1], Url.OAUTH_GET_USERS,
					false);

			Map<String, String> parametersMap = new LinkedHashMap<String, String>();
			parametersMap.put("access_token", tokenId);
			fullRequestUrl = url.setUrlParameters(fullRequestUrl, parametersMap);
			logger.info("Get oauth users request url:" + fullRequestUrl);

		} catch (Exception e) {
			logger.error("Assemble Url error!");
			throw e;
		}

		try {
			HttpUtility.sendGetRequest(fullRequestUrl);

			result = HttpUtility.readSingleLineRespone();
			// result = http.readMultipleLinesRespone();
		} catch (IOException e) {
			logger.error("Request Url error!");

			throw e;
		} finally {
			HttpUtility.disconnect();
		}
		return result;
	}

}
