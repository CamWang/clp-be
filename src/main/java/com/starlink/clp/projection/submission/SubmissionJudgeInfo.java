package com.starlink.clp.projection.submission;

public interface SubmissionJudgeInfo {
    Integer getLanguage();
    User getUser();
    interface User {
        Integer getId();
    }

    Problem getProblem();
    interface Problem {
        Integer getId();
    }

    Contest getContest();
    interface Contest {
        Integer getId();
    }
}
