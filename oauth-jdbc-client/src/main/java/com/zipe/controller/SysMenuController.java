package com.zipe.controller;

import com.zipe.vo.SysMenuVO;
import com.zipe.base.controller.BaseController;
import com.zipe.service.ISysMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/menu")
public class SysMenuController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(SysMenuController.class);

    @Autowired
    private ISysMenuService sysMenuService;

    @ResponseBody
    @GetMapping(value = "/menuTree")
    public Map getMenuTree() {
        List<SysMenuVO> sysMenuVOList = sysMenuService.findSysMenuTree();

        Map<String, List<SysMenuVO>> map = new HashMap();
        map.put("menu", sysMenuVOList);

        return map;
    }

}
