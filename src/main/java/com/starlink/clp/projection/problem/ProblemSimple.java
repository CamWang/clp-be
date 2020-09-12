package com.starlink.clp.projection.problem;

import com.starlink.clp.entity.Tag;

public interface ProblemSimple {
    Integer getId();
    String getTitle();
    Integer getType();
    Integer getDifficulty();
    Tag getTags();
    interface Tag {
        Integer getId();
        String getContent();
        String getColor();
        String getIcon();
    }

}
