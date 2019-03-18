package com.westos.rbac.controller.system;

import com.westos.rbac.dao.RoleDao;
import com.westos.rbac.dao.UserDao;
import com.westos.rbac.dao.impl.RoleDaoImpl;
import com.westos.rbac.dao.impl.UserDaoImpl;
import com.westos.rbac.domain.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author yihang
 */
@WebServlet("/system/user/tomodifyrole")
public class UserToModifyRoleServlet extends HttpServlet {
    private RoleDao roleDao = new RoleDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));
        List<Role> roles = roleDao.findAll();
        List<Role> userRoles = roleDao.findByUserId(userId);
        System.out.println(userRoles);
        req.setAttribute("roles",roles);
        req.setAttribute("userRoles",userRoles);
        req.setAttribute("userId",userId);
        req.getRequestDispatcher("/jsp/system/user/tomodifyrole.jsp").forward(req,resp);
    }
}
