package com.zipe.test.service;

import com.zipe.model.SysAuthority;
import com.zipe.model.SysUser;
import com.zipe.model.SysUserTitle;
import com.zipe.service.IUserService;
import com.zipe.test.base.TestBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserInfoServiceTest extends TestBase {

	@Autowired
	private IUserService userService;

	private String LOGING_ID = "Junit";
	private String EMAIL = "Junit@localhost.com";

	@Ignore
	@Before
	public void testSaveSysUser() {
		SysUser sysUser = new SysUser();
		sysUser.setLoginId(LOGING_ID);
		sysUser.setFirstName(LOGING_ID);
		sysUser.setLastName(LOGING_ID);
		sysUser.setEmail(EMAIL);
		sysUser.setAddress("address 10 st");
		sysUser.setBirthday("1984-01-01");
		sysUser.setImage("000003.jpg");
		sysUser.setPhone("1234567890");
		sysUser.setPassword("1234");
		SysAuthority auth = new SysAuthority();
		auth.setAuthorityId("01");
		auth.setName("ROLE_ADMIN");
		Set<SysAuthority> authoritySet = new HashSet<SysAuthority>();
		authoritySet.add(auth);

		SysUserTitle sysUserTitle = new SysUserTitle();
		sysUserTitle.setTitleId("01");
		sysUserTitle.setTitleName("ADMIN");
		Set<SysUserTitle> sysUserTitleSet = new HashSet<SysUserTitle>();
		sysUserTitleSet.add(sysUserTitle);

		sysUser.setAuthorities(authoritySet);
		sysUser.setSysUserTitle(sysUserTitleSet);
		try {
			userService.saveUser(sysUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@After
	public void testDelSysUser() {
		SysUser SysUser = new SysUser();
		SysUser.setLoginId(LOGING_ID);
		try {
			userService.delUser(SysUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Ignore
	@Test
	public void testFindSysUserByLoginId() {
		String loginId = LOGING_ID;
		SysUser SysUser = userService.findUserByLoginId(loginId);
		assertNotNull(SysUser);
		assertEquals(loginId, SysUser.getLoginId());
	}

	// @Ignore
	@Test
	public void testFindSysUserByEmail() {
		String email = EMAIL;
		SysUser SysUser = userService.findUserByEmail(email);
		assertEquals(email, SysUser.getEmail());
	}

}
