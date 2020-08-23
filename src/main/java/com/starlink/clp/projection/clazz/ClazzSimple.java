package com.starlink.clp.projection.clazz;

import com.starlink.clp.entity.School;
import com.starlink.clp.entity.User;

import java.util.Collection;
import java.util.List;

/**
 * @author Wang Chengci
 * @since 2020/8/15 18:32
 */

//给普通用户展示的信息
public interface ClazzSimple {
    Integer getId();
    String getName();
    String getDescription();

    School getSchool();
    interface School {
        Integer getId();
        String getName();
    }

}
