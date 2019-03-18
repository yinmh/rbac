package com.westos.rbac.controller.system;

import com.sun.xml.internal.bind.v2.model.core.ID;
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
import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yihang
 */
@WebServlet("/system/role/tomodifymodule")
public class RoleToModifyModuleServlet extends HttpServlet {
    private RoleDao roleDao = new RoleDaoImpl();
    private ModuleDao moduleDao = new ModuleDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Module> modules = moduleDao.findAll();
        int roleId = Integer.parseInt(req.getParameter("id"));
        System.out.println(roleId);
        List<Module> list = moduleDao.findByRoleId(roleId);
        req.setAttribute("id",roleId);
        req.setAttribute("list",list);
        req.setAttribute("modules",modules);
        req.getRequestDispatcher("/jsp/system/role/tomodifyrole.jsp").forward(req,resp);

    }
}
