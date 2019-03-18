package com.westos.rbac.service.Impl;

import com.westos.rbac.dao.ModuleDao;
import com.westos.rbac.dao.impl.ModuleDaoImpl;
import com.westos.rbac.domain.Module;
import com.westos.rbac.service.ModuleService;

import java.util.List;

/**
 * Author :ymh
 */
public class ModuleServiceImpl implements ModuleService {
    private ModuleDao moduleDao = new ModuleDaoImpl();
    @Override
    public List<Module> getAllModule() {
        List<Module> modules = moduleDao.findAll();
        for (Module module : modules) {
            List<Module> children = moduleDao.findByPId(module.getId());
            module.setChildren(children);
        }
        return modules;
    }
}
