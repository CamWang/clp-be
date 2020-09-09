package com.starlink.clp.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starlink.clp.constant.ExceptionEnum;
import com.starlink.clp.exception.ClpException;
import com.starlink.clp.exception.ExceptionResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author CamWang
 * @since 2020/9/8 10:34
 */
@Component
public class ClpAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper mapper;

    public ClpAccessDeniedHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setStatus(520);
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(mapper.writeValueAsString(
                new ExceptionResult(ExceptionEnum.UNAUTHORIZED.getCode(), ExceptionEnum.UNAUTHORIZED.getMessage())
        ));
    }
}
