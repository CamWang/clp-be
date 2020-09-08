package com.starlink.clp.config;

import com.starlink.clp.security.ClpAuthenticationFailureHandler;
import com.starlink.clp.security.ClpAuthenticationSuccessHandler;
import com.starlink.clp.security.LoginAccessDeniedHandler;
import com.starlink.clp.util.NoPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author CamWang
 * @since 2020/8/14 11:03
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ClpAuthenticationSuccessHandler clpAuthenticationSuccessHandler;
    private final ClpAuthenticationFailureHandler clpAuthenticationFailureHandler;
    private final LoginAccessDeniedHandler loginAccessDeniedHandler;

    public SecurityConfig(
            ClpAuthenticationFailureHandler clpAuthenticationFailureHandler,
            ClpAuthenticationSuccessHandler clpAuthenticationSuccessHandler,
            LoginAccessDeniedHandler loginAccessDeniedHandler
    ) {
        this.clpAuthenticationFailureHandler = clpAuthenticationFailureHandler;
        this.clpAuthenticationSuccessHandler = clpAuthenticationSuccessHandler;
        this.loginAccessDeniedHandler = loginAccessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new NoPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /**
                 *   // 打开该注释放开全部访问权限
                 *
                 *  .authorizeRequests()
                 *                     .anyRequest()
                 *                     .permitAll()
                 *                     .and()
                 */

                .formLogin()
                    .loginProcessingUrl("/login")
                    .successHandler(clpAuthenticationSuccessHandler)
                    .failureHandler(clpAuthenticationFailureHandler)
                    .and()
                .logout()
                    .logoutUrl("/logout")
                    .invalidateHttpSession(true)
                    .and()
                .authorizeRequests()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/").permitAll()
                    .antMatchers("/h2/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .exceptionHandling()
                    .accessDeniedHandler(loginAccessDeniedHandler);

        http.csrf().disable();
        http.headers().frameOptions().disable();    // 访问H2数据库必须
    }
}
