package dao;

import com.westos.rbac.dao.RoleDao;
import com.westos.rbac.dao.impl.RoleDaoImpl;
import com.westos.rbac.domain.Role;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class testRoleDao {
    RoleDao roleDao = new RoleDaoImpl();
    @Test
    @Ignore
    public void testFindByUserId(){
        List<Role> roles = roleDao.findByUserId(14);
        for (Role role : roles) {
            System.out.println(role);
        }
    }

    @Test
    public void testFindAll(){
        List<Role> roles = roleDao.findAll();
        for (Role role : roles) {
            System.out.println(role);
        }
    }
}
