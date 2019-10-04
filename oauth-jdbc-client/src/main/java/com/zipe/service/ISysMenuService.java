package com.zipe.service;


import com.zipe.vo.SysMenuVO;
import com.zipe.model.SysMenuEntity;

import java.util.List;

public interface ISysMenuService {
    public List<SysMenuEntity> findAllSysMenu();

    public List<SysMenuVO> findSysMenuTree();
}
