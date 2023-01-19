package ru.fadeev.pp_3_1_2.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.fadeev.pp_3_1_2.model.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
}
