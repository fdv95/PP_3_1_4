package ru.fadeev.pp_3_1_2.util;

import org.springframework.stereotype.Component;
import ru.fadeev.pp_3_1_2.model.Role;
import ru.fadeev.pp_3_1_2.model.User;
import ru.fadeev.pp_3_1_2.service.RoleService;
import ru.fadeev.pp_3_1_2.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
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
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");

        Set<Role> roleAdminSet = new HashSet<>();
        Set<Role> roleUserSet = new HashSet<>();

        roleAdminSet.add(roleAdmin);
        roleUserSet.add(roleUser);

        User admin = new User("admin", "admin", 22,
                "admin@mail.ru", "admin", roleAdminSet);

        User user = new User("user", "user", 22,
                "user@mail.ru", "user", roleUserSet);

        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);
        userService.addUser(admin);
        userService.addUser(user);
    }
}
