package dao;

import com.westos.rbac.dao.ModuleDao;
import com.westos.rbac.dao.impl.ModuleDaoImpl;
import com.westos.rbac.domain.Module;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class testModulDao {
    ModuleDao moduleDao = new ModuleDaoImpl();

    @Test
    @Ignore
    public void testFindAll(){
        List<Module> modules = moduleDao.findAll();
        for (Module module : modules) {
            System.out.println(module);
        }
    }

    @Test
    public void testFindByRoleId(){
        List<Module> modules = moduleDao.findByRoleId(1);
        System.out.println(modules.size());
    }

    @Test
    public void testFindByPId(){
        List<Module> modules = moduleDao.findByPId(1);
        for (Module module : modules) {
            System.out.println(module);
        }
    }
}