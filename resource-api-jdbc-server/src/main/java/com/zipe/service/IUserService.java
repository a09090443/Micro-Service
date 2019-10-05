package com.zipe.service;

import com.zipe.model.SysAuthority;
import com.zipe.model.SysUser;
import com.zipe.model.SysUserTitle;

import java.util.List;
import java.util.Set;

public interface IUserService {

    public List<SysUser> findAllUsers() throws Exception;

    public SysUser findUserByLoginId(String loginId);

    public SysUser findUserByEmail(String email);

    public SysUser findMaxLoginId();

    public List<SysAuthority> getAuthorities();

    public Set<SysAuthority> getSysAuthoritiesByAuthorityId(String authorityId);

    public List<SysUserTitle> getSysUserTitles();

    public Set<SysUserTitle> getSysUserTitlesByTitleId(String titleId);

    public void saveUser(SysUser userInfo) throws Exception;

    public void delUser(SysUser userInfo);
}
