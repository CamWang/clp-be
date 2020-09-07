package com.starlink.clp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileTypeEnum {
    USER_AVATAR(1),
    TEAM_AVATAR(2),
    SCHOOL_AVATAR(3),
    REGION_AVATAR(4),
    CONTEST_IMAGE(10),
    EXECUTABLE_FILE(20),
    TEST_INPUT_FILE(50),
    TEST_OUTPUT_FILE(60),
    VIDEO_FILE(80);

    private Integer code;
}
