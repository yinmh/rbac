package com.westos.rbac.controller.system;

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
import java.util.Arrays;
import java.util.List;

/**
 * @author yihang
 */
@WebServlet("/system/role/modifymodule")
public class RoleModifyModuleServlet extends HttpServlet {
    private ModuleDao moduleDao = new ModuleDaoImpl();
    private RoleDao roleDao = new RoleDaoImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int roleId = Integer.parseInt(req.getParameter("id"));
        String[] strings = req.getParameterValues("moc");
        System.out.println(Arrays.toString(strings));
        int[] modules = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            modules[i] = Integer.parseInt(strings[i]);
        }
        moduleDao.deleteRoleByRoleId(roleId);
        for (int module : modules) {
            moduleDao.insertRoleByRoleId(roleId,module);
        }

        req.getRequestDispatcher("/system/user/page?page=1").forward(req,resp);

    }
}
