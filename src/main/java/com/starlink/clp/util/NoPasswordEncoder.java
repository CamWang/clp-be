package com.starlink.clp.util;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author CamWang
 * @since 2020/9/8 9:31
 */
public class NoPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(encodedPassword);
    }

    public static PasswordEncoder getInstance() {
        return INSTANCE;
    }

    private static final PasswordEncoder INSTANCE = new NoPasswordEncoder();

    public NoPasswordEncoder() {

    }
}
