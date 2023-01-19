package ru.fadeev.pp_3_1_2.service;

import ru.fadeev.pp_3_1_2.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    void addRole(Role role);
}
