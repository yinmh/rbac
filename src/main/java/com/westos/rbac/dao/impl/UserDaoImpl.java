package com.westos.rbac.dao.impl;

import com.westos.rbac.dao.UserDao;
import com.westos.rbac.domain.Role;
import com.westos.rbac.domain.User;
import com.westos.rbac.util.JdbcUtil;
import com.westos.rbac.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yihang
 */
public class UserDaoImpl implements UserDao {
    @Override
    public User findByUsername(String username) {
        User user = new User();
        try (Connection conn = JdbcUtil.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement("select * from rbac_user where username= ?")) {
                pstm.setString(1, username);
                ResultSet rs = pstm.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("username");
                    String password = rs.getString("password");
                    int org_id = rs.getInt("org_id");
                    user.setId(id);
                    user.setUsername(name);
                    user.setPassword(password);
                    user.setOrgId(org_id);
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findById(int userId) {
        User user = new User();
        try (Connection conn = JdbcUtil.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement("select * from rbac_user where id= ?")) {
                pstm.setInt(1, userId);
                ResultSet rs = pstm.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("username");
                    String password = rs.getString("password");
                    int org_id = rs.getInt("org_id");
                    user.setId(id);
                    user.setUsername(name);
                    user.setPassword(password);
                    user.setOrgId(org_id);
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        ArrayList<User> users = new ArrayList<>();
        try (Connection conn = JdbcUtil.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement("select * from rbac_user")) {
                ResultSet rs = pstm.executeQuery();
                while (rs.next()) {
                    User user = new User();
                    int id = rs.getInt("id");
                    String name = rs.getString("username");
                    String password = rs.getString("password");
                    int org_id = rs.getInt("org_id");
                    user.setId(id);
                    user.setUsername(name);
                    user.setPassword(password);
                    user.setOrgId(org_id);
                    users.add(user);
                }
                return users;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findByPage(int page, int rows) {
        ArrayList<User> users = new ArrayList<>();
        try (Connection conn = JdbcUtil.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement("select id,username,password,org_id from rbac_user limit ?,?")) {
                pstm.setInt(1, (page - 1) * rows);
                pstm.setInt(2, rows);
                ResultSet rs = pstm.executeQuery();
                while (rs.next()) {
                    User user = new User();
                    int id = rs.getInt("id");
                    String name = rs.getString("username");
                    String password = rs.getString("password");
                    int org_id = rs.getInt("org_id");
                    user.setId(id);
                    user.setUsername(name);
                    user.setPassword(password);
                    user.setOrgId(org_id);
                    users.add(user);
                }
                return users;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int findCount() {
        int counts = 0;
        try (Connection conn = JdbcUtil.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement("select count(*) count from rbac_user")) {
                ResultSet rs = pstm.executeQuery();
                if (rs.next()) {
                    counts = rs.getInt("count");
                }
                return counts;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counts;
    }

    @Override
    public int insert(User user) {
        try (Connection conn = JdbcUtil.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement("insert into rbac_user(username,password,org_id,org_ids) values(?,?,?,?)")) {
                pstm.setString(1, user.getUsername());
                pstm.setString(2, user.getPassword());
                pstm.setInt(3, user.getOrgId());
                pstm.setString(4, StringUtil.join(user.getOrgIds()));
                int rows = pstm.executeUpdate();
                return rows;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(User user) {
        try (Connection conn = JdbcUtil.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement("update rbac_user set username = ?,password= ?" +
                    ",org_id= ?,org_ids= ? where id = ?")) {
                pstm.setString(1, user.getUsername());
                pstm.setString(2, user.getPassword());
                pstm.setInt(3, user.getOrgId());
                pstm.setString(4, StringUtil.join(user.getOrgIds()));
                pstm.setInt(5, user.getId());
                int rows = pstm.executeUpdate();
                return rows;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int userId) {
        try (Connection conn = JdbcUtil.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement("delete from rbac_user where id= ?")) {
                pstm.setInt(1, userId);
                int rows = pstm.executeUpdate();
                return rows;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteRoleByUserId(int userId) {
        try (Connection conn = JdbcUtil.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement("delete from rbac_user_role where user_id= ?")) {
                pstm.setInt(1, userId);
                int rows = pstm.executeUpdate();
                return rows;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int insertRoleByUserId(int userId, int roleId) {
        try (Connection conn = JdbcUtil.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement("insert into rbac_user_role(user_id,role_id) values(?,?)")) {
                pstm.setInt(1, userId);
                pstm.setInt(2, roleId);
                int rows = pstm.executeUpdate();
                return rows;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean exists(String username) {
        try (Connection conn = JdbcUtil.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement("select count(*) c from rbac_user where username = ?")) {
                pstm.setString(1, username);
                ResultSet rs = pstm.executeQuery();
                rs.next();
                int count = rs.getInt("c");
                return count == 1 ;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
