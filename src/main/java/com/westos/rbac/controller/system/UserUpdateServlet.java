package com.westos.rbac.controller.system;

import com.westos.rbac.domain.User;
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
@WebServlet("/system/user/update")
public class UserUpdateServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setId(Integer.parseInt(req.getParameter("id")));
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setOrgId(Integer.parseInt(req.getParameter("orgId")));
        int rows = userService.modifyUser(user);
        if(rows>0){
            req.getRequestDispatcher("/system/user/page?page=1").forward(req,resp);
        }
    }
}
