package ru.fadeev.pp_3_1_2.service;

import ru.fadeev.pp_3_1_2.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsersList();

    void addUser(User user);

    User getUserById(Long id);

    void updateUser(User updateUser);

    void deleteUser(Long id);

    User findByEmail(String email);
}