package com.zipe.test.repository;

import com.zipe.model.SysUser;
import com.zipe.repository.ISysUserRepository;
import com.zipe.test.base.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SysUserRepositoryTest extends TestBase {

	@Autowired
	private ISysUserRepository sysUserRepository;
	
	private String LOGING_ID = "Junit";
	private String EMAIL = "Junit@localhost.com";
	
	// @Ignore
	@Test
	public void testFindByLoginId() {
		String loginId = LOGING_ID;
		SysUser sysUser = sysUserRepository.findByLoginId(loginId);
		assertEquals(loginId, sysUser.getLoginId());
	}
	
	// @Ignore
	@Test
	public void testFindByEmail() {
		String email = EMAIL;
		SysUser sysUser = sysUserRepository.findByEmail(email);
		assertEquals(email, sysUser.getEmail());
	}
	
	// @Ignore
	@Test
	public void testFindLastUserInfo() {
		SysUser sysUser = sysUserRepository.findTopByOrderByLoginIdDesc();
		assertNotNull(sysUser);
	}
}
