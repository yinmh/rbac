package com.westos.rbac.controller.menu;

import com.westos.rbac.dao.ModuleDao;
import com.westos.rbac.dao.RoleDao;
import com.westos.rbac.dao.impl.ModuleDaoImpl;
import com.westos.rbac.dao.impl.RoleDaoImpl;
import com.westos.rbac.domain.Module;
import com.westos.rbac.domain.Role;
import com.westos.rbac.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author :ymh
 */
@WebServlet("/menu/list")
public class MenuServlet extends HttpServlet {
    private RoleDao roleDao = new RoleDaoImpl();
    private ModuleDao moduleDao = new ModuleDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user =(User) req.getSession().getAttribute("user");
        List<Module> modules = moduleDao.findAll();
        List<Role> roles = roleDao.findByUserId(user.getId());
        ArrayList<Module> list = new ArrayList<Module>();
        List<Module> parentList = new ArrayList<>();
        for (Role role : roles) {
            List<Module> r_m = moduleDao.findByRoleId(role.getId());
            for (Module module : r_m) {
                if(module.getPid() == 0){
                    parentList.add(module);
                }
                list.add(module);
            }
        }
        req.getSession().setAttribute("parentList",parentList);
        req.getSession().setAttribute("list",list);
        req.getSession().setAttribute("modules",modules);
        req.getSession().setAttribute("userId",user.getId());
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }
}
