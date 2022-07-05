package com.epam.spring.homework3.service.model;

import com.epam.spring.homework3.service.model.enums.ErrorType;
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
