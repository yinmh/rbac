package com.westos.rbac.dao.impl;

import com.westos.rbac.dao.RoleDao;
import com.westos.rbac.domain.Role;
import com.westos.rbac.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yihang
 */
public class RoleDaoImpl implements RoleDao {
    @Override
    public List<Role> findAll() {
        ArrayList<Role> roles = new ArrayList<>();
        try(Connection conn = JdbcUtil.getConnection()){
            try(PreparedStatement pstm = conn.prepareStatement("select id,name from rbac_role")){
                ResultSet rs = pstm.executeQuery();
                while (rs.next()) {
                    Role role = new Role();
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    role.setId(id);
                    role.setName(name);
                    roles.add(role);
                }
                return roles;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Role> findByUserId(int userId) {
        ArrayList<Role> roles = new ArrayList<>();
        try(Connection conn = JdbcUtil.getConnection()){
            try(PreparedStatement pstm = conn.prepareStatement("select b.id , b.name from rbac_user_role a inner join rbac_role b on a.role_id = b.id  where a.user_id = ?")){
                pstm.setInt(1,userId);
                ResultSet rs = pstm.executeQuery();
                while (rs.next()) {
                    Role role = new Role();
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    role.setId(id);
                    role.setName(name);
                    roles.add(role);
                }
                return roles;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
