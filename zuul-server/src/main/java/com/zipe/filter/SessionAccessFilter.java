package com.zipe.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class SessionAccessFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(SessionAccessFilter.class);

    // filterType：返回一個字符串代表過濾器的類型，在zuul中定義了四種不同生命週期的過濾器類型，具體如下：
    // pre：路由之前
    // routing：路由之時
    // post： 路由之後
    // error：發送錯誤調用
    @Override
    public String filterType() {
        return "pre";
    }

    // filterOrder：過濾的順序
    @Override
    public int filterOrder() {
        return 0;
    }

    // shouldFilter：這裡可以寫邏輯判斷，是否要過濾，本文true,永遠過濾。
    @Override
    public boolean shouldFilter() {
        return true;
    }

    // run：過濾器的具體邏輯。可用很複雜，包括查sql，nosql去判斷該請求到底有沒有權限訪問。
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(request.getMethod() + " , " + request.getRequestURL().toString());
        Object accessToken = request.getParameter("access_token");
        if (accessToken == null) {
            log.warn("access_token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("access_token is empty");
            } catch (Exception e) {
            }
            return null;
        }
        log.info("ok");
        return null;
    }
}