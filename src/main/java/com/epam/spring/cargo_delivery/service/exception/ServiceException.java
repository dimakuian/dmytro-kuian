package com.epam.spring.cargo_delivery.service.exception;

import com.epam.spring.cargo_delivery.service.model.enums.ErrorType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceException extends RuntimeException {

  private ErrorType errorType;

  public ServiceException(String message) {
    super(message);
  }

  public ErrorType getErrorType() {
    return ErrorType.FATAL_ERROR_TYPE;
  }
}
