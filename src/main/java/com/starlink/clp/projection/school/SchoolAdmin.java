package com.starlink.clp.projection.school;

/**
 * @author Qilin
 * @since 2020/8/16 1:47
 */
public interface SchoolAdmin {
    Integer getId();
    String getName();
    String getDescription();
    String getAvatar();

    SchoolSimple.User getUser();
    interface User{
        Integer getId();
        String getUsername();
        String getAvatar();
    }

    SchoolSimple.Clazzes getClazzes();
    interface Clazzes{
        Integer getId();
        String getName();
    }

    SchoolSimple.Teams getTeams();
    interface Teams {
        Integer getId();
        String getName();
    }

}
