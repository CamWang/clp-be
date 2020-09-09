package com.starlink.clp.service;

import com.starlink.clp.constant.ExceptionEnum;
import com.starlink.clp.entity.User;
import com.starlink.clp.exception.ClpException;
import com.starlink.clp.projection.user.UserInfo;
import com.starlink.clp.projection.user.UserSimple;
import com.starlink.clp.repository.UserRepository;
import com.starlink.clp.util.IgnoreNullPropertiesUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

/**
 * 用户Service层
 *
 * @author CamWang
 * @since 2020/8/13 9:01
 *
 * CHECKLIST
 * 1. password encoder需要被添加
 * 2. password matcher需要被添加
 *
 */

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public Page<UserSimple> getAllUserSimple(Pageable pageable) {
        return userRepository.getAllBy(pageable);
    }

    public Optional<User> getUserById(Integer id) { return userRepository.findById(id); }

    public UserInfo getUserInfoByUsername(String username) {
        return userRepository.getFirstByUsername(username);
    }

    public Boolean testIfUsernamePresent(String username) {
        return userRepository.existsUserByUsername(username);
    }

    public void registerUser(User user) {
        user.setRegister(new Date());
        user.setSilenced(false);
        user.setLocked(false);
        user.setEnabled(true);
        user.setRole("ROLE_USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void modifyUser(User user, String oldPassword) {
        User oldUser = userRepository.findUserByUsernameAndId(user.getUsername(), user.getId());
        if (oldUser == null) {
            throw new ClpException(ExceptionEnum.USER_NOT_EXIST);
        }
        BeanUtils.copyProperties(user, oldUser, IgnoreNullPropertiesUtil.getNullPropertyNames(user));
        userRepository.save(oldUser);
    }

    @Transactional
    public void setAvatar(String username, String avatar) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ClpException(ExceptionEnum.USER_NOT_EXIST);
        }
//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            throw new ClpException(ExceptionEnum.WRONG_PASSWORD);
//        }
        user.setAvatar(avatar);
        userRepository.save(user);
    }


}
