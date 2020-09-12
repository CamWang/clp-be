package com.starlink.clp.projection.contest;

import com.starlink.clp.constant.ContestEnum;

import java.util.Date;

public interface ContestSimple {
    Integer getId();
    String getTitle();
    String getDescription();
    ContestEnum getType();
    Date getStart();
    Date getEnd();
}
