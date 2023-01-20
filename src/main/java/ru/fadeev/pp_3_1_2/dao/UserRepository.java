package ru.fadeev.pp_3_1_2.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.fadeev.pp_3_1_2.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u JOIN FETCH u.roles where u.username = (:username)")
    User findByUsername(String username);
}
