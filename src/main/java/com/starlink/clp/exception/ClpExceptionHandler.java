package com.starlink.clp.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author CamWang
 * @since 2020/7/28 20:15
 */

@ControllerAdvice
public class ClpExceptionHandler {

    @ExceptionHandler(ClpException.class)
    public ResponseEntity<ExceptionResult> handleException(ClpException clpException) {
        return ResponseEntity.status(clpException.getExceptionEnum().getCode())
                .body(new ExceptionResult(clpException.getExceptionEnum()));
    }

}
