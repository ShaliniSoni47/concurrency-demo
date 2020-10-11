package com.shalini.concurrent.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ApplicationErrors {

	PROCCESSING_ERROR("App-00","Unable to process end request",HttpStatus.UNPROCESSABLE_ENTITY),
    BAD_REQUEST("APP-01","Something wrong with the request",HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("APP-02", "We are sorry for not being able to process your request.", HttpStatus.INTERNAL_SERVER_ERROR),
    DUMMY_ERROR("APP-03", "For testing", HttpStatus.BAD_REQUEST);

    private final String errorCode;
    private final String errorDescription;
    private final HttpStatus httpStatus;
}