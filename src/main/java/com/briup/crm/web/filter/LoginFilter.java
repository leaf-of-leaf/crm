package com.briup.crm.web.filter;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kj
 * @Date 2020/3/28 19:37
 * @Version 1.0
 */
public class LoginFilter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Boolean b1 = (request.getSession().getAttribute("user") == null);
        Boolean b2 = request.getServletPath().endsWith("login");
        if((b1 && b2) || (!b1 && !b2)){
            return true;
        }
        if(!b1 && b2) response.sendRedirect("/index");
        if(b1 && !b2) response.sendRedirect("/login");
        return false;
    }
}
