package com.zipe.service;

public interface ILoginUserService {

	public String getSysUsers() throws Exception;
	
	public String getSysAuthorities() throws Exception;
	
	public String getSysUserTitles() throws Exception;
	
	public String saveSysUser(String content) throws Exception;

}
