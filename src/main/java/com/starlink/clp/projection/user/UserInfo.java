package com.starlink.clp.projection.user;

import java.util.Date;

public interface UserInfo {
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

    UserSimple.Team getTeam();
    interface Team {
        Integer getId();
        String getName();
    }

    UserSimple.School getSchool();
    interface School {
        Integer getId();
        String getName();
    }

    UserSimple.Clazz getClazz();
    interface Clazz {
        Integer getId();
        String getName();
        String getStudentId();

    }
}
