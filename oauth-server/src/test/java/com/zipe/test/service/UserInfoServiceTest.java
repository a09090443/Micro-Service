package com.zipe.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zipe.model.UserInfo;
import com.zipe.service.IUserService;
import com.zipe.test.base.TestBase;

public class UserInfoServiceTest extends TestBase {

	@Autowired
	private IUserService userService;

	private String LOGING_ID = "Junit";
	private String EMAIL = "Junit@localhost.com";

	@Before
	public void testSaveUserInfo() {
		UserInfo userInfo = new UserInfo();
		userInfo.setLoginId(LOGING_ID);
		userInfo.setFirstName(LOGING_ID);
		userInfo.setLastName(LOGING_ID);
		userInfo.setEmail(EMAIL);
		userInfo.setAddress("address 10 st");
		userInfo.setBirthday("1984-01-01");
		userInfo.setImage("000003.jpg");
		userInfo.setPhone("1234567890");
		userInfo.setPassword("1234");
		try {
			userService.saveUser(userInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void testDelUserInfo() {
		UserInfo userInfo = new UserInfo();
		userInfo.setLoginId(LOGING_ID);
		try {
			userService.delUser(userInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Ignore
	@Test
	public void testFindUserInfoByLoginId() {
		String loginId = LOGING_ID;
		UserInfo userInfo = userService.findUserByLoginId(loginId);
		assertNotNull(userInfo);
		assertEquals(loginId, userInfo.getLoginId());
	}

	// @Ignore
	@Test
	public void testFindUserInfoByEmail() {
		String email = EMAIL;
		UserInfo userInfo = userService.findUserByEmail(email);
		assertEquals(email, userInfo.getEmail());
	}

}
