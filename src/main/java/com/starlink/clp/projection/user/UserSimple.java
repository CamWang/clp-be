package com.starlink.clp.projection.user;

import com.starlink.clp.entity.Clazz;
import com.starlink.clp.entity.School;
import com.starlink.clp.entity.Team;

import java.util.Date;

/**
 * @author CamWang
 * @since 2020/8/13 8:53
 */
public interface UserSimple {
    Integer getId();
    String getUsername();
    String getNickname();
    String getAvatar();

    Team getTeam();
    interface Team {
        Integer getId();
        String getName();
    }

    School getSchool();
    interface School {
        Integer getId();
        String getName();
    }

    Clazz getClazz();
    interface Clazz {
        Integer getId();
        String getName();
    }
}
