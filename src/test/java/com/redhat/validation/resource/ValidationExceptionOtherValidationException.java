package com.redhat.validation.resource;

import javax.validation.ValidationException;

public class ValidationExceptionOtherValidationException extends ValidationException {
    private static final long serialVersionUID = 1L;

    ValidationExceptionOtherValidationException() {
    }

    ValidationExceptionOtherValidationException(final Exception cause) {
        super(cause);
    }
}
