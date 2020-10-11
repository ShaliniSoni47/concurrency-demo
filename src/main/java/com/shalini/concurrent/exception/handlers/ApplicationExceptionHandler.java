package com.shalini.concurrent.exception.handlers;

import javax.servlet.ServletException;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import com.shalini.concurrent.enums.ApplicationErrors;
import com.shalini.concurrent.exception.custom.ApplicationException;
import com.shalini.concurrent.response.ResponseObject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ResponseObject> handleException(ApplicationException applicationException) {
        log.error("OOPS! We encountered an error : ",applicationException);
        return getResponseEntity(applicationException.getApplicationError());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, ServletException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ResponseObject> handleException(Exception requestException) {
        log.error("OOPS! Something wrong with request : ",requestException);
        return getResponseEntity(ApplicationErrors.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ResponseObject> handleException(Throwable throwable) {
        log.error("OOPS! We encountered an error : ",throwable);
        return getResponseEntity(ApplicationErrors.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ResponseObject> getResponseEntity(ApplicationErrors applicationError) {
        ResponseObject<?> responseObject = new ResponseObject(applicationError.getErrorCode(),applicationError.getErrorDescription());
        return new ResponseEntity<>(responseObject,applicationError.getHttpStatus());
    }
}
