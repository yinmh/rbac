package dao;

import com.westos.rbac.dao.UserDao;
import com.westos.rbac.dao.impl.UserDaoImpl;
import com.westos.rbac.domain.User;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * Author :ymh
 */
public class testUserDao {
    UserDao userDao = new UserDaoImpl();

    @Test
    @Ignore
    public void testFindUserByName(){
        User user = userDao.findByUsername("admin");
        System.out.println(user);
    }

    @Test
    @Ignore
    public void testFindUserById(){
        User user = userDao.findById(2);
        System.out.println(user);
    }

    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        System.out.println(users.size());
    }

    @Test
    @Ignore
    public void testFindCount(){
        int count = userDao.findCount();
        System.out.println(count);
    }


    @Test
    @Ignore
    public void testInsert(){
        User user = new User();
        user.setUsername("张三");
        user.setPassword("123");
        user.setOrgId(0);
        Integer[]  orgids = {1,2,3,4};
        user.setOrgIds(orgids);
        int rows = userDao.insert(user);
        System.out.println(rows);
    }
    @Test
    @Ignore
    public void testUpdate(){
        User user = new User();
        user.setId(21);
        user.setUsername("曹晨");
        user.setPassword("123");
        user.setOrgId(0);
        Integer[]  orgids = {11,22,3,4};
        user.setOrgIds(orgids);
        int rows = userDao.update(user);
        System.out.println(rows);
    }
    @Test
    @Ignore
    public void testDeleteRoleByUserId(){
        int rows = userDao.deleteRoleByUserId(15);
        System.out.println(rows);
    }

    @Test
    public void testFindByPage(){
        List<User> users = userDao.findByPage(1, 5);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testInsetUserRole(){
        int rows = userDao.insertRoleByUserId(4, 2);
        System.out.println(rows);
    }
}
