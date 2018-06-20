package com.localhost.test.repository;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.localhost.model.OauthClientDetails;
import com.localhost.repository.IOauthClientDetailsRepository;
import com.localhost.test.base.TestBase;

public class OauthClientDetailsRepositoryTest extends TestBase {

	@Autowired
	private IOauthClientDetailsRepository oauthClientDetailsRepository;

	// @Ignore
	@Test
	public void testFindAll() {
		List<OauthClientDetails> oauthClientDetailsList = oauthClientDetailsRepository.findAll();
		assertNotNull(oauthClientDetailsList);
	}

}
