package com.starlink.clp.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starlink.clp.constant.ExceptionEnum;
import com.starlink.clp.exception.ExceptionResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author CamWang
 * @since 2020/9/9 14:37
 */
@Component
public class ClpAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper mapper;

    public ClpAuthenticationEntryPoint(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(466);
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(mapper.writeValueAsString(
                new ExceptionResult(ExceptionEnum.LOGIN_ERROR.getCode(), ExceptionEnum.LOGIN_ERROR.getMessage())
        ));
        System.out.println(e.getMessage());
    }
}
