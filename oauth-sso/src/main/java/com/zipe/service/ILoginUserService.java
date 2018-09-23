package com.zipe.service;

public interface ILoginUserService {

	public String getUsers() throws Exception;
	
	public String getAuthorities() throws Exception;
	
	public String getPersonalTitles() throws Exception;
	
	public String saveUser(String content) throws Exception;

}
