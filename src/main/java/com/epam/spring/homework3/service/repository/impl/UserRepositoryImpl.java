package com.epam.spring.homework3.service.repository.impl;

import com.epam.spring.homework3.service.exception.EntityNotFoundException;
import com.epam.spring.homework3.service.model.User;
import com.epam.spring.homework3.service.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserRepositoryImpl implements UserRepository {

    private final List<User> list = new ArrayList<>();
    private static long ID_COUNT = 0;

    @Override
    public User getUser(long id) {
        log.info("User get for id {}", id);
        return list.stream().filter(u -> u.getId().equals(id))
            .findFirst()
            .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<User> listUsers() {
        log.info("Get all users");
        return new ArrayList<>(list);
    }

    @Override
    public User createUser(User user) {
        log.info("Create user {}", user);
        user.setId(++ID_COUNT);
        list.add(user);
        return user;
    }

    @Override
    public User updateUser(long id, User user) {
        log.info("Update user for id {}", id);
        boolean isDeleted = list.removeIf(u -> u.getId().equals(id));
        if (isDeleted) {
            list.add(user);
        } else {
            throw new EntityNotFoundException();
        }
        return user;
    }

    @Override
    public void deleteUser(long id) {
        log.info("Delete user for id {}", id);
        list.removeIf(u -> u.getId().equals(id));
    }
}
