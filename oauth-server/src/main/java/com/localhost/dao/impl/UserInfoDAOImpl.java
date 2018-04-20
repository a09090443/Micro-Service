package com.localhost.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.localhost.base.dao.impl.BaseHibernateDAOImpl;
import com.localhost.dao.IUserInfoDAO;
import com.localhost.model.UserInfo;

@Repository("userInfoDAO")
public class UserInfoDAOImpl extends BaseHibernateDAOImpl<UserInfo> implements IUserInfoDAO {
	private static final Logger logger = LoggerFactory.getLogger(UserInfoDAOImpl.class);

	public UserInfoDAOImpl() {
		this.setClazz(UserInfo.class);
	}

	@Override
	public UserInfo findUserByLoginId(String loginId) {
		Map<String, Object> argMap = new HashMap<String, Object>();
		argMap.put("loginId", loginId);
		StringBuilder hqlStr = new StringBuilder();
		// From tables
		hqlStr.append("FROM UserInfo U ");
		// Select conditions
		hqlStr.append("WHERE U.loginId = :loginId ");
		List<UserInfo> userList = (List<UserInfo>) this.queryHql(hqlStr.toString(), argMap, false);
		return CollectionUtils.isEmpty(userList) ? null : userList.get(0);
	}

	@Override
	public UserInfo findUserByEmail(String email) {
		Map<String, Object> argMap = new HashMap<String, Object>();
		argMap.put("email", email);
		StringBuilder hqlStr = new StringBuilder();
		// From tables
		hqlStr.append("FROM UserInfo U ");
		// Select conditions
		hqlStr.append("WHERE U.email = :email ");
		List<UserInfo> userList = (List<UserInfo>) this.queryHql(hqlStr.toString(), argMap, false);
		return CollectionUtils.isEmpty(userList) ? null : userList.get(0);
	}

	@Override
	public String getMaxUserId() {
		String hqlStr = "FROM UserInfo U ORDER BY U.userId DESC";
		List<UserInfo> userInfoList = (List<UserInfo>) this.queryHql(hqlStr.toString(), null, false);
		return CollectionUtils.isEmpty(userInfoList) ? "0" : userInfoList.get(0).getUserId();
	}

	@Override
	public void deleteById(Long entityId) {
		// TODO Auto-generated method stub
		
	}

}
