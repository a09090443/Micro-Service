package com.zipe.service;


import com.zipe.vo.SysMenuVO;
import com.zipe.model.SysMenu;

import java.util.List;

public interface ISysMenuService {
    public List<SysMenu> findAllSysMenu();

    public List<SysMenuVO> findSysMenuTree();
}
