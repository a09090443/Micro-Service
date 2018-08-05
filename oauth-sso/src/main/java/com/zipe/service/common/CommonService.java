package com.zipe.service.common;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import com.zipe.config.ConfigBean;
import com.zipe.url.constant.Url;
import com.zipe.utils.network.HttpUtility;

public abstract class CommonService {
	private static final Logger logger = LoggerFactory.getLogger(CommonService.class);

	@Autowired
	public ConfigBean configBean;

	protected OAuth2AuthenticationDetails getOauthDetail() {
		return (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
	}

	protected String getTokenId() {
		return this.getOauthDetail().getTokenValue();

	}

	protected String sendUrl(String apiServerName, Map<String, String> parametersMap, String uri, boolean isHttps)
			throws Exception {
		if (StringUtils.isBlank(this.getTokenId())) {
			throw new Exception("The token id is blank or empty!");
		}

		String result = null;
		Url url = new Url();
		String fullRequestUrl = null;
		try {
			String apiServer = configBean.getServer().get(apiServerName);
			fullRequestUrl = url.getUrlPath(apiServer.split(":")[0], apiServer.split(":")[1], uri, isHttps);

			parametersMap.put("access_token", this.getTokenId());
			fullRequestUrl = url.setUrlParameters(fullRequestUrl, parametersMap);
			logger.info("Send the request url:" + fullRequestUrl);

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
