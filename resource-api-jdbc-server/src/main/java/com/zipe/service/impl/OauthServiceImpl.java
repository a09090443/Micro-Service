package com.zipe.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zipe.model.OauthClientDetails;
import com.zipe.repository.IOauthClientDetailsRepository;
import com.zipe.service.IOauthService;

@Transactional
@Service("oauthService")
public class OauthServiceImpl implements IOauthService {
	private static final Logger logger = LoggerFactory.getLogger(OauthServiceImpl.class);
	
	@Autowired
	private IOauthClientDetailsRepository oauthClientDetailsRepository;

	@Override
	public List<OauthClientDetails> findAllOauthClientDetails() throws Exception {
		return oauthClientDetailsRepository.findAll();
	}
}