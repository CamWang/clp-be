package com.starlink.clp.config;

import com.starlink.clp.security.*;
import com.starlink.clp.util.NoPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author CamWang
 * @since 2020/8/14 11:03
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ClpAuthenticationSuccessHandler clpAuthenticationSuccessHandler;
    private final ClpAuthenticationFailureHandler clpAuthenticationFailureHandler;
    private final ClpAccessDeniedHandler clpAccessDeniedHandler;
    private final ClpAuthenticationEntryPoint clpAuthenticationEntryPoint;
    private final ValidateCodeFilter validateCodeFilter;

    public SecurityConfig(
            ClpAuthenticationFailureHandler clpAuthenticationFailureHandler,
            ClpAuthenticationSuccessHandler clpAuthenticationSuccessHandler,
            ClpAccessDeniedHandler clpAccessDeniedHandler,
            ClpAuthenticationEntryPoint clpAuthenticationEntryPoint,
            ValidateCodeFilter validateCodeFilter
    ) {
        this.clpAuthenticationFailureHandler = clpAuthenticationFailureHandler;
        this.clpAuthenticationSuccessHandler = clpAuthenticationSuccessHandler;
        this.clpAccessDeniedHandler = clpAccessDeniedHandler;
        this.clpAuthenticationEntryPoint = clpAuthenticationEntryPoint;
        this.validateCodeFilter = validateCodeFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new NoPasswordEncoder();
    }

    // 预请求放行
    @Override
    public void configure(final WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers(HttpMethod.OPTIONS);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /**
                 *   // 打开该注释放开全部访问权限
                 *
                 *
                 */
                .authorizeRequests()
                    .anyRequest()
                    .permitAll()
//                    .authenticated()
                    .and()
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                    .loginProcessingUrl("/login")
                    .successHandler(clpAuthenticationSuccessHandler)
                    .failureHandler(clpAuthenticationFailureHandler)
                    .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID")
                    .and()
//                .authorizeRequests()
//                    .antMatchers(
//                            "/user",
//                            "/login",
//                            "/",
//                            "/h2/**",
//                            "/logout")
//                    .permitAll()
////                    .antMatchers(HttpMethod.POST, "/user").permitAll()
////                    .antMatchers(HttpMethod.POST,"/user/avatar").hasRole("USER")
////                    .antMatchers(HttpMethod.GET, "/user/detail").hasRole("USER")
////                    .antMatchers(HttpMethod.PUT, "/user").hasRole("USER")
//                    .anyRequest()
//                    .authenticated()
//                    .and()
                .exceptionHandling()
                    .accessDeniedHandler(clpAccessDeniedHandler)
                    .authenticationEntryPoint(clpAuthenticationEntryPoint);

        http.csrf().disable();
        http.headers().frameOptions().disable();    // 访问H2数据库必须
        http.cors();    // 启动跨域配置
    }
}
