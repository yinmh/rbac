package com.westos.rbac.controller.system;

import com.westos.rbac.service.Impl.UserServiceImpl;
import com.westos.rbac.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author yihang
 */
@WebServlet("/system/user/delete")
public class UserDeleteServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));
        try {
            int rows = userService.deleteUser(userId);
            if(rows>0){
                req.getRequestDispatcher("/system/user/page?page=1").forward(req,resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
