package com.zipe.test.repository;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zipe.model.OauthClientDetails;
import com.zipe.repository.IOauthClientDetailsRepository;
import com.zipe.test.base.TestBase;

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
