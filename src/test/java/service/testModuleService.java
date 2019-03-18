package service;

import com.westos.rbac.domain.Module;
import com.westos.rbac.service.Impl.ModuleServiceImpl;
import com.westos.rbac.service.Impl.UserServiceImpl;
import com.westos.rbac.service.ModuleService;
import com.westos.rbac.service.UserService;
import org.junit.Test;

import java.util.List;

/**
 * Author :ymh
 */
public class testModuleService {
    private ModuleService moduleService = new ModuleServiceImpl();
    private UserService userService = new UserServiceImpl();
    @Test
    public void  getAllModule(){
        List<Module> lists = moduleService.getAllModule();
        for (Module list : lists) {
            System.out.println(list);
        }
    }

    @Test
    public void  testCount(){
        int count = userService.getUsersCount();
        System.out.println(count);
    }
}
