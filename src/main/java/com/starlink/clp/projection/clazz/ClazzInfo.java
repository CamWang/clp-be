package com.starlink.clp.projection.clazz;

import com.starlink.clp.entity.User;

import java.util.Collection;
import java.util.Date;

/**
 * @author Wang Chengci
 * @since 2020/8/15 18:31
 */

//给老师展示的信息
public interface ClazzInfo {
    Integer getId();
    String getName();
    String getDescription();


    ClazzSimple.School getSchool();
    interface School {
        Integer getId();
        String getName();
    }

    Collection<User> getUser();
    interface User{
        Integer getId();
        String getUsername();
        String getNickname();
        String getEmail();
        String getStudentId();
        String getPhone();
        String getAvatar();
        Boolean getEnabled();
        Boolean getLocked();
        Boolean getSilenced();
        Date getRegister();
    }


}
