package com.zipe.test.repository;

import com.zipe.model.SysUserTitle;
import com.zipe.repository.ISysUserTitleRepository;
import com.zipe.test.base.TestBase;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

public class SysUserTitleRepositoryTest extends TestBase {

	@Autowired
	private ISysUserTitleRepository sysUserTitleRepository;

	@Ignore
	@Test
	public void testFindAll() {
		List<SysUserTitle> sysUserTitleList = sysUserTitleRepository.findAll();
		assertNotNull(sysUserTitleList);
	}

	// @Ignore
	@Test
	public void testFindByTitleId() {
		Set<SysUserTitle> sysUserTitleList = sysUserTitleRepository.findSysUserTitleByTitleIdIn(Arrays.asList("01,02".split(",")));
		assertNotNull(sysUserTitleList);
	}

}
