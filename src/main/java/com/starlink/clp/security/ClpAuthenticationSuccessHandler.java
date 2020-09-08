package com.starlink.clp.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starlink.clp.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author CamWang
 * @since 2020/9/8 10:12
 */
@Component
public class ClpAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private ObjectMapper mapper;

    public ClpAuthenticationSuccessHandler(ObjectMapper objectMapper) {
        this.mapper = objectMapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setStatus(200);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(authentication.getPrincipal(), userDTO);
        httpServletResponse.getWriter().write(mapper.writeValueAsString(userDTO));
    }
}
