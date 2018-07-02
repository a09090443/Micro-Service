package com.zipe.service;

import java.util.List;

import com.zipe.model.OauthClientDetails;

public interface IOauthService {
	
	public List<OauthClientDetails> findAllOauthClientDetails() throws Exception;

}
