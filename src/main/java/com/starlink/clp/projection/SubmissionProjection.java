package com.starlink.clp.projection;

import java.util.Date;

public interface SubmissionProjection {
    Integer getId();
    Date getSubmit();
    String getCode();
    Date getStart();
    Date getEnd();
    String getResult();
    String getError();
    Integer getUserId();
}
