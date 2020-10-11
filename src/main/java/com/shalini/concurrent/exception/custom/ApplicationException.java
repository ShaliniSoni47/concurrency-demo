package com.shalini.concurrent.exception.custom;

import com.shalini.concurrent.enums.ApplicationErrors;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException{

    private final ApplicationErrors applicationError;

    public ApplicationException(ApplicationErrors applicationError) {
        this.applicationError = applicationError;
    }

    public ApplicationException(ApplicationErrors applicationError, Throwable throwable) {
        super(throwable);
        this.applicationError = applicationError;
    }
}