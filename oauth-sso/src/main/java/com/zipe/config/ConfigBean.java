package com.zipe.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "properties")
public class ConfigBean {

	private Map<String, String> server;

	public Map<String, String> getServer() {
		return server;
	}

	public void setServer(Map<String, String> server) {
		this.server = server;
	}

}
