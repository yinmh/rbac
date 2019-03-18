package com.westos.rbac.dao;

import com.westos.rbac.domain.Module;

import java.util.List;

/**
 * @author yihang
 */
public interface ModuleDao {

    /**
     * 查询所有模块
     * @return 模块集合
     */
    List<Module> findAll();

    /**
     * 查询某一角色的模块
     * @param roleId 角色编号
     * @return 模块集合
     */
    List<Module> findByRoleId(int roleId);
    /**
     * 通过父id查询
     * @param pId 角色编号
     * @return 子模块集合
     */
    List<Module> findByPId(int pId);

    /**
     * 通过用户Id删除该用户下的权限
     * @param roleId 用户角色编号
     */

    int deleteRoleByRoleId(int roleId);

    /**
     * 插入该用户下的角色
     * @param roleId 用户角色编号
     * @param moduleId 权限编号
     */

    int insertRoleByRoleId(int roleId,int moduleId);
}
