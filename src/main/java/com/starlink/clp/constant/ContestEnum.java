package com.starlink.clp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContestEnum {
    PUBLIC,
    CERTAIN_SCHOOL_ONLY,
    CERTAIN_TEAM_ONLY,
    CERTAIN_USER_ONLY,
    PRIVATE
}
