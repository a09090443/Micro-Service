package com.localhost.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.localhost.dao.IUserInfoDAO;
import com.localhost.model.UserInfo;
import com.localhost.service.IUserService;
import com.usefulness.utils.date.DateUtils;
import com.usefulness.utils.image.ImageUtils;


@Transactional
@Service("userService")
public class UserServiceImpl implements IUserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private Integer maxUserId;
	
	@Autowired
	private IUserInfoDAO userInfoDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<UserInfo> findAllUsers() throws Exception {
		return userInfoDAO.findAll();
	}

	@Override
	public UserInfo findUserByLoginId(String loginId) {
		UserInfo userInfo = userInfoDAO.findUserByLoginId(loginId);
		return userInfo;
	}

	@Override
	public UserInfo findUserByEmail(String email) {
		UserInfo userInfo = userInfoDAO.findUserByEmail(email);
		return userInfo;
	}

	@Override
	public void addUser(UserInfo userInfo) throws Exception {
		UserInfo checkUser = userInfoDAO.findUserByLoginId(userInfo.getLoginId());
		if(null != checkUser){
			throw new Exception("This login_id has been registered!!");
		}
		Integer newUserId = 0;
		String getTime;
		if (null == maxUserId) {
			String userId = userInfoDAO.getMaxUserId();
			if (userId.equals("0")) {
				maxUserId = 0;
			} else {
				maxUserId = Integer.valueOf(userId);
			}
		}
		newUserId = maxUserId + 1;
		getTime = DateUtils.getCurrentDate("yyyy-MM-dd hh:mm:ss");
		String formatStr = "%06d";
		String newUserIdStr = String.format(formatStr, newUserId);
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userInfo.setUserId(newUserIdStr);
		userInfo.setActivated(true);
		userInfo.setRegisterTime(getTime);
		userInfo.setImage(newUserIdStr + "." + ImageUtils.IMAGE_TYPE_JPG);
		userInfoDAO.save(userInfo);
		maxUserId += 1;
	}

}
