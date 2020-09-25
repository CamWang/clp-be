package com.starlink.clp.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author CamWang
 * @since 2020/9/25 10:26
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String message) {
        super(message);
    }
}