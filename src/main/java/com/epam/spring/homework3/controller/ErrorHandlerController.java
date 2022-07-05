package com.epam.spring.homework3.controller;

import com.epam.spring.homework3.service.exception.ServiceException;
import com.epam.spring.homework3.service.model.Error;
import com.epam.spring.homework3.service.model.enums.ErrorType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

@Slf4j
@RestControllerAdvice
public class ErrorHandlerController {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public List<Error> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    log.error("handleMethodArgumentNotValidException : message: {} ", ex.getMessage(), ex);
    return ex.getBindingResult().getAllErrors().stream()
        .map(err -> new Error(err.getDefaultMessage(), ErrorType.VALIDATION_ERROR_TYPE,
            LocalDateTime.now()))
        .collect(Collectors.toList());
  }


  @ExceptionHandler(ServiceException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Error handleServiceException(ServiceException ex, HandlerMethod hm) {
    log.error("handleServiceException : message: {} , method : {} ", ex.getMessage(),
        hm.getMethod().getName(), ex);
    return new Error(ex.getMessage(), ex.getErrorType(), LocalDateTime.now());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Error handleException(Exception ex, HandlerMethod hm) {
    log.error("handleServiceException : message: {} , method : {} ", ex.getMessage(),
        hm.getMethod().getName(), ex);
    return new Error(ex.getMessage(), ErrorType.FATAL_ERROR_TYPE, LocalDateTime.now());
  }
}
