package com.westos.rbac.dao.impl;

import com.westos.rbac.dao.ModuleDao;
import com.westos.rbac.domain.Module;
import com.westos.rbac.domain.Role;
import com.westos.rbac.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author yihang
 */
public class ModuleDaoImpl implements ModuleDao {
    @Override
    public List<Module> findAll() {
        ArrayList<Module> modules = new ArrayList<>();
        try (Connection conn = JdbcUtil.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement("select id,name,pid,code from rbac_module")) {
                ResultSet rs = pstm.executeQuery();
                while (rs.next()) {
                    Module module = new Module();
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int pid = rs.getInt("pid");
                    String code = rs.getString("code");
                    module.setId(id);
                    module.setName(name);
                    module.setPid(pid);
                    module.setCode(code);
                    modules.add(module);
                }
                HashMap<Integer, Module> map = new HashMap<>();
                ArrayList<Module> list1 = new ArrayList<>();
                for (Module module : modules) {
                    if(module.getPid() == 0){
                        list1.add(module);
                    }
                    map.put(module.getId(),module);
                }
                for (Module module : modules) {
                    int pid = module.getPid();
                    Module parent = map.get(pid);
                    if(parent != null){
                        parent.getChildren().add(module);
                    }
                }

                return list1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }

    @Override
    public List<Module> findByRoleId(int roleId) {
        ArrayList<Module> modules = new ArrayList<>();
        try (Connection conn = JdbcUtil.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement("select b.id , b.name,b.pid,b.code from rbac_role_module a inner join rbac_module b on a.module_id = b.id  where a.role_id = ?")) {
                pstm.setInt(1, roleId);
                ResultSet rs = pstm.executeQuery();
                while (rs.next()) {
                    Module module = new Module();
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int pid = rs.getInt("pid");
                    String code = rs.getString("code");
                    module.setId(id);
                    module.setName(name);
                    module.setPid(pid);
                    module.setCode(code);
                    modules.add(module);
                }
                return modules;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Module> findByPId(int pId) {
        ArrayList<Module> children = new ArrayList<>();
        try (Connection conn = JdbcUtil.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement("select id,name,pid,code from rbac_module where pid = ?")) {
                pstm.setInt(1,pId);
                ResultSet rs = pstm.executeQuery();
                while (rs.next()) {
                    Module module = new Module();
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int pid = rs.getInt("pid");
                    String code = rs.getString("code");
                    module.setId(id);
                    module.setName(name);
                    module.setPid(pid);
                    module.setCode(code);
                    children.add(module);
                }
            }
            return children;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int deleteRoleByRoleId(int roleId) {
        try (Connection conn = JdbcUtil.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement("delete from rbac_role_module where role_id= ?")) {
                pstm.setInt(1, roleId);
                int rows = pstm.executeUpdate();
                return rows;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int insertRoleByRoleId(int roleId, int moduleId) {
        try (Connection conn = JdbcUtil.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement("insert into rbac_role_module (role_id,module_id) values(?,?)")) {
                pstm.setInt(1, roleId);
                pstm.setInt(2, moduleId);
                int rows = pstm.executeUpdate();
                return rows;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
