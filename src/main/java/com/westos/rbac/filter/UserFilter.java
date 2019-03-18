package com.westos.rbac.filter;

import com.westos.rbac.dao.ModuleDao;
import com.westos.rbac.dao.impl.ModuleDaoImpl;
import com.westos.rbac.dao.impl.RoleDaoImpl;
import com.westos.rbac.domain.Module;
import com.westos.rbac.domain.Role;
import com.westos.rbac.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Author :ymh
 */
@WebFilter({"/product/*", "/system/*", "/order/*"})
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        User user = (User) req.getSession().getAttribute("user");
        System.out.println(user);
        List<Role> roles = new RoleDaoImpl().findByUserId(user.getId());
        for (Role role : roles) {
            List<Module> modules = new ModuleDaoImpl().findByRoleId(role.getId());
            for (Module module : modules) {
                System.out.println(module.getCode());
                if (module.getCode().equals(req.getRequestURI()) || module.getCode().length()>0) {
                    filterChain.doFilter(req, resp);
                    return;
                }
            }
        }
        //filterChain.doFilter(servletRequest, servletResponse);
        resp.sendError(403);
    }

    @Override
    public void destroy() {

    }
}
