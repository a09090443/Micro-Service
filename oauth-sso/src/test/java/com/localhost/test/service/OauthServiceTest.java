package com.localhost.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.localhost.service.IOauthService;
import com.localhost.test.base.TestBase;

public class OauthServiceTest extends TestBase {

	@Autowired
	private IOauthService oauthService;

	// @Ignore
	@Test
	public void testGetOauthUsers() throws Exception {
		oauthService.getOauthUsers();
	}

}
