package com.starlink.clp.security;

import com.starlink.clp.constant.ExceptionEnum;
import com.starlink.clp.exception.ClpException;
import com.starlink.clp.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author CamWang
 * @since 2020/9/8 9:11
 *
 * Spring Security需要的获取用户名的服务
 */
@Component
public class ClpUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public ClpUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || username.isBlank()) {
            throw new ClpException(ExceptionEnum.IDENTIFIER_PARAM_MISSING);
        }
        if (userRepository.existsUserByUsername(username)) {
            return userRepository.findByUsername(username);
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}
