package com.zipe.service;

import com.zipe.model.SysUser;

import java.util.List;

public interface IUserService {
	
	public List<SysUser> findAllUsers() throws Exception;
	
	public SysUser findUserByLoginId(String loginId);
	
	public SysUser findUserByEmail(String email);

	public SysUser findMaxLoginId();
	
	public void saveUser(SysUser userInfo) throws Exception;
	
	public void delUser(SysUser userInfo) throws Exception;
}
