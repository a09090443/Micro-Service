package com.zipe.model;

import java.io.Serializable;

public class OauthClientDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	private String clientId;

	private String resourceIds;

	private String clientSecret;

	private String scope;

	private String authorizedGrantTypes;

	private String webServerRedirectUri;

	private String authorities;

	private Long accessTokenValidity;

	private Long refreshTokenValidity;

	private String additionalInformation;

	private String autoapprove;

	public OauthClientDetails() {
	}

	public OauthClientDetails(String clientId) {
		this.clientId = clientId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public String getWebServerRedirectUri() {
		return webServerRedirectUri;
	}

	public void setWebServerRedirectUri(String webServerRedirectUri) {
		this.webServerRedirectUri = webServerRedirectUri;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public Long getAccessTokenValidity() {
		return accessTokenValidity;
	}

	public void setAccessTokenValidity(Long accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	public Long getRefreshTokenValidity() {
		return refreshTokenValidity;
	}

	public void setRefreshTokenValidity(Long refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public String getAutoapprove() {
		return autoapprove;
	}

	public void setAutoapprove(String autoapprove) {
		this.autoapprove = autoapprove;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (clientId != null ? clientId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof OauthClientDetails)) {
			return false;
		}
		OauthClientDetails other = (OauthClientDetails) object;
		if ((this.clientId == null && other.clientId != null)
				|| (this.clientId != null && !this.clientId.equals(other.clientId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "modelo.OauthClientDetails[ clientId=" + clientId + " ]";
	}

}