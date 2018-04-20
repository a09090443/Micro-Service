package com.localhost.dao;

import com.localhost.base.dao.BaseHibernateDAO;
import com.localhost.model.UserInfo;

public interface IUserInfoDAO extends BaseHibernateDAO<UserInfo> {
		
	public UserInfo findUserByLoginId(String loginId);
	
	public UserInfo findUserByEmail(String email);
	
	public String getMaxUserId();
}
