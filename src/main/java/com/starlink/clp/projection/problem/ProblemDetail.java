package com.starlink.clp.projection.problem;

import com.starlink.clp.projection.tag.TagInfo;

import java.util.List;

public interface ProblemDetail {
    Integer getId();
    String getTitle();
    Integer getType();
    Integer getDifficulty();
    String getText();
    Integer getMemoryLimit();
    Integer getOutputLimit();
    Integer getTimeLimit();

    List<TagInfo> getTags();
}
