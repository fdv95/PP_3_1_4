package ru.fadeev.pp_3_1_2.util;

import org.springframework.stereotype.Component;
import ru.fadeev.pp_3_1_2.model.Role;
import ru.fadeev.pp_3_1_2.model.User;
import ru.fadeev.pp_3_1_2.service.RoleService;
import ru.fadeev.pp_3_1_2.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class Init {
    private final UserService userService;
    private final RoleService roleService;

    public Init(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void initializeDB() {
        roleService.addRole(new Role("ROLE_ADMIN"));
        roleService.addRole(new Role("ROLE_USER"));
        userService.addUser(new User("name1", "lastname1", 20, "admin",
                "admin", Set.of(new Role(1L, "ROLE_ADMIN"))));
        userService.addUser(new User("name2", "lastname2", 20, "user1",
                "user1", Set.of(new Role(2L, "ROLE_USER"))));
    }
}
