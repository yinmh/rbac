package com.westos.rbac.service;


import com.westos.rbac.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    /**
     * 返回全部用户
     *
     */
    List<User>  getAllUser();

    /**
     * 通过用户名获取对应用户
     * @param UserName 用户编号
     */
    User getUserByUserName(String UserName);
    /**
     * 通过用户Id获取对应用户
     * @param userId 用户编号
     */
    User getUserById(int userId);

    /**
     * 添加用户
     * @param user 用户编号
     */
    int addUser(User user);

    /**
     * 查询用户总数
     * @return 用户总数
     */
    int getUsersCount();


    /**
     * 修改用户
     * @param user 用户编号
     */
    int modifyUser(User user);

    /**
     * 通过用户Id删除用户
     * @param userId 用户编号
     */
    int deleteUser(int userId) throws SQLException;

    /**
     * 分页查询用户
     * @param page 页号
     * @param rows 每页记录数
     * @return 返回用户集合
     */
    List<User> getUserByPage(int page, int rows);


    int modifyUserRole(int userId, int roleId);

}
