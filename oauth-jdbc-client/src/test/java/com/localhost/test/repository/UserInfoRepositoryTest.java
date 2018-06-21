package com.localhost.test.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.localhost.model.UserInfo;
import com.localhost.repository.IUserInfoRepository;
import com.localhost.test.base.TestBase;

public class UserInfoRepositoryTest extends TestBase {

	@Autowired
	private IUserInfoRepository userInfoRepository;
	
	private String LOGING_ID = "Junit";
	private String EMAIL = "Junit@localhost.com";
	
	// @Ignore
	@Test
	public void testFindByLoginId() {
		String loginId = LOGING_ID;
		UserInfo userInfo = userInfoRepository.findByLoginId(loginId);
		assertEquals(loginId, userInfo.getLoginId());
	}
	
	// @Ignore
	@Test
	public void testFindByEmail() {
		String email = EMAIL;
		UserInfo userInfo = userInfoRepository.findByEmail(email);
		assertEquals(email, userInfo.getEmail());
	}
	
	// @Ignore
	@Test
	public void testFindLastUserInfo() {
		UserInfo userInfo = userInfoRepository.findTopByOrderByLoginIdDesc();
		assertNotNull(userInfo);
	}
}
