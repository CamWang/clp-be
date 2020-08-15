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

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<UserSimple> getAllUserSimple(Pageable pageable) {
        return userRepository.getAllBy(pageable);
    }

    public Optional<User> getUserById(Integer id) { return userRepository.findById(id); }

    public UserInfo getUserInfoById(Integer id) {
        return userRepository.getFirstById(id);
    }

    public Boolean testIfUsernamePresent(String username) {
        return userRepository.existsUserByUsername(username);
    }

    public void registerUser(User user) {
        user.setRegister(new Date());
        user.setSilenced(false);
        user.setLocked(false);
        user.setEnabled(true);
        /**
         * 使用当前Spring Security加密器对密码进行加密处理，添加Spring Security支持后从容器里取
         * string PasswordEncoder.encode(rawPass)
         */
        userRepository.save(user);
    }

    @Transactional
    public void modifyUser(User user, String oldPassword) {
        User oldUser = userRepository.findUserByUsernameAndId(user.getUsername(), user.getId());
        if (oldUser == null) {
            throw new ClpException(ExceptionEnum.USER_NOT_EXIST);
        }

        /**
         * 添加密码比较，oldPassword与数据库存储的用户密码进行比较，添加Spring Security支持后从容器里取
         * boolean PasswordEncoder.matches(pass1, pass2)
         */
        BeanUtils.copyProperties(user, oldUser, IgnoreNullPropertiesUtil.getNullPropertyNames(user));
        userRepository.save(oldUser);
    }

    @Transactional
    public void setAvatar(Integer id, String username, String password, String avatar) {
        User oldUser = userRepository.findUserByUsernameAndId(username, id);
        if (oldUser == null) {
            throw new ClpException(ExceptionEnum.USER_NOT_EXIST);
        }
        /**
         * 添加密码比较，oldPassword与数据库存储的用户密码进行比较，添加Spring Security支持后从容器里取
         * boolean PasswordEncoder.matches(pass1, pass2)
         */
        oldUser.setAvatar(avatar);
        userRepository.save(oldUser);
    }

}
