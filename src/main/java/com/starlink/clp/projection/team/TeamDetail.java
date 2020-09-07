package com.starlink.clp.projection.team;

import com.starlink.clp.entity.School;
import com.starlink.clp.entity.User;

import java.util.Date;

public interface TeamDetail {
    Integer getId();
    String getName();
    String getDescription();
    Date getRegister();

    School getSchool();
    interface School {
        Integer getId();
        String getAvatar();
        String getName();
    }

    User getUser();
    interface User {
        Integer getId();
        String getNickname();
        String getAvatar();
    }

}
