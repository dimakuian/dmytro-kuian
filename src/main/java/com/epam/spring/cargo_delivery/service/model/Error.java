package com.epam.spring.cargo_delivery.service.model;

import com.epam.spring.cargo_delivery.service.model.enums.ErrorType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error {

  private String message;
  private ErrorType errorType;
  private LocalDateTime timestamp;
}
