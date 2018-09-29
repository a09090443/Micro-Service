package com.zipe.service;

import com.zipe.model.UserInfo;

import java.util.List;

public interface IUserService {
	
	public List<UserInfo> findAllUsers() throws Exception;
	
	public UserInfo findUserByLoginId(String loginId);
	
	public UserInfo findUserByEmail(String email);

	public UserInfo findMaxLoginId();
	
	public void saveUser(UserInfo userInfo) throws Exception;
	
	public void delUser(UserInfo userInfo) throws Exception;
}
