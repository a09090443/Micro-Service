package com.zipe.repository;

import com.zipe.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISysUserRepository extends JpaRepository<SysUser, Long> {
	
//	@Query("FROM UserInfo U WHERE U.loginId = :loginId")
	public SysUser findByLoginId(String loginId);
	
	public SysUser findByEmail(String email);

	public SysUser findTopByOrderByLoginIdDesc();
	
	public void deleteByLoginId(String loginId);
}
