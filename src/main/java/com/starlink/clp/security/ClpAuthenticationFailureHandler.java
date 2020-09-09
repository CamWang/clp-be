package com.starlink.clp.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starlink.clp.constant.ExceptionEnum;
import com.starlink.clp.exception.ClpException;
import com.starlink.clp.exception.ExceptionResult;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author CamWang
 * @since 2020/9/8 10:23
 */
@Component
public class ClpAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper mapper;

    public ClpAuthenticationFailureHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ExceptionEnum exceptionEnum;

        if (e instanceof UsernameNotFoundException) {
            exceptionEnum = ExceptionEnum.USER_NOT_EXIST;
        } else if (e instanceof BadCredentialsException) {
            exceptionEnum = ExceptionEnum.WRONG_PASSWORD;
        } else if (e instanceof LockedException) {
            exceptionEnum = ExceptionEnum.ACCOUNT_LOCKED;
        } else if (e instanceof CredentialsExpiredException) {
            exceptionEnum = ExceptionEnum.CREDENTIALS_EXPIRED;
        } else if (e instanceof AccountExpiredException) {
            exceptionEnum = ExceptionEnum.ACCOUNT_EXPIRED;
        } else if (e instanceof DisabledException) {
            exceptionEnum = ExceptionEnum.ACCOUNT_DISABLED;
        } else {
            exceptionEnum = ExceptionEnum.LOGIN_ERROR;
        }
        response.getWriter().write(mapper.writeValueAsString(
                new ExceptionResult(exceptionEnum.getCode(), exceptionEnum.getMessage())
        ));
        response.setStatus(exceptionEnum.getCode());
    }
}
