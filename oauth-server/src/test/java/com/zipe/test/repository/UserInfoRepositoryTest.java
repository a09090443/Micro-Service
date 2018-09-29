package com.zipe.test.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.zipe.model.UserInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zipe.repository.IUserInfoRepository;
import com.zipe.test.base.TestBase;

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
