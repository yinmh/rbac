package com.westos.rbac.controller.system;

import com.westos.rbac.dao.UserDao;
import com.westos.rbac.dao.impl.UserDaoImpl;
import org.omg.PortableInterceptor.USER_EXCEPTION;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author :ymh
 */
@WebServlet("/system/user/exist")
public class CheckExistServlet extends HttpServlet {
    private UserDao userDao = new UserDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean exists = userDao.exists(username);
        resp.getWriter().print(exists);
    }
}
