package com.starlink.clp.projection.contest;

import com.starlink.clp.constant.ContestEnum;
import com.starlink.clp.projection.problem.ProblemSimple;

import java.util.Date;
import java.util.List;

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

    List<ProblemSimple> getProblems();
}
