package com.zipe.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zipe.model.UserInfo;
import com.zipe.model.Authority;
import com.zipe.model.PersonalTitle;
import com.zipe.repository.IAuthorityRepository;
import com.zipe.repository.IPersonalTitleRepository;
import com.zipe.repository.IUserInfoRepository;
import com.zipe.service.IUserService;
import com.zipe.utils.date.DateUtils;
import com.zipe.utils.image.ImageUtils;

@Transactional
@Service("userService")
public class UserServiceImpl implements IUserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private Integer maxUserId;

    @Autowired
    private IUserInfoRepository userInfoRepository;

    @Autowired
    private IAuthorityRepository authorityRepository;

    @Autowired
    private IPersonalTitleRepository personalTitleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserInfo> findAllUsers() throws Exception {
        return userInfoRepository.findAll();
    }

    @Override
    public UserInfo findUserByLoginId(String loginId) {
        UserInfo userInfo = userInfoRepository.findByLoginId(loginId);
        return userInfo;
    }

    @Override
    public UserInfo findUserByEmail(String email) {
        UserInfo userInfo = userInfoRepository.findByEmail(email);
        return userInfo;
    }

    @Override
    public UserInfo findMaxLoginId() {
        UserInfo userInfo = userInfoRepository.findTopByOrderByLoginIdDesc();
        return userInfo;
    }

    @Override
    public List<Authority> getAuthorities() {
        List<Authority> authoritiesList = authorityRepository.findAll();
        return authoritiesList;
    }

    @Override
    public Set<Authority> getAuthoritiesByAuthorityId(String authorityId) {
        Set<Authority> authoritySet = authorityRepository
                .findAuthoritiesByAuthorityIdIn(Arrays.asList(authorityId.replaceAll(" ", "").split(",")));
        return authoritySet;
    }

    @Override
    public List<PersonalTitle> getPersonalTitles() {
        List<PersonalTitle> personalTitleList = personalTitleRepository.findAll();
        return personalTitleList;
    }

    @Override
    public Set<PersonalTitle> getPersonalTitlesByTitleId(String titleId) {
        Set<PersonalTitle> personalTitleSet = personalTitleRepository
                .findPersonalTitleByTitleIdIn(Arrays.asList(titleId.replaceAll(" ", "").split(",")));
        return personalTitleSet;
    }

    @Override
    public void saveUser(UserInfo userInfo) throws Exception {
//		UserInfo checkUser = userInfoRepository.findByLoginId(userInfo.getLoginId());
//		if (!Objects.isNull(checkUser)) {
//			logger.error("This login_id has been registered!!");
//			throw new Exception("This login_id has been registered!!");
//		}

        // Add new user
        if (StringUtils.isBlank(userInfo.getUserId())) {
            String newUserId;
            String currentTime;

            if (null == maxUserId) {
                UserInfo latestUserInfo = userInfoRepository.findTopByOrderByLoginIdDesc();
                if (Objects.isNull(latestUserInfo)) {
                    maxUserId = 0;
                } else {
                    maxUserId = Integer.valueOf(latestUserInfo.getUserId());
                }
            }
            String formatStr = "%06d";
            newUserId = String.format(formatStr, maxUserId + 1);
            currentTime = DateUtils.getCurrentDate("yyyy-MM-dd hh:mm:ss");
            userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
            userInfo.setUserId(newUserId);
            userInfo.setActivated(true);
            userInfo.setRegisterTime(currentTime);
            userInfo.setImage(newUserId + "." + ImageUtils.IMAGE_TYPE_JPG);
        }

        try {
            userInfoRepository.save(userInfo);
        } catch (Exception e) {
            logger.error("Save user : " + userInfo.getFirstName() + userInfo.getLastName() + " error.");
            throw e;
        }
    }

    @Override
    public void delUser(UserInfo userInfo) {
        try {
            userInfoRepository.deleteByLoginId(userInfo.getLoginId());
        } catch (Exception e) {
            logger.error("Delete Login Id: " + userInfo.getLoginId() + " error!!");
            throw e;
        }
    }
}
