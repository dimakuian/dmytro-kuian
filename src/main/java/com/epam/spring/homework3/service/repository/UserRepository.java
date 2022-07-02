package com.epam.spring.homework3.service.repository;

import com.epam.spring.homework3.service.model.User;

import java.util.List;

public interface UserRepository {
    User getUser(long id);

    List<User> listUsers();

    User createUser(User user);

    User updateUser(long id, User user);

    void deleteUser(long id);
}
