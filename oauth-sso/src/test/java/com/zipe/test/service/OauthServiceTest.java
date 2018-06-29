package com.zipe.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zipe.service.IOauthService;
import com.zipe.test.base.TestBase;

public class OauthServiceTest extends TestBase {

	@Autowired
	private IOauthService oauthService;

	// @Ignore
	@Test
	public void testGetOauthUsers() throws Exception {
		oauthService.getOauthUsers();
	}

}
