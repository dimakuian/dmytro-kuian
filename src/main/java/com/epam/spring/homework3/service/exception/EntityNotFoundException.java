package com.epam.spring.homework3.service.exception;

import com.epam.spring.homework3.service.model.enums.ErrorType;

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
