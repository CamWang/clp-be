package com.starlink.clp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClpEnum {
    TEAM_MAX_MEMBERS(3);

    private Integer value;
}
