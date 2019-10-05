package com.zipe.service.impl;

import com.zipe.service.ISysMenuService;
import com.zipe.service.common.CommonService;
import com.zipe.url.constant.Url.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedHashMap;

@Service("sysMenuService")
public class SysMenuServiceImpl extends CommonService implements ISysMenuService {
    private static final Logger logger = LoggerFactory.getLogger(SysMenuServiceImpl.class);

    @Override
    public String getMenuTree() throws Exception {

        LinkedHashMap<String, String> parametersMap = new LinkedHashMap<String, String>();
        String result = this.sendUrl("jdbc-api", parametersMap, URI.GET_SYS_MENU.getUri(), false, RequestMethod.GET.toString());
        return result;
    }
}
