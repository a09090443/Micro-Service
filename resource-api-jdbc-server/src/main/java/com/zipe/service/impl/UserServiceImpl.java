package com.zipe.service.impl;

import com.zipe.model.SysAuthority;
import com.zipe.model.SysUser;
import com.zipe.model.SysUserTitle;
import com.zipe.repository.ISysAuthorityRepository;
import com.zipe.repository.ISysUserRepository;
import com.zipe.repository.ISysUserTitleRepository;
import com.zipe.service.IUserService;
import com.zipe.utils.image.ImageUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service("userService")
public class UserServiceImpl implements IUserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private Integer maxUserId;

    @Autowired
    private ISysUserRepository sysUserRepository;

    @Autowired
    private ISysAuthorityRepository sysAuthorityRepository;

    @Autowired
    private ISysUserTitleRepository sysUserTitleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<SysUser> findAllUsers() {
        return sysUserRepository.findAll();
    }

    @Override
    public SysUser findUserByLoginId(String loginId) {
        SysUser sysUser = sysUserRepository.findByLoginId(loginId);
        return sysUser;
    }

    @Override
    public SysUser findUserByEmail(String email) {
        SysUser sysUser = sysUserRepository.findByEmail(email);
        return sysUser;
    }

    @Override
    public SysUser findMaxLoginId() {
        SysUser sysUser = sysUserRepository.findTopByOrderByLoginIdDesc();
        return sysUser;
    }

    @Override
    public List<SysAuthority> getAuthorities() {
        List<SysAuthority> authoritiesList = sysAuthorityRepository.findAll();
        return authoritiesList;
    }

    @Override
    public Set<SysAuthority> getSysAuthoritiesByAuthorityId(String authorityId) {
        Set<SysAuthority> sysAuthoritySet = sysAuthorityRepository
                .findSysAuthoritiesByAuthorityIdIn(Arrays.asList(authorityId.replaceAll(" ", "").split(",")));
        return sysAuthoritySet;
    }

    @Override
    public List<SysUserTitle> getSysUserTitles() {
        List<SysUserTitle> sysUserTitleList = sysUserTitleRepository.findAll();
        return sysUserTitleList;
    }

    @Override
    public Set<SysUserTitle> getSysUserTitlesByTitleId(String titleId) {
        Set<SysUserTitle> sysUserTitleSet = sysUserTitleRepository
                .findSysUserTitleByTitleIdIn(Arrays.asList(titleId.replaceAll(" ", "").split(",")));
        return sysUserTitleSet;
    }

    @Override
    public void saveUser(SysUser sysUser) {
//		SysUser checkUser = SysUserRepository.findByLoginId(SysUser.getLoginId());
//		if (!Objects.isNull(checkUser)) {
//			logger.error("This login_id has been registered!!");
//			throw new Exception("This login_id has been registered!!");
//		}

        // Add new user
        if (StringUtils.isBlank(sysUser.getUserId())) {
            String newUserId;

            if (null == maxUserId) {
                SysUser latestSysUser = sysUserRepository.findTopByOrderByLoginIdDesc();
                if (Objects.isNull(latestSysUser)) {
                    maxUserId = 0;
                } else {
                    maxUserId = Integer.valueOf(latestSysUser.getUserId());
                }
            }
            String formatStr = "%06d";
            newUserId = String.format(formatStr, maxUserId + 1);
            sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
            sysUser.setUserId(newUserId);
            sysUser.setActivated(true);
            sysUser.setRegisterTime(new Date());
            sysUser.setImage(newUserId + "." + ImageUtils.IMAGE_TYPE_JPG);
        }

        try {
            sysUserRepository.save(sysUser);
        } catch (Exception e) {
            logger.error("Save user : " + sysUser.getFirstName() + sysUser.getLastName() + " error.");
            throw e;
        }
    }

    @Override
    public void delUser(SysUser sysUser) {
        try {
            sysUserRepository.deleteByLoginId(sysUser.getLoginId());
        } catch (Exception e) {
            logger.error("Delete Login Id: " + sysUser.getLoginId() + " error!!");
            throw e;
        }
    }
}
