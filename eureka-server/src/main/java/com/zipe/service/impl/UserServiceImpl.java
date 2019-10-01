package com.zipe.service.impl;

import com.zipe.model.SysUser;
import com.zipe.repository.ISysUserRepository;
import com.zipe.service.IUserService;
import com.zipe.utils.image.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Transactional
@Service("userService")
public class UserServiceImpl implements IUserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private Integer maxUserId;

	@Autowired
	private ISysUserRepository sysUserRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<SysUser> findAllUsers(){
		return sysUserRepository.findAll();
	}

	@Override
	public SysUser findUserByLoginId(String loginId) {
		SysUser SysUser = sysUserRepository.findByLoginId(loginId);
		return SysUser;
	}

	@Override
	public SysUser findUserByEmail(String email) {
		SysUser SysUser = sysUserRepository.findByEmail(email);
		return SysUser;
	}

	@Override
	public SysUser findMaxLoginId() {
		SysUser SysUser = sysUserRepository.findTopByOrderByLoginIdDesc();
		return SysUser;
	}

	@Override
	public void saveUser(SysUser SysUser) {
		SysUser checkUser = sysUserRepository.findByLoginId(SysUser.getLoginId());
		if (!Objects.isNull(checkUser)) {
			logger.error("This login_id has been registered!!");
			return;
			// throw new Exception("This login_id has been registered!!");
		}

		Integer newUserId = 0;
		Date currentTime;

		if (null == maxUserId) {
			SysUser latestSysUser = sysUserRepository.findTopByOrderByLoginIdDesc();
			if (Objects.isNull(latestSysUser)) {
				maxUserId = 0;
			} else {
				maxUserId = Integer.valueOf(latestSysUser.getUserId());
			}
		}

		newUserId = maxUserId + 1;
		currentTime = new Date();
		String formatStr = "%06d";
		String newLoginId = String.format(formatStr, newUserId);
		SysUser.setPassword(passwordEncoder.encode(SysUser.getPassword()));
		SysUser.setUserId(newLoginId);
		SysUser.setActivated(true);
		SysUser.setRegisterTime(currentTime);
		SysUser.setImage(newLoginId + "." + ImageUtils.IMAGE_TYPE_JPG);

		try {
			sysUserRepository.save(SysUser);
		} catch (Exception e) {
			logger.error("Save user : " + SysUser.getFirstName() + SysUser.getLastName() + " error.");
			logger.error(e.getMessage());
			return;
		}

	}

	@Override
	public void delUser(SysUser SysUser) {
		sysUserRepository.deleteByLoginId(SysUser.getLoginId());
	}

}
