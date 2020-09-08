package com.starlink.clp.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starlink.clp.constant.ExceptionEnum;
import com.starlink.clp.exception.ClpException;
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

    private ObjectMapper mapper;

    public ClpAuthenticationFailureHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setStatus(467);
        response.setContentType("application/json;charset=UTF-8");

        if (e instanceof BadCredentialsException) {
            response.getWriter().write(mapper.writeValueAsString(new ClpException(ExceptionEnum.WRONG_PASSWORD)));
        } else if (e instanceof UsernameNotFoundException) {
            response.getWriter().write(mapper.writeValueAsString(new ClpException(ExceptionEnum.USER_NOT_EXIST)));
        } else if (e instanceof LockedException) {
            response.getWriter().write(mapper.writeValueAsString(new ClpException(ExceptionEnum.ACCOUNT_LOCKED)));
        } else if (e instanceof CredentialsExpiredException) {
            response.getWriter().write(mapper.writeValueAsString(new ClpException(ExceptionEnum.CREDENTIALS_EXPIRED)));
        } else if (e instanceof AccountExpiredException) {
            response.getWriter().write(mapper.writeValueAsString(new ClpException(ExceptionEnum.ACCOUNT_EXPIRED)));
        } else if (e instanceof DisabledException) {
            response.getWriter().write(mapper.writeValueAsString(new ClpException(ExceptionEnum.ACCOUNT_DISABLED)));
        } else {
            response.getWriter().write(mapper.writeValueAsString(new ClpException(ExceptionEnum.LOGIN_ERROR)));
        }
    }
}
