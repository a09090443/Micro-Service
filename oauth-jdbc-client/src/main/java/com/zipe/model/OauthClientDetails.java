package com.zipe.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "oauth_client_details", catalog = "", schema = "APPLICATION")
@NamedQueries({ @NamedQuery(name = "OauthClientDetails.findAll", query = "SELECT o FROM OauthClientDetails o"),
		@NamedQuery(name = "OauthClientDetails.findByClientId", query = "SELECT o FROM OauthClientDetails o WHERE o.clientId = :clientId"),
		@NamedQuery(name = "OauthClientDetails.findByResourceIds", query = "SELECT o FROM OauthClientDetails o WHERE o.resourceIds = :resourceIds"),
		@NamedQuery(name = "OauthClientDetails.findByClientSecret", query = "SELECT o FROM OauthClientDetails o WHERE o.clientSecret = :clientSecret"),
		@NamedQuery(name = "OauthClientDetails.findByScope", query = "SELECT o FROM OauthClientDetails o WHERE o.scope = :scope"),
		@NamedQuery(name = "OauthClientDetails.findByAuthorizedGrantTypes", query = "SELECT o FROM OauthClientDetails o WHERE o.authorizedGrantTypes = :authorizedGrantTypes"),
		@NamedQuery(name = "OauthClientDetails.findByWebServerRedirectUri", query = "SELECT o FROM OauthClientDetails o WHERE o.webServerRedirectUri = :webServerRedirectUri"),
		@NamedQuery(name = "OauthClientDetails.findByAuthorities", query = "SELECT o FROM OauthClientDetails o WHERE o.authorities = :authorities"),
		@NamedQuery(name = "OauthClientDetails.findByAccessTokenValidity", query = "SELECT o FROM OauthClientDetails o WHERE o.accessTokenValidity = :accessTokenValidity"),
		@NamedQuery(name = "OauthClientDetails.findByRefreshTokenValidity", query = "SELECT o FROM OauthClientDetails o WHERE o.refreshTokenValidity = :refreshTokenValidity"),
		@NamedQuery(name = "OauthClientDetails.findByAdditionalInformation", query = "SELECT o FROM OauthClientDetails o WHERE o.additionalInformation = :additionalInformation"),
		@NamedQuery(name = "OauthClientDetails.findByAutoapprove", query = "SELECT o FROM OauthClientDetails o WHERE o.autoapprove = :autoapprove") })
public class OauthClientDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "client_id", nullable = false, length = 255)
	private String clientId;

	@Size(max = 255)
	@Column(name = "resource_ids", length = 255)
	private String resourceIds;

	@Size(max = 255)
	@Column(name = "client_secret", length = 255)
	private String clientSecret;

	@Size(max = 255)
	@Column(length = 255)
	private String scope;

	@Size(max = 255)
	@Column(name = "authorized_grant_types", length = 255)
	private String authorizedGrantTypes;

	@Size(max = 255)
	@Column(name = "web_server_redirect_uri", length = 255)
	private String webServerRedirectUri;

	@Size(max = 255)
	@Column(length = 255)
	private String authorities;

	@Column(name = "access_token_validity")
	private Long accessTokenValidity;

	@Column(name = "refresh_token_validity")
	private Long refreshTokenValidity;

	@Size(max = 2000)
	@Column(name = "additional_information", length = 2000)
	private String additionalInformation;

	@Size(max = 255)
	@Column(length = 255)
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