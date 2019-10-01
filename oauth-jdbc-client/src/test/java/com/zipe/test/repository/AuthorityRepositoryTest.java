package com.zipe.test.repository;

import com.zipe.model.SysAuthority;
import com.zipe.repository.ISysAuthorityRepository;
import com.zipe.test.base.TestBase;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

public class AuthorityRepositoryTest extends TestBase {

	@Autowired
	private ISysAuthorityRepository sysAuthorityRepository;

	@Ignore
	@Test
	public void testFindAll() {
		List<SysAuthority> sysAuthorityList = sysAuthorityRepository.findAll();
		assertNotNull(sysAuthorityList);
	}

	// @Ignore
	@Test
	public void testFindByAuthorityId() {
		Set<SysAuthority> sysAuthorityList = sysAuthorityRepository.findSysAuthoritiesByAuthorityIdIn(Arrays.asList("01,02".split(",")));
		assertNotNull(sysAuthorityList);
	}

}
