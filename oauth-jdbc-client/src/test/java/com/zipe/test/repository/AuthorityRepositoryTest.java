package com.zipe.test.repository;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zipe.model.Authority;
import com.zipe.repository.IAuthorityRepository;
import com.zipe.test.base.TestBase;

public class AuthorityRepositoryTest extends TestBase {

	@Autowired
	private IAuthorityRepository authorityRepository;

	@Ignore
	@Test
	public void testFindAll() {
		List<Authority> authorityList = authorityRepository.findAll();
		assertNotNull(authorityList);
	}

	// @Ignore
	@Test
	public void testFindByAuthorityId() {
		Set<Authority> authorityList = authorityRepository.findAuthoritiesByAuthorityIdIn(Arrays.asList("01,02".split(",")));
		assertNotNull(authorityList);
	}

}
