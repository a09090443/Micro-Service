package com.zipe.test.repository;

import com.zipe.model.SysUser;
import com.zipe.repository.ISysUserRepository;
import com.zipe.test.base.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class SysUserRepositoryTest extends TestBase {

	@Autowired
	private ISysUserRepository sysUserRepository;

	@Test
	public void testFindAll() {
		List<SysUser> sysUserList = sysUserRepository.findAll();
		assertNotNull(sysUserList);
	}

}
