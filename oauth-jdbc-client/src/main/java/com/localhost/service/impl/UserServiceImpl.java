package com.localhost.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.localhost.model.UserInfo;
import com.localhost.repository.IUserInfoRepository;
import com.localhost.service.IUserService;
import com.usefulness.utils.date.DateUtils;
import com.usefulness.utils.image.ImageUtils;

@Transactional
@Service("userService")
public class UserServiceImpl implements IUserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private Integer maxUserId;

	@Autowired
	private IUserInfoRepository userInfoDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<UserInfo> findAllUsers() throws Exception {
		return userInfoDAO.findAll();
	}

	@Override
	public UserInfo findUserByLoginId(String loginId) {
		UserInfo userInfo = userInfoDAO.findByLoginId(loginId);
		return userInfo;
	}

	@Override
	public UserInfo findUserByEmail(String email) {
		UserInfo userInfo = userInfoDAO.findByEmail(email);
		return userInfo;
	}

	@Override
	public UserInfo findMaxLoginId() {
		UserInfo userInfo = userInfoDAO.findTopByOrderByLoginIdDesc();
		return userInfo;
	}

	@Override
	public void saveUser(UserInfo userInfo) {
		UserInfo checkUser = userInfoDAO.findByLoginId(userInfo.getLoginId());
		if (null != checkUser) {
			logger.error("This login_id has been registered!!");
			return;
			// throw new Exception("This login_id has been registered!!");
		}

		Integer newUserId = 0;
		String currentTime;

		if (null == maxUserId) {
			UserInfo latestUserInfo = userInfoDAO.findTopByOrderByLoginIdDesc();
			if (latestUserInfo.getLoginId().equals("0")) {
				maxUserId = 0;
			} else {
				maxUserId = Integer.valueOf(latestUserInfo.getLoginId());
			}
		}

		newUserId = maxUserId + 1;
		currentTime = DateUtils.getCurrentDate("yyyy-MM-dd hh:mm:ss");
		String formatStr = "%06d";
		String newLoginId = String.format(formatStr, newUserId);
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userInfo.setUserId(newLoginId);
		userInfo.setActivated(true);
		userInfo.setRegisterTime(currentTime);
		userInfo.setImage(newLoginId + "." + ImageUtils.IMAGE_TYPE_JPG);

		try {
			userInfoDAO.save(userInfo);
		} catch (Exception e) {
			logger.error("Save user : " + userInfo.getFirstName() + userInfo.getLastName() + " error.");
			logger.error(e.getMessage());
			return;
		}

	}

}
