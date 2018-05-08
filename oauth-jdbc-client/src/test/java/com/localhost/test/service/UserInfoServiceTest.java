package com.localhost.test.service;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.localhost.model.UserInfo;
import com.localhost.service.IUserService;
import com.localhost.test.base.TestBase;

public class UserInfoServiceTest extends TestBase{

	@Autowired
	private IUserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Ignore
	@Test
	public void testFindUserInfoByLoginId() {
		String loginId = "admin";
		UserInfo userInfo = userService.findUserByLoginId(loginId);
		System.out.println(userInfo);
	}
	
	@Test
	public void testFindUserInfoByEmail() {
		String email = "admin@localhost.com";
		UserInfo userInfo = userService.findUserByEmail(email);
		System.out.println(userInfo);
	}
	
//	@Test
//	public void testSaveUserInfo() {
//		UserInfo userInfo = new UserInfo();
//		userInfo.setUserId("000003");
//		userInfo.setLoginId("test2");
//		userInfo.setPassword(passwordEncoder.encode("1234"));
//		userInfo.setFirstName("test");
//		userInfo.setLastName("2");
//		userInfo.setEmail("test2@localhost.com");
//		userInfo.setAddress("test223333");
//		userInfo.setBirthday("1984-01-01");
//		userInfo.setImage("000003.jpg");
//		userInfo.setPhone("1234567890");
//		userInfo.setActivated(true);
//		userInfo.setRegisterTime("2017-12-31 02:30:49");
//		try {
//			userService.saveUser(userInfo);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	@Test
	public void testFindMaxLoginId() {
		UserInfo userInfo = userService.findMaxLoginId();
		System.out.println(userInfo);
	}

}
