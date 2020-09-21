package com.starlink.clp.projection.problem;

import com.starlink.clp.entity.Tag;
import com.starlink.clp.projection.tag.TagInfo;

import java.util.List;

public interface ProblemSimple {
    Integer getId();
    String getTitle();
    Integer getType();
    Integer getDifficulty();
    List<TagInfo> getTags();

}
