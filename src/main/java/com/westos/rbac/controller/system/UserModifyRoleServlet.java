package com.westos.rbac.controller.system;

import com.westos.rbac.dao.UserDao;
import com.westos.rbac.dao.impl.UserDaoImpl;
import com.westos.rbac.service.Impl.UserServiceImpl;
import com.westos.rbac.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yihang
 */
@WebServlet("/system/user/modifyrole")
public class UserModifyRoleServlet extends HttpServlet {
    private UserDao userDao = new UserDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));
        String[] strings = req.getParameterValues("roles");
        int[] roles = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            roles[i] = Integer.parseInt(strings[i]);
        }
        userDao.deleteRoleByUserId(userId);
        for (int roleId : roles) {
             int rows = userDao.insertRoleByUserId(userId, roleId);
        }
        req.getRequestDispatcher("/system/user/page?page=1").forward(req,resp);
    }
}
