package com.westos.rbac.controller.login;

import com.westos.rbac.domain.User;
import com.westos.rbac.service.Impl.UserServiceImpl;
import com.westos.rbac.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author :ymh
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        User user = userService.getUserByUserName(username);
        if(user == null ){
            req.getSession().setAttribute("Msg","用户不存在");
            resp.sendRedirect("/login.jsp");
            return;
        }
        if(!req.getSession().getAttribute("vcode").equals(req.getParameter("inputVcode"))){
            req.getSession().setAttribute("Msg","验证码错误");
            resp.sendRedirect("/login.jsp");
            return;
        }
        if(!user.getPassword().equals(req.getParameter("password"))){
            req.getSession().setAttribute("Msg","用户名密码不符");
            resp.sendRedirect("/login.jsp");
            return;
        }

        req.getSession().setAttribute("user",user);
        resp.sendRedirect("/menu/list");
        return;
    }
}
