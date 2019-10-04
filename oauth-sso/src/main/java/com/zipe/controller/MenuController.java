package com.zipe.controller;

import com.zipe.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/menu")
public class MenuController {


    @Autowired
    private ISysMenuService sysMenuService;

    @GetMapping(value = "/")
    public ModelAndView index(Authentication user) {
        ModelAndView modelAndView = new ModelAndView("pages/menu");
        return modelAndView;
    }

    @RequestMapping(value = "/getMenuTree", method = RequestMethod.GET, produces="application/json;charset=utf-8")
    @ResponseBody
    public String getMenuTree() {
        String result = null;
        try {
            result = sysMenuService.getMenuTree();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
