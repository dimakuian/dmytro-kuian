package com.epam.spring.cargo_delivery.service.repository;

import com.epam.spring.cargo_delivery.service.model.User;
import java.util.List;

public interface UserRepository {

  User getUser(long id);

  List<User> listUsers();

  User createUser(User user);

  User updateUser(long id, User user);

  void deleteUser(long id);
}
