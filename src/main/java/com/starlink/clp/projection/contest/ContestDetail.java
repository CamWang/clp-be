package com.starlink.clp.projection.contest;

import com.starlink.clp.constant.ContestEnum;

import java.util.Date;

public interface ContestDetail {
    Integer getId();
    String getTitle();
    String getDescription();
    ContestEnum getType();
    Integer getPenalty();
    Date getStart();
    Date getLatch();
    Date getEnd();
    Date getFinish();

    Problem getProblems();
    interface Problem {
        Integer getId();
        String getTitle();
        Integer getType();
        Integer getDifficulty();
    }
}
