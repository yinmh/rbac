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
@WebServlet("/system/user/add")
public class UserAddServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setOrgId(Integer.parseInt(req.getParameter("orgId")));
        int rows = userService.addUser(user);
        if(rows>0){
            req.setAttribute("msg","添加成功");
            req.getRequestDispatcher("/jsp/system/user/toadd.jsp").forward(req,resp);
        }else{
            req.setAttribute("msg","添加失败");
            req.getRequestDispatcher("/jsp/system/user/toadd.jsp").forward(req,resp);
        }
    }
}
