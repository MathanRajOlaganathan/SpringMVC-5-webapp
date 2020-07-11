package com.test.hplus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

@ControllerAdvice
public class ApplicationExceptionHandler {


    @ExceptionHandler({ApplicationException.class, AsyncRequestTimeoutException.class})
    public String handleException()
    {
        System.out.println("In Global exception handler");
        return "error";
    }

    @ExceptionHandler(LoginFailiureException.class)
    public ResponseEntity handleLoginFailure(LoginFailiureException le)
    {
        System.out.println("In handleLoginFailure Method");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(le.getMessage());
    }


}
