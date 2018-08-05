package com.zipe.url.constant;

import java.util.Map;

public class Url {

	public String getUrlPath(String serverIp, String serverPort, String uri, Boolean isHttps) throws Exception {
		StringBuilder sb = new StringBuilder();
		if (!isHttps) {
			sb.append(HTTP.HTTP.getType());
		} else {
			sb.append(HTTP.HTTPS.getType());
		}

		if (null != serverIp && !serverIp.isEmpty()) {
			sb.append(serverIp);
		} else {
			throw new Exception("Server ip is blank or empty");
		}

		if (null != serverPort && !serverPort.isEmpty()) {
			sb.append(":").append(serverPort);
		}
		sb.append(uri);
		return sb.toString();
	}

	public String setUrlParameters(String url, Map<String, String> paraMap) {

		StringBuilder sb = new StringBuilder();
		int mapSizeCount = 0;

		for (Map.Entry<String, String> entry : paraMap.entrySet()) {
			// int code = entry.getKey().hashCode() + entry.getValue().hashCode();
			sb.append(entry.getKey() + "=" + entry.getValue());
			mapSizeCount++;
			if (mapSizeCount < paraMap.size()) {
				sb.append("&");
			}
		}

		return url + "?" + sb.toString();
	}

	public enum URI {
		OAUTH_GET_USERS("/oauthApi/GET/users"), 
		GET_USERS("/userApi/GET/users");

		private String uri;

		private URI(String uri) {
			this.uri = uri;
		}

		public String getUri() {
			return uri;
		}
	}
	
	public enum HTTP{
		HTTP("http"),
		HTTPS("https");
		
		private String type;

		private HTTP(String type) {
			this.type = type;
		}

		public String getType() {
			return type + "://";
		}

		
	}
}
