package com.zipe.service.impl;

import com.zipe.vo.SysMenuVO;
import com.zipe.base.service.BaseService;
import com.zipe.model.SysMenu;
import com.zipe.repository.ISysMenuEntityRepository;
import com.zipe.service.ISysMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.util.Comparator.comparing;

@Transactional
@Service("sysMenuService")
public class SysMenuServiceImpl extends BaseService implements ISysMenuService {
    private static final Logger logger = LoggerFactory.getLogger(SysMenuServiceImpl.class);

    @Autowired
    private ISysMenuEntityRepository sysMenuEntityRepository;

    @Override
    public List<SysMenu> findAllSysMenu() {
        return sysMenuEntityRepository.findAll();
    }

    @Override
    public List<SysMenuVO> findSysMenuTree() {
        List<SysMenu> sysMenuEntityList = sysMenuEntityRepository.findAll();

        // Root_level's parentId is zero
        List<SysMenuVO> sysMenuVOList = this.getMappingMenu(sysMenuEntityList, 0);
        return sysMenuVOList;
    }

    private List<SysMenuVO> getMappingMenu(List<SysMenu> sysMenuEntityList, int parantId) {
        List<SysMenuVO> sysMenuVOList = new LinkedList<>();
        for (SysMenu sysMenuEntity : sysMenuEntityList) {
            if (parantId == sysMenuEntity.getParentId() && sysMenuEntity.getEnabled()) {
                SysMenuVO sysMenuVO = new SysMenuVO();
                sysMenuVO.setMenuId(sysMenuEntity.getMenuId());
                sysMenuVO.setName(sysMenuEntity.getMenuName());
                sysMenuVO.setOrderId(sysMenuEntity.getOrderId());
                sysMenuVO.setLink(sysMenuEntity.getPath());
                sysMenuVO.setSub(this.getMappingMenu(sysMenuEntityList, sysMenuEntity.getMenuId()));
                sysMenuVOList.add(sysMenuVO);
            }
        }
        sysMenuVOList.sort(
                comparing(SysMenuVO::getOrderId)
                        .thenComparing(SysMenuVO::getOrderId)
        );
        return sysMenuVOList;
    }
}
