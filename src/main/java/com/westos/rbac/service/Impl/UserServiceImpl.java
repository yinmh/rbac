package com.westos.rbac.service.Impl;

import com.westos.rbac.dao.ModuleDao;
import com.westos.rbac.dao.RoleDao;
import com.westos.rbac.dao.UserDao;
import com.westos.rbac.dao.impl.ModuleDaoImpl;
import com.westos.rbac.dao.impl.RoleDaoImpl;
import com.westos.rbac.dao.impl.UserDaoImpl;
import com.westos.rbac.domain.Module;
import com.westos.rbac.domain.Role;
import com.westos.rbac.domain.User;
import com.westos.rbac.service.UserService;
import com.westos.rbac.util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Author :ymh
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    private RoleDao roleDao = new RoleDaoImpl();
    private ModuleDao moduleDao = new ModuleDaoImpl();

    @Override
    public List<User> getAllUser() {
        return userDao.findAll();
    }

    @Override
    public User getUserByUserName(String userName) {
        User user = userDao.findByUsername(userName);
        if (user == null) {
            return null;
        }
        List<Role> roles = roleDao.findByUserId(user.getId());
        user.setRoles(roles);

        for (Role role : roles) {
            List<Module> modules = moduleDao.findByRoleId(role.getId());
            role.setModules(modules);
        }
        return user;
    }

    @Override
    public User getUserById(int userId) {
        return userDao.findById(userId);
    }

    @Override
    public int addUser(User user) {
        return userDao.insert(user);
    }

    @Override
    public int getUsersCount() {
        return userDao.findCount();
    }

    @Override
    public int modifyUser(User user) {
        return userDao.update(user);
    }

    @Override
    public int deleteUser(int userId) {
       try(Connection conn = JdbcUtil.getConnection()) {
           conn.setAutoCommit(false);
           userDao.deleteRoleByUserId(userId);
           int rows = userDao.delete(userId);
           conn.commit();
           return rows;
       } catch (SQLException e) {
           e.printStackTrace();
           return -1;
       }
    }

    @Override
    public List<User> getUserByPage(int page, int rows) {
        return userDao.findByPage(page,rows);
    }

    @Override
    public int modifyUserRole(int userId, int roleId) {
        try(Connection conn = JdbcUtil.getConnection()) {
            conn.setAutoCommit(false);
            userDao.deleteRoleByUserId(userId);
            int rows = userDao.insertRoleByUserId(userId,roleId);
            conn.commit();
            return rows;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
