package com.starlink.clp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DifficultyEnum {
    NOOB(0, "noob"),
    BEGINNER(1, "beginner"),
    EASY(2, "easy"),
    NORMAL(3, "normal"),
    HARD(4, "hard"),
    EXPERT(5, "expert"),
    HELL(6, "hell");

    private final Integer level;
    private final String nick;
}