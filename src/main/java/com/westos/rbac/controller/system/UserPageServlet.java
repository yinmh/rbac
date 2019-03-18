package com.westos.rbac.controller.system;

import com.westos.rbac.dao.UserDao;
import com.westos.rbac.dao.impl.UserDaoImpl;
import com.westos.rbac.domain.User;
import com.westos.rbac.service.Impl.UserServiceImpl;
import com.westos.rbac.service.UserService;
import jdk.nashorn.internal.runtime.FindProperty;

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
@WebServlet({"/system/user/page","/system/user"})
public class UserPageServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("page");
        int p = Integer.parseInt(page);
        if(p == 0){
            p = 1 ;
        }
        List<User> userList = userService.getUserByPage(p, 6);
        int count = userService.getUsersCount();
        int pageNum = (count - 1) / 6 + 1;

        req.getSession().setAttribute("users",userList);
        req.getSession().setAttribute("current",p);
        req.getSession().setAttribute("pageNum",pageNum);
        resp.sendRedirect("/jsp/system/user/page.jsp");
    }
}
