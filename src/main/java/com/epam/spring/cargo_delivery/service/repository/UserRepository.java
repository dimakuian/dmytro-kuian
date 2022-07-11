package com.epam.spring.cargo_delivery.service.repository;

import com.epam.spring.cargo_delivery.service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByEmail(String email);

}
