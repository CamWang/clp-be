package com.starlink.clp.security;

import com.starlink.clp.entity.ImageCode;
import com.starlink.clp.exception.ValidateCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @author CamWang
 * @since 2020/9/24 10:39
 */
@Component
public class ValidateCodeFilter  extends OncePerRequestFilter{

    @Autowired  // 定义的错误处理
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 如果请求路径是/login,并且是get请求的时候
        if ("/login".equalsIgnoreCase(httpServletRequest.getRequestURI())
                && "get".equalsIgnoreCase(httpServletRequest.getMethod())) {
            try {
                validateCode(httpServletRequest);
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }
        if ("/user".equalsIgnoreCase(httpServletRequest.getRequestURI())
                && "post".equalsIgnoreCase(httpServletRequest.getMethod())) {
            System.out.println("认证中");
            try {
                validateCode(httpServletRequest);
                httpServletRequest.removeAttribute("captcha");
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void validateCode(HttpServletRequest request) throws ServletRequestBindingException {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        ImageCode codeInSession = (ImageCode) session.getAttribute("code");
        String codeInRequest = request.getParameter("captcha");
        System.out.println(codeInSession.getCode());
        System.out.println(codeInRequest);

        if (codeInRequest.isBlank()) {
            throw new ValidateCodeException("验证码不能为空！");
        }
        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在！");
        }
        if (codeInSession.isExpire()) {
            session.removeAttribute("code");
            throw new ValidateCodeException("验证码已过期！");
        }
        if (!codeInSession.getCode().equalsIgnoreCase(codeInRequest)) {
            throw new ValidateCodeException("验证码不正确！");
        }
        session.removeAttribute("code");
    }
}
