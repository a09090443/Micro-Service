package com.localhost.service;

import java.util.List;

import com.localhost.model.OauthClientDetails;

public interface IOauthService {
	
	public List<OauthClientDetails> findAllOauthClientDetails() throws Exception;

}
