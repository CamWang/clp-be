package com.starlink.clp.projection.school;

/**
 * @author Qilin
 * @since 2020/8/16 1:37
 */
public interface SchoolSimple {
    Integer getId();
    String getName();
    String getDescription();
    String getAvatar();

    User getUser();
    interface User{
        Integer getId();
        String getUsername();
        String getAvatar();
    }

    Clazzes getClazzes();
    interface Clazzes{
        Integer getId();
        String getName();
    }

    Teams getTeams();
    interface Teams {
        Integer getId();
        String getName();
    }

}
