package com.zipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zipe.model.UserInfo;

public interface IUserInfoRepository extends JpaRepository<UserInfo, Long> {
	
//	@Query("FROM UserInfo U WHERE U.loginId = :loginId")
	public UserInfo findByLoginId(String loginId);
	
	public UserInfo findByEmail(String email);

	public UserInfo findTopByOrderByLoginIdDesc();
	
	public void deleteByLoginId(String loginId);
}
