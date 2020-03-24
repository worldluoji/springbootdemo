package com.springboot.zuul.demo.simplezuuldemo.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class PreFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("Request Method : " + request.getMethod() + " Request URL : " + request.getRequestURL().toString());
        // 这个是获取?name=luoji的参数
        String name = request.getParameter("name");
        if (name == null || name.isEmpty()) {
            log.info("用户验证不通过");
            // 实际系统中做验证，是远程调用到认证系统去做认证，这里只是简单模拟一下
            return false;
        }
        log.info("登录的用户：" + name);
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("shouldFilter为true，才会执行下run方法");
        return null;
    }
}
