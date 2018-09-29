package com.zipe.service;

import com.zipe.model.UserInfo;
import com.zipe.model.Authority;
import com.zipe.model.PersonalTitle;

import java.util.List;
import java.util.Set;

public interface IUserService {

    public List<UserInfo> findAllUsers() throws Exception;

    public UserInfo findUserByLoginId(String loginId);

    public UserInfo findUserByEmail(String email);

    public UserInfo findMaxLoginId();

    public List<Authority> getAuthorities();

    public Set<Authority> getAuthoritiesByAuthorityId(String authorityId);

    public List<PersonalTitle> getPersonalTitles();

    public Set<PersonalTitle> getPersonalTitlesByTitleId(String titleId);

    public void saveUser(UserInfo userInfo) throws Exception;

    public void delUser(UserInfo userInfo);
}
