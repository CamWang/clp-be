package com.starlink.clp.exception;

import com.starlink.clp.constant.Exception;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author CamWang
 * @since 2020/7/28 20:15
 */

@ControllerAdvice
public class ClpExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResult> handleException(Exception exception) {
        return ResponseEntity.status(exception.getExceptionEnum().getCode())
                .body(new ExceptionResult(exception.getExceptionEnum()));
    }
}
