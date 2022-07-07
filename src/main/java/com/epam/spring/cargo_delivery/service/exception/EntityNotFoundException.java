package com.epam.spring.cargo_delivery.service.exception;

import com.epam.spring.cargo_delivery.service.model.enums.ErrorType;

public class EntityNotFoundException extends ServiceException {

  public static final String DEFAULT_MESSAGE = "Entity not found";

  public EntityNotFoundException() {
    super(DEFAULT_MESSAGE);
  }

  @Override
  public ErrorType getErrorType() {
    return ErrorType.VALIDATION_ERROR_TYPE;
  }
}
