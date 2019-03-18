package com.westos.rbac.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author :ymh
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (req.getRequestURI().equals(req.getContextPath() + "/login.jsp")||
                req.getRequestURI().equals(req.getContextPath() + "/login") ||
                req.getRequestURI().equals(req.getContextPath() + "/logout") ||
                req.getRequestURI().equals(req.getContextPath() + "/verifyCode")
                ) {
            filterChain.doFilter(req, resp);
            return;
        }
        Object user = req.getSession().getAttribute("user");
        if(user != null){
            filterChain.doFilter(req,resp);
        }else{
            req.getSession().setAttribute("Msg","尚未登录");
            resp.sendRedirect("/login.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
