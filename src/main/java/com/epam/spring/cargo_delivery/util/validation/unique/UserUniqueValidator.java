package com.epam.spring.cargo_delivery.util.validation.unique;

import com.epam.spring.cargo_delivery.service.repository.UserRepository;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserUniqueValidator implements ConstraintValidator<Unique, String> {

  private UserRepository userRepository;

  @Override
  public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
    return userRepository.existsByLogin(login);
  }
}
