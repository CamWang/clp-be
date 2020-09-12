package com.starlink.clp.projection.problem;

public interface ProblemDetail {
    Integer getId();
    String getTitle();
    Integer getType();
    Integer getDifficulty();
    String getText();
    Integer getMemoryLimit();
    Integer getOutputLimit();
    Integer getTimeLimit();

    Tag getTags();
    interface Tag {
        Integer getId();
        String getContent();
        String getColor();
        String getIcon();
    }
}
